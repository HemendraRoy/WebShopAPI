package com.example.shop.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productId")
    private Integer productId;

    @Column(name="productName")
    private String productName;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Integer price;

    @Column(name="manufacturerId")
    private Integer manufacturerId;

    @Column(name="category")
    private String category;

    @Column(name="released")
    private LocalDateTime released;

    @Column(name="updated")
    private LocalDateTime updated;
}
