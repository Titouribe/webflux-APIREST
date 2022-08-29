package com.rest.app.repositories;

import com.rest.app.domain.entities.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<Product> findAllByProductNameLike(String name);

    Mono<Product> findByProductNameLike(String name);
}
