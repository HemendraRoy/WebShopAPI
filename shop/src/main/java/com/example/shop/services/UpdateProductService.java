package com.example.shop.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.shop.exceptions.InvalidEntryException;
import com.example.shop.exceptions.ManufacturerNotFoundException;
import com.example.shop.exceptions.ProductNotFoundException;
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
public class UpdateProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;

    public UpdateProductService(ProductRepository productRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
    }


    public ResponseEntity<ProductData> execute(Integer id, Product product){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){

            validate(product);
            Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(product.getManufacturerId());

            if(optionalManufacturer.isPresent()){
                LocalDateTime localDateTime = LocalDateTime.now();
                product.setUpdated(localDateTime);
                product.setProductId(id);
                product.setReleased(optionalProduct.get().getReleased());

                productRepository.save(product);
                ProductData productData = new ProductData(product,optionalManufacturer.get());
                return ResponseEntity.status(HttpStatus.OK).body(productData);
            }

            throw new ManufacturerNotFoundException();
        }

        throw new ProductNotFoundException();
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
}
