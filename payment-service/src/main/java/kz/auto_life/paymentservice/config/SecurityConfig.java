package kz.auto_life.paymentservice.config;

import kz.auto_life.paymentservice.filters.CustomAuthorizationFilter;
import kz.auto_life.paymentservice.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtProperties jwtProperties;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated();
        http.addFilterBefore(new CustomAuthorizationFilter(jwtProperties), UsernamePasswordAuthenticationFilter.class);
    }
}
