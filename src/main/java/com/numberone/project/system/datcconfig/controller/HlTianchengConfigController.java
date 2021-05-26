package com.numberone.project.system.datcconfig.controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.numberone.common.utils.poi.ExcelUtil;
import com.numberone.framework.aspectj.lang.annotation.Log;
import com.numberone.framework.aspectj.lang.enums.BusinessType;
import com.numberone.framework.web.controller.BaseController;
import com.numberone.framework.web.domain.AjaxResult;
import com.numberone.framework.web.page.TableDataInfo;
import com.numberone.project.system.datcconfig.domain.HlTianchengConfig;
import com.numberone.project.system.datcconfig.service.IHlTianchengConfigService;

/**
 * 月假时间设置Controller
 * 
 * @author fhx
 * @date 2020-06-19
 */
@Controller
@RequestMapping("/system/datcconfig")
public class HlTianchengConfigController extends BaseController
{
    private String prefix = "system/datcconfig";

    @Autowired
    private IHlTianchengConfigService hlTianchengConfigService;

    @RequiresPermissions("system:datcconfig:view")
    @GetMapping()
    public String datcconfig()
    {
        return prefix + "/datcconfig";
    }

    /**
     * 查询月假时间设置列表
     */
    @RequiresPermissions("system:datcconfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HlTianchengConfig hlTianchengConfig)
    {
        startPage();
        List<HlTianchengConfig> list = hlTianchengConfigService.selectHlTianchengConfigList(hlTianchengConfig);
        return getDataTable(list);
    }

    /**
     * 导出月假时间设置列表
     */
    @RequiresPermissions("system:datcconfig:export")
    @Log(title = "月假时间设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HlTianchengConfig hlTianchengConfig)
    {
        List<HlTianchengConfig> list = hlTianchengConfigService.selectHlTianchengConfigList(hlTianchengConfig);
        ExcelUtil<HlTianchengConfig> util = new ExcelUtil<HlTianchengConfig>(HlTianchengConfig.class);
        return util.exportExcel(list, "datcconfig");
    }

    /**
     * 新增月假时间设置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存月假时间设置
     */
    @RequiresPermissions("system:datcconfig:add")
    @Log(title = "月假时间设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HlTianchengConfig hlTianchengConfig)
    {


	        try {
				long pristart = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getPrimaryStart(), new ParsePosition(0)).getTime()/1000;
     
				long priend = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getPrimaryEnd(), new ParsePosition(0)).getTime()/1000;
				if(pristart>priend) {
					return error("小学时间设置开始时间不能小于结束时间");
				} 
				long midstart = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getMiddleStart(), new ParsePosition(0)).getTime()/1000;
				  
				long midend = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getMiddleEnd(), new ParsePosition(0)).getTime()/1000;
				if(midstart>midend) {
					return error("初中时间设置开始时间不能小于结束时间");
				}
				long histart = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getHighStart(), new ParsePosition(0)).getTime()/1000;
				  
				long hiend = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getHighEnd(), new ParsePosition(0)).getTime()/1000;
				if(histart>hiend) {
					return error("高中时间设置开始时间不能小于结束时间");
				}
			} catch (Exception e) {
				return error("时间格式设置有误");
				
			}
	        return toAjax(hlTianchengConfigService.insertHlTianchengConfig(hlTianchengConfig));
    }

    /**
     * 修改月假时间设置
     */
    @GetMapping("/edit/{configId}")
    public String edit(@PathVariable("configId") Long configId, ModelMap mmap)
    {
        HlTianchengConfig hlTianchengConfig = hlTianchengConfigService.selectHlTianchengConfigById(configId);
        mmap.put("hlTianchengConfig", hlTianchengConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存月假时间设置
     */
    @RequiresPermissions("system:datcconfig:edit")
    @Log(title = "月假时间设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HlTianchengConfig hlTianchengConfig)
    {
    	 try {
				long pristart = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getPrimaryStart(), new ParsePosition(0)).getTime()/1000;
  
				long priend = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getPrimaryEnd(), new ParsePosition(0)).getTime()/1000;
				if(pristart>priend) {
					return error("小学时间设置开始时间不能小于结束时间");
				}
				long midstart = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getMiddleStart(), new ParsePosition(0)).getTime()/1000;
				  
				long midend = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getMiddleEnd(), new ParsePosition(0)).getTime()/1000;
				if(midstart>midend) {
					return error("初中时间设置开始时间不能小于结束时间");
				}
				long histart = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getHighStart(), new ParsePosition(0)).getTime()/1000;
				  
				long hiend = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(hlTianchengConfig.getHighEnd(), new ParsePosition(0)).getTime()/1000;
				if(histart>hiend) {
					return error("高中时间设置开始时间不能小于结束时间");
				}
			} catch (Exception e) {
				return error("时间格式设置有误");
				
			}
    	
        return toAjax(hlTianchengConfigService.updateHlTianchengConfig(hlTianchengConfig));
    }

    /**
     * 删除月假时间设置
     */
    @RequiresPermissions("system:datcconfig:remove")
    @Log(title = "月假时间设置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hlTianchengConfigService.deleteHlTianchengConfigByIds(ids));
    }
}
