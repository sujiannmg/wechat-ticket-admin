package com.fuwenping.bysj.springmvc.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.commons.util.GlobalResult;
import com.fuwenping.bysj.commons.util.WechatUtil;
import com.fuwenping.bysj.entity.WechatUserInfo;
import com.fuwenping.bysj.service.spring.IWechatTicketService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 该类是电影接口后台信息控制器。
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
@RestController // 该类注入有别于@Controller
@RequestMapping("/wechatuserinfo")
public class WechatUserInfoWeappController extends BaseController {

  private static final Log log = LogFactory.getLog(MovieInterfaceWeappController.class);

  @javax.annotation.Resource(name = "WechatTicketService")
  private IWechatTicketService wechatTicketService;

    // 微信用户登陆
    @RequestMapping(value = { "wechatuserlogin" }, method = RequestMethod.POST)
    public GlobalResult wechatUserLogin(@RequestParam(value = "code", required = false) String code,
                                        @RequestParam(value = "rawData", required = false) String rawData,
                                        @RequestParam(value = "signature", required = false) String signature,
                                        @RequestParam(value = "encrypteData", required = false) String encrypteData,
                                        @RequestParam(value = "iv", required = false) String iv) {
      try {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        System.out.println("rawDataJson" + rawDataJson);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openId = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return GlobalResult.build(500, "签名校验失败", null);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        // User user = this.userMapper.selectById(openid);
        WechatUserInfo wechatUserInfo = wechatTicketService.getWechatUserInfoByPrimaryKey(openId);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (wechatUserInfo == null) {
          // 用户信息入库
          String nickName = rawDataJson.getString("nickName");
          String avatarUrl = rawDataJson.getString("avatarUrl");
          String gender = rawDataJson.getString("gender");
          String city = rawDataJson.getString("city");
          String country = rawDataJson.getString("country");
          String province = rawDataJson.getString("province");

          wechatUserInfo = new WechatUserInfo();
          wechatUserInfo.setNickName(nickName);
          wechatUserInfo.setGender(gender);
          wechatUserInfo.setCity(city);
          wechatUserInfo.setProvince(province);
          wechatUserInfo.setAvatarUrl(avatarUrl);
          wechatUserInfo.setUnionId(skey);
          wechatTicketService.saveWechatUserInfo(wechatUserInfo);
        } else {
          wechatTicketService.updateWechatUserInfo(null);
        }
        //encrypteData比rowData多了appid和openid
        //JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        //6. 把新的skey返回给小程序
        skey = UUID.randomUUID().toString();
        GlobalResult result = GlobalResult.build(200, null, skey);
        return result;
      } catch (WechatTicketException e) {
        e.printStackTrace();
        return null;
      }
  }
}
