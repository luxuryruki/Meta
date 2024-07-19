package com.example.meta.account.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name= "default",
             url= "${meta.facebook.graph-url-prefix}"
)
public interface MetaFeignClient {

    @GetMapping("/{uri}")
    Map<String, Object> callGet(@PathVariable("uri") String uri, @RequestBody Map<String, Object> data);
}
