# REST APIs for a Blogging plateform

* A SpringBoot project which provides REST API for Blogging plateform application. This API performs all the fundamental CRUD operations of any Blogging platform with user validation at every step.

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL

## Modules

* Signup, Login/Logout Module
* User Module
* Post Module
* Comments Module
* Follow Module

## Features

* User authentication & validation with session uuid.
* User Features:
    * Registering themselves with application, and logging in to get the valid session token
    * Viewing list of available items and ordering it
    * Only logged in users view post details, place a comment, update and access their details.
    
## Installation & Run

* `swagger ui link of api` http://54.206.95.16:8080/swagger-ui/index.html#
* Update the port number, username and password as per your local database config.

```
    server.port=8080

    spring.datasource.url=jdbc:mysql://localhost:3306/blogApp;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```

## API Root Endpoint

`https://localhost:8080/`

`http://localhost:8080/swagger-ui.html`

### Sample API Response for Customer Login

`POST   localhost:8008/SignIn`

* Request Body

```
    {
        
        "email": "pankaj@123",
        "password": "pasword"
    }
```

* Response

```
    {
         2022-11-14T14:12:43.129790400 : copy token 

    }
```
## Summary
This project perform basic create read update and delete operation as backend works in blogging application. 
