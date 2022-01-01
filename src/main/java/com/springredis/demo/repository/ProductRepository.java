package com.springredis.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.springredis.demo.entity.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
}
