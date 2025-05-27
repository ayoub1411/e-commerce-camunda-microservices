package com.products.service;

import com.products.dao.CategoryRepository;
import com.products.dao.ProductRepository;
import com.products.dto.ProductDto;
import com.products.dto.ProductRequest;
import com.products.entities.Product;
import com.products.mapper.ProductMapper;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final ProductMapper productDtoMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ProductMapper productMapper,
                              ProductMapper productDtoMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.productDtoMapper = productDtoMapper;
    }

    @Override
    public ProductDto createProduct(ProductRequest request) {

        // Use mapper to convert request to entity (handles category lookup internally)
        Product product = productMapper.toEntity(request);

        // Save the product
        product = productRepository.save(product);

        // Use mapper to convert entity to DTO
        return productDtoMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return productDtoMapper.toDto(product);
    }






}