package com.rest.app.services;

import com.rest.app.domain.entities.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICategoryService {
    Flux<Category> findAll();

    Mono<Category> findById(String idCategory);

    Mono<Category> findByCategoryName(String name);

    Mono<String> deleteCategoryById(String idCategory);

    Mono<String> deleteCategory(Category category);

}
