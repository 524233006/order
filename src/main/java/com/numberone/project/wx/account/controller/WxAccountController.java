package com.numberone.project.wx.account.controller;

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
import com.numberone.project.wx.account.domain.WxAccount;
import com.numberone.project.wx.account.service.IWxAccountService;

/**
 * 粉丝Controller
 * 
 * @author fhx
 * @date 2020-07-22
 */
@Controller
@RequestMapping("/wx/account")
public class WxAccountController extends BaseController
{
    private String prefix = "wx/account";

    @Autowired
    private IWxAccountService wxAccountService;

    @RequiresPermissions("wx:account:view")
    @GetMapping()
    public String account()
    {
        return prefix + "/account";
    }

    /**
     * 查询粉丝列表
     */
    @RequiresPermissions("wx:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WxAccount wxAccount)
    {
        startPage();
        List<WxAccount> list = wxAccountService.selectWxAccountList(wxAccount);
        return getDataTable(list);
    }

    /**
     * 导出粉丝列表
     */
    @RequiresPermissions("wx:account:export")
    @Log(title = "粉丝", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WxAccount wxAccount)
    {
        List<WxAccount> list = wxAccountService.selectWxAccountList(wxAccount);
        ExcelUtil<WxAccount> util = new ExcelUtil<WxAccount>(WxAccount.class);
        return util.exportExcel(list, "account");
    }

    /**
     * 新增粉丝
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存粉丝
     */
    @RequiresPermissions("wx:account:add")
    @Log(title = "粉丝", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WxAccount wxAccount)
    {
        return toAjax(wxAccountService.insertWxAccount(wxAccount));
    }

    /**
     * 修改粉丝
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WxAccount wxAccount = wxAccountService.selectWxAccountById(id);
        mmap.put("wxAccount", wxAccount);
        return prefix + "/edit";
    }

    /**
     * 修改保存粉丝
     */
    @RequiresPermissions("wx:account:edit")
    @Log(title = "粉丝", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WxAccount wxAccount)
    {
        return toAjax(wxAccountService.updateWxAccount(wxAccount));
    }

    /**
     * 删除粉丝
     */
    @RequiresPermissions("wx:account:remove")
    @Log(title = "粉丝", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wxAccountService.deleteWxAccountByIds(ids));
    }
}
