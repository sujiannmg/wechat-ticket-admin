package com.fuwenping.bysj.springmvc.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fuwenping.bysj.commons.exception.WechatTicketException;
import com.fuwenping.bysj.commons.util.GlobalResult;
import com.fuwenping.bysj.commons.util.WechatUtil;
import com.fuwenping.bysj.entity.TicketOrderInfo;
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
@RequestMapping("/ticket")
public class TicketOrderInfoWeappController extends BaseController {

  private static final Log log = LogFactory.getLog(TicketOrderInfoWeappController.class);

  @javax.annotation.Resource(name = "WechatTicketService")
  private IWechatTicketService wechatTicketService;

    // 微信用户登陆
    @RequestMapping(value = { "order" }, method = RequestMethod.POST)
    public GlobalResult saveTicketOrderInfo(@RequestParam(value = "order", required = false) String order) {
      try {

        JSONObject orderJson = JSON.parseObject(order);
        System.out.println("-------orderJson------" + orderJson);
        // TicketOrderInfo ticketOrderInfo = wechatTicketService.get

//        JSONObject cinemaAddress1 = JSON.parseObject(cinemaAddress);
//        System.out.println("-------cinemaAddress1------" + cinemaAddress1);
//        JSONObject movieName1 = JSON.parseObject(movieName);
//        System.out.println("-------movieName1------" + movieName1);
//        JSONObject movieTime1 = JSON.parseObject(movieTime);
//        System.out.println("-------movieTime1------" + movieTime1);
//        JSONObject movieLabel1 = JSON.parseObject(movieLabel);
//        System.out.println("-------movieLabel1------" + movieLabel1);
//        JSONObject cinemaSpecificAddress1 = JSON.parseObject(cinemaSpecificAddress);
//        System.out.println("-------cinemaSpecificAddress1------" + cinemaSpecificAddress1);
//        JSONObject movieOrderNum1 = JSON.parseObject(movieOrderNum);
//        System.out.println("-------movieOrderNum1------" + movieOrderNum1);
//        JSONObject userPhoneNum1 = JSON.parseObject(userPhoneNum);
//        System.out.println("-------userPhoneNum1------" + userPhoneNum1);
//        JSONObject movieSerialNum1 = JSON.parseObject(movieSerialNum);
//        System.out.println("-------movieSerialNum1------" + movieSerialNum1);
//        JSONObject orderVerificationNum1 = JSON.parseObject(orderVerificationNum);
//        System.out.println("-------orderVerificationNum1------" + orderVerificationNum1);
//        JSONObject orderSumPrice1 = JSON.parseObject(orderSumPrice);
//        System.out.println("-------orderSumPrice1------" + orderSumPrice1);

        wechatTicketService.updateWechatUserInfo(null);
        //encrypteData比rowData多了appid和openid
        //JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        //6. 把新的skey返回给小程序
        GlobalResult result = GlobalResult.build(200, null, null);
        return null;
      } catch (WechatTicketException e) {
        e.printStackTrace();
        return null;
      }
  }
}
