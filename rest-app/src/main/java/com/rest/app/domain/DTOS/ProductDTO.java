package com.rest.app.domain.DTOS;

import com.rest.app.domain.entities.Category;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductDTO {

    private String productId;

    @NotBlank
    private String productName;

    @Min(1)
    private BigDecimal productPrice;

    private Date dateCreated;

    @NotNull
    @Valid
    private Category category;
}
