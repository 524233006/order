package com.numberone.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UploadUtils {

	 public static String putb64(String imgStr) throws Exception {
		 
		 if (imgStr == null) // 图像数据为空
	           return "error";
		
		  String ACCESS_KEY = "9KwiS13UxERt_0jvHedJ3w2Aalw9tKwjISuW7Y9y";
		  String SECRET_KEY = "0gXSFa8u7sbSfEJhmIu_1kxLIc-36ZlBLTOEFc0F";
		  //要上传的空间
		    String bucketname = "putbase64";
		    //上传到七牛后保存的文件名
		   
		    String key = UUID.randomUUID().toString()+".png" ;
		  
		    //密钥配置
		  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

		     String token = auth.uploadToken(bucketname, key, 3600, null);
		 
		     FileInputStream fis = null;
		    
		     //要上传的空间(bucket)的存储区域为华南时
		      //Zone z = Zone.zone2();
		
		     try{
		      //文件大小
		    	
		    	
		    	  String url = "upload-z2.qiniup.com/putb64/" +key ;
		         //构造post对象
		         HttpPost post = new HttpPost(url);
					 post.addHeader("Content-Type", "application/octet-stream"); 
		         post.addHeader("Authorization", "UpToken " + token);
		         post.setEntity(new StringEntity(imgStr));

		         //请求与响应
		         HttpClient c = HttpClientBuilder.create().build();
		         HttpResponse res = c.execute(post);

		         //输出
		         System.out.println(res.getStatusLine());
		         String responseBody = EntityUtils.toString(res.getEntity(), "UTF-8");
		         System.out.println(responseBody);

		     }catch(Exception e){
		         e.printStackTrace();
		         throw e;
		     }finally{
		         if(fis != null){
		             fis.close();
		         }
		     }
		     return  "success";
		 }
	 
	 public static void putbase64(String imgStr) {
		 String accessKey = "9KwiS13UxERt_0jvHedJ3w2Aalw9tKwjISuW7Y9y";
		  String secretKey = "0gXSFa8u7sbSfEJhmIu_1kxLIc-36ZlBLTOEFc0F";
		  //要上传的空间
		    String bucketname = "putbase64";
		 Auth auth = Auth.create(accessKey, secretKey);
		 StringMap putPolicy = new StringMap();
		 putPolicy.put("callbackUrl", "http://csfuzheedu.com/api/uploadCallback");
		 putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
		 putPolicy.put("callbackBodyType", "application/json");
		 long expireSeconds = 3600;
		 String upToken = auth.uploadToken(bucketname, null, expireSeconds, putPolicy);
		 System.out.println(upToken);
		 String key = UUID.randomUUID().toString()+".png" ;
		 String url = "http://up-z2.qiniup.com/putbase64/" +key ;
         //构造post对象
         HttpPost post = new HttpPost(url);
			 post.addHeader("Content-Type", "application/octet-stream"); 
         post.addHeader("Authorization", "UpToken " + upToken);
         try {
			post.setEntity(new StringEntity(imgStr));
			//请求与响应
			HttpClient c = HttpClientBuilder.create().build();
			HttpResponse res;
			
				res = c.execute(post);
				//输出
				System.out.println(res.getStatusLine());
				String responseBody = EntityUtils.toString(res.getEntity(), "UTF-8");
				System.out.println(responseBody);
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 }
	 /**
	  * 
	  * @param file 文件路径
	  * @param key  
	  */
	 
	 public static void put64image(String file,String key){
		
		  String accessKey = "9KwiS13UxERt_0jvHedJ3w2Aalw9tKwjISuW7Y9y";
		  String secretKey = "0gXSFa8u7sbSfEJhmIu_1kxLIc-36ZlBLTOEFc0F";
		  //要上传的空间
		  String bucketname = "putbase64";
		  Auth auth = Auth.create(accessKey, secretKey);
	        FileInputStream fis = null;        
	        int l = (int) (new File(file).length());        
	        byte[] src = new byte[l];
	        try {
				fis = new FileInputStream(new File(file));
				fis.read(src);
			    String file64 = Base64.encodeToString(src, 0);//up-z2.qiniup.com   upload-z2.qiniup.com
			    String url = "http://upload-z2.qiniup.com/putb64/" + l+"/key/"+ UrlSafeBase64.encodeToString(key);   
			    //非华东空间需要根据注意事项 1 修改上传域名
			    String upToken = auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
			    RequestBody rb = RequestBody.create(null, file64);
			    Request request = new Request.Builder().
			                url(url).
			                addHeader("Content-Type", "application/octet-stream")
			                .addHeader("Authorization", "UpToken " +upToken)
			                .post(rb).build();
			   System.out.println(request.headers());
			   OkHttpClient client = new OkHttpClient();
			   okhttp3.Response response = client.newCall(request).execute();
			   System.out.println(response); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
		         if(fis != null){
		             try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         }
		     }
	       
	    }  
	 
	
   


}
