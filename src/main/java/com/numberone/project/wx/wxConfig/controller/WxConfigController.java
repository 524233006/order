package com.numberone.project.wx.wxConfig.controller;

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
import com.numberone.project.wx.wxConfig.domain.WxConfig;
import com.numberone.project.wx.wxConfig.service.IWxConfigService;

/**
 * 微信配置Controller
 * 
 * @author fhx
 * @date 2020-07-21
 */
@Controller
@RequestMapping("/wx/wxConfig")
public class WxConfigController extends BaseController
{
    private String prefix = "wx/wxConfig";

    @Autowired
    private IWxConfigService wxConfigService;

    @RequiresPermissions("wx:wxConfig:view")
    @GetMapping()
    public String wxConfig()
    {
        return prefix + "/wxConfig";
    }

    /**
     * 查询微信配置列表
     */
    @RequiresPermissions("wx:wxConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WxConfig wxConfig)
    {
        startPage();
        List<WxConfig> list = wxConfigService.selectWxConfigList(wxConfig);
        return getDataTable(list);
    }

    /**
     * 导出微信配置列表
     */
    @RequiresPermissions("wx:wxConfig:export")
    @Log(title = "微信配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WxConfig wxConfig)
    {
        List<WxConfig> list = wxConfigService.selectWxConfigList(wxConfig);
        ExcelUtil<WxConfig> util = new ExcelUtil<WxConfig>(WxConfig.class);
        return util.exportExcel(list, "wxConfig");
    }

    /**
     * 新增微信配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存微信配置
     */
    @RequiresPermissions("wx:wxConfig:add")
    @Log(title = "微信配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WxConfig wxConfig)
    {
        return toAjax(wxConfigService.insertWxConfig(wxConfig));
    }

    /**
     * 修改微信配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WxConfig wxConfig = wxConfigService.selectWxConfigById(id);
        mmap.put("wxConfig", wxConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存微信配置
     */
    @RequiresPermissions("wx:wxConfig:edit")
    @Log(title = "微信配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WxConfig wxConfig)
    {
        return toAjax(wxConfigService.updateWxConfig(wxConfig));
    }

    /**
     * 删除微信配置
     */
    @RequiresPermissions("wx:wxConfig:remove")
    @Log(title = "微信配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wxConfigService.deleteWxConfigByIds(ids));
    }
}
