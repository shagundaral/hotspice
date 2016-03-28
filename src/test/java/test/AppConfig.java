package test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import service.HSService;
import serviceimpl.HSImpl;

@Configuration
public class AppConfig {

	
	@Bean
    public HSService getService() {
        return new HSImpl();
    }
	
}
