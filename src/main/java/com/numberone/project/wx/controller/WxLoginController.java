package com.numberone.project.wx.controller;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.util.StringUtils;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.numberone.project.hn.grade.domain.HnGrade;
import com.numberone.project.hn.grade.service.IHnGradeService;
import com.numberone.project.hn.oder.domain.HnStuOder;
import com.numberone.project.hn.oder.service.IHnStuOderService;
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.project.hn.school.service.IHnSchoolService;
import com.numberone.project.hn.schoolProduct.domain.HnSchoolProduct;
import com.numberone.project.hn.schoolProduct.service.IHnSchoolProductService;
import com.numberone.project.hn.student.domain.HnStudent;
import com.numberone.project.hn.student.service.IHnStudentService;
import com.numberone.project.wx.account.domain.WxAccount;
import com.numberone.project.wx.account.service.IWxAccountService;
import com.numberone.project.wx.config.OurWxPayConfig;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
@Controller
@RequestMapping("/api")
public class WxLoginController {
	
	  private static final Logger log = LoggerFactory.getLogger(WxLoginController.class);
		@Value("${wx.appId}")
	    private  String appID;//这里是AppId,我放到配置文件中了,也可以在这里写,直接定义全局变量,下面的开发者密码一样
	    @Value("${wx.secret}")
	    private String appSecret;//AppSecret,开发者密码
	    @Autowired
	    private WxMpService wxMpService;
	    @Autowired
	    private IWxAccountService  wxAccountService;
	    //@Autowired
	    //WxMpMyTemplateMsgServiceImpl wxMpTemplateMsgService;
	    
		@Autowired
		private IHnSchoolService hnSchoolService;
	
	
		@Autowired
	    private IHnSchoolProductService  schoolProductService;

		@Autowired
	    private IHnStuOderService  stuOderService;
		@Autowired
		private IHnGradeService hnGradeService;
		
		@Autowired
	    private IHnStudentService  hnStudentService;
	    /**
	     * 网页微信授权登录接口  
	     * 1、引导用户进入授权页面同意授权，获取code
	     * 2、通过code换取网页授权access_token（与基础支持中的access_token不同）
	     * 3、如果需要，开发者可以刷新网页授权access_token，避免过期    snsapi_userinfo
	     * 4、通过网页授权access_token和openid获取用户基本信息（支持UnionID机制） snsapi_base
	     * @return
	     */
	    @RequestMapping(value = "/wxLogin", method = RequestMethod.GET)
	    public void wxLogin(String schoolProductId,HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	
	    	HttpSession session =request.getSession();
            
            if (session.getAttribute("token") == null) {
            	//这个url的域名必须要进行再公众号中进行注册验证，这个地址是成功后的回调地址
    	    	// String contents2 = "http://csfuzheedu.com/api/wxLogin?shoolProductId="+1;
            	String backUrl;
            	if(StringUtils.isEmpty(schoolProductId)) {
            		 backUrl="http://csfuzheedu.com/api/callBack";
            	}else {
            		 backUrl="http://csfuzheedu.com/api/callBack?schoolProductId="+schoolProductId;
            	}
    	    	// 第一步：用户同意授权，获取code
    	    	//url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appID + "&redirect_uri=http%3A%2F%2Fcsfuzheedu.com%2Fjs%2FcallBack&response_type=code"+ "&scope=snsapi_userinfo"+ "&state=STATE#wechat_redirect";
    	    	// URIUtil.encodeURIComponent(backUrl);   URLEncoder.encode(backUrl)
    	    	//System.out.println(URIUtil.encodeURIComponent(backUrl));
    	    	//System.out.println(URLEncoder.encode(backUrl));
    	    	//return "redirect:"+url;必须重定向，否则不能成功
    	    	
    	    	response.sendRedirect(wxMpService.oauth2buildAuthorizationUrl(backUrl, "snsapi_userinfo", "STATE"));
            	 
            }else { 
            	
            	if(StringUtils.isEmpty(schoolProductId)) {
            		response.sendRedirect("http://csfuzheedu.com/api/toindex");
	           	}else {
	           		response.sendRedirect("http://csfuzheedu.com/api/toindex?schoolProductId="+schoolProductId);
	           	}		    	
            }
	    	
	    	
	    }
	    
	    
	    
	   
	    /**
	     * 公众号微信登录授权回调函数
	     * @param modelMap
	     * @param req
	     * @param resp
	     * @return
	     * @throws ServletException
	     * @throws IOException
	     * @author  fhx
	     * @date 
	     * @parameter
	     
	     * @throws WxErrorException */
	    @RequestMapping(value = "/callBack", method = RequestMethod.GET)
	    public String  callBack(HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
	    	//start 获取微信用户基本信息
                String code =req.getParameter("code");
                String schoolProductId = req.getParameter("schoolProductId");
                if(StringUtils.isEmpty(code)) {
                	//重定向
                	try {
                		if(schoolProductId!=null&&!schoolProductId.isEmpty()) {
                        	
                			resp.sendRedirect("http://csfuzheedu.com/api/wxLogin?schoolProductId="+schoolProductId);
                        }else {
                        	resp.sendRedirect("http://csfuzheedu.com/api/wxLogin");
                        }
                		
					} catch (IOException e) {
						log.error("重定向失败");
					}
                }
               
                //第二步：通过code换取网页授权access_token
              //  String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appID + "&secret="+appSecret+ "&code="+code+ "&grant_type=authorization_code";
              //   System.out.println("url:"+url);
               //if(StringUtils.isEmpty(code)) {
            	  //重定向
               //}
                WxMpOAuth2AccessToken token =  wxMpService.oauth2getAccessToken(code);
                WxMpUser  user = wxMpService.oauth2getUserInfo(token, null);
               
              //  System.out.println(JSONObject.toJSON(user));
             //   System.out.println(user.getOpenId());
                WxAccount  account = wxAccountService.selectWxAccountByOpenId(user.getOpenId());
                if(account==null) {
                	
                	account= new WxAccount();
                	account.setHeadImgUrl(user.getHeadImgUrl());
                	account.setCity(user.getCity());
                	account.setCountry(user.getCountry());
                	if(checkSpecialChar(user.getNickname())) {
                		account.setNickname(filterString(user.getNickname()));
                	}else {
                		
                		account.setNickname(user.getNickname());
                	}
                	account.setOpenId(user.getOpenId());
                	account.setProvince(user.getProvince());
                	account.setSex(user.getSex()+"");
                	account.setSexdesc(user.getSexDesc()); 
                	wxAccountService.insertWxAccount(account);
                }
                
                mmap.put("account",account);
                HttpSession session =req.getSession();
                if (session.getAttribute("token") == null) {
                    session.setAttribute("token", account.getOpenId());
                    if(schoolProductId!=null&&!schoolProductId.isEmpty()) {
                    	if(session.getAttribute(schoolProductId)!=null) {
                    		session.removeAttribute("schoolProductId");
                    	}
                    	session.setAttribute("schoolProductId", schoolProductId);
                    }
                    //session过期时间设置，以秒为单位，即在没有活动30分钟后，session将失效
                    session.setMaxInactiveInterval(30 * 60*2);
                }
                if(schoolProductId!=null&&!schoolProductId.equals("null")&&!"".equals(schoolProductId)&&!schoolProductId.isEmpty()) {
    	    		HnSchoolProduct  schoolProduct = schoolProductService.selectHnSchoolProductById(Long.valueOf(schoolProductId));
    	    		mmap.put("schoolProduct", schoolProduct);
    	    		 return "mobile/index";
                }
    	    	else {
    	    		List<HnSchool> schoollist = hnSchoolService.selectHnSchoolList(new HnSchool());
    	    		mmap.put("schoollist", schoollist);
    	    		return "mobile/index2";
    	    	}
	    } 
	    
	    @RequestMapping(value = "/toindex")
	    public String  toindex(HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
	    	HttpSession session =req.getSession();

	    	String schoolProductId = req.getParameter("schoolProductId");
           
            WxAccount  account = wxAccountService.selectWxAccountByOpenId((String)session.getAttribute("token"));
            mmap.put("account",account);
            if(account==null) {
            	try {
					resp.sendRedirect("http://csfuzheedu.com/api/wxLogin?schoolProductId="+schoolProductId);
				} catch (IOException e) {
					log.error("重定向失败");
				}
            }
            if(session.getAttribute("schoolProductId")==null&&schoolProductId!=null&&!schoolProductId.isEmpty()) {
            	session.setAttribute("schoolProductId", schoolProductId);
            	session.setMaxInactiveInterval(30 * 60*2);
            }
           // List<DictData>  dictDataList = dictDataService.selectDictDataByType("grade_level");
	    	//mmap.put("dictDataList", dictDataList);
            if(schoolProductId!=null&&!schoolProductId.equals("null")&&!"".equals(schoolProductId)&&!schoolProductId.isEmpty()) {
            	if(session.getAttribute("schoolProductId")!=null) {
            		session.removeAttribute("schoolProductId");
            	}
            		session.setAttribute("schoolProductId", schoolProductId);
            	
            	HnSchoolProduct  schoolProduct = schoolProductService.selectHnSchoolProductById(Long.valueOf(schoolProductId));
	    		 mmap.put("schoolProduct", schoolProduct);
	    		 return "mobile/index";
            }
	    	else {
	    		List<HnSchool> schoollist = hnSchoolService.selectHnSchoolList(new HnSchool());
	    		mmap.put("schoollist", schoollist);
	    		return "mobile/index2";
	    	}
	    } 
	    public static boolean checkSpecialChar(String str) throws PatternSyntaxException {
	        // 清除掉所有特殊字符
	        String regEx =  ".*[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]+.*";
	        Pattern p = Pattern.compile(regEx);
	        Matcher m = p.matcher(str);
	        return m.matches();
	    }
	                            
	     public   static   String filterString(String   str)   throws   PatternSyntaxException   {   
	         String regEx= "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]";
	         Pattern   p   =   Pattern.compile(regEx);   
	         Matcher   m   =   p.matcher(str);   
	         return   m.replaceAll("_").trim();   
	     }
	    /**
	     * 消息推送
	     * @param req
	     * @param resp
	     * @throws WxErrorException
	     */
	    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
	    public void  sendMessage(HttpServletRequest req, HttpServletResponse resp) throws WxErrorException {
              //  List<WxMpTemplate>  templateList =  wxMpTemplateMsgService.getAllPrivateTemplate();
                WxMpTemplateMessage  templateMessage = new WxMpTemplateMessage();
                templateMessage.setToUser("ovEzovsTeA_ntD5AGs2H_TsdmEB0");
                templateMessage.setTemplateId("tMseWGdTA1iQNwJmwSMopdWTD4Dz8IoEKq4cT3iidKs");
                templateMessage.setUrl("http://weixin.qq.com/download");
                List<WxMpTemplateData> data = Arrays.asList(
                        new WxMpTemplateData("first", "亲，恭喜您。"),
                        new WxMpTemplateData("keyword1", "订购成功"),
                        new WxMpTemplateData("keyword2", "11111111"),
                        new WxMpTemplateData("keywordei3", "哪个班级"),
                        new WxMpTemplateData("keyword4","哪个学校"),
                        new WxMpTemplateData("keyword5", "￥" +298 ),
                        new WxMpTemplateData("remark", "欢迎再次光临！"));
                templateMessage.setData(data);
               // wxMpTemplateMsgService.sendTemplateMsg(templateMessage);
               
	    }  
	    
	    
		/*
		 * @RequestMapping(value = "/count", method = RequestMethod.GET) public String
		 * count(HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws
		 * WxErrorException {
		 * 
		 * // mmap.put("account", account); return "mobile/count"; }
		 */
	   
	    
		/*
		 * @PostMapping(value = "/upload")
		 * 
		 * @ResponseBody public String upload(String img,HttpServletRequest req,
		 * HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
		 * System.out.println(img); String[] imgarr = img.split(","); img = imgarr[1];
		 * System.out.println(img); String key = UUID.randomUUID().toString()+".png" ;
		 * Configuration cfg = new Configuration(Zone.zone2()); UploadManager
		 * uploadManager = new UploadManager(cfg); String accessKey =
		 * "9KwiS13UxERt_0jvHedJ3w2Aalw9tKwjISuW7Y9y"; String secretKey
		 * ="0gXSFa8u7sbSfEJhmIu_1kxLIc-36ZlBLTOEFc0F"; String bucket= "putbase64";
		 * Scanner scanner = new Scanner(System.in); String filePath =
		 * scanner.nextLine(); //Key 表示文件上传到服务器中的名称，为空的话默认为文件Hash值 Auth auth =
		 * Auth.create(accessKey,secretKey); String upToken = auth.uploadToken(bucket);
		 * try { //Put64.put64image(img); //UploadUtils.putbase64(img); Response
		 * response = uploadManager.put(filePath,key,upToken); DefaultPutRet putRet =
		 * new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
		 * System.out.println(putRet.hash); System.out.println(putRet.key); } catch
		 * (QiniuException e) { // TODO Auto-generated catch block e.printStackTrace();
		 * } // mmap.put("account", account); return "success"; }
		 */
		/**
		 * 
		 * @param img
		 * @param req
		 * @param resp
		 * @param mmap
		 * @return
		 * @throws WxErrorException
		 */
		// @PostMapping(value = "/upload")
		// @ResponseBody
		// public String upload(String img,HttpServletRequest req,HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
		//	 System.out.println(img); 
		//	 String[] imgarr = img.split(","); 
		//	 img = imgarr[1];
			 //base64 字符串 
		//	 System.out.println(img);
		//	 String key = UUID.randomUUID().toString()+".png" ; 
		//     String filepath = "E:\\upload\\" ;
		 //    String path = req.getSession().getServletContext().getRealPath("/uploadFile/");//此处为tomcat下的路径，服务重启路径会变化
		 //    System.out.println(path);
		 //    File folder = new File(path);
		     //检测folder是否是文件夹，不是就创建
		  //   if (!folder.isDirectory()) {
		 //         folder.mkdirs();
		 //    }
		  //   try {
		  //  	 if( Base64StrToImage.GenerateImage(img, filepath+key)) {
		//		  UploadUtils.put64image(filepath+key,key);
				  //删除本地图片
		//	  }
		 //    } catch (Exception e) { // TODO
		//     } 
		//  		return "success"; 
		// }
	    
		 
		
		 @RequestMapping(value = "/pay")
		 public String pay(String schoolProductId ,String key,HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
			 //获取学校产品id  产生订单
			   String domain = "http://img.csfuzheedu.com";
			   String url = domain+"/"+key;
			   //获取学生对应的url
			   //学生信息
			   HttpSession session =req.getSession();
	              
             if (session.getAttribute("student") == null) {
               //重定向
            	 
            	 try {
					resp.sendRedirect("http://csfuzheedu.com/api/wxLogin");
				} catch (IOException e) {
					log.error("获取学校产品id失败");
				}
			    	return null;
             }else {
             	HnStudent student = (HnStudent)session.getAttribute("student");
             	student = hnStudentService.selectHnStudentById(student.getId());
             	student.setUrl(url);
             	hnStudentService.updateHnStudent(student); 
             	mmap.put("student", student); 
             	//String  schoolProductId = session.getAttribute("schoolProductId").toString();
             	HnSchool hnSchool = hnSchoolService.selectHnSchoolById(student.getSchoolId());
             	mmap.put("hnSchool", hnSchool); 
             	HnSchoolProduct schoolProduct = schoolProductService.selectHnSchoolProductById(Long.valueOf(schoolProductId));
             	mmap.put("schoolProduct", schoolProduct); 
             	 HnGrade hnGrade = hnGradeService.selectHnGradeById(student.getGradeId());
             	mmap.put("hnGrade", hnGrade);  
             	mmap.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
             	//HnClasses hnClasses =	hnClassesService.selectHnClassesById(student.getClassId());
             	//String dictData = dictDataService.selectDictLabel("grade_level", hnClasses.getGradeId().toString());
             	//mmap.put("hnClasses", hnClasses);  
     	    	//mmap.put("dictData", dictData);
             }
            
			 return "mobile/topay";
		 } 
		 
		 @GetMapping("/prepay")
		 @ResponseBody
	     public Map<String,String> wxPayFunction(String schoolProductId,HttpServletRequest request) throws Exception{
	 
	       
	        String notifyUrl = "http://csfuzheedu.com/api/paycallback";  //我这里的回调地址是随便写的，到时候需要换成处理业务的回调接口
	        HnSchoolProduct  schoolProduct = schoolProductService.selectHnSchoolProductById(Long.valueOf(schoolProductId));
	       //获取学生信息
	    	HttpSession session =request.getSession();
	    	String token  = session.getAttribute("token").toString();
	    	HnStudent  student = (HnStudent)session.getAttribute("student");
	        //产生订单
	        //获取openid
	        HnStuOder stuOrder = new HnStuOder();
	        stuOrder.setSchoolProductId(Long.valueOf(schoolProductId));
	        stuOrder.setStatus(0+"");
	        stuOrder.setOrderCode(System.currentTimeMillis()+ "");
	        stuOrder.setScoolId(schoolProduct.getSchoolId());
	        stuOrder.setStuName(student.getStuName());
	        stuOrder.setPrice(schoolProduct.getPrice());
	        stuOrder.setStuId(student.getId());
	        stuOrder.setGradeId(student.getGradeId());
	        try {
				stuOderService.insertHnStuOder(stuOrder);
			} catch (Exception e) {
				log.error("调用订单数据失败");
			 throw new Exception("调用订单数据失败");
			}
	        OurWxPayConfig ourWxPayConfig = new OurWxPayConfig();
	         WXPay wxPay = new WXPay(ourWxPayConfig);
	       //  BigDecimal totalPrice  = new BigDecimal(schoolProduct.getPrice()); //此时的单位是元
	        // totalPrice.multiply(totalPrice).toBigInteger().toString();
	         //根据微信支付api来设置
	         Map<String,String> data = new HashMap<>();
	         data.put("appid",ourWxPayConfig.getAppID());
	         data.put("mch_id",ourWxPayConfig.getMchID());         //商户号
	         data.put("trade_type","JSAPI");                         //支付场景 APP 微信app支付 JSAPI 公众号支付  NATIVE 扫码支付
	         data.put("notify_url",notifyUrl);                     //回调地址
				
				 // InetAddress localHost = InetAddress.getLocalHost();
				 // String hostAddress =localHost.getHostAddress(); 
				 // System.out.println(hostAddress);
				 
	         data.put("spbill_create_ip","47.112.135.70");             //终端ip
	         data.put("total_fee",(new BigDecimal(schoolProduct.getPrice()*100)).toBigInteger().toString());       //订单总金额
	       //  data.put("total_fee",(new BigDecimal(1)).toBigInteger().toString());       //订单总金额
	         data.put("fee_type","CNY");                           //默认人民币
	         data.put("out_trade_no",stuOrder.getOrderCode());   //交易号
	         data.put("body","产品名称");
	         data.put("nonce_str",System.currentTimeMillis() / 1000 + "");   // 随机字符串小于32位
	         data.put("openid",token);
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
             respData.put("prepay_id", respData.get("prepay_id"));
             return respData;
         }
         throw new Exception(respData.get("return_msg"));
     }
		  
		 @RequestMapping(value = "/paycallback")
		 @ResponseBody
		 public void paycallback(HttpServletRequest request, HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
			 String inputLine=""; 
			 String notityXml = "";
			  try {
				  while ((inputLine = request.getReader().readLine()) != null) {
			  notityXml += inputLine; 
			  }
				  request.getReader().close(); 
				  } catch (Exception e)
			  { 
					  log.error("支付回调方法出异常");
			  } 
			  log.info("异步回调XML信息："+notityXml);
			  if(!notityXml.isEmpty()){ 
				  Map<String, String> resultMap = new HashMap<String, String>();;
				try {
					resultMap = WXPayUtil.xmlToMap(notityXml);
					 log.info(resultMap.get("out_trade_no") +"===="+ resultMap.get("result_code")+"====="+resultMap.get("return_code") );resultMap.get("out_trade_no") ;
					  resultMap.get("result_code");
					 /* Iterator<String> it = resultMap.keySet().iterator();
			            while (it.hasNext()) {
			                String parameter = (String) it.next();
			                String parameterValue = resultMap.get(parameter);
			                String v = "";
			                if (null != parameterValue) {
			                    v = parameterValue.trim();
			                }
			                resultMap.put(parameter, v);
			            }*/
					 Double total_fee = resultMap.get("total_fee").length()>0?Double.parseDouble(resultMap.get("total_fee"))/100.00 :0.00;
					log.info("回调支付金额："+total_fee);
					 String resXml = "";
					 if( resultMap.get("return_code").equals("SUCCESS")) {
						 
						 String orderCode = resultMap.get("out_trade_no");
						 HnStuOder stuOrder = stuOderService.selectHnStuOderByOrderCode(orderCode);
						 	if(stuOrder!=null&&stuOrder.getStatus().equals("0")) {
						 		stuOrder.setStatus("1");
						 		stuOrder.setPrice(total_fee);
						 		stuOderService.updateHnStuOder(stuOrder);
						 	}
						 resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
						 	 
						 BufferedOutputStream out = new BufferedOutputStream(
	                    		resp.getOutputStream());
	                    out.write(resXml.getBytes());
	                    out.flush();
	                    out.close();
	                    return;
					 }
				}catch (Exception  e) {
					 log.error("支付回调出现异常", e);
				}		

					
		 } 
			
	}	 
		
			/*
			 * public void getWxPayResult(HttpServletRequest request) throws Exception{
			 * ResultBean result = new ResultBean(); String inputLine=""; String notityXml =
			 * ""; try { while ((inputLine = request.getReader().readLine()) != null) {
			 * notityXml += inputLine; } request.getReader().close(); } catch (Exception e)
			 * { e.printStackTrace(); } System.out.println("异步回调XML信息："+notityXml);
			 * if(!notityXml.isEmpty()){ //解析并读取统一下单中的参数信息 Map<String, Object> prepayMap =
			 * XmlUtil.getPrepayMapInfo(notityXml); if(!prepayMap.isEmpty()){ String orderId
			 * = prepayMap.get("out_trade_no")+""; String
			 * resCode=prepayMap.get("result_code")+""; String
			 * returnCode=prepayMap.get("return_code")+"";
			 * System.out.println("解析并读取统一下单中的参数信息:"+orderId+"==="+resCode+"==="+returnCode)
			 * ; } //回调中业务逻辑完毕 }else{ result.fillCode(ResultBeanCodeEnum.OPERA_FAIL); }
			 * }else{ result.fillCode(ResultBeanCodeEnum.OPERA_FAIL); } return result; }
			 */
	    
			/*
			 * public static Map<String, Object> getPrepayMapInfo(String Str) {
			 * //解析并读取统一下单中的参数信息 //1.去掉前后的xml标签 String notityXml = Str.replaceAll("</?xml>",
			 * ""); System.out.println(notityXml); //2.匹配一段一段这样的数据
			 * <attach><![CDATA[支付测试]]></attach> Pattern pattern =
			 * Pattern.compile("<.*?/.*?>"); Matcher matcher = pattern.matcher(notityXml);
			 * //3.配置是否包含<![CDATA[CNY]]> CDATA 包裹的数据 Pattern pattern2 =
			 * Pattern.compile("!.*]"); Map<String, Object> mapInfo = new HashMap<>();
			 * while(matcher.find()) { //获取键 String key = matcher.group().replaceAll(".
			 ", "");key=key.substring(0,key.length()-1);
			Matcher matcher2 = pattern2.matcher(matcher.group());
			String value = matcher.group().replaceAll("</?.*?>", "");
			// 获取值
			if(matcher2.find()&&!value.equals("DATA"))
			{
				value = matcher2.group().replaceAll("!.*\\[", "");
				value = value.substring(0, value.length() - 2);
			}mapInfo.put(key,value);
		}return mapInfo;}*/
}	 