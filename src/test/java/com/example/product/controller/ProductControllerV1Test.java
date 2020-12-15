package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import com.example.product.util.ProductTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductControllerV1.class)
class ProductControllerV1Test {

    @MockBean
    ProductService productService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void it_should_return_all_products() {

        when(productService.getAllProducts())
                .thenReturn(ProductTestUtil.createProducts());

        webTestClient.get().uri("/v1/product/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$", hasSize(5));

        Mockito.verify(productService, times(1)).getAllProducts();

    }

    @Test
    void it_should_return_a_product_by_id() {
        Product product = Product.builder()
                .id("1234")
                .description("some product description")
                .fileName("some_file.jpeg")
                .height(100)
                .width(100)
                .price(240.0)
                .rating(4)
                .title("some product")
                .build();

        Mockito.when(productService.getProductById("1234"))
                .thenReturn(Mono.just(product));

        webTestClient.get().uri("/v1/product/{id}", "1234")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.payload").isNotEmpty()
                .jsonPath("$.payload.id").isEqualTo("1234")
                .jsonPath("$.payload.title").isEqualTo("some product")
                .jsonPath("$.payload.price").isEqualTo(240.0);

        Mockito.verify(productService, times(1)).getProductById("1234");

    }

    @Test
    void it_should_return_product_with_rating_greater_than() {
        Mockito.when(productService.getProductsWithRating("greaterthan",4))
                .thenReturn(ProductTestUtil.productWithRatingGreaterThan(4));

        webTestClient.get().uri("/v1/product/rating/{type}/{rating}", "greaterthan", 4)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].title").isEqualTo("some product5")
                .jsonPath("$.[0].description").isEqualTo("some product description5")
                .jsonPath("$.[0].rating").isEqualTo(5);


        Mockito.verify(productService, times(1)).getProductsWithRating("greaterthan", 4);

    }

    @Test
    void it_should_return_product_with_rating_greater_than_equal_to() {
        Mockito.when(productService.getProductsWithRating("greaterthanequal",4))
                .thenReturn(ProductTestUtil.productWithRatingGreaterThanEqual(4));

        webTestClient.get().uri("/v1/product/rating/{type}/{rating}", "greaterthanequal", 4)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].title").isEqualTo("some product4")
                .jsonPath("$.[0].description").isEqualTo("some product description4")
                .jsonPath("$.[0].rating").isEqualTo(4)
                .jsonPath("$[1].title").isEqualTo("some product5")
                .jsonPath("$.[1].description").isEqualTo("some product description5")
                .jsonPath("$.[1].rating").isEqualTo(5);


        Mockito.verify(productService, times(1)).getProductsWithRating("greaterthanequal", 4);

    }

    @Test
    void it_should_return_product_with_rating_less_than() {
        Mockito.when(productService.getProductsWithRating("lessthan",2))
                .thenReturn(ProductTestUtil.productWithRatingLessThan(2));

        webTestClient.get().uri("/v1/product/rating/{type}/{rating}", "lessthan", 2)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].title").isEqualTo("some product1")
                .jsonPath("$.[0].description").isEqualTo("some product description1")
                .jsonPath("$.[0].rating").isEqualTo(1);


        Mockito.verify(productService, times(1)).getProductsWithRating("lessthan", 2);

    }

    @Test
    void it_should_return_product_with_rating_less_than_equal_to() {
        Mockito.when(productService.getProductsWithRating("lessthanequal",2))
                .thenReturn(ProductTestUtil.productWithRatingLessThanEqual(2));

        webTestClient.get().uri("/v1/product/rating/{type}/{rating}", "lessthanequal", 2)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].title").isEqualTo("some product1")
                .jsonPath("$.[0].description").isEqualTo("some product description1")
                .jsonPath("$.[0].rating").isEqualTo(1)
                .jsonPath("$[1].title").isEqualTo("some product2")
                .jsonPath("$.[1].description").isEqualTo("some product description2")
                .jsonPath("$.[1].rating").isEqualTo(2);


        Mockito.verify(productService, times(1)).getProductsWithRating("lessthanequal", 2);

    }

    @Test
    void it_should_return_product_with_rating_equal_to() {
        Mockito.when(productService.getProductsWithRating("equal",4))
                .thenReturn(ProductTestUtil.productWithRatingEqual(4));

        webTestClient.get().uri("/v1/product/rating/{type}/{rating}", "equal", 4)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].title").isEqualTo("some product4")
                .jsonPath("$.[0].description").isEqualTo("some product description4")
                .jsonPath("$.[0].rating").isEqualTo(4);


        Mockito.verify(productService, times(1)).getProductsWithRating("equal", 4);

    }
}
