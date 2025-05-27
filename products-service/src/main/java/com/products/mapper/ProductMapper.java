
package com.products.mapper;

import com.products.dao.CategoryRepository;
import com.products.dto.ProductRequest;
import com.products.entities.Product;
import com.products.entities.Category;
import com.products.dto.ProductDto;
import com.products.dto.CategoryDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper {

    CategoryRepository categoryRepository;

    /**
     * Converts Product entity to ProductDto
     * @param product Product entity
     * @return ProductDto with nested CategoryDto
     */
    public ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        CategoryDto categoryDto = toCategoryDto(product.getCategory());

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                categoryDto
        );
    }


    public Product toEntity(ProductRequest request) {
        if (request == null) {
            return null;
        }

        Category category = null;
        if (request.categoryId() != null) {
            category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + request.categoryId()));
        }

        return new Product(
                request.name(),
                request.price(),
                category
        );
    }

    /**
     * Converts Category entity to CategoryDto
     * @param category Category entity
     * @return CategoryDto
     */
    private CategoryDto toCategoryDto(Category category) {
        if (category == null) {
            return null;
        }

        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

    /**
     * Converts ProductDto back to Product entity
     * Note: This creates a new Product with Category reference
     * @param productDto ProductDto
     * @return Product entity
     */
    public Product toEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        Category category = toCategoryEntity(productDto.category());

        Product product = new Product(
                productDto.name(),
                productDto.price(),
                category
        );

        // Set ID if converting existing DTO back to entity
        if (productDto.id() != null) {
            // Note: You might need a setter for ID or use reflection
            // This assumes you have a way to set the ID
        }

        return product;
    }

    /**
     * Converts CategoryDto to Category entity
     * @param categoryDto CategoryDto
     * @return Category entity
     */
    private Category toCategoryEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }

        Category category = new Category(categoryDto.name());
        // Set ID if needed - might require reflection or setter

        return category;
    }
}