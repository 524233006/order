package com.numberone.project.hn.schoolProduct.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.numberone.common.constant.Constants;
import com.numberone.common.utils.Base64StrToImage;
import com.numberone.common.utils.DateUtils;
import com.numberone.common.utils.QRCodeUtilEx;
import com.numberone.common.utils.StringUtils;
import com.numberone.common.utils.poi.ExcelUtil;
import com.numberone.framework.aspectj.lang.annotation.Log;
import com.numberone.framework.aspectj.lang.enums.BusinessType;
import com.numberone.framework.config.NumberOneConfig;
import com.numberone.framework.config.ServerConfig;
import com.numberone.framework.web.controller.BaseController;
import com.numberone.framework.web.domain.AjaxResult;
import com.numberone.framework.web.page.TableDataInfo;
import com.numberone.project.hn.product.domain.HnProduct;
import com.numberone.project.hn.product.service.IHnProductService;
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.project.hn.school.service.IHnSchoolService;
import com.numberone.project.hn.schoolProduct.domain.HnSchoolProduct;
import com.numberone.project.hn.schoolProduct.service.IHnSchoolProductService;

/**
 * 学校产品Controller
 * 
 * @author fhx
 * @date 2020-07-29
 */
@Controller
@RequestMapping("/hn/schoolProduct")
public class HnSchoolProductController extends BaseController
{
    private String prefix = "hn/schoolProduct";

    @Autowired
    private IHnSchoolProductService hnSchoolProductService;
    @Autowired
    private IHnSchoolService hnSchoolService;
    @Autowired
    private IHnProductService hnProductService;
    @Autowired
    private ServerConfig serverConfig;

    @RequiresPermissions("hn:schoolProduct:view")
    @GetMapping()
    public String schoolProduct()
    {
        return prefix + "/schoolProduct";
    }

    /**
     * 查询学校产品列表
     */
    @RequiresPermissions("hn:schoolProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HnSchoolProduct hnSchoolProduct)
    {
        startPage();
      
        List<HnSchoolProduct> list = hnSchoolProductService.selectHnSchoolProductList(hnSchoolProduct);
        return getDataTable(list);
    }

    /**
     * 导出学校产品列表
     */
    @RequiresPermissions("hn:schoolProduct:export")
    @Log(title = "学校产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HnSchoolProduct hnSchoolProduct)
    {
        List<HnSchoolProduct> list = hnSchoolProductService.selectHnSchoolProductList(hnSchoolProduct);
        ExcelUtil<HnSchoolProduct> util = new ExcelUtil<HnSchoolProduct>(HnSchoolProduct.class);
        return util.exportExcel(list, "schoolProduct");
    }

    /**
     * 新增学校产品
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	  mmap.put("schools", hnSchoolService.selectHnSchoolList(new HnSchool()));
    	  mmap.put("products", hnProductService.selectHnProductList(new HnProduct()));
    	  
        return prefix + "/add";
    }

    /**
     * 新增保存学校产品
     */
    @RequiresPermissions("hn:schoolProduct:add")
    @Log(title = "学校产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HnSchoolProduct hnSchoolProduct)
    {
        return toAjax(hnSchoolProductService.insertHnSchoolProduct(hnSchoolProduct));
    }

    /**
     * 修改学校产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, HttpServletRequest request,ModelMap mmap)
    {
        HnSchoolProduct hnSchoolProduct = hnSchoolProductService.selectHnSchoolProductById(id);
        if(hnSchoolProduct.getUrl()==null||"".equals(hnSchoolProduct.getUrl())) {
        	 // 上传文件路径
            String filePath = NumberOneConfig.getUploadPath();
            String fileName = DateUtils.datePath() + "/"+ UUID.randomUUID().toString()+".png" ;
            File desc = new File(filePath + File.separator + fileName);

            if (!desc.getParentFile().exists())
            {
                desc.getParentFile().mkdirs();
            }
            if (!desc.exists())
            {
                try {
					desc.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            // 上传并返回新文件名称
            int dirLastIndex = NumberOneConfig.getProfile().length() + 1;
            String currentDir = StringUtils.substring(filePath, dirLastIndex);
            String pathFileName = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
            String url = serverConfig.getUrl() + pathFileName;
   		 String contents2 = "http://csfuzheedu.com/api/wxLogin?schoolProductId="+hnSchoolProduct.getId();
	try {
		//生成base64的图片字节码
		 String imgstr = QRCodeUtilEx.encodeStr(contents2,hnSchoolProduct.getHnSchool().getSchoolName());
		 
		if( Base64StrToImage.GenerateImage(imgstr, filePath + File.separator + fileName)) {
			hnSchoolProduct.setUrl(url);
	   		hnSchoolProductService.updateHnSchoolProduct(hnSchoolProduct);
		}
		
		//QRcode.writeToFile(contents2, "png", desc);
	//	QRcode.pressText(hnSchoolProduct.getHnSchool().getSchoolName(), desc, 5, Color.black, 14);
		//hnSchoolProduct.setUrl(url);
	//	hnSchoolProductService.updateHnSchoolProduct(hnSchoolProduct);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
   		
       }
        mmap.put("schools", hnSchoolService.selectHnSchoolList(new HnSchool()));
  	  mmap.put("products", hnProductService.selectHnProductList(new HnProduct()));
        mmap.put("hnSchoolProduct", hnSchoolProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存学校产品
     */
    @RequiresPermissions("hn:schoolProduct:edit")
    @Log(title = "学校产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HnSchoolProduct hnSchoolProduct)
    {
        return toAjax(hnSchoolProductService.updateHnSchoolProduct(hnSchoolProduct));
    }

    /**
     * 删除学校产品
     */
    @RequiresPermissions("hn:schoolProduct:remove")
    @Log(title = "学校产品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hnSchoolProductService.deleteHnSchoolProductByIds(ids));
    }
}
