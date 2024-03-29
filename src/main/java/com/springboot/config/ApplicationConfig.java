package com.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("user/index").setViewName("user/index");
		
		registry.addViewController("user/logonview").setViewName("user/logonview");
		registry.addViewController("user/loginview").setViewName("user/loginview");
		registry.addViewController("user/resetview").setViewName("user/resetview");
	}

}
