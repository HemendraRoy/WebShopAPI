package com.example.shop.services;

import com.example.shop.exceptions.ManufacturerNotFoundException;
import com.example.shop.models.Manufacturer;
import com.example.shop.repositories.ManufacturerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public GetManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public ResponseEntity<Manufacturer> execute(Integer id){
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);

        if(optionalManufacturer.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalManufacturer.get());
        }

        throw new ManufacturerNotFoundException();
    }
}
