package com.numberone.project.hn.classes.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.numberone.common.exception.BusinessException;
import com.numberone.common.utils.StringUtils;
import com.numberone.common.utils.poi.ExcelUtil;
import com.numberone.common.utils.security.ShiroUtils;
import com.numberone.framework.aspectj.lang.annotation.Log;
import com.numberone.framework.aspectj.lang.enums.BusinessType;
import com.numberone.framework.web.controller.BaseController;
import com.numberone.framework.web.domain.AjaxResult;
import com.numberone.framework.web.page.TableDataInfo;
import com.numberone.project.hn.classes.domain.HnClasses;
import com.numberone.project.hn.classes.service.IHnClassesService;
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.project.hn.school.service.IHnSchoolService;
import com.numberone.project.system.user.domain.User;

/**
 * 班级Controller
 * 
 * @author fhx
 * @date 2020-07-28
 */
@Controller
@RequestMapping("/hn/classes")
public class HnClassesController extends BaseController
{
    private String prefix = "hn/classes";

    @Autowired
    private IHnClassesService hnClassesService;
    @Autowired
    private IHnSchoolService hnSchoolService;

    @RequiresPermissions("hn:classes:view")
    @GetMapping()
    public String classes(ModelMap mmap)
    {
    	 mmap.put("schools", hnSchoolService.selectHnSchoolList(new HnSchool()));
        return prefix + "/classes";
    }

    /**
     * 查询班级列表
     */
    @RequiresPermissions("hn:classes:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HnClasses hnClasses)
    {
        startPage();
        List<HnClasses> list = hnClassesService.selectHnClassesList(hnClasses);
        return getDataTable(list);
    }

    /**
     * 导出班级列表
     */
    @RequiresPermissions("hn:classes:export")
    @Log(title = "班级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HnClasses hnClasses)
    {
        List<HnClasses> list = hnClassesService.selectHnClassesList(hnClasses);
        ExcelUtil<HnClasses> util = new ExcelUtil<HnClasses>(HnClasses.class);
        return util.exportExcel(list, "classes");
    }
    
    
    @Log(title = "班级管理", businessType = BusinessType.IMPORT)
    //@RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<HnClasses> util = new ExcelUtil<HnClasses>(HnClasses.class);
        List<HnClasses> classesList = util.importExcel(file.getInputStream());
        String message = hnClassesService.importClasses(classesList, updateSupport);
        return AjaxResult.success(message);
    }
    
    
    @RequiresPermissions("hn:classes:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<HnClasses> util = new ExcelUtil<HnClasses>(HnClasses.class);
        return util.importTemplateExcel("班级数据");
    }

    /**
     * 新增班级
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	  mmap.put("schools", hnSchoolService.selectHnSchoolList(new HnSchool()));
        return prefix + "/add";
    }

    /**
     * 新增保存班级
     */
    @RequiresPermissions("hn:classes:add")
    @Log(title = "班级", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HnClasses hnClasses)
    {
        return toAjax(hnClassesService.insertHnClasses(hnClasses));
    }

    /**
     * 修改班级
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HnClasses hnClasses = hnClassesService.selectHnClassesById(id);
        mmap.put("hnClasses", hnClasses);
        return prefix + "/edit";
    }

    /**
     * 修改保存班级
     */
    @RequiresPermissions("hn:classes:edit")
    @Log(title = "班级", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HnClasses hnClasses)
    {
        return toAjax(hnClassesService.updateHnClasses(hnClasses));
    }

    /**
     * 删除班级
     */
    @RequiresPermissions("hn:classes:remove")
    @Log(title = "班级", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hnClassesService.deleteHnClassesByIds(ids));
    }
    

}
