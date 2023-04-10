package kz.auto_life.authservice.config;

import kz.auto_life.authservice.filters.CustomAuthenticationFilter;
import kz.auto_life.authservice.filters.CustomAuthorizationFilter;
import kz.auto_life.authservice.properties.JwtProperties;
import kz.auto_life.authservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletContext;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;
    private final ServletContext servletContext;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/update").authenticated()
                .anyRequest().permitAll();
        http.addFilter(jwtAuthorizationFilter());
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), userRepository, jwtProperties));
        http.addFilterBefore(new CustomAuthorizationFilter(jwtProperties, servletContext), UsernamePasswordAuthenticationFilter.class);
    }

    public CustomAuthenticationFilter jwtAuthorizationFilter() throws Exception {
        CustomAuthenticationFilter jwtAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(), userRepository, jwtProperties);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/auth/login");
        return jwtAuthenticationFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }
}
