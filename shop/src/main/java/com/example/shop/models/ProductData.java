package com.example.shop.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductData {
    private int productId;

    private String productName;

    private int price;

    private int manufacturerId;

    private String description;

    private String category;

    private LocalDateTime released;

    private LocalDateTime updated;

    private String manufacturerName;

    private String region;

    public ProductData(Product product, Manufacturer manufacturer){
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.manufacturerId = product.getManufacturerId();
        this.category = product.getCategory();
        this.released = product.getReleased();
        this.updated = product.getUpdated();
        this.manufacturerName = manufacturer.getManufacturerName();
        this.region = manufacturer.getRegion();
    }
}
