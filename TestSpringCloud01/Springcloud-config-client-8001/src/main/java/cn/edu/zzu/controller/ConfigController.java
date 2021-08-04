package cn.edu.zzu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Value("${spring.application.name}")
    private String applicationName ;

    @Value("${eureka.client.service-url.defaultZone}")
    private String EurekaServer;

    @Value("${server.port}")
    private String serverProt;

    @GetMapping("/info")
    public String info(){
        return  "  applicationName:"+applicationName+ "<br>"+
                "  EurekaServer:"+EurekaServer+"<br>"+
                "  serverProt:"+serverProt+"<br>";
    }
}
