package com.example.shop.services;

import com.example.shop.models.Manufacturer;
import com.example.shop.repositories.ManufacturerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetManufacturersService {

    private final ManufacturerRepository manufacturerRepository;


    public GetManufacturersService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public ResponseEntity<List<Manufacturer>> execute(){
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(manufacturers);
    }
}
