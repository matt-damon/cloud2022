package com.damon.cloud.jifen.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


//权限控制，校验请求头的source
@Component
public class CustomerRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        System.out.println("req come in...");
        String source = httpServletRequest.getHeader("source");
        System.out.println(source);
        return source;
    }
}
