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
		/*
		 * From browser the server redirects caals from http @ 8080 to https @ 443. But
		 * from below code response entity returns - <302
		 * Found,{Cache-Control=[private], Expires=[Thu, 01 Jan 1970 05:30:00 IST],
		 * Location=[https://localhost/ssl/test/secured], Content-Length=[0], Date=[Mon,
		 * 25 May 2020 13:10:53 GMT]}>
		 * 
		 * resp.getBody(); return null
		 */
//		ResponseEntity<String> resp = restTemplate.exchange("http://localhost:8080/ssl/test/secured", HttpMethod.GET,
//				null, String.class, "");
		ResponseEntity<String> resp = restTemplate.exchange("https://localhost/ssl/test/secured", HttpMethod.GET, null,
				String.class, "");
		System.out.println("---------- " + resp.getBody());
		
		return resp.getBody();
	}
}