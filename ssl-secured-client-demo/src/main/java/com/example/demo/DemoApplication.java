package com.example.demo;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		// System.setProperty("javax.net.debug", "all");

		/*
		 * trustStore is added to avoid below error - I/O error on GET request for
		 * "https://localhost/ssl/test/secured":
		 * sun.security.validator.ValidatorException: PKIX path building failed:
		 * sun.security.provider.certpath.SunCertPathBuilderException: unable to find
		 * valid certification path to requested target; nested exception is
		 * javax.net.ssl.SSLHandshakeException:
		 * sun.security.validator.ValidatorException: PKIX path building failed:
		 * sun.security.provider.certpath.SunCertPathBuilderException: unable to find
		 * valid certification path to requested target
		 */
		System.setProperty("javax.net.ssl.trustStore",
				"D:\\Data\\study\\devl\\projects\\ssl\\ssl-demo\\ssl-secured-client-demo\\src\\main\\resources\\ssl-server.jts");

		/*
		 * HttpsURLConnection.setDefaultHostnameVerifier ... added to avoid below error
		 * I/O error on GET request for "https://localhost/ssl/test/secured":
		 * java.security.cert.CertificateException: No name matching localhost found;
		 * nested exception is javax.net.ssl.SSLHandshakeException:
		 * java.security.cert.CertificateException: No name matching localhost found
		 */
		HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname, SSLSession sslSession) {
				// localhost is added in hosts file -> 192.168.56.1 localhost
				return hostname.equals("localhost");
			}
		});
	}

}
