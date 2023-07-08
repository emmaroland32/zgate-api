package com.programmingtechie.productservice.repository;

import com.programmingtechie.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<Product, String> {
}
