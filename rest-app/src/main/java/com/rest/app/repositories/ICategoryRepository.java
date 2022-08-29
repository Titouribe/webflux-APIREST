package com.rest.app.repositories;

import com.rest.app.domain.entities.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ICategoryRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> findByCategoryName(String categoryName);
}
