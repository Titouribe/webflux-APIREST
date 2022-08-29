package com.rest.app.services;

import com.rest.app.domain.entities.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    Flux<Product> findAll();

    Flux<Product> findAllByProductNameLike(String name);

    Mono<Product> findById(String id);

    Mono<Product> findByNameLike(String name);

    Mono<Void> delete(Product product);

    Mono<Product> saveProduct(Product product);
}
