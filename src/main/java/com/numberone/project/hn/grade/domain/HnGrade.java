package com.numberone.project.hn.grade.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.numberone.framework.aspectj.lang.annotation.Excel;
import com.numberone.framework.aspectj.lang.annotation.Excel.ColumnType;
import com.numberone.framework.web.domain.BaseEntity;

/**
 * 年级对象 hn_grade
 * 
 * @author fhx
 * @date 2020-10-10
 */
public class HnGrade extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
   
    @Excel(name = "序号", cellType = ColumnType.NUMERIC)
    private Long gradeId;

    /** 年级 */
    @Excel(name = "年级")
    private String gradeName;

    /** 学校 */
    @Excel(name = "学校")
    private Long schoolId;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setGradeId(Long gradeId) 
    {
        this.gradeId = gradeId;
    }

    public Long getGradeId() 
    {
        return gradeId;
    }
    public void setGradeName(String gradeName) 
    {
        this.gradeName = gradeName;
    }

    public String getGradeName() 
    {
        return gradeName;
    }
    public void setSchoolId(Long schoolId) 
    {
        this.schoolId = schoolId;
    }

    public Long getSchoolId() 
    {
        return schoolId;
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
            .append("gradeId", getGradeId())
            .append("gradeName", getGradeName())
            .append("schoolId", getSchoolId())
            .append("status", getStatus())
            .toString();
    }
}
