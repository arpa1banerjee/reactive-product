package com.example.product.service;

import com.example.product.exception.ProductDBException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final com.example.product.repository.ProductRepository productRepository;

    public void addProduct(Flux<Product> product) {
        productRepository.deleteAll().subscribe(null, null, () -> log.info("all data deleted"));
        Flux<Product> savedProduct = productRepository.saveAll(product);
        savedProduct
                .log()
                .subscribe(data -> log.info(data.toString()),
                        Throwable::printStackTrace,
                        () -> log.info("All products has been saved in DB"));
    }

    public Mono<Product> getProductById(String id) {
        log.info("fetching product from database with id: {}", id);
        return productRepository.findById(id)
                .log()
                .onErrorMap(ex -> new ProductDBException("could not connect to product db"))
                .switchIfEmpty(Mono.error(new ProductNotFoundException("product not found with id: " + id)));
    }

    public Flux<Product> getAllProducts() {
        log.info("fetching all products from DB");
        return productRepository.findAll()
                .log()
                .onErrorMap(ex -> new ProductDBException("Exception occurred while doing operation in product db", ex))
                .switchIfEmpty(Flux.error(new ProductNotFoundException("No products found")));
    }

    public Flux<Product> getProductsWithRating(String type, int rating) {
        log.info("fetching products with rating {} {}", type, rating);
        Flux<Product> products = Flux.empty();
        switch (type.toLowerCase()) {
            case "equal":
                products = productRepository.findByRating(rating);
                break;
            case "greaterthan":
                products = productRepository.findByRatingGreaterThan(rating);
                break;
            case "greaterthanequal":
                products = productRepository.findByRatingGreaterThanEqual(rating);
                break;
            case "lessthan":
                products = productRepository.findByRatingLessThan(rating);
                break;
            case "lessthanequal":
                products = productRepository.findByRatingLessThanEqual(rating);
                break;
            default:
                log.warn("not supported type!");
        }

        return products
                .log()
                .onErrorMap(ex -> new ProductDBException("Exception occurred ", ex))
                .switchIfEmpty(Flux.error(new ProductNotFoundException("No product found with rating " + type + " " +rating)));

    }
}
