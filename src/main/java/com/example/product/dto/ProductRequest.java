package com.example.product.dto;

import com.couchbase.client.java.repository.annotation.Field;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String title;
    private String fileName;
    private int width;
    private int height;
    private String type;
    private String description;

    @Min(value = 0 , message = "Value should be greater than or equal to 0")
    @Max(value = 5 , message = "Value should be less than or equal to 5")
    @NotNull(message = "It can not be null. Please provide no. in b/w 0 to 5")
    private Integer rating;

    @Min(value = 0 , message = "Value should be greater than 0")
    @NotNull(message = "It can not be null. Please provide a value")
    private Double price;

}
