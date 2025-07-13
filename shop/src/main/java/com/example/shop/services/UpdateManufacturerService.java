package com.example.shop.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.shop.exceptions.InvalidEntryException;
import com.example.shop.exceptions.ManufacturerNotFoundException;
import com.example.shop.models.Manufacturer;
import com.example.shop.repositories.ManufacturerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public UpdateManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public ResponseEntity<Manufacturer> execute(Integer id, Manufacturer manufacturer){
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);

        if(optionalManufacturer.isPresent()){
            validate(manufacturer);
            manufacturer.setManufacturerId(id);
            manufacturerRepository.save(manufacturer);
            return ResponseEntity.status(HttpStatus.OK).body(manufacturer);
        }

        throw new ManufacturerNotFoundException();
    }

    private void validate(Manufacturer manufacturer){

        if(StringUtil.isNullOrEmpty(manufacturer.getManufacturerName())){
            throw new InvalidEntryException("Manufacturer name can't be empty");
        }
    }
}
