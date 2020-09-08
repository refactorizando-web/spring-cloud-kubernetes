package com.refactorizando.shop.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.refactorizando.shop.service.conf.RibbonConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@RibbonClient(name = "order-service", configuration = RibbonConfiguration.class)
public class SpringCloudKubernetesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudKubernetesApplication.class, args);
	}

}
