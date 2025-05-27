package com.products.service;





import com.products.dao.CategoryRepository;
import com.products.dto.CategoryDto;
import com.products.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }




    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryDto(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return new CategoryDto(c.getId(), c.getName());
    }
}
