# PetStore REST API Automation Testing

This project is an automation testing framework built to test the **Swagger PetStore REST API**. It uses **Java**, **REST-assured**, and **TestNG** to perform end-to-end API tests including CRUD operations on User resources.

---

## 📌 Features

- 🧱 Modular framework with clean structure (POM-style)
- 📤 Create, Retrieve, Update, Delete (CRUD) operations for User API
- 🔄 Data-driven testing with `@DataProvider`
- ✅ TestNG-based test execution and reporting
- 🔧 Easily extendable and maintainable code
- 🔐 Uses POJO classes for request/response mapping
- 📂 Organized packages (endpoints, payloads, tests, utilities)

---

## 🚀 Technologies Used

| Tool            | Description                           |
|-----------------|---------------------------------------|
| Java            | Programming language                  |
| TestNG          | Testing framework                     |
| REST-assured    | REST API testing library              |
| Maven           | Build automation tool                 |
| Faker           | Library for generating fake test data |
| Swagger PetStore| Sample REST API used for testing      |


## 📁 Project Structure

```src
├── main
│ └── java
│ └── api
│ ├── endpoints/ # API call implementations
│ ├── payload/ # POJO classes for request bodies
│ └── utilities/ # Utilities like DataProvider, config
├── test
│ └── java
│ └── api
│ └── tests/ # TestNG test classes
└── resources/
└── routes.properties # Endpoint URLs


## Sample Test Cases

### 🧑‍💻 User API (Base: `https://petstore.swagger.io/v2/user`)
- **POST `/user`** – Creates a new user with Faker-generated data
- **GET `/user/{username}`** – Retrieves the created user by username
- **PUT `/user/{username}`** – Updates user information
- **DELETE `/user/{username}`** – Deletes the user and verifies deletion

### 📦 Order API (Base: `https://petstore.swagger.io/v2/store/order`)
- **POST `/store/order`** – Places an order for a pet
- **GET `/store/order/{orderId}`** – Retrieves the order by ID
- **DELETE `/store/order/{orderId}`** – Deletes the order and confirms deletion

### 🗃️ Inventory API
- **GET `/store/inventory`** – Fetches pet inventories by status (available, pending, sold)




