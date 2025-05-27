package com.products.service;




import com.products.dto.CategoryDto;

import java.util.List;

public interface CategoryService {


    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
}
