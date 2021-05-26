package com.numberone.project.hn.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.numberone.framework.aspectj.lang.annotation.Excel;
import com.numberone.framework.web.domain.BaseEntity;
import com.numberone.project.hn.classes.domain.HnClasses;
import com.numberone.project.hn.school.domain.HnSchool;

/**
 * 学生对象 hn_student
 * 
 * @author fhx
 * @date 2020-07-29
 */
public class HnStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 学校 */
    @Excel(name = "学校")
    private Long schoolId;

    /** 班级 */
    @Excel(name = "班级")
    private Long classId;
    /** 年级 */
    @Excel(name = "年级")
    private Long gradeId;
    
    /** 班级名称 */
    @Excel(name = "班级名称")
    private String className;
   

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/** 学生姓名 */
    @Excel(name = "学生姓名")
    private String stuName;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String tel;

    /** url */
    private String url;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 关联账号 */
    private Long acountId;

    /** openID */
    private String openId;

    /** unionID */
    private String unionId;

    /** 头像 */
    @Excel(name = "头像")
    private String headImg;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;
    
    /** 学生身份证号码 */
    @Excel(name = "学生身份证号码 ")
    private String cardId;
    
    
    
    public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	private HnSchool hnSchool;
    
    private HnClasses hnClasses;
    public HnSchool getHnSchool() {
		return hnSchool;
	}

    public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
    
	public void setHnSchool(HnSchool hnSchool) {
		this.hnSchool = hnSchool;
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
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setStuName(String stuName) 
    {
        this.stuName = stuName;
    }

    public String getStuName() 
    {
        return stuName;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setAcountId(Long acountId) 
    {
        this.acountId = acountId;
    }

    public Long getAcountId() 
    {
        return acountId;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setUnionId(String unionId) 
    {
        this.unionId = unionId;
    }

    public String getUnionId() 
    {
        return unionId;
    }
    public void setHeadImg(String headImg) 
    {
        this.headImg = headImg;
    }

    public String getHeadImg() 
    {
        return headImg;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("schoolId", getSchoolId())
            .append("classId", getClassId())
            .append("gradeId", getGradeId())
            .append("className", getClassName())
            .append("stuName", getStuName())
            .append("gender", getGender())
            .append("tel", getTel())
            .append("url", getUrl())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("acountId", getAcountId())
            .append("openId", getOpenId())
            .append("unionId", getUnionId())
            .append("headImg", getHeadImg())
            .append("nickName", getNickName())
            .toString();
    }
}
