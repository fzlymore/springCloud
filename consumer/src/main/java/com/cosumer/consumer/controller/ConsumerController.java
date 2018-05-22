package com.cosumer.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerController {
   @Autowired
    RestTemplate restTemplate;

   @RequestMapping("/ribbon-consumer")
   public String helloConsumer(){
      return restTemplate.getForEntity("http://HELLO-SERVICE/hello/hello",String.class).getBody();
   }

   /**
    * ribbon通过RestTemplate中的getForEntity方法得到想要的返回值
    * getForEntity有三种重载 1 url 返回值的class类型  url的参数
    * 2 url 返回值的class类型  Map里面的参数为？后面的键值对 如 name = "didi"
    * 3 uri对象 返回值的class类型
    * @return
    */
   public String method (){
      //第一种
      ResponseEntity <String> responseEntity = restTemplate.getForEntity(
              "http://USER-SERVICE/user?name={1}",String.class,"didi"
      );
      String body = responseEntity.getBody();

      //第二种
      Map map = new HashMap();
      map.put("name","didi");
      ResponseEntity responseEntity1 = restTemplate.getForEntity(
              "http://USER-SERVICE/user?name={1}",String.class,map
      );
      String body1 = responseEntity1.getBody().toString();

      //第三种
      UriComponents uriComponents = UriComponentsBuilder.fromUriString(
              "http://USER-SERVICE/user?name={name}"
      ).build().expand("didi").encode();
      URI uri = uriComponents.toUri();
      ResponseEntity <String> responseEntity2 = restTemplate.getForEntity(
              uri,String.class
      );
      String body2 = responseEntity2.getBody();

      return body;
   }

   /**
    * ribbon通过RestTemplate中的getForObject方法得到想要的返回值
    * 对getForEntity方法的封装  通过HttpMessageConverterExtractor
    * 三种重载方法和getForEntity一致
    */
   public void method2 (){
      UriComponents uriComponents = UriComponentsBuilder.fromUriString(
              "http://USER-SERVICE/user?name={name}"
      ).build().expand("didi").encode();
      URI uri = uriComponents.toUri();
      String body =   restTemplate.getForObject(uri,String.class);
   }

   //post方法和get方法差不多 也是三种方法
   public void method3(){
      restTemplate.postForEntity("http://USER-SERVICE/user","didi",String.class);
   }
}
