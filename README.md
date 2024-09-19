# Simple CRUD
This repository contains a simple CRUD project built using Java Spring. The aim of this repository is to practice CRUD Methods using Java Spring.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)

## Installation

1. Clone the repository:

```bash
$ git clone https://github.com/kgazineu/
```

2. Install dependencies with Maven

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints:
The table Product has the following columns (these values and types can be changed in the ProductModel) :
```markdown
 UUID id 
 String name
 BigDecimal value 
```

```markdown
POST /product - Register a new data.

GET /product - Retrieve a list of all data.

GET /product/{id} - Retrieve a single data.

PUT /product/{id} - Alter data.

DELETE /product/{id} - Inactivate data.
```

## Database
The project uses PostgresSQL as the database.
The table structure is generated automatically by Hibernate on ProductModel class.

## Docker

You can run this project with Docker by running the following command:


```bash
$ docker-compose up
```
Run the application and access http://localhost:5432

For practical purposes, the application.properties file is already configured to use the database in the Docker container.

To install Docker locally you can [click here](https://www.docker.com/products/docker-desktop/).
