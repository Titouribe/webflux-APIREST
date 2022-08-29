package com.rest.app.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String productId;
    private String productName;

    private BigDecimal productPrice;

    private Date dateCreated;
    private Category category;

    public Product(String productName, BigDecimal productPrice, Category category) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.dateCreated = new Date();
        this.category = category;
    }
}
