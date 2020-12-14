package com.example.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	/*@Bean
	CommandLineRunner insertData(ProductService productService) {
		return args -> {
			JsonParser parser = new JsonParser();
			Flux<Product> productFlux = Flux.fromIterable(parser.extractProductsFromJson("product.json"));
			productService.addProduct(productFlux);
		};
	}*/
}
