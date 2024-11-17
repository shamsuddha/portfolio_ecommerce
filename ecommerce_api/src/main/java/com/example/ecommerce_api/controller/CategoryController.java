package com.example.ecommerce_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_api.controller.request_dto.CategorySearchDto;
import com.example.ecommerce_api.entity.Category;
import com.example.ecommerce_api.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity(categoryService.saveCategory(category), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        return new ResponseEntity(categoryService.updateCategory(category), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteCategory(@RequestBody Category category) {
        return new ResponseEntity(categoryService.deleteCategory(category), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Category>> search(@RequestBody CategorySearchDto categorySearchDto) {
        return new ResponseEntity(categoryService.searchCategory(categorySearchDto), HttpStatusCode.valueOf(200));
    }
}
