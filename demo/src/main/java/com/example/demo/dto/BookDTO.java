package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Image;

import lombok.Data;
@Data
public class BookDTO {
    
    private Long id;
    private String title; 
    private String author;
    private Integer pages;
    private Double price;
    private Integer stock;
    private Image image;
    private List<String> categories;

}
