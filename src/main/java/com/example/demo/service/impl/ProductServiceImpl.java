package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private ModelMapper modelMapper = new ModelMapper();


    public List<ProductDto> readAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapFromProductToDto).collect(toList());
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
       // User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
       // product.setUsername(loggedInUser.getUsername());
        return productRepository.save(product);
    }

    /*@Override
    public ProductDto readSingleProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromProductToDto(product);
    }

    @Override
    public Product updateSingleProduct(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        return productRepository.save(product);
    }

    @Override
    public void deleteSingleProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("For id " + id));
        productRepository.delete(product);
    }*/

    private ProductDto mapFromProductToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

}