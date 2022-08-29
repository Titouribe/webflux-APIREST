package com.rest.app.domain.DTOS;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDTO {

    private String categoryId;
    @NotBlank
    private String categoryName;

}
