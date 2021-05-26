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
public class HlTianchengConfigVO 
{
    private String primary_name;
    private String middle_name;
    private String high_name;
    private long primary_type;
    private long middle_type;
    private long high_type;
    private long primary_start;
    private long primary_end;
    private long middle_start;
    private long middle_end;
    private long high_start;
    private long high_end;

    public String getPrimary_name() {
		return primary_name;
	}

	public void setPrimary_name(String primary_name) {
		this.primary_name = primary_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getHigh_name() {
		return high_name;
	}


	public void setHigh_name(String high_name) {
		this.high_name = high_name;
	}

	public long getPrimary_type() {
		return primary_type;
	}

	public void setPrimary_type(long primary_type) {
		this.primary_type = primary_type;
	}

	public long getMiddle_type() {
		return middle_type;
	}

	public void setMiddle_type(long middle_type) {
		this.middle_type = middle_type;
	}

	public long getHigh_type() {
		return high_type;
	}

	public void setHigh_type(long high_type) {
		this.high_type = high_type;
	}





	public long getPrimary_start() {
		return primary_start;
	}





	public void setPrimary_start(long primary_start) {
		this.primary_start = primary_start;
	}





	public long getPrimary_end() {
		return primary_end;
	}





	public void setPrimary_end(long primary_end) {
		this.primary_end = primary_end;
	}





	public long getMiddle_start() {
		return middle_start;
	}





	public void setMiddle_start(long middle_start) {
		this.middle_start = middle_start;
	}





	public long getMiddle_end() {
		return middle_end;
	}





	public void setMiddle_end(long middle_end) {
		this.middle_end = middle_end;
	}





	public long getHigh_start() {
		return high_start;
	}





	public void setHigh_start(long high_start) {
		this.high_start = high_start;
	}





	public long getHigh_end() {
		return high_end;
	}





	public void setHigh_end(long high_end) {
		this.high_end = high_end;
	}


}
