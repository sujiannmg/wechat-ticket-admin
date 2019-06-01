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
        // System.out.println("-------orderJson------" + orderJson);
        TicketOrderInfo ticketOrderInfo = new TicketOrderInfo();

        // 订单信息入库
        String cinemaName = orderJson.getString("cinemaName");
        String cinemaAddress = orderJson.getString("cinemaAddress");
        String movieName = orderJson.getString("movieName");
        String movieTime = orderJson.getString("movieTime");
        String movieLabel = orderJson.getString("movieLabel");
        String cinemaSpecificAddress = orderJson.getString("cinemaSpecificAddress");
        String movieOrderNum = orderJson.getString("movieOrderNum");
        String userPhoneNum = orderJson.getString("userPhoneNum");
        String movieSerialNum = orderJson.getString("movieSerialNum");
        String orderVerificationNum = orderJson.getString("orderVerificationNum");
        String orderSumPrice = orderJson.getString("orderSumPrice");

        ticketOrderInfo.setCinemaName(cinemaName);
        ticketOrderInfo.setCinemaAddress(cinemaAddress);
        ticketOrderInfo.setMovieName(movieName);
        ticketOrderInfo.setMovieTime(movieTime);
        ticketOrderInfo.setMovieLabel(movieLabel);
        ticketOrderInfo.setCinemaSpecificAddress(cinemaSpecificAddress);
        ticketOrderInfo.setMovieOrderNum(movieOrderNum);
        ticketOrderInfo.setUserPhoneNum(userPhoneNum);
        ticketOrderInfo.setMovieSerialNum(movieSerialNum);
        ticketOrderInfo.setOrderVerificationNum(orderVerificationNum);
        ticketOrderInfo.setOrderSumPrice(orderSumPrice);

        wechatTicketService.saveTicketOrderInfo(ticketOrderInfo);
        GlobalResult result = GlobalResult.build(200, null, null);
        return result;
      } catch (WechatTicketException e) {
        e.printStackTrace();
        return null;
      }
  }
}
