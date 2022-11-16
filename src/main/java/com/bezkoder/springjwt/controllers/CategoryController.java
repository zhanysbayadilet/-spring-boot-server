package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.CategoryDTO;
import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.security.services.CategoryService;
import com.bezkoder.springjwt.util.NotCreatedException;
import com.bezkoder.springjwt.util.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<CategoryDTO> getCategories(){
        System.out.println(categoryService.findAll().stream().map(this::convertToCategoryDTO)
                .collect(Collectors.toList()));
        return categoryService.findAll().stream().map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }

    @PostMapping()
    public CategoryDTO create(@RequestBody @Valid CategoryDTO categoryDTO,
                          BindingResult bindingResult) {
        check(bindingResult);
        categoryService.save(convertToCategory(categoryDTO));
        return categoryDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategoryDTO> delete(@PathVariable("id") int id){
        if (categoryService.findOne(id) == null){
            throw new NotFoundException();
        }
        categoryService.delete(id);
        return categoryService.findAll().stream().map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }

    //convertTo
    public Category convertToCategory(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    public CategoryDTO convertToCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public void check(BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ){
                errorMsg.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new NotCreatedException(errorMsg.toString());
        }else {
            ResponseEntity.ok(HttpStatus.OK);
        }
    }

}
