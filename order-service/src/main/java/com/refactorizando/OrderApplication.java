package com.refactorizando;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OrderApplication implements CommandLineRunner {


  public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class, args);
  }

  public void run(String... args) throws Exception {
    log.info("Order service is running ");
  }

}