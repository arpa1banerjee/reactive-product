# reactive-product
product service with reactive webflux stack connecting to couchbase

**couchbase server running in port 8091**

*sample product*
```
{
        "id": "75d6289e-b4bb-4530-8b8c-c4f3c12f9658",
        "title": "Legums",
        "fileName": "11.jpg",
        "width": 399,
        "height": 600,
        "type": "vegetable",
        "description": "Cooked legums on the wooden table",
        "rating": 0,
        "price": 14.77
    }
```

## List of API(s)
* **get all products** (Flux) - `GET - /v1/product`
* **get product by id** (Mono) - `GET - /v1/product/{id}` 
* **get products with rating** (Flux) (equal, greater than, greater than or equal, less than, less than or equal) - `GET - /v1/product/{type}/{rating}`
* **save product** (Mono) - `POST - /v1/product`
* **update product** (Mono) - `PUT - /v1/product/{id}`
* **delete product** (Void) - `DELETE - /v1/product/{id}`
