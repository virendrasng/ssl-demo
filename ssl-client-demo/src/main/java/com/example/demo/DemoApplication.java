package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.setProperty("javax.net.debug", "all");
		System.setProperty("javax.net.ssl.trustStore", "D:\\Data\\study\\devl\\projects\\ssl\\ssl-demo\\ssldemo-pkcs12-jks\\src\\main\\resources\\ssl-server.jts");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				return hostname.equals("local-host");
			}
		});
	}

}
