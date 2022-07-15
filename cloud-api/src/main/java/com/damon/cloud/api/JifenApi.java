package com.damon.cloud.api;


import com.damon.cloud.entity.Jifen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;


//接口声明
@FeignClient(name = "cloud-jifen", path = "/jifen")  //由EnableFeignClients扫描
public interface JifenApi {

    @PostMapping("/save")
    Map save(@RequestBody Jifen jifen);


    @GetMapping("/delete")
    Map delete(@RequestParam("jifenId") Integer jifenId);
}
