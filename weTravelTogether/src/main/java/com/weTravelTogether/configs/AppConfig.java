package com.weTravelTogether.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@ComponentScan(basePackages = "com.weTravelTogether")
@EnableJpaRepositories(basePackages = "com.weTravelTogether.repos")
@Import(RepositoryRestMvcConfiguration.class)
public class AppConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPersistenceUnitName("ProdPersistenceUnit");
        return bean;
    }
}
