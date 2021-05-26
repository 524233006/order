
  package com.numberone.project.system.datcconfig.controller;
  
  import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import
  org.springframework.stereotype.Controller;
import
  org.springframework.web.bind.annotation.PostMapping;
import
  org.springframework.web.bind.annotation.RequestMapping;
import
  org.springframework.web.bind.annotation.ResponseBody;

import com.numberone.framework.web.controller.BaseController;
import
  com.numberone.project.system.datcconfig.domain.HlTianchengConfig;
import com.numberone.project.system.datcconfig.domain.HlTianchengConfigVO;
import
  com.numberone.project.system.datcconfig.service.IHlTianchengConfigService;
  
 /**
	 * 月假时间设置Controller
	 * 
	 * @author fhx
	 * @date 2020-06-18
	 */

  @Controller
  
  @RequestMapping("datc") 
  public class DatcController extends BaseController {
  
  
  @Autowired private IHlTianchengConfigService hlTianchengConfigService;
  
  
 /**
	 * 查询月假时间设置列表
	 */
		  
		  @PostMapping("/queryData")
		  
		  @ResponseBody public HlTianchengConfigVO queryData() {
		  
		  HlTianchengConfig data = hlTianchengConfigService.selectOne(); 
		  HlTianchengConfigVO  vo = new HlTianchengConfigVO();
		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//注意月份是MM
	      long time = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(data.getPrimaryStart(), new ParsePosition(0)).getTime()/1000;
	        //获取时间戳
		  vo.setPrimary_name("小学");
		  vo.setPrimary_type(17);
		  vo.setPrimary_start((new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(data.getPrimaryStart(), new ParsePosition(0)).getTime()/1000);
		  vo.setPrimary_end((new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(data.getPrimaryEnd(), new ParsePosition(0)).getTime()/1000);
		  vo.setMiddle_name("初中");
		  vo.setMiddle_type(39);
		  vo.setMiddle_start((new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(data.getMiddleStart(), new ParsePosition(0)).getTime()/1000);
		  vo.setMiddle_end((new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(data.getMiddleEnd(), new ParsePosition(0)).getTime()/1000);
		  vo.setHigh_name("高中");
		  vo.setHigh_type(38);
		  vo.setHigh_start((new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(data.getHighStart(), new ParsePosition(0)).getTime()/1000);
		  vo.setHigh_end((new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(data.getHighEnd(), new ParsePosition(0)).getTime()/1000);
		  return vo; 
		  }
		  
		  
		  }
		 