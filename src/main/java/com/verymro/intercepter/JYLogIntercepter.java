package com.verymro.intercepter;

import com.alibaba.fastjson.JSON;
import com.verymro.annotation.JYLog;
import com.verymro.db.LogSave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rui.wang
 * @version 1.0
 * @description: 请求拦截器，日志处理
 * @date 2021/6/23 8:05
 */
public class JYLogIntercepter implements HandlerInterceptor {

    Logger LOOGER = LoggerFactory.getLogger(JYLogIntercepter.class);

    ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof  HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            JYLog annotation = method.getAnnotation(JYLog.class);
            if(annotation !=null ) {
                threadLocal.set(System.currentTimeMillis());
            }
        }
        return true;
    }

    @Autowired
    LogSave logSave;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if(handler instanceof  HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            JYLog annotation = method.getAnnotation(JYLog.class);
            LOOGER.info("bean:{}",logSave);
            if(annotation !=null ) {
                Long startTime = threadLocal.get();
                String requestURL = request.getRequestURL().toString();
                Long endTime = System.currentTimeMillis();
                String desc = annotation.desc();
                Map<String,Object> logMap = new HashMap<>();
                logMap.put("desc",desc);
                logMap.put("api",requestURL);
                logMap.put("params",JSON.toJSONString(parameterMap));
                logMap.put("time",(endTime-startTime)+"毫秒");
                LOOGER.info("\n 描述：【{}】 \n 请求接口：【{}】 \n 请求参数：【{}】 \n 方法消耗时间：【{}】毫秒 \n  ",desc,requestURL, JSON.toJSONString(parameterMap),(endTime-startTime));
                logSave.saveLog(JSON.toJSONString(logMap));
            }
        }

    }
}
