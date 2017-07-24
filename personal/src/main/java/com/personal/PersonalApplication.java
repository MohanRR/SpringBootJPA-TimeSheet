package com.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.personal.config.JwtFilter;

@SpringBootApplication
public class PersonalApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/company/*","/user/*");

		return registrationBean;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PersonalApplication.class, args);
	}
}
