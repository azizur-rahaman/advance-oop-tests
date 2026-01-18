# Project Report: Gadget Inventory System

## 1. High-Level System Architecture

The system follows a classic **Client-Server Architecture**.

- **Frontend (Client)**: Built with **Next.js (React + TypeScript)**. It serves as the Presentation Layer. It handles user interactions, renders the UI, and communicates with the backend via HTTP REST calls. It is stateless and relies on the API for data.
- **Backend (Server)**: Built with **Spring Boot (Java)**. It serves as the Business and Data Access Layer. It exposes a RESTful API to perform CRUD operations.
- **Database**: **H2 Database** (In-Memory). It stores the persistent data. For this assignment, an in-memory database is used for ease of setup and testing, resetting data on each restart.

### Interaction Flow
1. **User Action**: User clicks "Create Gadget" on the Frontend.
2. **HTTP Request**: Frontend sends a `POST /api/gadgets` request with JSON payload to the Backend.
3. **Controller**: `GadgetController` receives the request.
4. **Service**: `GadgetService` validates and processes the business logic.
5. **Repository**: `GadgetRepository` saves the entity to the H2 Database using Hibernate/JPA.
6. **Response**: Backend returns the created object as JSON. Frontend updates the UI.

## 2. CRUD Workflow Explanation

- **Create (POST)**: 
  - User fills a form. 
  - Data is sent to `POST /api/gadgets`. 
  - Backend creates a new `Gadget` entity and saves it.
- **Read (GET)**: 
  - On page load, Frontend calls `GET /api/gadgets`. 
  - Backend retrieves all records from DB and returns a JSON list. 
  - `GET /api/gadgets/{id}` is used for fetching single item details for editing.
- **Update (PUT)**: 
  - User edits a form pre-filled with existing data. 
  - Data is sent to `PUT /api/gadgets/{id}`. 
  - Backend finds the entity, updates fields, and saves it.
- **Delete (DELETE)**: 
  - User clicks delete. 
  - Request sent to `DELETE /api/gadgets/{id}`. 
  - Backend removes the entity from the DB.

## 3. Object-Oriented Principles Used

### Encapsulation
The `Gadget` class encapsulates the data fields (`id`, `name`, `price`, etc.) by making them `private`. Access is controlled via public `getters` and `setters`. This ensures data integrity and controls how properties are modified.

### Inheritance
The `GadgetRepository` interface extends `JpaRepository`. This is a prime example of inheritance where our specific repository inherits a vast amount of generic CRUD functionality (save, findAll, deleteById) from the parent Spring Data interface without writing implementation code.

### Polymorphism
Spring's Dependency Injection uses polymorphism. The `GadgetService` depends on the `GadgetRepository` interface, not a concrete class. At runtime, Spring provides the specific implementation (proxy) that fulfills the contract. This allows for loose coupling.

### Abstraction
The Service layer handles the business logic, abstracting the complexity of database operations from the Controller. The Controller doesn't know *how* data is saved, only that the Service handles it. Similarly, JPA abstracts the SQL queries, allowing us to work with Java Objects instead of raw SQL.

## 4. Codespaces & Cloud Integration
The project is configured with a `.devcontainer`. This defines a Docker-based development environment that includes:
- **Java JDK 17**
- **Node.js LTS**
- **Maven**
- **Extensions**: VS Code extensions for Java and ESLint.

When the repository is opened in GitHub Codespaces, this environment is automatically provisioned, ensuring that the "run anywhere" promise is kept and dependency hell is avoided. Both Frontend (Port 3000) and Backend (Port 8080) ports are forwarded automatically to the browser.
