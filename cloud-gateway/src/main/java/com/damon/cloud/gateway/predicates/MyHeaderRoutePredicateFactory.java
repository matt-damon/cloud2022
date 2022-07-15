package com.damon.cloud.gateway.predicates;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

//自定义谓词
@Component
public class MyHeaderRoutePredicateFactory extends AbstractRoutePredicateFactory<MyConfig> {

    public MyHeaderRoutePredicateFactory() {
        super(MyConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyConfig config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //从请求头中获取key对应的value
                String value = serverWebExchange.getRequest().getHeaders().getFirst(config.getKey());
                if (StringUtils.isEmpty(value)) {
                    return false;
                } else if (value.equals(config.getValue())) {
                    return true;
                }
                return false;
            }
        };
    }

    //切割配置
    // MyHeader=name,xx
    // name -> MyConfig#key  xx -> MyConfig#value
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("key", "value");
    }

}
