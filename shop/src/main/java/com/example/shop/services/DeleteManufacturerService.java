package com.example.shop.services;

import com.example.shop.exceptions.ManufacturerNotFoundException;
import com.example.shop.models.Manufacturer;
import com.example.shop.models.Product;
import com.example.shop.repositories.ManufacturerRepository;
import com.example.shop.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeleteManufacturerService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;


    public DeleteManufacturerService(ProductRepository productRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public ResponseEntity<Void> execute(Integer id){
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);

        if(optionalManufacturer.isPresent()){
            List<Product> products = productRepository.findByManufacturerId(id);
            for(Product product: products){
                productRepository.delete(product);
            }
            manufacturerRepository.delete(optionalManufacturer.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ManufacturerNotFoundException();
    }
}
