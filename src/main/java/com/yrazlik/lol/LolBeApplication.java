package com.yrazlik.lol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class LolBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LolBeApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<ApiKeyFilter> apiKeyFilter(){
	    FilterRegistrationBean<ApiKeyFilter> registrationBean = new FilterRegistrationBean<>();
	    registrationBean.setFilter(new ApiKeyFilter());
	    registrationBean.addUrlPatterns("/league/*", "/match/*", "/summoner/*");
	    return registrationBean;    
	}

}
