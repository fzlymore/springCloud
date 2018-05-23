package feignconsumer.feignconsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("HELLO-SERVICE")
public interface HelloService {

    @RequestMapping("/hello/hello")
    String hello();
}
