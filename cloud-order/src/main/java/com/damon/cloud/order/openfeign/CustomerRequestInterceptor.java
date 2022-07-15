package com.damon.cloud.order.openfeign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component //openfeign拦截器，统一指定sentinel权限控制source头
public class CustomerRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("source", "cloud-order");
    }
}
