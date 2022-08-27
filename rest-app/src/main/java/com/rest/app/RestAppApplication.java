package com.rest.app;

import com.rest.app.domain.entities.Category;
import com.rest.app.domain.entities.Product;
import com.rest.app.repositories.ICategoryRepository;
import com.rest.app.repositories.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@SpringBootApplication
public class RestAppApplication implements CommandLineRunner {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    private static final Logger log = LoggerFactory.getLogger(RestAppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        mongoTemplate.dropCollection("products").subscribe();
        mongoTemplate.dropCollection("categories").subscribe();

        Category books = new Category("Books");
        Category tech = new Category("Tech");
        Category vehicles = new Category("Vehicles");

        Flux.just(books, tech, vehicles)
                .flatMap(categoryRepository::save)
                .doOnNext(category -> log.info(category.getCategoryName()))
                .thenMany(Flux.just(new Product("product test one", BigDecimal.valueOf(99.9), books),
                                new Product("product test two", BigDecimal.valueOf(102), books),
                                new Product("product test three", BigDecimal.valueOf(86.5), vehicles),
                                new Product("product test four", BigDecimal.valueOf(22), tech),
                                new Product("product test five", BigDecimal.valueOf(55), tech))
                        .flatMap(product -> productRepository.save(product)))
                .subscribe(product -> log.info(product.getProductName()));

    }
}
