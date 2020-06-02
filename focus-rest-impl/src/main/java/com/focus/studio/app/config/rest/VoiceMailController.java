package com.focus.studio.app.config.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v2")
public class VoiceMailController {

	
	@Autowired
	private  RestTemplate restTemplate;
	
	private static final String URL_BASE="https://sandbox.2600hz.com:8443";
	
	private static final Logger logger = LoggerFactory.getLogger(VoiceMailController.class);
	
	private static final String AUTHORIZATION="Basic NDY0MmU2NDA0MGNkYjhiODljMzEwYTIxYTA3YzdmNjI6MjMyNjQxNTY1OTA3NWU3NTAwMGNlY2Q3YmNiZjM3NTY=";

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/accounts/{ACCOUNT_ID}/vmboxes/{VM_BOX_ID}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> listMessages(
			@RequestParam(defaultValue = "true", required = false,name = "paginate") Boolean paginate,
			@PathVariable("ACCOUNT_ID") String accountID,
			@PathVariable("VM_BOX_ID") String vmboxID,HttpServletRequest request
			) {	
		logger.info("listMessages init");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization",AUTHORIZATION);
		
		HttpEntity<Object> entityUpdate = new HttpEntity<>(headers);

		Map<String, String> params = new HashMap<String, String>();
		params.put("ACCOUNT_ID", accountID);
		params.put("VM_BOX_ID", vmboxID);
		params.put("PAGINATE", paginate.toString());
		
		logger.info("ACCOUNT_ID --> {}",accountID);
		logger.info("VM_BOX_ID --> {}",vmboxID);
		logger.info("PAGINATE --> {}",paginate);
		
		StringBuilder sbu = new StringBuilder().append(URL_BASE).append("/v2/accounts/{ACCOUNT_ID}/vmboxes/{VM_BOX_ID}/messages?paginate={PAGINATE}");
		
		HttpEntity<String> response = restTemplate.exchange(sbu.toString(), HttpMethod.GET, entityUpdate, String.class,params);
		logger.info("listMessages end");
		return new ResponseEntity<String>(response.getBody(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value="/accounts/{ACCOUNT_ID}/vmboxes/{VM_BOX_ID}/messages/{MEDIA_ID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> transitionMessage(
			@RequestParam(required = true, name = "folder") String folder,
			@PathVariable("ACCOUNT_ID") String accountID,
			@PathVariable("VM_BOX_ID") String vmboxID,
			@PathVariable("MEDIA_ID") String mediaID,
			HttpServletRequest request
			) {	
		logger.info("transitionMessage init");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization",AUTHORIZATION);
		
		HttpEntity<Object> entityUpdate = new HttpEntity<>(headers);

		Map<String, String> params = new HashMap<String, String>();
		params.put("ACCOUNT_ID", accountID);
		params.put("VM_BOX_ID", vmboxID);
		params.put("MEDIA_ID", mediaID);
		params.put("FOLDER", folder);

		logger.info("ACCOUNT_ID --> {}",accountID);
		logger.info("VM_BOX_ID --> {}",vmboxID);
		logger.info("FOLDER --> {}",folder);
		
		StringBuilder sbu = new StringBuilder().append(URL_BASE).append("/v2/accounts/{ACCOUNT_ID}/vmboxes/{VM_BOX_ID}/messages/{MEDIA_ID}?folder={FOLDER}");
		
		HttpEntity<String> response = restTemplate.exchange(sbu.toString(), HttpMethod.POST, entityUpdate, String.class,params);
		logger.info("transitionMessage end");
		return new ResponseEntity<String>(response.getBody(), new HttpHeaders(), HttpStatus.OK);
	}
	
	
}
