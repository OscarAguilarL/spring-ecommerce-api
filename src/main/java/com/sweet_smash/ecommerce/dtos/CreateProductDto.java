package com.sweet_smash.ecommerce.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateProductDto {

    private String name;
    private String label;
    private String description;
    private double unitPrice;
    private List<String> images;
    private String stockName;
}
