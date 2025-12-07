# 🏗 Architecture Overview

## System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                         CLIENT LAYER                         │
│  (Postman, cURL, Browser, Frontend Application, Mobile)     │
└────────────────────────┬────────────────────────────────────┘
                         │ HTTP Requests (JSON)
                         │
┌────────────────────────▼────────────────────────────────────┐
│                    SPRING BOOT APPLICATION                   │
│                    (Port 8080)                               │
│                                                              │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              CONTROLLER LAYER (@RestController)       │  │
│  │  - UserController      - TodoController              │  │
│  │  - PostController      - AlbumController             │  │
│  │  - CommentController   - PhotoController             │  │
│  │                                                       │  │
│  │  Responsibilities:                                    │  │
│  │  • Handle HTTP requests                              │  │
│  │  • Validate input                                    │  │
│  │  • Return HTTP responses                             │  │
│  └───────────────────────┬──────────────────────────────┘  │
│                          │                                  │
│  ┌───────────────────────▼──────────────────────────────┐  │
│  │              SERVICE LAYER (@Service)                 │  │
│  │  - UserService         - TodoService                 │  │
│  │  - PostService         - AlbumService                │  │
│  │  - CommentService      - PhotoService                │  │
│  │                                                       │  │
│  │  Responsibilities:                                    │  │
│  │  • Business logic                                    │  │
│  │  • Transaction management                            │  │
│  │  • Data transformation                               │  │
│  └───────────────────────┬──────────────────────────────┘  │
│                          │                                  │
│  ┌───────────────────────▼──────────────────────────────┐  │
│  │           REPOSITORY LAYER (@Repository)              │  │
│  │  - UserRepository      - TodoRepository              │  │
│  │  - PostRepository      - AlbumRepository             │  │
│  │  - CommentRepository   - PhotoRepository             │  │
│  │                                                       │  │
│  │  Extends: JpaRepository<Entity, Integer>             │  │
│  │  Provides: CRUD + Custom queries                     │  │
│  └───────────────────────┬──────────────────────────────┘  │
│                          │                                  │
│  ┌───────────────────────▼──────────────────────────────┐  │
│  │              MODEL LAYER (@Entity)                    │  │
│  │  - User                - Todo                        │  │
│  │  - Post                - Album                       │  │
│  │  - Comment             - Photo                       │  │
│  │                                                       │  │
│  │  JPA Entities mapped to database tables              │  │
│  └───────────────────────┬──────────────────────────────┘  │
│                          │                                  │
└──────────────────────────┼──────────────────────────────────┘
                           │ JDBC / JPA
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                    POSTGRESQL DATABASE                       │
│                                                              │
│  Tables:                                                     │
│  • users                                                     │
│  • posts        (FK: user_id)                               │
│  • comments     (FK: post_id)                               │
│  • todos        (FK: user_id)                               │
│  • albums       (FK: user_id)                               │
│  • photos       (FK: album_id)                              │
└─────────────────────────────────────────────────────────────┘
```

---

## Request Flow Example

### Example: GET /posts?userId=1

```
1. Client Request
   ↓
   GET http://localhost:8080/posts?userId=1

2. PostController.getAllPosts(@RequestParam userId)
   ↓
   • Receives request
   • Extracts userId parameter
   • Calls service layer

3. PostService.getPostsByUserId(1)
   ↓
   • Business logic (if any)
   • Calls repository

4. PostRepository.findByUserId(1)
   ↓
   • JPA generates SQL query:
   • SELECT * FROM posts WHERE user_id = 1

5. PostgreSQL Database
   ↓
   • Executes query
   • Returns result set

6. JPA / Hibernate
   ↓
   • Maps result to Post entities
   • Returns List<Post>

7. PostService
   ↓
   • Returns List<Post> to controller

8. PostController
   ↓
   • Wraps in ResponseEntity
   • Returns HTTP 200 with JSON body

9. Client receives response:
   [
     {
       "id": 1,
       "user": {"id": 1, ...},
       "title": "Post Title",
       "body": "Post content...",
       "createdAt": "2024-12-07T...",
       "updatedAt": "2024-12-07T..."
     }
   ]
```

---

## Database Schema Relationships

```
┌─────────────┐
│   USERS     │
│  (id, ...)  │
└──────┬──────┘
       │
       │ 1:N (One user has many posts)
       ├─────────────────┐
       │                 │
       ▼                 ▼
┌─────────────┐   ┌─────────────┐
│   POSTS     │   │   TODOS     │
│(id, user_id)│   │(id, user_id)│
└──────┬──────┘   └─────────────┘
       │
       │ 1:N (One post has many comments)
       │
       ▼
┌─────────────┐
│  COMMENTS   │
│(id, post_id)│
└─────────────┘

       ┌─────────────┐
       │   USERS     │
       │  (id, ...)  │
       └──────┬──────┘
              │
              │ 1:N (One user has many albums)
              │
              ▼
       ┌─────────────┐
       │   ALBUMS    │
       │(id, user_id)│
       └──────┬──────┘
              │
              │ 1:N (One album has many photos)
              │
              ▼
       ┌─────────────┐
       │   PHOTOS    │
       │(id,album_id)│
       └─────────────┘
```

---

## Technology Stack

```
┌────────────────────────────────────────┐
│          Application Stack              │
├────────────────────────────────────────┤
│                                         │
│  ┌──────────────────────────────────┐  │
│  │   Spring Boot 3.2.0              │  │
│  │   • Auto-configuration           │  │
│  │   • Embedded Tomcat server       │  │
│  │   • Production-ready features    │  │
│  └──────────────────────────────────┘  │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │   Spring Web MVC                 │  │
│  │   • @RestController              │  │
│  │   • @RequestMapping              │  │
│  │   • Request/Response handling    │  │
│  └──────────────────────────────────┘  │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │   Spring Data JPA                │  │
│  │   • JpaRepository                │  │
│  │   • Entity mapping               │  │
│  │   • Query methods                │  │
│  └──────────────────────────────────┘  │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │   Hibernate ORM                  │  │
│  │   • Object-Relational Mapping    │  │
│  │   • Transaction management       │  │
│  │   • Lazy/Eager loading           │  │
│  └──────────────────────────────────┘  │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │   PostgreSQL JDBC Driver         │  │
│  │   • Database connectivity        │  │
│  └──────────────────────────────────┘  │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │   Jackson (JSON)                 │  │
│  │   • JSON serialization           │  │
│  │   • JSON deserialization         │  │
│  └──────────────────────────────────┘  │
│                                         │
└────────────────────────────────────────┘
```

---

## HTTP Methods Mapping

```
┌──────────┬─────────────┬────────────────┬──────────────┐
│  Method  │   Purpose   │  HTTP Status   │  Idempotent  │
├──────────┼─────────────┼────────────────┼──────────────┤
│  GET     │  Retrieve   │  200 OK        │     Yes      │
│          │             │  404 Not Found │              │
├──────────┼─────────────┼────────────────┼──────────────┤
│  POST    │  Create     │  201 Created   │     No       │
│          │             │  400 Bad Req   │              │
├──────────┼─────────────┼────────────────┼──────────────┤
│  PUT     │  Replace    │  200 OK        │     Yes      │
│          │  (Full)     │  404 Not Found │              │
├──────────┼─────────────┼────────────────┼──────────────┤
│  PATCH   │  Update     │  200 OK        │     Yes      │
│          │ (Partial)   │  404 Not Found │              │
├──────────┼─────────────┼────────────────┼──────────────┤
│ DELETE   │  Remove     │  204 No Content│     Yes      │
│          │             │  404 Not Found │              │
└──────────┴─────────────┴────────────────┴──────────────┘
```

---

## Project File Organization

```
backend/
│
├── src/main/java/com/example/jsonplaceholder/
│   │
│   ├── JsonplaceholderApplication.java  ← Main entry point
│   │
│   ├── config/
│   │   └── DataInitializer.java         ← Optional sample data
│   │
│   ├── controller/                      ← REST API endpoints
│   │   ├── UserController.java
│   │   ├── PostController.java
│   │   ├── CommentController.java
│   │   ├── TodoController.java
│   │   ├── AlbumController.java
│   │   └── PhotoController.java
│   │
│   ├── service/                         ← Business logic
│   │   ├── UserService.java
│   │   ├── PostService.java
│   │   ├── CommentService.java
│   │   ├── TodoService.java
│   │   ├── AlbumService.java
│   │   └── PhotoService.java
│   │
│   ├── repository/                      ← Data access
│   │   ├── UserRepository.java
│   │   ├── PostRepository.java
│   │   ├── CommentRepository.java
│   │   ├── TodoRepository.java
│   │   ├── AlbumRepository.java
│   │   └── PhotoRepository.java
│   │
│   ├── model/                           ← JPA entities
│   │   ├── User.java
│   │   ├── Post.java
│   │   ├── Comment.java
│   │   ├── Todo.java
│   │   ├── Album.java
│   │   └── Photo.java
│   │
│   └── dto/                             ← Data transfer objects
│       └── UserIdDto.java
│
├── src/main/resources/
│   └── application.properties           ← Configuration
│
└── pom.xml                              ← Maven dependencies
```

---

## Design Patterns Used

### 1. **Layered Architecture**
- Controller → Service → Repository → Database
- Clear separation of concerns

### 2. **Repository Pattern**
- Abstraction over data access
- JpaRepository provides CRUD operations

### 3. **Dependency Injection**
- @Autowired for loose coupling
- Spring manages object lifecycle

### 4. **RESTful API Design**
- Resource-based URLs
- HTTP methods for operations
- JSON for data exchange

### 5. **DTO Pattern**
- UserIdDto for nested object references
- Clean request/response handling

---

## Key Features

✅ **RESTful API** - Standard HTTP methods
✅ **Layered Architecture** - Clean code organization
✅ **JPA/Hibernate** - Object-relational mapping
✅ **PostgreSQL** - Reliable database
✅ **Spring Boot** - Rapid development
✅ **CORS Enabled** - Frontend ready
✅ **Auto Timestamps** - Audit fields
✅ **Cascade Delete** - Referential integrity

---

This architecture provides:
- **Scalability** - Easy to add new features
- **Maintainability** - Clear code structure
- **Testability** - Each layer can be tested independently
- **Flexibility** - Easy to swap implementations
