package com.rest.app.services.impl;

import com.rest.app.constants.Constants;
import com.rest.app.constants.ErrorConstants;
import com.rest.app.constants.ValidConstants;
import com.rest.app.domain.entities.Product;
import com.rest.app.exceptions.RequestException;
import com.rest.app.repositories.IProductRepository;
import com.rest.app.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    private static final String PRODUCT = "Product";

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Flux<Product> findAllByProductNameLike(String name) {
        return productRepository.findAllByProductNameLike(name)
                .switchIfEmpty(Flux
                        .error(new RequestException(Constants.NOT_FOUND, ErrorConstants.notFound(Constants.PRODUCT, name))));
    }

    @Override
    public Mono<Product> findById(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono
                        .error(new RequestException(Constants.NOT_FOUND, ErrorConstants.notFound(Constants.PRODUCT, id))));
    }

    @Override
    public Mono<Product> findByNameLike(String name) {
        return productRepository.findByProductNameLike(name)
                .switchIfEmpty(Mono
                        .error(new RequestException(Constants.NOT_FOUND, ErrorConstants.notFound(Constants.PRODUCT, name))));
    }

    @Transactional
    @Override
    public Mono<Void> delete(Product product) {
        return productRepository.delete(product);
    }

    @Transactional
    @Override
    public Mono<Product> saveProduct(Product product) {
        product.setDateCreated(new Date());
        return productRepository.save(product);
    }

}
