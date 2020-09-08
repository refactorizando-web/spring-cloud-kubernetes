package com.refactorizando.model;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "order")
@Getter
@Setter
public class Order {

  @Id
  private BigInteger id;

  private String destination;

  private String description;

  private double price;

  @Field("departure_date")
  private LocalDate departureDate;

  @Field("arrival_date")
  private LocalDate arrivalDate;

}
