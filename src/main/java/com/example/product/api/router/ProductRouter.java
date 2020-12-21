package com.example.product.api.router;

import com.example.product.api.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ProductRouter {

    private static final String ROUTE_PREFIX = "/v1/functional/product";

    @Bean
    public RouterFunction<ServerResponse> productRoute(ProductHandler productHandler) {
        return RouterFunctions
                .route(GET(ROUTE_PREFIX).and(accept(APPLICATION_JSON)),
                        productHandler::getAllProducts)
                .andRoute(GET(ROUTE_PREFIX + "/{id}").and(accept(APPLICATION_JSON)),
                        productHandler::getProductById)
                .andRoute(POST(ROUTE_PREFIX).and(accept(APPLICATION_JSON)),
                        productHandler::createProduct)
                .andRoute(DELETE(ROUTE_PREFIX+"/{id}").and(accept(APPLICATION_JSON))
                        ,productHandler::deleteProduct)
                .andRoute(PUT(ROUTE_PREFIX+"/{id}").and(accept(APPLICATION_JSON))
                        ,productHandler::updateProduct);
    }
}
