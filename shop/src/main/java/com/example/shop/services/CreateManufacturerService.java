package com.example.shop.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.shop.exceptions.InvalidEntryException;
import com.example.shop.models.Manufacturer;
import com.example.shop.repositories.ManufacturerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class CreateManufacturerService {
    private final ManufacturerRepository manufacturerRepository;


    public CreateManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public ResponseEntity<Manufacturer> execute(Manufacturer manufacturer){

        validate(manufacturer);

        manufacturerRepository.save(manufacturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(manufacturer);
    }

    private void validate(Manufacturer manufacturer){

        if(StringUtil.isNullOrEmpty(manufacturer.getManufacturerName())){
            throw new InvalidEntryException("Manufacturer name can't be empty");
        }
    }
}
