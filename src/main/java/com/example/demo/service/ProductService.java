package com.example.demo.service;
import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> readAllProducts();
    Product createProduct(ProductDto productDto);
  //  ProductDto readSingleProduct(Long id);
  //  void deleteSingleProduct(Long id);
  //  Product updateSingleProduct(ProductDto productDto);


}

