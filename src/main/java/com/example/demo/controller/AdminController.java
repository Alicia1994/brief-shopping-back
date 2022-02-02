package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin")

public class AdminController {

    @Autowired
    private ProductService productService;

    /* HANDLE POSTS */
    @PostMapping
    public ResponseEntity<Product> createPost(@RequestPart("product") ProductDto productDto,
                                              @RequestPart("file") MultipartFile file)
            throws IOException {
        String fileName = "";
        Product product = null;
        if (null != file) {
            String[] nameExtension = Objects.requireNonNull(file.getContentType().split("/"));
            fileName = "post-name" + "." + nameExtension[1];
            productDto.setImage(fileName);
            product = productService.createProduct(productDto);
          //  FileUtils.saveImages(product.getId(), fileName, file);
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping
    public ResponseEntity<Product> updatePost(@RequestPart ("product") ProductDto productDto,
                                              @RequestPart(value = "file", required = false) MultipartFile file)
            throws IOException {
        String fileName = "";
        String lastFile ="";
        Product product = null;
        if (file == null){
          //  product = productService.updateSingleProduct(productDto);
        } else {
            String [] nameExtension = Objects.requireNonNull(file.getContentType().split("/"));
            fileName = "product-name" + "." + nameExtension[1];
            lastFile = productDto.getImage() != null ? productDto.getImage() : "";
            productDto.setImage(fileName);
           // product = productService.updateSingleProduct(productDto);
          //  FileUtils.saveFileAndReplace(lastFile, file, fileName, product.getId());
        }
        return new ResponseEntity(product, HttpStatus.OK);
    }

   // @DeleteMapping("/{id}")
   // public void deletePost(@PathVariable Long id) {
      //  productService.deleteSingleProduct(id);
  //  }

}
