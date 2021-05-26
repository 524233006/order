package com.numberone.project.hn.oder.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.numberone.framework.aspectj.lang.annotation.Excel;
import com.numberone.framework.aspectj.lang.annotation.Excel.Type;
import com.numberone.framework.web.domain.BaseEntity;
import com.numberone.project.hn.grade.domain.HnGrade;
import com.numberone.project.hn.product.domain.HnProduct;
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.project.hn.schoolProduct.domain.HnSchoolProduct;
import com.numberone.project.hn.student.domain.HnStudent;

/**
 * 订单对象 hn_stu_oder
 * 
 * @author fhx
 * @date 2020-07-29
 */
public class HnStuOder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 学校id */
  
    private Long scoolId;
    
    /** 学校 */
    @Excel(name = "学校名称")
    private String schoolName;
    
    
    @Excel(name = "产品名称")
    private String productName;
    
    
	@Excel(name = "年级名称")
    private String gradeName;
    
    /** 年级 */
  
    private Long gradeId;
    
    /** 班级id */
  
    private Long classId;
    /** 班级 */
    @Excel(name = "班级")
    private String className;
   
	/** 学生 */
    @Excel(name = "学生编号")
    private Long stuId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String stuName;
    
    /** 电话*/
    @Excel(name = "电话")
    private String tel;

    /** 价格 */
    @Excel(name = "价格")
    private Double price;
    
    /** 退款金额 */
    @Excel(name = "退款金额")
    private Double refundMoney;
    /** 帐号状态（0未支付 1已付款） */
    @Excel(name = "帐号状态", readConverterExp = "0=未支付,1=已付款")
    private String status;
    /** 订单号 */
    @Excel(name = "订单号")
    private String  orderCode;
   

	/** 产品 */
   
    private Long schoolProductId;

    /** 微信账号 */
    private Long accountId;
    /**是否退款*/
    private String state;
    /**退款时间*/
    private Date refundTime;
   
    
   
    private HnSchool  hnSchool;
   
    private HnGrade  hnGrade;
    private HnStudent  hnStudent;
    private HnProduct  hnProduct;
    private HnSchoolProduct  hnSchoolProduct;
    private Integer  num;
   
    private Double totalMoney;
   
    @Excel(name = "时间", width = 30, type = Type.EXPORT)
   
    private String updateDay;
    @Excel(name = "月", width = 30, type = Type.EXPORT)
    private String updateMonth;

	private String startTime;
	private String endTime;
    
	 public Double getRefundMoney() {
		return refundMoney;
	}


	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}


	/** 交易时间 */
		/*
		 * @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") private Date deelTime;
		 * 
		 * 
		 * public Date getDeelTime() {
		 * 
		 * return deelTime; }
		 * 
		 * public void setDeelTime(Date deelTime) { this.deelTime = deelTime; }
		 */

	
	public String getStartTime() {
		return startTime;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Date getRefundTime() {
		return refundTime;
	}


	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUpdateMonth() {
		return updateMonth;
	}

	public void setUpdateMonth(String updateMonth) {
		this.updateMonth = updateMonth;
	}

	public String getUpdateDay() {
		return updateDay;
	}

	public void setUpdateDay(String updateDay) {
		this.updateDay = updateDay;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
    public String getClassName() {
			return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	public HnGrade getHnGrade() {
		return hnGrade;
	}

	public void setHnGrade(HnGrade hnGrade) {
		this.hnGrade = hnGrade;
	}

	public HnStudent getHnStudent() {
		return hnStudent;
	}

	public void setHnStudent(HnStudent hnStudent) {
		this.hnStudent = hnStudent;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public HnSchool getHnSchool() {
		return hnSchool;
	}

	public void setHnSchool(HnSchool hnSchool) {
		this.hnSchool = hnSchool;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
    public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public HnProduct getHnProduct() {
		return hnProduct;
	}

	public void setHnProduct(HnProduct hnProduct) {
		this.hnProduct = hnProduct;
	}

	public HnSchoolProduct getHnSchoolProduct() {
		return hnSchoolProduct;
	}

	public void setHnSchoolProduct(HnSchoolProduct hnSchoolProduct) {
		this.hnSchoolProduct = hnSchoolProduct;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setScoolId(Long scoolId) 
    {
        this.scoolId = scoolId;
    }

    public Long getScoolId() 
    {
        return scoolId;
    }
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setStuId(Long stuId) 
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }
    public void setStuName(String stuName) 
    {
        this.stuName = stuName;
    }

    public String getStuName() 
    {
        return stuName;
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
    public void setSchoolProductId(Long schoolProductId) 
    {
        this.schoolProductId = schoolProductId;
    }

    public Long getSchoolProductId() 
    {
        return schoolProductId;
    }
    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("scoolId", getScoolId())
            .append("classId", getClassId())
            .append("stuId", getStuId())
            .append("stuName", getStuName())
            .append("price", getPrice())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("schoolProductId", getSchoolProductId())
            .append("accountId", getAccountId())
            .toString();
    }
}
