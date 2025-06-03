package com.products.service;




import com.products.dto.FullOrderLineResponse;
import com.products.dto.LineOrderDto;
import com.products.dto.ProductDto;
import com.products.dto.ProductRequest;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductRequest request);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);

   List<FullOrderLineResponse> purchaseOrder(List<LineOrderDto> linesOrder);


}
