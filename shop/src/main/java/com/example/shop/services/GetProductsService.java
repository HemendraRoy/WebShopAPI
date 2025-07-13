package com.example.shop.services;

import com.example.shop.models.Manufacturer;
import com.example.shop.models.Product;
import com.example.shop.models.ProductData;
import com.example.shop.repositories.ManufacturerRepository;
import com.example.shop.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetProductsService {
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;


    public GetProductsService(ProductRepository productRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public ResponseEntity<List<ProductData>> execute(){
        List<Product> products = productRepository.findAll();
        List<ProductData> productDatas = new ArrayList<>();
        for(Product product : products){
            Optional<Manufacturer> manufacturer = manufacturerRepository.findById(product.getManufacturerId());
            //manufacturer obviously exists as the product is present with manufacturerId
            productDatas.add(new ProductData(product,manufacturer.get()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(productDatas);
    }
}
