package com.example.demo.model;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@Data

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int price;

    @Column
    private String image;

    @OneToOne(fetch=FetchType.EAGER)
    private Category category;

}
