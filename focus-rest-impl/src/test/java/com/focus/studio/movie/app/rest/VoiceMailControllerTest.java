package com.focus.studio.movie.app.rest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.focus.studio.app.config.FocusStudioRestApiApplication;
import com.focus.studio.app.config.rest.VoiceMailController;

import io.netty.handler.codec.Headers;


@SpringBootTest(classes = FocusStudioRestApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class VoiceMailControllerTest {

	@LocalServerPort
	private Integer port;
	
	@Autowired
	private RestTemplate restTemplate;	
	@Value("${application.url.rest.client.base}")
	private String URL;

	private String URL_BASE;
	
	
	private static final Logger logger = LoggerFactory.getLogger(VoiceMailControllerTest.class);

	
	@Test
	public void testListMessages() {
		logger.info("testListMessages init");
		URL_BASE = String.format(URL, Integer.toString(port));
				 
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<Object> entity = new HttpEntity<>(headers);

		Map<String, String> params = new HashMap<String, String>();
		params.put("ACCOUNT_ID", "4642e64040cdb8b89c310a21a07c7f62");
		params.put("VM_BOX_ID", "b37675a2d7b90d60f0ee5d4175502394");
		
		StringBuilder sbu = new StringBuilder().append(URL_BASE).append("/v2/accounts/{ACCOUNT_ID}/vmboxes/{VM_BOX_ID}/messages?paginate=false");
		
		System.out.println(sbu.toString());
		
		HttpEntity<String> response = restTemplate.exchange(sbu.toString(), HttpMethod.GET, entity, String.class,params);
		
		logger.info(response.getBody());
		HttpHeaders h = response.getHeaders();
		
		logger.info(h.toString());
		
		logger.info("testListMessages end");
	}
	
	
	@Test
	public void testTransitionMessage() {
		logger.info("testTransitionMessage init");
		URL_BASE = String.format(URL, Integer.toString(port));
				 
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<Object> entity = new HttpEntity<>(headers);

		Map<String, String> params = new HashMap<String, String>();
		params.put("ACCOUNT_ID", "4642e64040cdb8b89c310a21a07c7f62");
		params.put("VM_BOX_ID", "b37675a2d7b90d60f0ee5d4175502394");
		params.put("MEDIA_ID", "202005-9601d9571ca163cdd4dbc4f7d90ae4a5");
		
		
		StringBuilder sbu = new StringBuilder().append(URL_BASE).append("/v2/accounts/{ACCOUNT_ID}/vmboxes/{VM_BOX_ID}/messages/{MEDIA_ID}?folder=deleted");
		
		System.out.println(sbu.toString());
		
		HttpEntity<String> response = restTemplate.exchange(sbu.toString(), HttpMethod.POST, entity, String.class,params);
		
		logger.info(response.getBody());
		HttpHeaders h = response.getHeaders();
		
		logger.info(h.toString());
		logger.info("testTransitionMessage end");
		
		logger.info("testListMessages init");
		URL_BASE = String.format(URL, Integer.toString(port));
				 
		HttpHeaders headersg = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<Object> entityg = new HttpEntity<>(headersg);

		Map<String, String> paramsg = new HashMap<String, String>();
		paramsg.put("ACCOUNT_ID", "4642e64040cdb8b89c310a21a07c7f62");
		paramsg.put("VM_BOX_ID", "b37675a2d7b90d60f0ee5d4175502394");
		
		StringBuilder sbug = new StringBuilder().append(URL_BASE).append("/v2/accounts/{ACCOUNT_ID}/vmboxes/{VM_BOX_ID}/messages?paginate=false");
		
		System.out.println(sbu.toString());
		
		HttpEntity<String> responseg = restTemplate.exchange(sbug.toString(), HttpMethod.GET, entityg, String.class,paramsg);
		
		logger.info(responseg.getBody());
		HttpHeaders hg= responseg.getHeaders();
		
		logger.info(hg.toString());
		
		logger.info("testListMessages end");
		
		
	}


}
