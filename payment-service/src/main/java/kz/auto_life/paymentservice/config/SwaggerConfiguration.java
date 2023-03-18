package kz.auto_life.paymentservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("payment-service url: http://176.9.24.125:12565")
                        .version("1.0")
                        .description("Сервис для работы с платежами")
                        .termsOfService("http://swagger.io/terms/"));
    }
}
