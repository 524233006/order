package com.numberone.project.wx.wxConfig.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.numberone.framework.aspectj.lang.annotation.Excel;
import com.numberone.framework.web.domain.BaseEntity;

/**
 * 微信配置对象 wx_config
 * 
 * @author fhx
 * @date 2020-07-21
 */
public class WxConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 公众号名称 */
    @Excel(name = "公众号名称")
    private String name;

    /** 原始id */
    @Excel(name = "原始id")
    private String startId;

    /** appid */
    @Excel(name = "appid")
    private String appid;

    /** 秘钥 */
    private String secret;

    /** token */
    private String token;

    /** accesstoken */
    private String accesstoken;

    /** 学校id */
    @Excel(name = "学校id")
    private Long schoolId;

    /** 学校名称 */
    @Excel(name = "学校名称")
    private String schoolName;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStartId(String startId) 
    {
        this.startId = startId;
    }

    public String getStartId() 
    {
        return startId;
    }
    public void setAppid(String appid) 
    {
        this.appid = appid;
    }

    public String getAppid() 
    {
        return appid;
    }
    public void setSecret(String secret) 
    {
        this.secret = secret;
    }

    public String getSecret() 
    {
        return secret;
    }
    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }
    public void setAccesstoken(String accesstoken) 
    {
        this.accesstoken = accesstoken;
    }

    public String getAccesstoken() 
    {
        return accesstoken;
    }
    public void setSchoolId(Long schoolId) 
    {
        this.schoolId = schoolId;
    }

    public Long getSchoolId() 
    {
        return schoolId;
    }
    public void setSchoolName(String schoolName) 
    {
        this.schoolName = schoolName;
    }

    public String getSchoolName() 
    {
        return schoolName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("startId", getStartId())
            .append("appid", getAppid())
            .append("secret", getSecret())
            .append("token", getToken())
            .append("accesstoken", getAccesstoken())
            .append("schoolId", getSchoolId())
            .append("schoolName", getSchoolName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remarks", getRemarks())
            .toString();
    }
}
