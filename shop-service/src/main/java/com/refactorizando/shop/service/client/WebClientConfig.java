package com.refactorizando.shop.service.client;

import javax.net.ssl.SSLException;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  private static final String URL = "http://order-service:8080";

  @Bean
  public WebClient webClient() throws SSLException {

    return WebClient.builder()
        .baseUrl(URL)
        .defaultHeader(HttpHeaders.CONTENT_TYPE,
            MediaType.APPLICATION_JSON_VALUE)
        .defaultUriVariables(Collections.singletonMap("url",
            URL)).build();

  }

}
