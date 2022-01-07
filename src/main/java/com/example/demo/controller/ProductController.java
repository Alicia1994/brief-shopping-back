package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> readAllProducts() {
        return new ResponseEntity<>(productService.readAllProducts(), HttpStatus.OK);
    }

   // @GetMapping("/get/{id}")
    //public ResponseEntity<ProductDto> readSingleProduct(@PathVariable Long id) {
    //    return new ResponseEntity<>(productService.readSingleProduct(id), HttpStatus.OK);
    //}

}