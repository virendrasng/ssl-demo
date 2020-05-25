package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SecuredServerController {
	@Value("${server.ssl.keyStoreType:JKS}")
	private String type;
	@Value("${server.ssl.key-store}")
	private String keyParFile;

	@GetMapping("/secured")
	public String secured() {
		System.out.println("Inside secured()");
		return "Hello " + type + ", " + keyParFile + " - " + new Date();
	}
}