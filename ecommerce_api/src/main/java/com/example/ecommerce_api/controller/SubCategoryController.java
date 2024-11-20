package com.example.ecommerce_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_api.controller.request_dto.CategorySearchDto;
import com.example.ecommerce_api.controller.request_dto.SubCategorySearchDto;
import com.example.ecommerce_api.entity.Category;
import com.example.ecommerce_api.entity.SubCategory;
import com.example.ecommerce_api.service.CategoryService;
import com.example.ecommerce_api.service.SubCategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/sub-category")
@AllArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping("/save")
    public ResponseEntity<SubCategory> save(@RequestBody SubCategory subCategory) {
        return new ResponseEntity(subCategoryService.saveSubCategory(subCategory), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/update")
    public ResponseEntity<SubCategory> update(@RequestBody SubCategory subCategory) {
        return new ResponseEntity(subCategoryService.updateSubCategory(subCategory), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody SubCategory subCategory) {
        return new ResponseEntity(subCategoryService.deleteSubCategory(subCategory), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<SubCategory>> search(@RequestBody SubCategorySearchDto subCategorySearchDto) {
        return new ResponseEntity(subCategoryService.searchSubCategory(subCategorySearchDto), HttpStatusCode.valueOf(200));
    }

}
