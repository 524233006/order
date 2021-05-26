package com.numberone.project.hn.student.controller;

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
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.project.hn.school.service.IHnSchoolService;
import com.numberone.project.hn.student.domain.HnStudent;
import com.numberone.project.hn.student.service.IHnStudentService;

/**
 * 学生Controller
 * 
 * @author fhx
 * @date 2020-07-29
 */
@Controller
@RequestMapping("/hn/student")
public class HnStudentController extends BaseController
{
    private String prefix = "hn/student";

    @Autowired
    private IHnStudentService hnStudentService;
    
    @Autowired 
    private IHnSchoolService hnSchoolService;

    @RequiresPermissions("hn:student:view")
    @GetMapping()
    public String student(ModelMap mmap)
    {
    	List<HnSchool> schoolList = hnSchoolService.selectHnSchoolList(new HnSchool());
        mmap.put("schoolList", schoolList);
        return prefix + "/student";
    }

    /**
     * 查询学生列表
     */
    @RequiresPermissions("hn:student:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HnStudent hnStudent)
    {
        startPage();
     
        List<HnStudent> list = hnStudentService.selectHnStudentList(hnStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生列表
     */
    @RequiresPermissions("hn:student:export")
    @Log(title = "学生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HnStudent hnStudent)
    {
        List<HnStudent> list = hnStudentService.selectHnStudentList(hnStudent);
        ExcelUtil<HnStudent> util = new ExcelUtil<HnStudent>(HnStudent.class);
        return util.exportExcel(list, "student");
    }

    /**
     * 新增学生
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生
     */
    @RequiresPermissions("hn:student:add")
    @Log(title = "学生", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HnStudent hnStudent)
    {
        return toAjax(hnStudentService.insertHnStudent(hnStudent));
    }

    /**
     * 修改学生
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HnStudent hnStudent = hnStudentService.selectHnStudentById(id);
        mmap.put("hnStudent", hnStudent);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生
     */
    @RequiresPermissions("hn:student:edit")
    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HnStudent hnStudent)
    {
        return toAjax(hnStudentService.updateHnStudent(hnStudent));
    }

    /**
     * 删除学生
     */
    @RequiresPermissions("hn:student:remove")
    @Log(title = "学生", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hnStudentService.deleteHnStudentByIds(ids));
    }
}
