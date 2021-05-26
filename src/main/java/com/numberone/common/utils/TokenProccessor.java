package com.numberone.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class TokenProccessor {

    

    private TokenProccessor(){};

    private static final TokenProccessor instance = new TokenProccessor();

     

   public static TokenProccessor getInstance() {

       return instance;

   }

 

   /**

    * 生成Token

    * @return

    */

   public String makeToken() {

       String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
       try {
       MessageDigest md = MessageDigest.getInstance("md5");
       byte[] array = md.digest(token.getBytes("UTF-8"));
       StringBuilder sb = new StringBuilder();
       for (byte item : array) {
           sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
       }
       return sb.toString().toUpperCase();
       } catch (NoSuchAlgorithmException e) {
    	   
       }catch(UnsupportedEncodingException e) {
    	   
       }
       
     //   try {

         //  MessageDigest md = MessageDigest.getInstance("md5");

        //   byte md5[] =  md.digest(token.getBytes());
         

          // BASE64Encoder encoder = new BASE64Encoder();

          // return encoder.encode(md5);
         
           return token;
    //   } catch (NoSuchAlgorithmException e) {

           // TODO Auto-generated catch block

     //      e.printStackTrace();

    //   }

   //     return null;

   }

}
