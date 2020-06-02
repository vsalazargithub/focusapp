package com.focus.studio.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackageClasses = {DatabaseConfiguration.class,WebConfiguration.class})
public class FocusStudioRestApiApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FocusStudioRestApiApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FocusStudioRestApiApplication.class, args);
	}
	
}
