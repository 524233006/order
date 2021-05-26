package com.numberone.common.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Base64StrToImage {

	 
	 public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
	        if (imgStr == null) // 图像数据为空
	            return false;
	        Decoder decoder = Base64.getDecoder();
	        try {
	            // Base64解码
	            byte[] bytes = decoder.decode(imgStr);
	            for (int i = 0; i < bytes.length; ++i) {
	                if (bytes[i] < 0) {// 调整异常数据
	                    bytes[i] += 256;
	                }
	            }
	            // 生成图片
	            OutputStream out = new FileOutputStream(imgFilePath);
	            out.write(bytes);
	            out.flush();
	            out.close();
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
	

}
