package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.Data;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDto {
    private Long id;
    private String name;
    private Date dateCommande;
    private int quantity;
    private int stock;
    private Long userId;
    private Set <Product> products = new HashSet<>();
}
