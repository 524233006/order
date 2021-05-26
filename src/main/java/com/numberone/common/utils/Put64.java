package com.numberone.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import com.qiniu.util.*;
import okhttp3.*;
public class Put64 {
   
  
     
    public static void put64image(String imgstr) throws Exception {
       // String file = "D:\\Documents\\Pictures\\1.png";//图片路径
      //  FileInputStream fis = null;        
      //  int l = (int) (new File(file).length());        
    //    byte[] src = new byte[l];
    //    fis = new FileInputStream(new File(file));
    //    fis.read(src);
    //    String file64 = Base64.encodeToString(src, 0);
    	
    	 String ak = "9KwiS13UxERt_0jvHedJ3w2Aalw9tKwjISuW7Y9y";
    	    String sk = "0gXSFa8u7sbSfEJhmIu_1kxLIc-36ZlBLTOEFc0F";    // 密钥配置
    	    Auth auth = Auth.create(ak, sk);    // TODO Auto-generated constructor stub 
    	    String bucketname = "putbase64";    //空间名
    	    String token =    auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
		    
    	  String key = UUID.randomUUID().toString()+".png";    //上传的图片名
        String url = "http://up-z2.qiniup.com/putbase64/key/"+ UrlSafeBase64.encodeToString(key);      
   //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, imgstr);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + token)
                .post(rb).build();
        System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
    }   
    
}