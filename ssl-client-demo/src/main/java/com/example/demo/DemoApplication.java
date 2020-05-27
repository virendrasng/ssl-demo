package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		// System.setProperty("javax.net.debug", "all");

		/*
		 * trustStore is added to avoid below error -
		 * 
		 * I/O error on GET request for "https://localhost/ssl/test/secured":
		 * sun.security.validator.ValidatorException: PKIX path building failed:
		 * sun.security.provider.certpath.SunCertPathBuilderException: unable to find
		 * valid certification path to requested target; nested exception is
		 * javax.net.ssl.SSLHandshakeException:
		 * sun.security.validator.ValidatorException: PKIX path building failed:
		 * sun.security.provider.certpath.SunCertPathBuilderException: unable to find
		 * valid certification path to requested target
		 */
		System.setProperty("javax.net.ssl.trustStore",
				"D:\\Data\\study\\devl\\projects\\ssl\\ssl-demo\\ssl-client-demo\\src\\main\\resources\\ssl-server.jts");

		/*
		 * HttpsURLConnection.setDefaultHostnameVerifier ... added to avoid below error
		 * I/O error on GET request for "https://localhost/ssl/test/secured":
		 * java.security.cert.CertificateException: No name matching localhost found;
		 * nested exception is javax.net.ssl.SSLHandshakeException:
		 * java.security.cert.CertificateException: No name matching localhost found
		 * 
		 * This error can also be avoided by adding -dname "CN=localhost,OU=QE,O=example.com,L=Brno,C=CZ" in the jks certificate and then export jts.
		 */
		// javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new
		// javax.net.ssl.HostnameVerifier() {
		// public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
		// return hostname.equals("localhost");
		// }
		// });
	}

}
