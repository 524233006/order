package com.numberone.project.hn.schoolProduct.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.numberone.framework.aspectj.lang.annotation.Excel;
import com.numberone.framework.web.domain.BaseEntity;
import com.numberone.project.hn.product.domain.HnProduct;
import com.numberone.project.hn.school.domain.HnSchool;

/**
 * 学校产品对象 hn_school_product
 * 
 * @author fhx
 * @date 2020-07-29
 */
public class HnSchoolProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 学校 */
    @Excel(name = "学校")
    private Long schoolId;

    /** 产品 */
    @Excel(name = "产品")
    private Long productId;

    /** 价格 */
    @Excel(name = "价格")
    private Double price;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 二维码 */
    @Excel(name = "二维码")
    private String url;

    /** 购买须知 */
    private String remarks;
    
    private HnSchool hnSchool;
    private HnProduct hnProduct;
    /*   private String  productName;
    private String  schoolName;*/

    
	public HnSchool getHnSchool() {
		return hnSchool;
	}

	public void setHnSchool(HnSchool hnSchool) {
		this.hnSchool = hnSchool;
	}

	public HnProduct getHnProduct() {
		return hnProduct;
	}

	public void setHnProduct(HnProduct hnProduct) {
		this.hnProduct = hnProduct;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSchoolId(Long schoolId) 
    {
        this.schoolId = schoolId;
    }

    public Long getSchoolId() 
    {
        return schoolId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
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
            .append("schoolId", getSchoolId())
            .append("productId", getProductId())
            .append("price", getPrice())
            .append("status", getStatus())
            .append("url", getUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remarks", getRemarks())
            .toString();
    }
}
