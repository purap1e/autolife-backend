package kz.auto_life.finecarservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FineCarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FineCarServiceApplication.class, args);
    }

}
