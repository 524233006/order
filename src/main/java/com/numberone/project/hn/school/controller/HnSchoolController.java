package com.numberone.project.hn.school.controller;

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
import com.numberone.project.hn.grade.domain.HnGrade;
import com.numberone.project.hn.grade.service.IHnGradeService;
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.project.hn.school.service.IHnSchoolService;
import com.numberone.project.system.user.domain.User;
import com.numberone.project.system.user.domain.UserRole;

/**
 * 学校Controller
 * 
 * @author fhx
 * @date 2020-07-21
 */
@Controller
@RequestMapping("/hn/school")
public class HnSchoolController extends BaseController
{
    private String prefix = "hn/school";

    @Autowired 
    private IHnSchoolService hnSchoolService;
    @Autowired
    private IHnGradeService hnGradeService;

    @RequiresPermissions("hn:school:view")
    @GetMapping()
    public String school()
    {
        return prefix + "/school";
    }

    /**
     * 查询学校列表
     */
    @RequiresPermissions("hn:school:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HnSchool hnSchool)
    {
        startPage();
        List<HnSchool> list = hnSchoolService.selectHnSchoolList(hnSchool);
        return getDataTable(list);
    }

    /**
     * 导出学校列表
     */
    @RequiresPermissions("hn:school:export")
    @Log(title = "学校", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HnSchool hnSchool)
    {
        List<HnSchool> list = hnSchoolService.selectHnSchoolList(hnSchool);
        ExcelUtil<HnSchool> util = new ExcelUtil<HnSchool>(HnSchool.class);
        return util.exportExcel(list, "school");
    }

    /**
     * 新增学校
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学校
     */
    @RequiresPermissions("hn:school:add")
    @Log(title = "学校", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HnSchool hnSchool)
    {
        return toAjax(hnSchoolService.insertHnSchool(hnSchool));
    }

    /**
     * 修改学校
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,ModelMap mmap)
    {
        HnSchool hnSchool = hnSchoolService.selectHnSchoolById(id);
        mmap.put("hnSchool", hnSchool);
        return prefix + "/edit";
    }

    /**
     * 修改保存学校
     */
    @RequiresPermissions("hn:school:edit")
    @Log(title = "学校", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HnSchool hnSchool)
    {
        return toAjax(hnSchoolService.updateHnSchool(hnSchool));
    }
    
    
    
    /**
     * 年级List
     */
    @RequiresPermissions("hn:school:edit")
    @RequestMapping("/authGrade/{id}")
    public String authGrade(@PathVariable("id") Long id, ModelMap mmap)
    {
        mmap.put("school", hnSchoolService.selectHnSchoolById(id));
        return prefix + "/authGrade";
    }
    
    /**
     * 查询已分配年级列表
     */
    @RequiresPermissions("hn:school:edit")
    @PostMapping("/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(HnGrade hnGrade)
    {
        startPage();
        List<HnGrade> list = hnGradeService.selectHnGradeList(hnGrade);
        return getDataTable(list);
    }
    
    @GetMapping("/addGrade/{id}")
    public String addGrade(@PathVariable("id") Long id, ModelMap mmap)
    {
    	 mmap.put("school", hnSchoolService.selectHnSchoolById(id));
        return prefix + "/addGrade";
    }
    
   
    /**
     * 删除
     */
    @Log(title = "删除", businessType = BusinessType.GRANT)
    @PostMapping("/authChange")
    @ResponseBody
    public AjaxResult authChange(HnGrade hnGrade)
    {
        return toAjax(hnGradeService.deleteHnGradeById(hnGrade.getGradeId()));
    }
    /**
     * 删除学校
     */
    @RequiresPermissions("hn:school:remove")
    @Log(title = "学校", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hnSchoolService.deleteHnSchoolByIds(ids));
    }
    
    /**
     * 分配用户
     */
    @RequiresPermissions("hn:school:edit")
    @GetMapping("/authProduct/{id}")
    public String authProduct(@PathVariable("id") Long id, ModelMap mmap)
    {
        mmap.put("school", hnSchoolService.selectHnSchoolById(id));
        return prefix + "/authProduct";
    }
}
