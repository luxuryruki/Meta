package com.example.meta.feign;

import com.example.meta.message.domain.MessageRequest;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name= "default",
             url= "${meta.facebook.graph-url-prefix}"
)
public interface MetaFeignClient {

    @GetMapping("/{uri}")
    Map<String, Object> callGet(@PathVariable("uri") String uri,  @SpringQueryMap Map<String, Object> data);

    @PostMapping("/{uri}")
    Map<String, Object> callPostWithPrams(@PathVariable("uri") String uri,  @SpringQueryMap Map<String, Object> data);

    @PostMapping("/{uri}")
    Map<String, Object> callPostWithBody(@PathVariable("uri") String uri,  @RequestBody Map<String, Object> data);

    @PostMapping("/{uri}")
    Map<String, Object> callPostWitParamsAndBody(@PathVariable("uri") String uri, @SpringQueryMap Map<String, Object> params,  @RequestBody MessageRequest body);
}
