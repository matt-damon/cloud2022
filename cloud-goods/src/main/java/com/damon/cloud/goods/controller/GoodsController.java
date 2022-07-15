package com.damon.cloud.goods.controller;

import com.damon.cloud.entity.Goods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Value("${server.port}")
    private String PORT;

    @RequestMapping("findById/{id}")
    public Goods findById(@PathVariable String id) {
        System.out.println("goods id:" + id);
        return new Goods("小米" + PORT, 99);
    }


    @PostMapping("save")
    public Map save(@RequestBody Goods goods) {
        System.out.println("save goods:" + goods);
        return new HashMap() {{
            put("code", 200);
            put("msg", "goods saved");
        }};
    }
}
