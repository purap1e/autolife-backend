package kz.auto_life.finecarservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@OpenAPIDefinition(
        info = @Info(
                title = "Fine-car-service",
                description = "Сервис для отслеживания и оплаты штрафов на автомобиль",
                version = "1.0",
                license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
        )
)
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Fine-car-service")
                        .version("1.0")
                        .description("Сервис для отслеживания и оплаты штрафов на автомобиль")
                        .termsOfService("http://swagger.io/terms/"));
    }

}
