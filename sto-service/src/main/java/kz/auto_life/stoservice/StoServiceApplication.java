package kz.auto_life.stoservice;

import kz.auto_life.stoservice.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({JwtProperties.class})
public class StoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoServiceApplication.class, args);
    }
}
