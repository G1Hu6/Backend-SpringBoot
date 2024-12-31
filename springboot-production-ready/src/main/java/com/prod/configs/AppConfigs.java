package com.prod.configs;

import com.prod.auth.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestClient;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getCurrentAuditorAware")
public class AppConfigs {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public AuditorAware<String> getCurrentAuditorAware() {
        return new AuditorAwareImpl();
    }
}
