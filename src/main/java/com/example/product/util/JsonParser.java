package com.example.product.util;

import com.example.product.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class JsonParser {

    public List<Product> extractProductsFromJson(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> products = new ArrayList<>();
        try {
            products = Arrays.asList(
                    mapper.readValue(
                            new File(getClass().getClassLoader().getResource(fileName).toURI()),
                            Product[].class
                    )
            );
            products = products.stream()
                    .peek(prd -> prd.setId(UUID.randomUUID().toString()))
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            log.error("some exception occurred while parsing product json file", e);
        }
        return products;
    }

}
