package com.rest.app.domain.mappers;

import com.rest.app.domain.DTOS.CategoryDTO;
import com.rest.app.domain.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
