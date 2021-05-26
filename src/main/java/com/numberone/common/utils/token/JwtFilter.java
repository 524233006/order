/*
 * package com.numberone.common.utils.token;
 * 
 * 
 * 
 * 
 * import java.io.IOException; import java.io.PrintWriter;
 * 
 * import javax.servlet.Filter; import javax.servlet.FilterChain; import
 * javax.servlet.FilterConfig; import javax.servlet.ServletRequest; import
 * javax.servlet.ServletResponse; import javax.servlet.http.HttpServletRequest;
 * import javax.servlet.http.HttpServletResponse;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.stereotype.Component;
 * 
 * 
 *//**
	 * 创建jwt过滤器：
	 * 
	 * @author csyh
	 *
	 */
/*
 * 
 * 
 * @Component public class JwtFilter implements Filter {
 * 
 *//**
	 * 排除拦截的请求
	 */
/*
 * private String[] excludedPages = {"/login"};
 * 
 * private static Logger logger = LoggerFactory.getLogger(JwtFilter.class);
 * private JwtUtil jwtUtil = new JwtUtil();
 * 
 * @Override public void doFilter(ServletRequest request, ServletResponse
 * response, FilterChain chain) { HttpServletRequest httpRequest =
 * (HttpServletRequest) request; HttpServletResponse httpResponse =
 * (HttpServletResponse) response; try { for (String page : excludedPages) { if
 * (httpRequest.getRequestURI().equals(page)) { chain.doFilter(httpRequest,
 * httpResponse); return; } } //Constants.TOKEN_HEADER String token =
 * httpRequest.getHeader("Constants.TOKEN_HEADER"); TokenStatus tokenStatus =
 * jwtUtil.verifyToken(token); switch (tokenStatus) { //有效 case VALID: String
 * user = jwtUtil.getUserNameFromToken(token); //Constants.LOGIN_USER
 * httpRequest.setAttribute("Constants.LOGIN_USER", user);
 * chain.doFilter(httpRequest, httpResponse); break; //无效 case INVALID:
 * accessDeny(httpResponse, 1); break; //过期 case EXPIRED:
 * accessDeny(httpResponse, 2); break; default: accessDeny(httpResponse, 1);
 * break; } } catch (Exception e) { logger.error("jwtFilter Exception: {}",
 * e.getMessage()); accessDeny(httpResponse, 1); } }
 * 
 * 
 *//**
	 * token 校验失败提示
	 *
	 * @param type 1无效，2过期
	 *//*
		 * private void accessDeny(HttpServletResponse response, int type) { PrintWriter
		 * writer = null; response.setCharacterEncoding("UTF-8");
		 * response.setContentType("text/html; charset=utf-8"); try { writer =
		 * response.getWriter(); // ResultModel resultModel = new ResultModel(); // if
		 * (1 == type) { // resultModel = ResultTools.result(1001, "", null); // } else
		 * if (2 == type) { // resultModel = ResultTools.result(1002, "", null); // } //
		 * JSONObject jsonObject = new JSONObject(resultModel); //
		 * writer.print(jsonObject); } catch (IOException e) {
		 * System.out.print(e.getMessage()); } finally { if (writer != null) {
		 * writer.close(); } } }
		 * 
		 * @Override public void init(FilterConfig filterConfig) {
		 * logger.info("jwtFilter init ..."); }
		 * 
		 * @Override public void destroy() { logger.info("jwtFilter destroy ..."); } }
		 */