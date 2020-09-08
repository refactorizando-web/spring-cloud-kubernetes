package com.refactorizando.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refactorizando.model.Order;
import com.refactorizando.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

  private final OrderRepository orderRepository;


  @GetMapping("/orders")
  public ResponseEntity<List<Order>> getOrders() {
    log.info("Client is requesting new deals!");

    return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);

  }

  @GetMapping("/")
  public ResponseEntity<String> info() throws UnknownHostException {

    String serviceInfo = "Host: " + InetAddress.getLocalHost().getHostName() + "<br/>"
        + "IP: " + InetAddress.getLocalHost().getHostAddress() + "<br/>"
        + "Type: " + "Order Service" + "<br/>";
    return new ResponseEntity<>(serviceInfo, HttpStatus.OK);
  }
}