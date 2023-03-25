package kz.auto_life.shopservice.configs;

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
                        .title("shop-service")
                        .version("1.0")
                        .description("Сервис работы с автозапчастями")
                        .termsOfService("http://swagger.io/terms/"));
    }
}
