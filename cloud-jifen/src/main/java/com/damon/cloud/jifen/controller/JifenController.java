package com.damon.cloud.jifen.controller;

import com.damon.cloud.entity.Goods;
import com.damon.cloud.entity.Jifen;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("jifen")
@RefreshScope //配置动态刷新
public class JifenController {

    @Value("${pic.url}")
    private String url;

    @RequestMapping("/test-refresh")
    public String dynamicRefresh() {
        return url;
    }


    @PostMapping("/save")
    public Map save(@RequestBody Jifen jifen) {
        System.out.println("save jifen:" + jifen);
        return new HashMap() {{
            put("isSuccess", true);
            put("msg", "jifen saved");
        }};
    }

    @PostMapping("/update")
    public Map update(@RequestBody Jifen jifen) {
        System.out.println("update jifen:" + jifen);
        return new HashMap() {{
            put("isSuccess", true);
            put("msg", "jifen updated");
        }};
    }


    //token是gateway内置过滤器中添加的
    @GetMapping("/delete")
    public Map delete(Integer jifenId, @RequestHeader("token") String token) {
        System.out.println("delete jifen:" + jifenId + ",token:" + token);
        return new HashMap() {{
            put("isSuccess", true);
            put("msg", "jifen deleted");
        }};
    }

    @GetMapping("/{jifenId}")
    public Jifen findJifenById(@PathVariable Integer jifenId) {
        return new Jifen(jifenId, 12, jifenId + "号积分");
    }


}
