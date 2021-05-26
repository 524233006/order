package com.numberone.project.wx.account.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.numberone.framework.aspectj.lang.annotation.Excel;
import com.numberone.framework.web.domain.BaseEntity;

/**
 * 粉丝对象 wx_account
 * 
 * @author fhx
 * @date 2020-07-22
 */
public class WxAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** openid */
    private String openId;

    /** 头像 */
    @Excel(name = "头像")
    private String headImgUrl;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 性别 */
    @Excel(name = "性别")
    private String sexdesc;

    /** 地址 */
    @Excel(name = "地址")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 国家 */
    private String country;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** unionid */
    @Excel(name = "unionid")
    private String unionId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long stuId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setHeadImgUrl(String headImgUrl) 
    {
        this.headImgUrl = headImgUrl;
    }

    public String getHeadImgUrl() 
    {
        return headImgUrl;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setSexdesc(String sexdesc) 
    {
        this.sexdesc = sexdesc;
    }

    public String getSexdesc() 
    {
        return sexdesc;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setUnionId(String unionId) 
    {
        this.unionId = unionId;
    }

    public String getUnionId() 
    {
        return unionId;
    }
    public void setStuId(Long stuId) 
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openId", getOpenId())
            .append("headImgUrl", getHeadImgUrl())
            .append("nickname", getNickname())
            .append("sexdesc", getSexdesc())
            .append("province", getProvince())
            .append("city", getCity())
            .append("country", getCountry())
            .append("sex", getSex())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("unionId", getUnionId())
            .append("stuId", getStuId())
            .toString();
    }
}
