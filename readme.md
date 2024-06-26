# Task 1.5
### Get all the products
GET http://localhost:7007/api/healthproducts
Response: {
"1": {
"id": 1,
"category": null,
"name": "Product Name",
"price": 10.99,
"calories": 100.0,
"description": "Product Description",
"expireDate": [
2024,
4,
30
]
}
}

### Get product by id
GET http://localhost:7007/api/healthproducts/1
Response: {
"id": 1,
"category": null,
"name": "Product Name",
"price": 10.99,
"calories": 100.0,
"description": "Product Description",
"expireDate": [
2024,
4,
30
]
}

### Create a healthy product
POST http://localhost:7007/api/healthproducts/

{
"name": "Product Name",
"price": 10.99,
"calories": 100,
"description": "Product Description",
"expireDate": "2024-04-30"
}
Response: {
"id": 1,
"category": null,
"name": "Product Name",
"price": 10.99,
"calories": 100.0,
"description": "Product Description",
"expireDate": [
2024,
4,
30
]
}

### Update existing healthy product by id
PUT http://localhost:7007/api/healthproducts/1

{
"name": "TEST",
"price": 12.99,
"calories": 130,
"description": "test"
}
Response:
{
"id": 1,
"category": null,
"name": "TEST",
"price": 12.99,
"calories": 130.0,
"description": "test",
"expireDate": null
}

### Delete existing healthy product by id
DELETE http://localhost:7007/api/healthproducts/1
Response:
{
"id": 1,
"category": null,
"name": "TEST",
"price": 12.99,
"calories": 130.0,
"description": "test",
"expireDate": null
}

# Task 2.2
    HTTP Method         REST Resource                   Exception and status code
        
    GET                 /api/healthproducts             APIException & 404
    GET                 /api/healthproducts/{id}        APIException & 404
    POST                /api/healthproducts/            APIException & 500
    PUT                 /api/healthproducts/{id}        APIException & 404
    DELETE              /api/healthproducts/{id}        APIException & 404

# Task 2.3
I have done it in the following steps:
1)
I start by initializing the following: (It lets my logger know the class its being used in)
Logger logger = Logger.getLogger(HealthProductRoutes.class.getName());
2)
I then add a file to my logger, which my exceptions will be written to.
3)
If my routes catch an exception, then they will be writing to the file using my logException method.