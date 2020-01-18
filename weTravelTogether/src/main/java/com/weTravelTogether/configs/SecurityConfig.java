package com.weTravelTogether.configs;

import com.weTravelTogether.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable(); // TODO: keep only in dev
        http.logout().disable();
        http.addFilterAfter(jwtAuthorizationFilter, BasicAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers(
                        "/login", "/registration", "/v2/api-docs",
                        "/configuration/**", "/swagger*/**",
                        "/webjars/**", "/refresh-token", "/","/actuator", "/actuator/*").permitAll()
                .anyRequest().authenticated();

                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.setHeader("Authorization", "Bearer"); // we can point explicitly to register/login/refresh URL
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}