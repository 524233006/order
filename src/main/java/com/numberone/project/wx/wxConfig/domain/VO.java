package com.numberone.project.wx.wxConfig.domain;



import com.numberone.framework.aspectj.lang.annotation.Excel;
import com.numberone.framework.web.domain.BaseEntity;

/**
 * 微信配置对象 wx_config
 * 
 * @author fhx
 * @date 2020-07-21
 */
public class VO extends BaseEntity
{
    /** appid */
    @Excel(name = "appId")
    private String appId;

    /** 秘钥 */
    private String timeStamp;

    /** token */
    private String nonceStr;

  

    /** 学校名称 */
    @Excel(name = "学校名称")
    private String signType;



	public String getAppId() {
		return appId;
	}



	public void setAppId(String appId) {
		this.appId = appId;
	}



	public String getTimeStamp() {
		return timeStamp;
	}



	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}



	public String getNonceStr() {
		return nonceStr;
	}



	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}



	public String getSignType() {
		return signType;
	}



	public void setSignType(String signType) {
		this.signType = signType;
	}

   
  
}

