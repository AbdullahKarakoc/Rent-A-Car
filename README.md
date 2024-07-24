# Rent A Car API Documentation

## Introduction

The Rent A Car API provides endpoints to manage cars, users, branches, payments,staff, branches address, insurances, and rentals. It allows users to perform CRUD operations and manage car bookings.

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

context-path: http://localhost:8088/api/v1

### Cars

| HTTP Method | Endpoint                  | Description                               | Authority    |
|-------------|---------------------------|-------------------------------------------|--------------|
| POST        | /cars                     | Adds a new car                            | ADMIN        |
| GET         | /cars                     | Retrieves all cars                        | ADMIN & USER |
| GET         | /cars/{id}                | Retrieves a car by ID                     | ADMIN & USER |
| PUT         | /cars/{id}                | Updates a car by ID                       | ADMIN        |
| DELETE      | /cars/{id}                | Deletes a car by ID                       | ADMIN        |

### Users

| HTTP Method | Endpoint               | Description            | Authority    |
|-------------|------------------------|------------------------|--------------|
| POST        | /auth/register         | Create new user        | USER         |
| GET         | /auth/activate-account | Activate user account  | USER         |
| POST        | /auth/authenticate     | Authenticate user      | USER         |
| GET         | /users                 | Retrieves all users    | ADMIN & USER |
| GET         | /users/{id}            | Retrieves a user by ID | ADMIN & USER |
| PUT         | /users/{id}            | Updates a user by ID   | ADMIN & USER |
| DELETE      | /users/{id}            | Deletes a user  by ID  | ADMIN & USER |  

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

### ERD

[Rent A Car ERD](images/Rent A Car.png)


## Getting Started

1. Clone the repository.
2. Configure application.properties for database setup.
3. `docker-compose up -d` run docker.
4. Build and run the application.
5. Create a new user and verify it with the code in the incoming e-mail.
6. Log in with the verified user and use the token in jwt format for authorization.
7. Rent a car using the necessary endpoints.


### Downloads

[Download Postman Collection](https://github.com/AbdullahKarakoc/Rent-A-Car/raw/dev_test/RentAcar.postman_collection.zip)

[Download ERD](https://github.com/AbdullahKarakoc/Rent-A-Car/raw/dev_test/RentACar_ERD.zip)


## Testing Postman

### Authentication and Authorization

- User Register/POST

   `http://localhost:8088/api/v1/auth/register`

```json
{
  "firstname": "Abdullah",
  "lastname": "karakoç",
  "email": "abdullahkrkc1453@gmail.com",
  "password": "12345678"
}
```
- User account activation/GET (Token in Maildev: http://localhost:1080)

  `http://localhost:8088/api/v1/auth/activate-account?token=926959`

- User Login/POST
  
  `http://localhost:8088/api/v1/auth/authenticate`

```json
{
  "email": "abdullahkrkc1453@gmail.com",
  "password": "12345678"
}
```

### Rental Transactions

- Create Branch/POST

  `http://localhost:8088/api/v1/branches`

```json
{
  "branchName": "Central",
  "branchAddress": {
    "street": "123 Main St",
    "city": "Ankara",
    "country": "TURKEY",
    "zipCode": "06000"
  }
}
```

- Create Car/POST

  `http://localhost:8088/api/v1/cars`

```json
{
  "brand": "TOYOTA",
  "segment": "SUV",
  "model": "RAV4",
  "color": "BLUE",
  "year": 2022,
  "plateNumber": "34XYZ78",
  "pricePerHour": 50,
  "insurances": {
    "provider": "Allianz",
    "cost": 100.0,
    "category": "FULL_COVERAGE"
  },
  "branchUUID": "354b8c39-ad13-4fa2-ad35-03836c89d460"
}
```


- Create Staff/POST

  `http://localhost:8088/api/v1/staff`

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "phone": "1234567890",
  "email": "john.doe@example.com",
  "branchUUID": "354b8c39-ad13-4fa2-ad35-03836c89d460"
}
```


- Create Customer/POST

  `http://localhost:8088/api/v1/customers`

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "licenseNumber": "AB123456",
  "phone": "1234567890",
  "email": "john.doe@example.com"
}
```

- Create Rental/POST

  `http://localhost:8088/api/v1/rentals`

```json
{
  "rentalDate": "2024-07-12T00:00:00",
  "returnDate": "2024-07-20T00:00:00",
  "carUUID": "d0a53173-071c-4034-85ac-ec9062af68b0",
  "staffUUID": "c49563ba-e9a4-41f8-9e8b-7cd3756b270a",
  "customerUUID":"e1f1f153-2cac-4c80-8e55-20fcbd19e5a3",
  "payment": {
    "totalAmount": 450,
    "paymentType": "CASH"
  }
}
```

- Rental Response/GET

  `http://localhost:8088/api/v1/rentals`


```json
{
  "rentalUUID": "d485ae6b-f40e-4110-970d-dffecb914d29",
  "rentalDate": "2024-07-12T00:00:00",
  "returnDate": "2024-07-20T00:00:00",
  "car": {
    "carUUID": "dc94cd83-7e6d-43a5-a568-504edee54f48",
    "brand": "TOYOTA",
    "segment": "SUV",
    "model": "RAV4",
    "color": "BLUE",
    "year": 2022,
    "plateNumber": "34XYZ78",
    "pricePerHour": 50,
    "insurance": {
      "insuranceUUID": "31ff450c-55ca-454a-bf1f-0147018f0326",
      "provider": "Allianz",
      "cost": 100.0,
      "category": "FULL_COVERAGE",
      "createdAt": "2024-07-17T16:21:14.893823",
      "updatedAt": null,
      "createdBy": "abdullahkarakoc405@gmail.com",
      "updatedBy": null
    },
    "branch": {
      "branchUUID": "649ac92b-14d8-4084-8257-62e9810bb8a7",
      "branchName": "Central",
      "address": {
        "addressUUID": "3c10d966-e44f-45fc-90d9-65ec0199cd25",
        "street": "123 Main St",
        "city": "Ankara",
        "country": "TURKEY",
        "zipCode": "06000",
        "createdAt": "2024-07-17T16:20:57.896517",
        "updatedAt": null,
        "createdBy": "abdullahkarakoc405@gmail.com",
        "updatedBy": null
      },
      "createdAt": "2024-07-17T16:20:57.88652",
      "updatedAt": null,
      "createdBy": "abdullahkarakoc405@gmail.com",
      "updatedBy": null
    },
    "createdAt": "2024-07-17T16:21:14.921825",
    "updatedAt": null,
    "createdBy": "abdullahkarakoc405@gmail.com",
    "updatedBy": null,
    "available": false
  },
  "staff": {
    "staffUUID": "1b310db8-aa4f-4578-a9cc-184f6008fa57",
    "firstName": "John",
    "lastName": "Doe",
    "phone": "1234567890",
    "email": "john.doe@example.com",
    "branch": {
      "branchUUID": "649ac92b-14d8-4084-8257-62e9810bb8a7",
      "branchName": "Central",
      "address": {
        "addressUUID": "3c10d966-e44f-45fc-90d9-65ec0199cd25",
        "street": "123 Main St",
        "city": "Ankara",
        "country": "TURKEY",
        "zipCode": "06000",
        "createdAt": "2024-07-17T16:20:57.896517",
        "updatedAt": null,
        "createdBy": "abdullahkarakoc405@gmail.com",
        "updatedBy": null
      },
      "createdAt": "2024-07-17T16:20:57.88652",
      "updatedAt": null,
      "createdBy": "abdullahkarakoc405@gmail.com",
      "updatedBy": null
    },
    "createdAt": "2024-07-17T16:22:09.58698",
    "updatedAt": null,
    "createdBy": "abdullahkarakoc405@gmail.com",
    "updatedBy": null
  },
  "customer": {
    "customerUUID": "8b674466-0772-454a-b8f7-a25311601f98",
    "firstName": "John",
    "lastName": "Doe",
    "phone": "1234567890",
    "email": "john.doe@example.com",
    "createdAt": "2024-07-17T16:21:35.000499",
    "updatedAt": null,
    "createdBy": "abdullahkarakoc405@gmail.com",
    "updatedBy": null
  },
  "payment": {
    "paymentUUID": "53af2ec7-1d14-4dc3-8e12-2a08bdfeb4b8",
    "totalAmount": 200,
    "payment": null,
    "createdAt": "2024-07-17T16:22:33.8552133",
    "updatedAt": "2024-07-17T16:22:33.8552133",
    "createdBy": "abdullahkarakoc405@gmail.com",
    "updatedBy": "abdullahkarakoc405@gmail.com"
  },
  "createdAt": "2024-07-17T16:22:33.8692262",
  "updatedAt": "2024-07-17T16:22:33.8692262",
  "createdBy": "abdullahkarakoc405@gmail.com",
  "updatedBy": "abdullahkarakoc405@gmail.com"
}
```



## Author

>**Abdullah Karakoç**

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.
