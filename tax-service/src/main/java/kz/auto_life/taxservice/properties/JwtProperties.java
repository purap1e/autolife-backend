package kz.auto_life.taxservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
@Getter
@Setter
public class JwtProperties {
    private String secret;
    private int expiresAt;
}
