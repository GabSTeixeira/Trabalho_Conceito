package br.com.webank.webank.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    
    @Bean
    public ModelMapper modelMaper(){
        return new ModelMapper();
    }
}
