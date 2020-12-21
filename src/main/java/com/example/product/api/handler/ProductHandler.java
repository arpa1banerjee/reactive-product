package com.example.product.api.handler;

import com.example.product.dto.ProductRequest;
import com.example.product.model.Product;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    private final ProductService productService;

    public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAllProducts(), Product.class);
    }

    public Mono<ServerResponse> getProductById(ServerRequest serverRequest) {

        Mono<Product> productMono = productService.getProductById(serverRequest.pathVariable("id"));

        return productMono.flatMap(product -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(product))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {

        Mono<ProductRequest> productTobeInserted = serverRequest.bodyToMono(ProductRequest.class);

        return productTobeInserted.flatMap(product ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productService.addProduct(product), Product.class));

    }

    public Mono<ServerResponse> deleteProduct(ServerRequest serverRequest) {

        String id = serverRequest.pathVariable("id");
        Mono<Void> deleteItem = productService.deleteProduct(id);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deleteItem, Void.class);
    }

    public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {

        String id = serverRequest.pathVariable("id");

        Mono<ProductRequest> updatedItem = serverRequest.bodyToMono(ProductRequest.class);

        return updatedItem.flatMap(product ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productService.updateProduct(id, product), Product.class))
                .switchIfEmpty(notFound);


    }

}
