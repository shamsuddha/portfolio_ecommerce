package com.example.ecommerce_api.service.common;

import com.example.ecommerce_api.entity.*;
import com.example.ecommerce_api.exception.UserInformException;
import com.example.ecommerce_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EntityValidationService {

    private final BedRepository bedRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subcategoryRepository;

    public Bed validateBed(String id) {
        Objects.requireNonNull(id);
        return bedRepository.findById(id)
                .orElseThrow(() -> new UserInformException(String
                        .format("Bed not found with id: [%s]", id)));
    }

    public Category validateCategory(String id) {
        Objects.requireNonNull(id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new UserInformException(String
                        .format("Category not found with id: [%s]", id)));
    }

    public SubCategory validateSubCategory(String id) {
        Objects.requireNonNull(id);
        return subcategoryRepository.findById(id)
                .orElseThrow(() -> new UserInformException(String
                        .format("SubCategory not found with id: [%s]", id)));
    }

}
