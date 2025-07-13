package com.example.shop;

import com.example.shop.models.Manufacturer;
import com.example.shop.models.Product;
import com.example.shop.models.ProductData;
import com.example.shop.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopHandler {

    private final CreateManufacturerService createManufacturerService;
    private final CreateProductService createProductService;
    private final GetManufacturerService getManufacturerService;
    private final GetProductService getProductService;
    private final GetProductsService getProductsService;
    private final GetManufacturersService getManufacturersService;
    private final DeleteProductService deleteProductService;
    private final DeleteManufacturerService deleteManufacturerService;
    private final UpdateProductService updateProductService;
    private final UpdateManufacturerService updateManufacturerService;

    public ShopHandler(CreateManufacturerService createManufacturerService, CreateProductService createProductService, GetManufacturerService getManufacturerService, GetProductService getProductService, GetProductsService getProductsService, GetManufacturersService getManufacturersService, DeleteProductService deleteProductService, DeleteManufacturerService deleteManufacturerService, UpdateProductService updateProductService, UpdateManufacturerService updateManufacturerService) {
        this.createManufacturerService = createManufacturerService;
        this.createProductService = createProductService;
        this.getManufacturerService = getManufacturerService;
        this.getProductService = getProductService;
        this.getProductsService = getProductsService;
        this.getManufacturersService = getManufacturersService;
        this.deleteProductService = deleteProductService;
        this.deleteManufacturerService = deleteManufacturerService;
        this.updateProductService = updateProductService;
        this.updateManufacturerService = updateManufacturerService;
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProductData> getProduct(@PathVariable Integer id){
        return getProductService.execute(id);
    }

    @GetMapping("/manufacturer/{id}")
    public ResponseEntity<Manufacturer> getManufacturer(@PathVariable Integer id){
        return getManufacturerService.execute(id);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductData>> getProducts(){
        return getProductsService.execute();
    }

    @GetMapping("manufacturers")
    public ResponseEntity<List<Manufacturer>> getManufacturers(){
        return getManufacturersService.execute();
    }

    @PostMapping("/product")
    public ResponseEntity<ProductData> createProduct(@RequestBody Product product){
        return createProductService.execute(product);
    }

    @PostMapping("/manufacturer")
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody Manufacturer manufacturer){
        return createManufacturerService.execute(manufacturer);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        return deleteProductService.execute(id);
    }

    @DeleteMapping("/manufacturer/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Integer id){
        return deleteManufacturerService.execute(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductData> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        return updateProductService.execute(id,product);
    }

    @PutMapping("manufacturer/{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable Integer id, @RequestBody Manufacturer manufacturer){
        return updateManufacturerService.execute(id,manufacturer);
    }
}
