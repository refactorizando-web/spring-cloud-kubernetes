package com.refactorizando.shop.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.refactorizando.shop.service.exception.CustomWebClientResponseException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final WebClient client;

  @HystrixCommand(fallbackMethod = "getFallbackName", commandProperties = {
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
  public String getOrders() {

    log.info("Making a call to get Orders from order service ");

    var orders = client.get()
        .uri(uriBuilder -> uriBuilder.path("orders").build()).retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse ->
            Mono.error(new CustomWebClientResponseException(
                "Client error getting info from timetable with error: " + clientResponse.statusCode()))
        )
        .onStatus(HttpStatus::is5xxServerError, clientResponse ->
            Mono.error(new CustomWebClientResponseException(
                "Server error getting info from timetable" + clientResponse.statusCode()))
        ).bodyToMono(String.class).block();

    log.debug("Orders {} ", orders);

    return orders;
  }


  private String getFallbackName() {
    return "Ups Fallback";
  }
}
