package com.rest.app.services.impl;

import com.rest.app.constants.ValidConstants;
import com.rest.app.domain.entities.Category;
import com.rest.app.repositories.ICategoryRepository;
import com.rest.app.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Flux<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> findById(String idCategory) {
        return categoryRepository.findById(idCategory);
    }

    @Override
    public Mono<Category> findByCategoryName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    @Override
    public Mono<String> deleteCategoryById(String idCategory) {
        categoryRepository.deleteById(idCategory);
        return Mono.just(ValidConstants.deleteCorrectly("Category", idCategory));
    }

    @Override
    public Mono<String> deleteCategory(Category category) {
        categoryRepository.delete(category);
        return Mono.just(ValidConstants.deleteCorrectly("Category", category.getCategoryName()));
    }
}
