# reactive-product
product service with reactive webflux connecting to couchbase

**coucbase server running in port 8091**

## List of API(s)
* **get all products** (Flux) - `/v1/product/all`
* **get product by id** (Mono) - `/v1/product/{id}` 
* **get products with rating** (Flux) (equal, greater than, greater than or equal, less than, less than or equal) - `/v1/product/{type}/{rating}`
