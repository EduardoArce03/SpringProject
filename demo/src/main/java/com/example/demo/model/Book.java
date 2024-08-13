package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    private String author;

    @NotNull(message = "Pages cannot be null")
    private Integer pages;

    @NotNull(message = "Price cannot be null")
    private Double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id" , referencedColumnName = "id")
    private Image image;
}
