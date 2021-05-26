package com.numberone.project.wx.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.numberone.framework.web.controller.BaseController;
import com.numberone.framework.web.domain.AjaxResult;
import com.numberone.project.hn.classes.domain.HnClasses;
import com.numberone.project.hn.classes.service.IHnClassesService;
import com.numberone.project.hn.grade.domain.HnGrade;
import com.numberone.project.hn.grade.service.IHnGradeService;
import com.numberone.project.hn.oder.domain.HnStuOder;
import com.numberone.project.hn.oder.service.IHnStuOderService;
import com.numberone.project.hn.product.domain.HnProduct;
import com.numberone.project.hn.product.service.IHnProductService;
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.project.hn.school.service.IHnSchoolService;
import com.numberone.project.hn.schoolProduct.domain.HnSchoolProduct;
import com.numberone.project.hn.schoolProduct.service.IHnSchoolProductService;
import com.numberone.project.hn.student.domain.HnStudent;
import com.numberone.project.hn.student.service.IHnStudentService;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import me.chanjar.weixin.common.error.WxErrorException;
@Controller
@RequestMapping("/app")
public class MobileController extends BaseController{
	
	 private static final Logger log = LoggerFactory.getLogger(WxLoginController.class);
		@Autowired
		private IHnSchoolService hnSchoolService;
		@Autowired
		private IHnClassesService hnClassesService;
		
		@Autowired
	    private IHnStudentService studentService;
		@Autowired
	    private IHnSchoolProductService  schoolProductService;
		@Autowired
	    private IHnProductService  hnProductService;
		@Autowired
	    private IHnStuOderService  stuOderService;
		@Autowired
	    private IHnGradeService  hnGradeService;
		
		
		  @RequestMapping(value = "/index") 
		  public String index(String schoolProductId,HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws WxErrorException { 
			  
		      if(schoolProductId!=null&&!schoolProductId.equals("null")&&!"".equals(schoolProductId)&&!schoolProductId.isEmpty()) {
		    	  HnSchoolProduct schoolProduct = schoolProductService.selectHnSchoolProductById(Long.valueOf(schoolProductId)) ; 
		    	  log.info("获取学校id"+schoolProduct.getHnSchool().getId());
		    	  mmap.put("schoolProduct", schoolProduct); 
		    	  return "mobile/index"; 
		     } else {
			  
		    	 List<HnSchool> schoollist = hnSchoolService.selectHnSchoolList(new HnSchool()); 
		    	 mmap.put("schoollist", schoollist);
		    	 return "mobile/index2";
		     }
		  
		  }
		
		
		/**
		 * 学生信息填写
		 */
	    @RequestMapping(value = "/info")
	    public String  info(String schoolId,String className,String gradeId,HttpServletRequest req, HttpServletResponse response,ModelMap mmap) throws WxErrorException {
	    	
	    	HttpSession session =req.getSession();
	    
             if (StringUtils.isEmpty(session.getAttribute("token"))||StringUtils.isEmpty(session.getAttribute("schoolProductId"))) {
                //重定向
            	  try {
					response.sendRedirect("http://csfuzheedu.com/api/wxLogin");
				} catch (IOException e) {
					log.error("没有获取到token");
				}
            	 return null;
            	 
             }else {
             	String sessiontoken = session.getAttribute("token").toString();
             	String schoolProductId = session.getAttribute("schoolProductId").toString();
             	mmap.put("schoolProductId", schoolProductId);
             	HnStudent student = new HnStudent();
             	student.setSchoolId(Long.valueOf(schoolId));
             	student.setClassName(className);
             	student.setGradeId(Long.valueOf(gradeId));
             	student.setOpenId(sessiontoken);
		    	mmap.put("student", student);
		    	
             }
	    	return "mobile/info";
                
	    } 
	    
		
	    @PostMapping("/addGrade")
	    @ResponseBody
	    public AjaxResult addGrade(HnGrade hnGrade)
	    {
	        if (hnGrade==null||hnGrade.getSchoolId()==null)
	        {
	            return AjaxResult.error("没有选择学校");
	        }
	        List<HnGrade> grageList = hnGradeService.selectHnGradeList(hnGrade);
	        return AjaxResult.success(grageList);
	    }
		 
		 
	    @PostMapping("/addClasses")
	    @ResponseBody
	    public AjaxResult addClasses(String schoolId,String gradeId)
	    {
	      
	        if (schoolId==null||"".equals(schoolId))
	        {
	            return AjaxResult.error("没有选择学校");
	        }
	        else if (gradeId==null||"".equals(gradeId))
	        {
	            return error("没有选择班级");
	        }
	        HnClasses classes = new HnClasses();
	        classes.setSchoolId(Long.valueOf(schoolId));
	        classes.setGradeId(Long.valueOf(gradeId));
	        List<HnClasses> classesList = hnClassesService.selectHnClassesList(classes);
	        return AjaxResult.success(classesList);
	    }
		    
	    @RequestMapping(value = "/center") 
		  public String center(String schoolProductId,HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws WxErrorException { 
	    	 HttpSession session =req.getSession();
			  if (session.getAttribute("student") != null) {
				  HnStudent	 student = (HnStudent)session.getAttribute("student"); 
				  HnSchoolProduct hnSchoolProduct = new HnSchoolProduct();
				 
				  if(StringUtils.isEmpty(schoolProductId)||"null".equals(schoolProductId)) {
					   schoolProductId = session.getAttribute("schoolProductId").toString();
					 
				  }
				  hnSchoolProduct.setId(Long.valueOf(schoolProductId));
			    	hnSchoolProduct.setSchoolId(student.getSchoolId());
			    	hnSchoolProduct.setStatus(0+"");
			    	List<HnSchoolProduct> productList = schoolProductService.selectHnSchoolProductList(hnSchoolProduct);
			    	mmap.put("productList", productList);
		            return "mobile/list";
                }else {
                	 //重定向
              	  try {
  					resp.sendRedirect("http://csfuzheedu.com/api/wxLogin");
  				} catch (IOException e) {
  					log.error("没有获取到token");
  				}
              	 return null;
                }
		  
		  }
		    
		    @RequestMapping(value = "/list")
		    public String  list(HttpServletRequest req,String schoolId,String gradeId,String className,HnStudent student,HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
		    	//HnStudent student = new HnStudent();
		    	 HttpSession session =req.getSession();
		    	 if(session.getAttribute("token") == null) {
		    		 //重定向
	            	  try {
	            		  resp.sendRedirect("http://csfuzheedu.com/api/wxLogin");
					} catch (IOException e) {
						log.error("没有获取到token");
					}
	            	 return null;
		    	 }
		    	
		    	String  schoolProductId = session.getAttribute("schoolProductId")==null?"":session.getAttribute("schoolProductId").toString();
		    	
		    	String openId= session.getAttribute("token").toString();
		    	student.setSchoolId(Long.valueOf(schoolId));
		    	student.setClassName(className);
		    	student.setOpenId(openId);
		    	List<HnStudent>  list = studentService.selectHnStudentList(student);
		    	if(list==null||list.isEmpty()) {
		    		 studentService.insertHnStudent(student);
		    		
		    	}else if(list.size()==1){
		    		student = list.get(0);
		    	}
		    	if (session.getAttribute("student") != null) {
		    		session.removeAttribute("student");//根据参数清除对应的值
                }
		    	 session.setAttribute("student", student);
                 //session过期时间设置，以秒为单位，即在没有活动30分钟后，session将失效
                 session.setMaxInactiveInterval(30 * 60*2);
		    	HnSchoolProduct hnSchoolProduct = new HnSchoolProduct();
		    	if(!StringUtils.isEmpty(schoolProductId)){
		    		hnSchoolProduct.setId(Long.valueOf(schoolProductId));
		    		mmap.put("schoolProductId", schoolProductId);
		    	}
		    	
		    	hnSchoolProduct.setSchoolId(student.getSchoolId());
		    	hnSchoolProduct.setStatus(0+"");
		    	List<HnSchoolProduct> productList = schoolProductService.selectHnSchoolProductList(hnSchoolProduct);
		    	mmap.put("productList", productList);
		    	mmap.put("schoolProductId", schoolProductId);
	            return "mobile/list";
		    } 
		
		  @RequestMapping(value = "/detail") 
		  public String detail(String id,HnStudent student, HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
		  
		  HnSchoolProduct hnSchoolProduct = schoolProductService.selectHnSchoolProductById(Long.valueOf(id));
		  HnProduct product=  hnProductService.selectHnProductById(hnSchoolProduct.getProductId());
		  mmap.put("product", product); 
		  mmap.put("hnSchoolProduct", hnSchoolProduct); 
		  return "mobile/detail";
		  }
		 
		  /**
		     * 电子签名上传
		     * @param req
		     * @param resp
		     * @param mmap
		     * @return
		     * @throws WxErrorException
		     */
		    @RequestMapping(value = "/canvas")
		    public String  canvas(String id ,HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
		    	//获取学校产品id  
				 HnSchoolProduct hnSchoolProduct = schoolProductService.selectHnSchoolProductById(Long.valueOf(id)); 
				 mmap.put("hnSchoolProduct", hnSchoolProduct); 
				
		    	String accessKey = "9KwiS13UxERt_0jvHedJ3w2Aalw9tKwjISuW7Y9y";
				  String secretKey = "0gXSFa8u7sbSfEJhmIu_1kxLIc-36ZlBLTOEFc0F";
				  //要上传的空间
				  String bucketname = "putbase64";
				  Auth auth = Auth.create(accessKey, secretKey);
				  String upToken = auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
	            
				  mmap.put("upToken", upToken);
	                return "mobiledemo/zhanggang";
		    } 
		 
		  
		  @RequestMapping(value = "/sign")
		  public String sign(String id , HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
		  //获取学校产品id  产生订单
			 HnSchoolProduct hnSchoolProduct = schoolProductService.selectHnSchoolProductById(Long.valueOf(id)); 
			 mmap.put("hnSchoolProduct", hnSchoolProduct); 
			 return "mobile/sign";
		 }   
			  
			/*
			 * @RequestMapping(value = "/topay") public String topay(String id ,String
			 * key,HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws
			 * WxErrorException { //获取学校产品id 产生订单 String domain =
			 * "http://qdyep96oj.bkt.clouddn.com"; String url = domain+"/"+key+".png";
			 * //获取学生对应的url //学生信息 HttpSession session =req.getSession();
			 * 
			 * if (session.getAttribute("student") == null) { //重定向
			 * 
			 * }else { HnStudent student = (HnStudent)session.getAttribute("student");
			 * student.setUrl(url); mmap.put("student", student); }
			 * 
			 * return "mobile/topay"; }
			 */
		  
		  
		
		  @RequestMapping(value = "/payok")
		  public String payok(HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
			 
			  return "mobile/payok";
		 }   	
		  @RequestMapping(value = "/orderList")
		  public String orderList(HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
			  HttpSession session =req.getSession();
			  if(session.getAttribute("student")==null) {
				  try {
						//应该提示您的登陆信息已失效，请重新登陆
					  resp.sendRedirect("http://csfuzheedu.com/api/wxLogin?schoolProductId="+ session.getAttribute("schoolProductId"));
					} catch (IOException e) {
						log.error("重定向失败");
					}
				  return null;
			  }else {
				
				 
				  HnStudent	 student= (HnStudent)session.getAttribute("student"); 
				  String schoolProductId= session.getAttribute("schoolProductId").toString();
				  HnStuOder stuOrder = new HnStuOder();
				  stuOrder.setStuId(student.getId());
				  stuOrder.setStatus("1");
				  stuOrder.setSchoolProductId(Long.valueOf(schoolProductId));
				  List<HnStuOder> studentOrderList=  stuOderService.selectHnStuOderList(stuOrder);
				  mmap.put("studentOrderList", studentOrderList);
				  mmap.put("schoolProductId", schoolProductId);
				  return "mobile/order";
			  }
			 
		 }   
		  @RequestMapping(value = "/paydetail")
		  public String paydetail(String id,HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
			  HttpSession session =req.getSession();
			  if(session.getAttribute("schoolProductId")!=null) {
		    		String schoolProductId = session.getAttribute("schoolProductId").toString();
		    		 mmap.put("schoolProductId", schoolProductId);
		    	}
			  HnStuOder stuOrder =   stuOderService.selectHnStuOderById(Long.valueOf(id));
			  mmap.put("stuOrder", stuOrder);
			  return "mobile/paydetail";
		 }  
		  @RequestMapping(value = "/my")
		  public String my(HttpServletRequest req, HttpServletResponse resp,ModelMap mmap) throws WxErrorException {
			  HttpSession session =req.getSession();
		    	 if(session.getAttribute("token") == null) {
		    		 //重定向
	            	  try {
	            		  resp.sendRedirect("http://csfuzheedu.com/api/wxLogin");
					} catch (IOException e) {
						log.error("没有获取到token");
					}
	            	 return null;
		     }
		    	if(session.getAttribute("schoolProductId")!=null) {
		    		String schoolProductId = session.getAttribute("schoolProductId").toString();
		    		 mmap.put("schoolProductId", schoolProductId);
		    	}
		    	
		    	 //根据token 查询有几个孩子数据
		      List<HnStudent> list =  studentService.selectListByOpenId(session.getAttribute("token").toString());
		      mmap.put("list", list);
			  return "mobile/my";
		 }  
		  
		  @RequestMapping(value = "/getechartsData")
		  @ResponseBody
		  public AjaxResult getechartsData() {
			  HnStuOder stuorder =   new HnStuOder();
			  stuorder.setStatus("1");
			  List<HnStuOder> list = stuOderService.countByDay(stuorder);
			  if(list.isEmpty()) {
				  return AjaxResult.error("没有数据");
			  }
		
			  return AjaxResult.success(list);
		 } 
		  @RequestMapping(value = "/test") 
		 public String test() {
			 return "mobile/1.html";
		 }
}	
