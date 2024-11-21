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

  private final CategoryRepository categoryRepository;

  public Category validateCategory(String id) {
    Objects.requireNonNull(id);
    return categoryRepository.findById(id)
        .orElseThrow(() -> new UserInformException(String
            .format("Category not found with id: [%s]", id)));
  }

}
