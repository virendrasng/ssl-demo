package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
class SecuredServerController {
//	@Autowired
	private RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}
	
	@GetMapping("/secured")
	public String secured() {
		System.out.println("Inside secured()");
		ResponseEntity<String> resp = restTemplate.exchange("https://local-host/ssl/test/secured", HttpMethod.GET, null, String.class, "");
		
		return resp.getBody();
	}
}