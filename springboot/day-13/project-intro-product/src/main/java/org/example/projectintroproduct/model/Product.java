package org.example.projectintroproduct.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private String thumbnail;
    private int price;
    private double rating;
    private int priceDiscount;
}
