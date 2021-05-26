package com.numberone.project.hn.classes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.numberone.framework.aspectj.lang.annotation.Excel;
import com.numberone.framework.web.domain.BaseEntity;
import com.numberone.project.hn.school.domain.HnSchool;

/**
 * 班级对象 hn_classes
 * 
 * @author fhx
 * @date 2020-07-28
 */
public class HnClasses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 学校 */
    @Excel(name = "学校")
    private Long schoolId;

    /** 年级 */
    @Excel(name = "年级，小学1到6，初中789，高中10,11,12")
    private Long gradeId;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private String classCode;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String className;

    /** 帐号状态0正常 1停用 */
    @Excel(name = "帐号状态 0=正常,1=停用", readConverterExp = "0=正常,1=停用")
    private String status;
    
    private HnSchool school;
    

    public HnSchool getSchool() {
		return school;
	}

	public void setSchool(HnSchool school) {
		this.school = school;
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
    public void setGradeId(Long gradeId) 
    {
        this.gradeId = gradeId;
    }

    public Long getGradeId() 
    {
        return gradeId;
    }
    public void setClassCode(String classCode) 
    {
        this.classCode = classCode;
    }

    public String getClassCode() 
    {
        return classCode;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("schoolId", getSchoolId())
            .append("gradeId", getGradeId())
            .append("classCode", getClassCode())
            .append("className", getClassName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
