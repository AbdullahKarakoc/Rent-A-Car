# Rent A Car API Documentation

## Introduction

The Rent A Car API provides endpoints to manage car rentals, customers, branches, payments, insurances, and rentals. It allows users to perform CRUD operations and manage car bookings.

## TECHNOLOGIES

### Environment and Tools

- Java 21
- Spring Boot 3.2.5
- Maven
- PostgreSQL
- Docker

### Library and Dependencies

- Spring Boot Data JPA
- Hibernate
- Spring Boot Security
- Lombok
- Model Mapper
- SpringDoc OpenAPI (Swagger 3)
- Spring Boot Validation
- Bearer Authentication
- JWT

## STRUCTURE
### Architecture
> **REST** architecture implemented
* REST is an architecture that works over the HTTP protocol, which enables communication between client and server. REST is a transfer method used in software built on service-oriented architecture. It enables the application to communicate by carrying XML and JSON data between the client and the server. Services that use the REST architecture are called RESTful services.</br>

### 3.2 Principle
> Efforts were made to remain faithful to **SOLID** principles.
```
1. (S)ingle Responsibility Principle (SRP: Tek Sorumluluk Prensibi )
```
```
2. (O)pen/Closed Principle (OCP: Açık Kapalı Prensibi)
```
```
3. (L)iskov ‘s Substitution Principle (LSP: Liskov’un Yerine Geçme Prensibi )
```
```
4. (I)nterface Segregation Principle (ISP: Arayüz Ayrıştırma Prensibi )
```
```
5. (D)ependency Inversion Principle (DIP: Bağımlılık Ters Çevirme Prensibi )
```


## Endpoints

### Cars

| HTTP Method | Endpoint                  | Description                               | Authority    |
|-------------|---------------------------|-------------------------------------------|--------------|
| POST        | /cars                     | Adds a new car                            | ADMIN        |
| GET         | /cars                     | Retrieves all cars                        | ADMIN & USER |
| GET         | /cars/{id}                | Retrieves a car by ID                     | ADMIN & USER |
| PUT         | /cars/{id}                | Updates a car by ID                       | ADMIN        |
| DELETE      | /cars/{id}                | Deletes a car by ID                       | ADMIN        |

### Customers

| HTTP Method | Endpoint                  | Description                               | Authority    |
|-------------|---------------------------|-------------------------------------------|--------------|
| POST        | /customers                | Creates a new customer                    | ADMIN & USER |
| GET         | /customers                | Retrieves all customers                   | ADMIN        |
| GET         | /customers/{id}           | Retrieves a customer by ID                | ADMIN & USER |
| PUT         | /customers/{id}           | Updates a customer by ID                  | ADMIN & USER |
| DELETE      | /customers/{id}           | Deletes a customer by ID                  | ADMIN        |

### Branches

| HTTP Method | Endpoint                  | Description                               | Authority    |
|-------------|---------------------------|-------------------------------------------|--------------|
| POST        | /branches                 | Adds a new branch                         | ADMIN        |
| GET         | /branches                 | Retrieves all branches                    | ADMIN & USER |
| GET         | /branches/{id}            | Retrieves a branch by ID                  | ADMIN & USER |
| PUT         | /branches/{id}            | Updates a branch by ID                    | ADMIN        |
| DELETE      | /branches/{id}            | Deletes a branch by ID                    | ADMIN        |

### Branch Addresses

| HTTP Method | Endpoint                  | Description                         | Authority    |
|-------------|---------------------------|-------------------------------------|--------------|
| POST        | /branch-addresses         | Add a new branch address            | ADMIN        |
| GET         | /branch-addresses         | Retrieve all branch addresses       | ADMIN & USER |
| GET         | /branch-addresses/{id}    | Retrieve a branch address by ID     | ADMIN & USER |
| PUT         | /branch-addresses/{id}    | Update a branch address by ID       | ADMIN        |
| DELETE      | /branch-addresses/{id}    | Delete a branch address by ID       | ADMIN        |

### Payments

| HTTP Method | Endpoint                  | Description                               | Authority    |
|-------------|---------------------------|-------------------------------------------|--------------|
| POST        | /payments                 | Creates a new payment                     | ADMIN & USER |
| GET         | /payments                 | Retrieves all payments                    | ADMIN & USER |
| GET         | /payments/{id}            | Retrieves a payment by ID                 | ADMIN & USER |
| PUT         | /payments/{id}            | Updates a payment by ID                   | ADMIN        |
| DELETE      | /payments/{id}            | Deletes a payment by ID                   | ADMIN        |

### Insurances

| HTTP Method | Endpoint                  | Description                               | Authority    |
|-------------|---------------------------|-------------------------------------------|--------------|
| POST        | /insurances               | Creates a new insurance                   | ADMIN        |
| GET         | /insurances               | Retrieves all insurances                  | ADMIN & USER |
| GET         | /insurances/{id}          | Retrieves an insurance by ID              | ADMIN & USER |
| PUT         | /insurances/{id}          | Updates an insurance by ID                | ADMIN        |
| DELETE      | /insurances/{id}          | Deletes an insurance by ID                | ADMIN        |

### Rentals

| HTTP Method | Endpoint                  | Description                               | Authority    |
|-------------|---------------------------|-------------------------------------------|--------------|
| POST        | /rentals                  | Creates a new rental                      | ADMIN & USER |
| GET         | /rentals                  | Retrieves all rentals                     | ADMIN & USER |
| GET         | /rentals/{id}             | Retrieves a rental by ID                  | ADMIN & USER |
| PUT         | /rentals/{id}             | Updates a rental by ID                    | ADMIN        |
| DELETE      | /rentals/{id}             | Deletes a rental by ID                    | ADMIN        |

### Staff

| HTTP Method | Endpoint                  | Description                               | Authority    |
|-------------|---------------------------|-------------------------------------------|--------------|
| POST        | /staff                    | Creates a new staff member                | ADMIN        |
| GET         | /staff                    | Retrieves all staff members               | ADMIN & USER |
| GET         | /staff/{id}               | Retrieves a staff member by ID            | ADMIN & USER |
| PUT         | /staff/{id}               | Updates a staff member by ID              | ADMIN        |
| DELETE      | /staff/{id}               | Deletes a staff member by ID              | ADMIN        |

## Authentication

- Bearer Authentication with email verification  is used for authentication and authorization.
- Endpoints specify required authorities (ADMIN or USER) for access.

## Additional Features

>**http://localhost:1080** for MailDev 

>**http://localhost:8088/api/v1/swagger-ui/index.html** for Swagger ui

>**applicaton-dev.yml** for developers configuration

>**docker-compose.yml** for docker configuration


## Getting Started

1. Clone the repository.
2. Configure application.properties for database setup.
3. docker-compose up -d   run docker.
4. Build and run the application.
5. Create a new user and verify it with the code in the incoming e-mail.
6. Log in with the verified user and use the token in jwt format for authorization.
7. Rent a car using the necessary endpoints.








## Author

Abdullah Karakoç

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.
