package com.autel.spring.boot.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * 拦截处理类
 * @author A20019
 * @date 2020/3/26
 */

@Component
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER  = LoggerFactory.getLogger(HttpInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        String str = "";
        Map<String, String[]> params = request.getParameterMap();
        for (String key: params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                str += key + "=" + value + "&";
            }
        }
        str = str.equals("") ? null : str.substring(0,str.length()-1);
        LOGGER.info(String.format("请求参数：url: " + url + "; method: " + method + "; params: " + str));

        /**
         * 含有 hello 的请求不拦截
         */
        if("/hello".equals(uri)){
            return true;
        }

        /**
         * 请求要有用户的id
         */
        String userId = request.getParameter("userId");
        if(!StringUtils.isEmpty(userId)){
            return true;
        }else{
            this.output(response, "{\n" + "\"code\": \"4001\",\n" + "\"message\": \"参数错误\"\n" + "}");
            return false;
        }
    }

    /**
     * 定义编码，字节流输出结果
     * @param response
     * @param result
     * @throws IOException
     */
    public void output(HttpServletResponse response, String result) throws IOException {
        response.setHeader("content-type","text/html;chatset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(result);
    }

}
