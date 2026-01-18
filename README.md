# Gadget Inventory - Full Stack CRUD Application

This is a full-stack application built for the Advanced Object-Oriented Programming assignment.
It allows users to manage a inventory of gadgets (Items), demonstrating CRUD operations.

## 🚀 Tech Stack

- **Backend**: Java 17, Spring Boot 3.2.0, H2 Database (In-Memory), JPA/Hibernate.
- **Frontend**: Next.js 14, TypeScript, React 18, CSS Modules/Vanilla Modern CSS.
- **Environment**: GitHub Codespaces compatible (Docker/DevContainer).

## 🛠 Project Structure

```bash
/
├── backend/            # Spring Boot Application
│   ├── src/main/java   # Java Source Code
│   └── pom.xml         # Maven Dependencies
├── frontend/           # Next.js Application
│   ├── src/app         # App Router Pages
│   └── package.json    # Node Dependencies
├── .devcontainer/      # Codespaces Configuration
└── README.md           # This file
```

## 🔧 How to Run

### Requirement
- Java 17+
- Node.js 18+
- Maven

### Running in Codespaces (Recommended)
This repository is configured with a `.devcontainer`. Opening it in GitHub Codespaces will automatically install all dependencies (Java, Node, Maven).

### Running Manually

#### 1. Start Backend
```bash
cd backend
mvn spring-boot:run
```
The Backend API will start at [http://localhost:8080](http://localhost:8080).

#### 2. Start Frontend
Open a new terminal:
```bash
cd frontend
npm install
npm run dev
```
The Frontend UI will be available at [http://localhost:3000](http://localhost:3000).

## 📋 System Architecture
- **API Layer**: Spring Boot Controllers expose REST endpoints.
- **Service Layer**: Handles business logic.
- **Data Layer**: JPA Repositories interact with H2 Database.
- **Client**: Next.js fetches data via `fetch` API directly from the Spring Boot backend.

## 📝 Features (CRUD)
- **Create**: Add new gadgets with Name, Brand, Type, Price, and Status.
- **Read**: View list of all gadgets.
- **Update**: Edit details of an existing gadget.
- **Delete**: Remove a gadget from the inventory.

## 🛡 Object-Oriented Principles
- **Encapsulation**: Used in `Gadget` class with private fields and public accessors.
- **Abstraction**: `GadgetImage` Service obscures the DB implementation details.
- **Inheritance**: `GadgetRepository` inherits standard CRUD methods from `JpaRepository`.
- **Polymorphism**: Interface implementation in Service layer dependencies (Implicit via Spring DI).
