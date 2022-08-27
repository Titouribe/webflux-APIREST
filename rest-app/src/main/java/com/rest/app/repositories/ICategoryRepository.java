package com.rest.app.repositories;

import com.rest.app.domain.entities.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends ReactiveMongoRepository<Category, String> {
}
