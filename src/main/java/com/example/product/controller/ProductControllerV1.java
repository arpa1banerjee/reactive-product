package com.example.product.controller;

import com.example.product.dto.ProductRequest;
import com.example.product.model.Product;
import com.example.product.service.ProductService;
import com.example.product.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductControllerV1 {

    private final ProductService productService;

    @GetMapping()
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<Product>>> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(product -> new ResponseEntity<>(
                        ApiResponse.ofSuccess(
                                HttpStatus.OK.value(),
                                "Successfully fetched product",
                                product),
                        HttpStatus.OK)
                );
    }

    @GetMapping("/rating/{type}/{val}")
    public Flux<Product> getProductsWithRating(@PathVariable String type, @PathVariable int val) {
        return productService.getProductsWithRating(type, val);
    }

    @PostMapping
    public Mono<ResponseEntity<ApiResponse<Product>>> saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest)
                .map(product -> new ResponseEntity<>(
                        ApiResponse.ofSuccess(
                                HttpStatus.OK.value(),
                                "Successfully saved product",
                                product),
                        HttpStatus.OK)
                );
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<Product>>> updateProduct(@PathVariable String id,
                                                                    @Valid @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest)
                .map(product -> new ResponseEntity<>(
                        ApiResponse.ofSuccess(
                                HttpStatus.OK.value(),
                                "Successfully updated product",
                                product),
                        HttpStatus.OK)
                );
    }

}
