package com.yeahmobi.loadbalance_manager.helper;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

public class JsonArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonArgumentResolver.class);

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JsonArgument.class) != null;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        // 把reqeust的body读取到StringBuilder
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();

        char[] buf = new char[1024];
        int rd;
        while ((rd = reader.read(buf)) != -1) {
            sb.append(buf, 0, rd);
        }

        // 利用fastjson转换为对应的类型
        String json = sb.toString();
        try {
            return JSON.parseObject(json, parameter.getParameterType());
        } catch (JSONException e) {
            LOGGER.info("Ignored invalid json param: " + json);
            return null;
        }
    }

}
