package com.numberone;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.numberone.common.constant.Constants;
import com.numberone.common.utils.Base64StrToImage;
import com.numberone.common.utils.DateUtils;
import com.numberone.common.utils.QRCodeUtilEx;
import com.numberone.common.utils.StringUtils;
import com.numberone.common.utils.ZxingHandler;
import com.numberone.framework.config.NumberOneConfig;
import com.numberone.framework.config.ServerConfig;
import com.numberone.project.hn.product.domain.HnProduct;
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.project.hn.schoolProduct.domain.HnSchoolProduct;

public class Test {
	
	 

	public static void main(String[] args) {
		 try {
			
			 String filePath = "D:/numberone/uploadPath/upload";
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
	         
	         
	        
	            int dirLastIndex = "D:/numberone/uploadPath".length() + 1;
	            String currentDir = StringUtils.substring(filePath, dirLastIndex);
	            String pathFileName = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
	         
	           
	        	
	        	//定义上传文件存放的路径
	       //    String path = request.getSession().getServletContext().getRealPath("/uploadFile/");//此处为tomcat下的路径，服务重启路径会变化
	       //   System.out.println(path);
	         // 定义文件在上传路径中的文件夹名称
	      //     File folder = new File(path );
	           //检测folder是否是文件夹，不是就创建
	      //     if (!folder.isDirectory()) {
	      //         folder.mkdirs();
	      //     }
	         
	       	 // 二维码
	     //  	 String fileName = UUID.randomUUID().toString()+".png" ;
	   	//	String imgPath2 =path +folder.separator+fileName;
	   		//String contents2 = "http://csfuzheedu.com/app/info?shoolProductId="+hnSchoolProduct.getId();
	   		 String contents2 = "http://csfuzheedu.com/api/wxLogin?schoolProductId="+1;
	   		int width2 = 300, height2 = 300;
	   	//	 String filePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + "/uploadFile/" + fileName;   
	   	//	ZxingHandler.encode2(contents2, width2, height2, imgPath2);
	   		
	   		//ZxingHandler.encode2(contents2, width2, height2, filePath + File.separator + fileName);
	   		
		
	   		
			 
	          String  str= QRCodeUtilEx.encodeStr(contents2,"学校名");
	          Base64StrToImage.GenerateImage(str, filePath + File.separator + fileName);
	          System.out.println(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	

    String msg= "Type is";
  
    /*public static void main(String[] args) {
        Long l1 = new Long(7);
        Long l2= new Long(7);
        if(l1==l2)
            System.out.println(1);
        else System.out.println("noo");
    }*/
    public void showType(int n){
 // String imgPath2 = "target\\zxing.png";
        
    //    HnSchoolProduct hnSchoolProduct = hnSchoolProductService.selectHnSchoolProductById(id);
		/*
		 * if(hnSchoolProduct.getUrl()==null) { String
		 * imgPath2="E:\\upload\\"+UUID.randomUUID().toString()+".png"; String contents2
		 * ="http://csfuzheedu.com"; int width2 = 300, height2 = 300;
		 * ZxingHandler.encode2(contents2, width2, height2, imgPath2);
		 * hnSchoolProduct.setUrl(imgPath2); }
		 */
        
      
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
          
           
        	
        	//定义上传文件存放的路径
       //    String path = request.getSession().getServletContext().getRealPath("/uploadFile/");//此处为tomcat下的路径，服务重启路径会变化
       //   System.out.println(path);
         // 定义文件在上传路径中的文件夹名称
      //     File folder = new File(path );
           //检测folder是否是文件夹，不是就创建
      //     if (!folder.isDirectory()) {
      //         folder.mkdirs();
      //     }
         
       	 // 二维码
     //  	 String fileName = UUID.randomUUID().toString()+".png" ;
   	//	String imgPath2 =path +folder.separator+fileName;
   		//String contents2 = "http://csfuzheedu.com/app/info?shoolProductId="+hnSchoolProduct.getId();
   		 String contents2 = "http://csfuzheedu.com/api/wxLogin?schoolProductId="+1;
   		int width2 = 300, height2 = 300;
   	//	 String filePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + "/uploadFile/" + fileName;   
   	//	ZxingHandler.encode2(contents2, width2, height2, imgPath2);
	try {
		String	imgstr = QRCodeUtilEx.encodeStr(contents2,"底部文字");
		 Base64StrToImage.GenerateImage(imgstr, filePath + File.separator + fileName);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
   	//	ZxingHandler.encode2(contents2, width2, height2, filePath + File.separator + fileName);
   	
      
    }
}
