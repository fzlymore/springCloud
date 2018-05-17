package eureka.hello.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/hello")
@RestController
public class HelloController {
    private final Logger LOGGER = Logger.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello")
    public String index (){
        ServiceInstance instance = client.getLocalServiceInstance();
        LOGGER.info("/hello,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "Hello!";
    }
}
