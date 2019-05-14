package com.fuwenping.bysj.commons.wechatlogin;

/**
 * 该类取的自己小程序的appid、secret，封装为一个接口
 *
 * @author 付文萍
 * @version 0.0.-RELEASE
 */
public interface WechatUserConstantInterface {

    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 你的appid
    public static final String WX_LOGIN_APPID = "wx1d46daac7277268b";
    // 你的密匙
    public static final String WX_LOGIN_SECRET = "529cc6a8a8d63a081e715d52349270a2";
    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";
}
