package com.numberone.common.utils;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @author csyh  内置tomcat 时 特殊字符处理（做移动接口对接出现md5加密字符串）
 *
 */
/*
 * @Configuration public class TomcatConfig {
 * 
 * @Bean public ConfigurableServletWebServerFactory webServerFactory() {
 * TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
 * factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
 * //允许的特殊字符 connector.setProperty("relaxedQueryChars", "|{}[]-."); }); return
 * factory; } }
 */
