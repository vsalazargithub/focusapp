package com.focus.studio.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class WebConfiguration {

	@Bean	
	public RestTemplate RestTemplate() {
		RestTemplate restTemplate =	new RestTemplate();
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter());
		restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory());
		return restTemplate;
	}
	
	@Autowired
	public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
		return new HttpComponentsClientHttpRequestFactory();
	}
	
	@Bean
	@Qualifier("mappingJackson2HttpMessageConverterRestTemplate")
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();    
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		messageConverter.setPrettyPrint(false);
		messageConverter.setObjectMapper(objectMapper);
		restTemplate.getMessageConverters().add(messageConverter);
		return messageConverter;
	}

}
