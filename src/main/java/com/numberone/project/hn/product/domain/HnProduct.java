package com.numberone.project.hn.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.numberone.framework.aspectj.lang.annotation.Excel;
import com.numberone.framework.web.domain.BaseEntity;

/**
 * 产品对象 hn_product
 * 
 * @author fhx
 * @date 2020-07-28
 */
public class HnProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 价格 */
    @Excel(name = "价格")
    private Double price;

    /** url */
    @Excel(name = "url")
    private String url;

    /** 简介 */
    @Excel(name = "简介")
    private String shortDesc;

    /** 产品描述 */
    @Excel(name = "产品描述")
    private String descrption;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 购买须知 */
    @Excel(name = "购买须知")
    private String remarks;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setShortDesc(String shortDesc) 
    {
        this.shortDesc = shortDesc;
    }

    public String getShortDesc() 
    {
        return shortDesc;
    }
    public void setDescrption(String descrption) 
    {
        this.descrption = descrption;
    }

    public String getDescrption() 
    {
        return descrption;
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
            .append("productName", getProductName())
            .append("price", getPrice())
            .append("url", getUrl())
            .append("shortDesc", getShortDesc())
            .append("descrption", getDescrption())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remarks", getRemarks())
            .toString();
    }
}
