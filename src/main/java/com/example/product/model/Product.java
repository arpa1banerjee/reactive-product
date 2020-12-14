package com.example.product.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {
    @Id
    private String id;

    @Field
    private String title;

    @Field
    @JsonAlias("filename")
    private String fileName;

    @Field
    private int width;

    @Field
    private int height;

    @Field
    private String type;

    @Field
    private String description;

    @Field
    private Integer rating;

    @Field
    private Double price;


}
