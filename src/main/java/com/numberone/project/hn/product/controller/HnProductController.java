package com.numberone.project.hn.product.controller;

import java.io.IOException;
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

import com.numberone.common.utils.file.FileUploadUtils;
import com.numberone.common.utils.poi.ExcelUtil;
import com.numberone.framework.aspectj.lang.annotation.Log;
import com.numberone.framework.aspectj.lang.enums.BusinessType;
import com.numberone.framework.web.controller.BaseController;
import com.numberone.framework.web.domain.AjaxResult;
import com.numberone.framework.web.page.TableDataInfo;
import com.numberone.project.hn.product.domain.HnProduct;
import com.numberone.project.hn.product.service.IHnProductService;

/**
 * 产品Controller
 * 
 * @author fhx
 * @date 2020-07-28
 */
@Controller
@RequestMapping("/hn/product")
public class HnProductController extends BaseController
{
    private String prefix = "hn/product";

    @Autowired
    private IHnProductService hnProductService;

    @RequiresPermissions("hn:product:view")
    @GetMapping()
    public String product()
    {
        return prefix + "/product";
    }

    /**
     * 查询产品列表
     */
    @RequiresPermissions("hn:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HnProduct hnProduct)
    {
        startPage();
        List<HnProduct> list = hnProductService.selectHnProductList(hnProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品列表
     */
    @RequiresPermissions("hn:product:export")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HnProduct hnProduct)
    {
        List<HnProduct> list = hnProductService.selectHnProductList(hnProduct);
        ExcelUtil<HnProduct> util = new ExcelUtil<HnProduct>(HnProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 新增产品
     */
    @GetMapping("/add")
    public String add()
    {
    	
        return prefix + "/add";
    }

    /**
     * 新增保存产品
     */
    @RequiresPermissions("hn:product:add")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HnProduct hnProduct,MultipartFile file)
    {
    	try {
    		if(file!=null) {
    			String filaName = FileUploadUtils.upload(file);
    		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return toAjax(hnProductService.insertHnProduct(hnProduct));
    }

    /**
     * 修改产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HnProduct hnProduct = hnProductService.selectHnProductById(id);
        mmap.put("hnProduct", hnProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品
     */
    @RequiresPermissions("hn:product:edit")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HnProduct hnProduct)
    {
        return toAjax(hnProductService.updateHnProduct(hnProduct));
    }

    /**
     * 删除产品
     */
    @RequiresPermissions("hn:product:remove")
    @Log(title = "产品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hnProductService.deleteHnProductByIds(ids));
    }
}
