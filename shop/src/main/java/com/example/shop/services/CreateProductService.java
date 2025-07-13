package com.example.shop.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.shop.exceptions.InvalidEntryException;
import com.example.shop.exceptions.ManufacturerNotFoundException;
import com.example.shop.models.Manufacturer;
import com.example.shop.models.Product;
import com.example.shop.models.ProductData;
import com.example.shop.repositories.ManufacturerRepository;
import com.example.shop.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CreateProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;

    public CreateProductService(ProductRepository productRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public ResponseEntity<ProductData> execute(Product product){

        validate(product);
        setTime(product);
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(product.getManufacturerId());

        if(optionalManufacturer.isPresent()) {
            productRepository.save(product);
            ProductData productData = new ProductData(product,optionalManufacturer.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(productData);
        }

        throw new ManufacturerNotFoundException();
    }

    private void validate(Product product){
        if(StringUtil.isNullOrEmpty(product.getProductName())){
            throw new InvalidEntryException("Name can't be empty");
        }
        if(product.getManufacturerId() == null){
            throw new InvalidEntryException("Manufacturer Id must be provided");
        }
        if(product.getPrice() == null || product.getPrice() <=0){
            throw new InvalidEntryException("Price can't be less than zero or null");
        }
    }

    private void setTime(Product product){
        LocalDateTime localDateTime = LocalDateTime.now();
        product.setReleased(localDateTime);
        product.setUpdated(localDateTime);
    }
}
