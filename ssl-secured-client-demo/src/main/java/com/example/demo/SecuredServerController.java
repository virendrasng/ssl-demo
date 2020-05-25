package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
class SecuredServerController {
	private RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}

	@GetMapping("/secured")
	public String secured() {
		System.out.println("Inside secured() of client");
		ResponseEntity<String> resp = restTemplate.exchange("https://localhost/ssl/test/secured", HttpMethod.GET, null,
				String.class, "");
		System.out.println("---------- " + resp.getBody());
		
		return resp.getBody();
	}
}