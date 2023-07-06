# Bank "CryptoGuay" Example with Spring Boot 

An example of the use of some design concerns to "simulate" a bank
 - DDD
 - Hexagonal Architecture
 - JWT
 - REST

Technology:
- Spring Boot
- Spring Security
- Spring MVC
- Spring Data
- Spring Rest
- In memory database h2

Features:
- Create a user
- Create a wallet
- Do a deposit in a user wallet
- Visualize a wallet
- Transfer from wallet A to wallet B

## Build & run

The project can be build with maven or with mvn-wrapper

```shell
?> ./mvnw package
```

### Run
To run, first build then
```shell
?> java -jar target/banking-0.0.1-SNAPSHOT.jar
```

### Tests
Test are in the folder src/test can be executed with
```shell
?> mvn package test
```


### H2 UI Console access
It's posible to access a h2 console to show records in a database
```
http://localhost:8080/h2-console
```

### Swagger UI 
Can execute some REST with the swagger ui by the next url
```
http://localhost:8080/swagger-ui/index.html
```