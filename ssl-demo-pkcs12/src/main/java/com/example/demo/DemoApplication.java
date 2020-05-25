package com.example.demo;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		System.setProperty("javax.net.debug", "all");
//		System.setProperty("javax.net.ssl.trustStore", "D:\\Data\\study\\devl\\projects\\ssl\\ssl-demo\\ssl-client-demo\\src\\main\\resources\\ssl-server.jts");
//		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		/*
		 * If we want to access on 8080 as http then provide only
		 * TomcatEmbeddedServletContainerFactory tomcat = new
		 * TomcatEmbeddedServletContainerFactory()
		 */
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			// This code is to enforce redirection from 8080 (http) to 443 (https)
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		tomcat.addAdditionalTomcatConnectors(redirectConnector());
		return tomcat;
	}

	private Connector redirectConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080); // We can add more ports like this.
		connector.setSecure(false);
		connector.setRedirectPort(443);

		return connector;
	}
}
