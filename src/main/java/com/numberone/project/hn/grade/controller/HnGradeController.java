package com.numberone.project.hn.grade.controller;

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

/**
 * 年级Controller
 * 
 * @author fhx
 * @date 2020-10-10
 */
@Controller
@RequestMapping("/hn/grade")
public class HnGradeController extends BaseController
{
    private String prefix = "hn/grade";

    @Autowired
    private IHnGradeService hnGradeService;

    @RequiresPermissions("hn:grade:view")
    @GetMapping()
    public String grade()
    {
        return prefix + "/grade";
    }

    /**
     * 查询年级列表
     */
    @RequiresPermissions("hn:grade:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HnGrade hnGrade)
    {
        startPage();
        List<HnGrade> list = hnGradeService.selectHnGradeList(hnGrade);
        return getDataTable(list);
    }

    /**
     * 导出年级列表
     */
    @RequiresPermissions("hn:grade:export")
    @Log(title = "年级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HnGrade hnGrade)
    {
        List<HnGrade> list = hnGradeService.selectHnGradeList(hnGrade);
        ExcelUtil<HnGrade> util = new ExcelUtil<HnGrade>(HnGrade.class);
        return util.exportExcel(list, "grade");
    }

    /**
     * 新增年级
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存年级
     */
    @RequiresPermissions("hn:grade:add")
    @Log(title = "年级", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HnGrade hnGrade)
    {
        return toAjax(hnGradeService.insertHnGrade(hnGrade));
    }

    /**
     * 修改年级
     */
    @GetMapping("/edit/{gradeId}")
    public String edit(@PathVariable("gradeId") Long gradeId, ModelMap mmap)
    {
        HnGrade hnGrade = hnGradeService.selectHnGradeById(gradeId);
        mmap.put("hnGrade", hnGrade);
        return prefix + "/edit";
    }

    /**
     * 修改保存年级
     */
    @RequiresPermissions("hn:grade:edit")
    @Log(title = "年级", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HnGrade hnGrade)
    {
        return toAjax(hnGradeService.updateHnGrade(hnGrade));
    }

    /**
     * 删除年级
     */
    @RequiresPermissions("hn:grade:remove")
    @Log(title = "年级", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hnGradeService.deleteHnGradeByIds(ids));
    }
}
