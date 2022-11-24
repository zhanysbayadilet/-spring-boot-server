package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.Category;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryDTO {

    private int id;
    private String category_img;
    private String category_name;
    private String category_description;

    public CategoryDTO toDto(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .category_img(category.getCategory_img())
                .category_name(category.getCategory_name())
                .category_description(category.getCategory_description())
                .build();
    }
}
