package com.numberone.project.wx.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.numberone.project.wx.config.OurWxPayConfig;
import com.numberone.project.wx.config.WxpayParam;

@Controller
@RequestMapping("/mywxpay")
public class MyWxPayController { 
			
	@GetMapping("/pay")
	@ResponseBody
	     public Map<String,String> wxPayFunction() throws Exception{
	 
	         WxpayParam wxpayParam = new WxpayParam();
	        String notifyUrl = "http://csfuzheedu.com/api/pay";  //我这里的回调地址是随便写的，到时候需要换成处理业务的回调接口
	 
	         OurWxPayConfig ourWxPayConfig = new OurWxPayConfig();
	         WXPay wxPay = new WXPay(ourWxPayConfig);
	 
	         //根据微信支付api来设置
	         Map<String,String> data = new HashMap<>();
	         data.put("appid",ourWxPayConfig.getAppID());
	         data.put("mch_id",ourWxPayConfig.getMchID());         //商户号
	         data.put("trade_type","JSAPI");                         //支付场景 APP 微信app支付 JSAPI 公众号支付  NATIVE 扫码支付
	         data.put("notify_url",notifyUrl);                     //回调地址
	         data.put("spbill_create_ip","113.246.26.148");             //终端ip
	         data.put("total_fee",wxpayParam.getTotalFee());       //订单总金额
	         data.put("fee_type","CNY");                           //默认人民币
	         data.put("out_trade_no",wxpayParam.getOutTradeNo());   //交易号
	         data.put("body",wxpayParam.getBody());
	         data.put("nonce_str","bfrhncjkfdkfd");   // 随机字符串小于32位
	         data.put("openid","ovEzovsTeA_ntD5AGs2H_TsdmEB0");
	         String s = WXPayUtil.generateSignature(data, ourWxPayConfig.getKey());  //签名
	        data.put("sign",s);
	         
	         /** wxPay.unifiedOrder 这个方法中调用微信统一下单接口 */
	         Map<String, String> respData = wxPay.unifiedOrder(data); 
	         if (respData.get("return_code").equals("SUCCESS")){
	 
	             //返回给APP端的参数，APP端再调起支付接口
	             Map<String,String> repData = new HashMap<>();
	            repData.put("appid",ourWxPayConfig.getAppID());
             repData.put("mch_id",ourWxPayConfig.getMchID());
             repData.put("prepayid",respData.get("prepay_id"));
             repData.put("package","WXPay");
            repData.put("noncestr",respData.get("nonce_str"));
             repData.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
             String sign = WXPayUtil.generateSignature(repData,ourWxPayConfig.getKey()); //签名
             respData.put("sign",sign);
            respData.put("timestamp",repData.get("timestamp"));
             respData.put("package","WXPay");
             return respData;
         }
         throw new Exception(respData.get("return_msg"));
     }
	
	@GetMapping("/prepay")
	@ResponseBody
    public String prepay() throws Exception{

        WxpayParam wxpayParam = new WxpayParam();
       String notifyUrl = "http://csfuzheedu.com/api/pay";  //我这里的回调地址是随便写的，到时候需要换成处理业务的回调接口
       //HnStuOder stuOrder = new HnStuOder();
        OurWxPayConfig ourWxPayConfig = new OurWxPayConfig();
        WXPay wxPay = new WXPay(ourWxPayConfig);
        String nonce_str = System.currentTimeMillis() / 1000 + "";
        //根据微信支付api来设置
        Map<String,String> data = new HashMap<>();
        data.put("appid",ourWxPayConfig.getAppID());
        data.put("mch_id",ourWxPayConfig.getMchID());         //商户号
        data.put("trade_type","JSAPI");                         //支付场景 APP 微信app支付 JSAPI 公众号支付  NATIVE 扫码支付
        data.put("notify_url",notifyUrl);                     //回调地址
        data.put("spbill_create_ip","113.246.26.148");             //终端ip
        data.put("total_fee",new BigDecimal(365).toString());       //订单总金额
        data.put("fee_type","CNY");                           //默认人民币
        data.put("out_trade_no","12345566667");   //交易号
        data.put("body","购买");
        data.put("nonce_str",nonce_str);   // 随机字符串小于32位
        data.put("openid","ovEzovsTeA_ntD5AGs2H_TsdmEB0");
        String s = WXPayUtil.generateSignature(data, ourWxPayConfig.getKey());  //签名
       data.put("sign",s);
        
        /** wxPay.unifiedOrder 这个方法中调用微信统一下单接口 */
        Map<String, String> respData = wxPay.unifiedOrder(data); 
        if (respData.get("return_code").equals("SUCCESS")){

            //返回给APP端的参数，APP端再调起支付接口
            Map<String,String> repData  = new HashMap<>();
            repData.put("appId",ourWxPayConfig.getAppID());
            repData.put("timeStamp",String.valueOf(System.currentTimeMillis()/1000));
            repData.put("nonceStr",respData.get("nonce_str"));
            repData.put("mch_id",ourWxPayConfig.getMchID());
            repData.put("package",respData.get("prepay_id"));
          //  repData.put("prepayid",respData.get("prepay_id"));
            String sign = WXPayUtil.generateSignature(repData,ourWxPayConfig.getKey()); //签名
            //respData.put("sign",sign);
			  repData.get("timestamp");
			  
        }
        return "mobile/pay3";
	}

}
