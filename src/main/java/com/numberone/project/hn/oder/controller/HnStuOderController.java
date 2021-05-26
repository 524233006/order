package com.numberone.project.hn.oder.controller;

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
import com.numberone.project.hn.classes.service.IHnClassesService;
import com.numberone.project.hn.oder.domain.HnStuOder;
import com.numberone.project.hn.oder.service.IHnStuOderService;
import com.numberone.project.hn.product.service.IHnProductService;
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.project.hn.school.service.IHnSchoolService;

/**
 * 订单Controller
 * 
 * @author fhx
 * @date 2020-07-29
 */
@Controller
@RequestMapping("/hn/oder")
public class HnStuOderController extends BaseController
{
    private String prefix = "hn/oder";

    @Autowired
    private IHnStuOderService hnStuOderService;
    @Autowired 
    private IHnSchoolService hnSchoolService;
    @Autowired
    private IHnClassesService hnClassesService;
   
    @Autowired
    private IHnProductService hnProductService;
    
    @RequiresPermissions("hn:oder:view")
    @GetMapping()
    public String oder(ModelMap mmap)
    {
    	List<HnSchool> schoolList = hnSchoolService.selectHnSchoolList(new HnSchool());
    	mmap.put("schoolList", schoolList);
    	return prefix + "/oder";
    }

    /**
     * 查询订单列表
     */
    @RequiresPermissions("hn:oder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HnStuOder hnStuOder)
    {
        startPage();
        List<HnStuOder> list = hnStuOderService.selectHnStuOderList(hnStuOder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @RequiresPermissions("hn:oder:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HnStuOder hnStuOder)
    {
        List<HnStuOder> list = hnStuOderService.selectHnStuOderList(hnStuOder);
        ExcelUtil<HnStuOder> util = new ExcelUtil<HnStuOder>(HnStuOder.class);
        return util.exportExcel(list, "oder");
    }

    /**
     * 新增订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单
     */
    @RequiresPermissions("hn:oder:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HnStuOder hnStuOder)
    {
        return toAjax(hnStuOderService.insertHnStuOder(hnStuOder));
    }

    /**
     * 修改订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HnStuOder hnStuOder = hnStuOderService.selectHnStuOderById(id);
        mmap.put("hnStuOder", hnStuOder);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单
     */
    @RequiresPermissions("hn:oder:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HnStuOder hnStuOder)
    {
        return toAjax(hnStuOderService.updateHnStuOderNoTime(hnStuOder));
    }

    /**
     * 删除订单
     */
    @RequiresPermissions("hn:oder:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hnStuOderService.deleteHnStuOderByIds(ids));
    }
}
