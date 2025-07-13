package com.example.shop.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="manufacturers")
@Data
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manufacturerId")
    private Integer manufacturerId;

    @Column(name="manufacturerName")
    private String manufacturerName;

    @Column(name="Region")
    private String region;
}
