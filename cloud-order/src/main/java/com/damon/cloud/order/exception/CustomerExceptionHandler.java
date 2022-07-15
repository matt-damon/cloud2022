package com.damon.cloud.order.exception;

import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//全局处理Controller层的Sentinel异常
@ControllerAdvice
public class CustomerExceptionHandler {

    //流控规则异常处理
    @ExceptionHandler(FlowException.class)
    @ResponseBody //转成json
    public Map handlerFlowException() {
        return new HashMap() {{
            put("code", "5xx");
            put("msg", "系统繁忙，请稍后重试");
        }};
    }

    //熔断降级规则异常处理
    @ExceptionHandler(DegradeException.class)
    @ResponseBody //转成json
    public Map handlerDegradeException() {
        return new HashMap() {{
            put("code", "5xx");
            put("msg", "系统开小差，请稍后重试");
        }};
    }

    //权限规则异常处理
    @ExceptionHandler(AuthorityException.class)
    @ResponseBody //转成json
    public Map handlerAuthorityException() {
        return new HashMap() {{
            put("code", "5xx");
            put("msg", "无权限访问");
        }};
    }

}
