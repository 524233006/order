package com.numberone.project.wx.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class UploadController {

	
	@PostMapping("/upload")
    public void uploadPhoto(@RequestParam("uploadFile")MultipartFile uploadFile, @RequestParam("aiIdCard")String aiIdCard, HttpServletRequest request) {
        //定义上传文件存放的路径
        String path = request.getSession().getServletContext().getRealPath("/uploadFile/");//此处为tomcat下的路径，服务重启路径会变化
       System.out.println(path);
      // 定义文件在上传路径中的文件夹名称
        File folder = new File(path );
        //检测folder是否是文件夹，不是就创建
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        //获取文件的原始名称
        String oldName = uploadFile.getOriginalFilename();
        oldName.substring(oldName.lastIndexOf(".")) ; // 获取文件的后缀名
        //生成新的文件名（下面根据自己需要决定是否使用）
        String newName ="定义新名字" + oldName.substring(oldName.lastIndexOf("."));
        //文件保存操作
        try {
			uploadFile.transferTo(new File(folder, oldName));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        //返回保存的url，根据url可以进行文件查看或者下载
        String filePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + "/uploadFile/" +"/"+ oldName;   
        //           　String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
      }
}
