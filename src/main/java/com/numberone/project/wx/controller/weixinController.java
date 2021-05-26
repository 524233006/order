package com.numberone.project.wx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

@Controller
public class weixinController {

	@Autowired
	private WxMpService wxMpService;

	/**
	 * 1.接入公众号:公众号服务器配置的接口地址为这个
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */

	@RequestMapping(value = "/wechart/index", method = RequestMethod.GET)
	@ResponseBody
	public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
		System.out.println("————————微信接入——————————");
		if (wxMpService.checkSignature(timestamp, nonce, signature)) {
			return echostr;
		} else {
			return null;
		}
	}
	
	
	/**
	 * 消息的接收和回复
	 */
	@PostMapping(value = "/wechart/index")
	@ResponseBody
	public void sendWxMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("————————微信消息接收和发送——————————");
		// 获取消息流
		WxMpXmlMessage message = WxMpXmlMessage.fromXml(request.getInputStream());
		// 消息的处理：文本 text
		String msgType = message.getMsgType();// 消息的类型
		String fromUser = message.getFromUser();// 发送消息用户的账号
		String toUser = message.getToUser();// 开发者账号
		String content = message.getContent();// 文本内容
		String msg = message.getMsg();

		// 回复文本消息
		if ("text".equals(msgType)) {
			// 创建文本消息内容
			WxMpXmlOutTextMessage text = WxMpXmlOutTextMessage.TEXT().toUser(message.getFromUser())
					.fromUser(message.getToUser()).content("你好，很高心认识你").build();
			// 转化为xml格式
			String xml = text.toXml();
			System.out.println(xml);
			// 返回消息
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(xml);
			out.close();
		}
	}

		/**
		 * 可用
		 * @param token  
		 * @param timestamp
		 * @param nonce
		 * @return
		 */
	
	/*
	 * @RequestMapping(value= "/wechart/index",method = RequestMethod.GET)
	 * 
	 * @ResponseBody public String auth(String signature, String timestamp, String
	 * nonce, String echostr) {
	 * 
	 * String sortStr = sort("token",timestamp,nonce); String mySignature =
	 * shal(sortStr);
	 * 
	 * if(!"".equals(signature) && !"".equals(mySignature) &&
	 * signature.equals(mySignature)){ return echostr; }else { return null; }
	 * 
	 * }
	 */
	 

	
		/*
		 * public String sort(String token, String timestamp, String nonce) { String[]
		 * strArray = {token, timestamp, nonce}; Arrays.sort(strArray); StringBuilder sb
		 * = new StringBuilder(); for (String str : strArray) { sb.append(str); } return
		 * sb.toString(); }
		 */
	 

	
	/*
	 * public String shal(String str){ try { MessageDigest digest =
	 * MessageDigest.getInstance("SHA-1"); digest.update(str.getBytes()); byte
	 * messageDigest[] = digest.digest();
	 * 
	 * StringBuffer hexString = new StringBuffer(); // 字节数组转换为 十六进制 数 for (int i =
	 * 0; i < messageDigest.length; i++) { String shaHex =
	 * Integer.toHexString(messageDigest[i] & 0xFF); if (shaHex.length() < 2) {
	 * hexString.append(0); } hexString.append(shaHex); } return
	 * hexString.toString();
	 * 
	 * } catch (NoSuchAlgorithmException e) { e.printStackTrace(); } return ""; }
	 */

	
	

}