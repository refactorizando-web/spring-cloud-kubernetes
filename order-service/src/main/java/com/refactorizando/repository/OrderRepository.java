package com.refactorizando.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.refactorizando.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {


}
