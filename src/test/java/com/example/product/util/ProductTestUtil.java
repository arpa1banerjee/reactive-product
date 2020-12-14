package com.example.product.util;

import com.example.product.model.Product;
import reactor.core.publisher.Flux;

public class ProductTestUtil {

    public static Flux<Product> emptyProductFlux() {
        return Flux.empty();
    }

    public static Flux<Product> createProducts() {
        Product product1 = Product.builder().id("1234").description("some product description1").fileName("some_file1.jpeg").height(100).width(100).price(240.0).rating(1).title("some product1").build();
        Product product2 = Product.builder().id("1235").description("some product description2").fileName("some_file2.jpeg").height(200).width(200).price(241.0).rating(2).title("some product2").build();
        Product product3 = Product.builder().id("1236").description("some product description3").fileName("some_file3.jpeg").height(300).width(300).price(242.0).rating(3).title("some product3").build();
        Product product4 = Product.builder().id("1237").description("some product description4").fileName("some_file4.jpeg").height(400).width(400).price(243.0).rating(4).title("some product4").build();
        Product product5 = Product.builder().id("1238").description("some product description5").fileName("some_file5.jpeg").height(500).width(500).price(244.0).rating(5).title("some product5").build();

        return Flux.just(product1, product2, product3, product4, product5);
    }

    public static Flux<Product> productWithRatingEqual(int rating) {
        return createProducts()
                .filter(product -> product.getRating() == rating);
    }
    public static Flux<Product> productWithRatingGreaterThanEqual(int rating) {
        return createProducts()
                .filter(product -> product.getRating() >= rating);
    }
    public static Flux<Product> productWithRatingGreaterThan(int rating) {
        return createProducts()
                .filter(product -> product.getRating() > rating);
    }
    public static Flux<Product> productWithRatingLessThanEqual(int rating) {
        return createProducts()
                .filter(product -> product.getRating() <= rating);
    }
    public static Flux<Product> productWithRatingLessThan(int rating) {
        return createProducts()
                .filter(product -> product.getRating() < rating);
    }
}
