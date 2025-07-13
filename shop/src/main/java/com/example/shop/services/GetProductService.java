package com.example.shop.services;

import com.example.shop.exceptions.ProductNotFoundException;
import com.example.shop.models.Manufacturer;
import com.example.shop.models.Product;
import com.example.shop.models.ProductData;
import com.example.shop.repositories.ManufacturerRepository;
import com.example.shop.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;


    public GetProductService(ProductRepository productRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public ResponseEntity<ProductData> execute(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            int manufacturerId = product.getManufacturerId();
            Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(manufacturerId);
            //manufacturer obviously exists as the product is present with manufacturerId
            Manufacturer manufacturer = optionalManufacturer.get();
            return ResponseEntity.status(HttpStatus.OK).body(new ProductData(product,manufacturer));

        }

        throw new ProductNotFoundException();
    }
}
