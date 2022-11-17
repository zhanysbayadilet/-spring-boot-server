package com.bezkoder.springjwt.dto;

import lombok.Data;

@Data
public class CategoryDTO {

    private int id;

    private String category_img;

    private String category_name;

    private String category_description;
}
