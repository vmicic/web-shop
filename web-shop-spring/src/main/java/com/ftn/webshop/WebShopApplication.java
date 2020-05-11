package com.ftn.webshop;

import java.util.Arrays;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebShopApplication {
	
	private static Logger logger = LoggerFactory.getLogger(WebShopApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(WebShopApplication.class, args);
		
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		
		StringBuilder sb = new StringBuilder("Application beans:\n");
		for (String beanName : beanNames) {
			sb.append(beanName + "\n");
		}
		

	}

	@Bean
	public KieContainer kieContainer() {
		logger.info("checking");
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn", "web-shop-drools", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}
	
}
