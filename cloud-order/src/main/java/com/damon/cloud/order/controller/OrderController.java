package com.damon.cloud.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.damon.cloud.api.JifenApi;
import com.damon.cloud.entity.Goods;
import com.damon.cloud.entity.Jifen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Resource //openfeign代理类
    private JifenApi jifenApi;


    @RequestMapping("save")
    public Map save() {
        //远程调用cloud-goods服务
        //String url = "http://localhost:8001/goods/findById/1";
        String serviceName = "cloud-goods";
        String url = "http://" + serviceName + "/goods/findById/1";//ribbon会进行解析

        Goods goods = restTemplate.getForObject(url, Goods.class);
        System.out.println(goods);
        //保存订单
        System.out.println("save order success");
        return new HashMap() {
            {
                put("code", 200);
                put("msg", "success");
            }
        };
    }

    //通过openfeign远程调用cloud-jifen服务，底层采用http请求
    @RequestMapping("test-jifen-save")
    public Map testJifenSave() {
        //url=http://cloud-jifen/jifen/save
        Map save = jifenApi.save(new Jifen(1, 10, "2"));
        return save;
    }

    //通过openfeign远程调用cloud-jifen服务，底层采用http请求
    @RequestMapping("test-jifen-delete")
    public Map testJifenDelete() {
        //url=http://cloud-jifen/jifen/delete?jifenId=1
        Map save = jifenApi.save(new Jifen(1, 10, "2"));
        return save;
    }

    @RequestMapping("test1")
    public String test1() {
        return "test1";
    }


    @RequestMapping("test2")
    @SentinelResource("hotkey-test2")  //自定义资源
    public String test2(String name, Integer age) {
        System.out.println("name + age:" + name + "," + age);
        return "test2";
    }


    //sentinel权限控制 origin指定cloud-order
    @RequestMapping("test-auth")
    public String testAuth() {
        Map delete = jifenApi.delete(1);
        return "authTest";
    }

    //全局异常测试
    @RequestMapping("test-exp")
    @SentinelResource(value = "test-exception")
    public String testException() {
        return "testException";
    }
}
