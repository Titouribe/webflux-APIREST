package com.rest.app.controllers;

import com.rest.app.constants.Constants;
import com.rest.app.constants.ErrorConstants;
import com.rest.app.constants.ValidConstants;
import com.rest.app.domain.DTOS.ProductDTO;
import com.rest.app.domain.mappers.ProductMapper;
import com.rest.app.exceptions.RequestException;
import com.rest.app.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public Mono<ResponseEntity<Flux<ProductDTO>>> findAll(@RequestParam(required = false) String name) {
        if (name == null) {
            return Mono.just(ResponseEntity.ok(productService.findAll().map(productMapper::toDTO)));
        } else {
            return Mono.just(ResponseEntity.ok(productService.findAllByProductNameLike(name).map(productMapper::toDTO)));
        }
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> findById(@PathVariable String id) {
        return productService.findById(id)
                .map(product -> ResponseEntity.ok(productMapper.toDTO(product)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Mono<ProductDTO>>> saveProduct(@RequestBody @Valid ProductDTO productDTO){
        return Mono.just(new ResponseEntity<>(productService.saveProduct(productMapper.toEntity(productDTO))
                .map(productMapper::toDTO), HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Mono<ProductDTO>>> updateProduct(@RequestBody @Valid ProductDTO productDTO,
                                                                @PathVariable String id){
        return productService.findById(id)
                .flatMap(product -> {
                    product.setProductName(productDTO.getProductName());
                    product.setProductPrice(productDTO.getProductPrice());
                    product.setCategory(productDTO.getCategory());
                    return Mono.just(ResponseEntity.ok(productService.saveProduct(product).map(productMapper::toDTO)));
                })
                .switchIfEmpty(Mono
                        .error(new RequestException(Constants.NOT_FOUND, ErrorConstants.notFound(Constants.PRODUCT, id))));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deleteById(@PathVariable String id){
        return productService.findById(id)
                .flatMap(product -> productService.delete(product)
                        .then(Mono.just(ResponseEntity.ok(ValidConstants.deleteCorrectly(Constants.PRODUCT,product.getProductName())))))
                .defaultIfEmpty(new ResponseEntity<>(ErrorConstants
                        .notFound(Constants.PRODUCT, id),HttpStatus.NOT_FOUND));
    }
}
