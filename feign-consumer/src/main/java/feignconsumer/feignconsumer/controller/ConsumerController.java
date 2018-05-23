package feignconsumer.feignconsumer.controller;

import feignconsumer.feignconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/feign-consumer")
    public String helloconsumer(){
        return helloService.hello();
    }
}
