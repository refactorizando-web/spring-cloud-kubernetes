package com.refactorizando.shop.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.refactorizando.shop.service.conf.ShopConfig;
import com.refactorizando.shop.service.exception.CustomWebClientResponseException;
import com.refactorizando.shop.service.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ShopController {

  private final DiscoveryClient discoveryClient;

  private final ShopConfig config;

  private final OrderService orderService;

  private final WebClient client;

  @GetMapping("/order")
  public String getOrders() {
    return orderService.getOrders();
  }

  @GetMapping("/")
  public String load() {

    log.info("Getting discovery service information ");

    ArrayList<String> serviceList = new ArrayList<>();

    if (discoveryClient != null) {

      List<String> services = this.discoveryClient.getServices();

      log.info("Services are {} ", services);

      services.forEach(service -> {

        var instances = this.discoveryClient.getInstances(service);

        serviceList.add("[" + service + " : " + ((!CollectionUtils.isEmpty(instances)) ? instances.size() : 0)
            + " instances ]");
      });

    }

    return String.format(config.getMessage(), getMessageOrderService(), String.join(" and ", serviceList));
  }

  private String getMessageOrderService() {
    return client.get()
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse ->
            Mono.error(new CustomWebClientResponseException(
                "Client error getting info from timetable with error: " + clientResponse.statusCode()))
        )
        .onStatus(HttpStatus::is5xxServerError, clientResponse ->
            Mono.error(new CustomWebClientResponseException(
                "Server error getting info from timetable" + clientResponse.statusCode()))
        ).bodyToMono(String.class).block();
  }
}
