package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.util.ProductTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@Import(ProductService.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void it_should_return_all_products() {

        Mockito.when(productRepository.findAll()).thenReturn(ProductTestUtil.createProducts());

        Flux<Product> allProducts = productService.getAllProducts();

        StepVerifier.create(allProducts.log())
                .expectNextCount(5)
                .verifyComplete();

    }

    @Test
    void it_should_throw_product_not_found_exception() {

        Mockito.when(productRepository.findAll()).thenReturn(ProductTestUtil.emptyProductFlux());

        Flux<Product> allProducts = productService.getAllProducts();

        StepVerifier.create(allProducts.log())
                .expectErrorMessage("No products found")
                .verify();

    }

    @Test
    void it_should_return_a_product_by_id() {

        Mockito.when(productRepository.findById("1234")).thenReturn(
                Mono.just(Product.builder()
                        .id("1234")
                        .description("some product description")
                        .fileName("some_file.jpeg")
                        .height(100)
                        .width(100)
                        .price(240.0)
                        .rating(4)
                        .title("some product")
                        .build())
        );

        Mono<Product> productMono = productService.getProductById("1234");

        StepVerifier.create(productMono.log())
                .expectSubscription()
                .expectNextMatches(product -> product.getId().equals("1234"))
                .verifyComplete();

    }
}
