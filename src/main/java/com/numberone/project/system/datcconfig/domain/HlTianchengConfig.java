package com.numberone.project.system.datcconfig.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.numberone.framework.aspectj.lang.annotation.Excel;
import com.numberone.framework.web.domain.BaseEntity;

/**
 * 月假时间设置对象 hl_tiancheng_config
 * 
 * @author fhx
 * @date 2020-06-19
 */
public class HlTianchengConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long configId;

    /** 月份 */
    @Excel(name = "月份")
    private String configMonth;

    /** 小学开始时间 */
    @Excel(name = "小学开始时间")
    private String primaryStart;

    /** 小学结束时间 */
    @Excel(name = "小学结束时间")
    private String primaryEnd;

    /** 中学开始时间 */
    @Excel(name = "中学开始时间")
    private String middleStart;

    /** 中学结束时间 */
    @Excel(name = "中学结束时间")
    private String middleEnd;

    /** 高中开始时间 */
    @Excel(name = "高中开始时间")
    private String highStart;

    /** 高中结束时间 */
    @Excel(name = "高中结束时间")
    private String highEnd;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** 备注信息 */
    private String remarks;

    /** 删除标志 */
    @Excel(name = "删除标志")
    private String status;

    public void setConfigId(Long configId) 
    {
        this.configId = configId;
    }

    public Long getConfigId() 
    {
        return configId;
    }
    public void setConfigMonth(String configMonth) 
    {
        this.configMonth = configMonth;
    }

    public String getConfigMonth() 
    {
        return configMonth;
    }
    public void setPrimaryStart(String primaryStart) 
    {
        this.primaryStart = primaryStart;
    }

    public String getPrimaryStart() 
    {
        return primaryStart;
    }
    public void setPrimaryEnd(String primaryEnd) 
    {
        this.primaryEnd = primaryEnd;
    }

    public String getPrimaryEnd() 
    {
        return primaryEnd;
    }
    public void setMiddleStart(String middleStart) 
    {
        this.middleStart = middleStart;
    }

    public String getMiddleStart() 
    {
        return middleStart;
    }
    public void setMiddleEnd(String middleEnd) 
    {
        this.middleEnd = middleEnd;
    }

    public String getMiddleEnd() 
    {
        return middleEnd;
    }
    public void setHighStart(String highStart) 
    {
        this.highStart = highStart;
    }

    public String getHighStart() 
    {
        return highStart;
    }
    public void setHighEnd(String highEnd) 
    {
        this.highEnd = highEnd;
    }

    public String getHighEnd() 
    {
        return highEnd;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("configId", getConfigId())
            .append("configMonth", getConfigMonth())
            .append("primaryStart", getPrimaryStart())
            .append("primaryEnd", getPrimaryEnd())
            .append("middleStart", getMiddleStart())
            .append("middleEnd", getMiddleEnd())
            .append("highStart", getHighStart())
            .append("highEnd", getHighEnd())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("status", getStatus())
            .toString();
    }
}
