package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@ViewIndexed(designDoc = "product", viewName = "all")
@N1qlPrimaryIndexed
public interface ProductRepository extends ReactiveCouchbaseRepository<Product, String> {

    Flux<Product> findByRatingGreaterThan(int rating);

    Flux<Product> findByRatingGreaterThanEqual(int rating);

    Flux<Product> findByRatingLessThan(int rating);

    Flux<Product> findByRatingLessThanEqual(int rating);

    Flux<Product> findByRating(int rating);

}
