package com.numberone.project.wx.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.github.wxpay.sdk.WXPayConfig;

/**
 * 配置我们自己的信息
 * @author csyh  
 *   参考  https://www.cnblogs.com/wang-yaz/p/8632624.html
 */



public class OurWxPayConfig implements WXPayConfig {
	 
    /** 加载证书  这里证书需要到微信商户平台进行下载*/
    private byte [] certData;

    public OurWxPayConfig() throws  Exception{
        InputStream certStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("cert/apiclient_cert.p12");
       this.certData = IOUtils.toByteArray(certStream);
        certStream.close();
    }

    /** 设置我们自己的appid
     * 商户号
     * 秘钥
     * */

    @Override
    public String getAppID() {
        return "wx9f5073e0b41cd9d9";
    }

    @Override
    public String getMchID() {
        return "1442179002";
    }

    @Override
    public String getKey() {
        return "o5mkvgi30vdhjdcsvsizuk1i4owjboqd";
    }

    @Override
    public InputStream getCertStream() {
        return new ByteArrayInputStream(this.certData);
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
