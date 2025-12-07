# JSONPlaceholder API Clone - Spring Boot Backend

A complete REST API backend application built with Spring Boot and PostgreSQL, mimicking the functionality of JSONPlaceholder.

## 📋 Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [Installation & Running](#installation--running)
- [API Endpoints](#api-endpoints)
- [Testing the API](#testing-the-api)

## ✨ Features

Complete REST API with all HTTP methods:
- ✅ **GET** - Retrieve all resources
- ✅ **GET** - Retrieve single resource by ID
- ✅ **POST** - Create new resource
- ✅ **PUT** - Update entire resource
- ✅ **PATCH** - Partially update resource
- ✅ **DELETE** - Remove resource

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## 📦 Prerequisites

Before running this application, ensure you have the following installed:

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12 or higher
- Any REST client (Postman, curl, or browser extensions)

## 🗄 Database Setup

### 1. Create PostgreSQL Database

```bash
# Connect to PostgreSQL
psql -U postgres

# Create database
CREATE DATABASE jsonplaceholder;

# Exit psql
\q
```

### 2. Run the SQL Schema

Navigate to the SQL file location and execute:

```bash
psql -U postgres -d jsonplaceholder -f /path/to/Untitled.sql
```

Or copy the SQL content from the `Untitled.sql` file and execute it in your PostgreSQL client.

### 3. Configure Database Connection

Update `src/main/resources/application.properties` with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/jsonplaceholder
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## 🚀 Installation & Running

### 1. Clone and Navigate to Project

```bash
cd /Users/azizurrahaman/Documents/GitHub/advance-oop-tests/backend
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

Or run the JAR file:

```bash
java -jar target/jsonplaceholder-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8080**

## 📡 API Endpoints

### Users API
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/users` | Get all users |
| GET | `/users/{id}` | Get user by ID |
| POST | `/users` | Create new user |
| PUT | `/users/{id}` | Update entire user |
| PATCH | `/users/{id}` | Partially update user |
| DELETE | `/users/{id}` | Delete user |

### Posts API
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/posts` | Get all posts |
| GET | `/posts?userId={userId}` | Get posts by user ID |
| GET | `/posts/{id}` | Get post by ID |
| POST | `/posts` | Create new post |
| PUT | `/posts/{id}` | Update entire post |
| PATCH | `/posts/{id}` | Partially update post |
| DELETE | `/posts/{id}` | Delete post |

### Comments API
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/comments` | Get all comments |
| GET | `/comments?postId={postId}` | Get comments by post ID |
| GET | `/comments/{id}` | Get comment by ID |
| POST | `/comments` | Create new comment |
| PUT | `/comments/{id}` | Update entire comment |
| PATCH | `/comments/{id}` | Partially update comment |
| DELETE | `/comments/{id}` | Delete comment |

### Todos API
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/todos` | Get all todos |
| GET | `/todos?userId={userId}` | Get todos by user ID |
| GET | `/todos/{id}` | Get todo by ID |
| POST | `/todos` | Create new todo |
| PUT | `/todos/{id}` | Update entire todo |
| PATCH | `/todos/{id}` | Partially update todo |
| DELETE | `/todos/{id}` | Delete todo |

### Albums API
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/albums` | Get all albums |
| GET | `/albums?userId={userId}` | Get albums by user ID |
| GET | `/albums/{id}` | Get album by ID |
| POST | `/albums` | Create new album |
| PUT | `/albums/{id}` | Update entire album |
| PATCH | `/albums/{id}` | Partially update album |
| DELETE | `/albums/{id}` | Delete album |

### Photos API
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/photos` | Get all photos |
| GET | `/photos?albumId={albumId}` | Get photos by album ID |
| GET | `/photos/{id}` | Get photo by ID |
| POST | `/photos` | Create new photo |
| PUT | `/photos/{id}` | Update entire photo |
| PATCH | `/photos/{id}` | Partially update photo |
| DELETE | `/photos/{id}` | Delete photo |

## 🧪 Testing the API

### Using cURL

**Create a User:**
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "username": "johndoe",
    "email": "john@example.com",
    "phone": "123-456-7890",
    "website": "johndoe.com"
  }'
```

**Get All Users:**
```bash
curl http://localhost:8080/users
```

**Get User by ID:**
```bash
curl http://localhost:8080/users/1
```

**Update User (PUT):**
```bash
curl -X PUT http://localhost:8080/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Smith",
    "username": "johnsmith",
    "email": "johnsmith@example.com",
    "phone": "098-765-4321",
    "website": "johnsmith.com"
  }'
```

**Partial Update (PATCH):**
```bash
curl -X PATCH http://localhost:8080/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "email": "newemail@example.com"
  }'
```

**Delete User:**
```bash
curl -X DELETE http://localhost:8080/users/1
```

**Create a Post:**
```bash
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "My First Post",
    "body": "This is the content of my first post."
  }'
```

**Get Posts by User:**
```bash
curl http://localhost:8080/posts?userId=1
```

### Using Postman

1. Import the base URL: `http://localhost:8080`
2. Create requests for each endpoint
3. Set appropriate headers: `Content-Type: application/json`
4. Test all CRUD operations

## 📁 Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/jsonplaceholder/
│   │   │   ├── controller/
│   │   │   │   ├── UserController.java
│   │   │   │   ├── PostController.java
│   │   │   │   ├── CommentController.java
│   │   │   │   ├── TodoController.java
│   │   │   │   ├── AlbumController.java
│   │   │   │   └── PhotoController.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   ├── PostService.java
│   │   │   │   ├── CommentService.java
│   │   │   │   ├── TodoService.java
│   │   │   │   ├── AlbumService.java
│   │   │   │   └── PhotoService.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── PostRepository.java
│   │   │   │   ├── CommentRepository.java
│   │   │   │   ├── TodoRepository.java
│   │   │   │   ├── AlbumRepository.java
│   │   │   │   └── PhotoRepository.java
│   │   │   ├── model/
│   │   │   │   ├── User.java
│   │   │   │   ├── Post.java
│   │   │   │   ├── Comment.java
│   │   │   │   ├── Todo.java
│   │   │   │   ├── Album.java
│   │   │   │   └── Photo.java
│   │   │   └── JsonplaceholderApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🔧 Configuration

### Default Configuration (`application.properties`)

```properties
server.port=8080
spring.datasource.url=jdbc:postgresql://localhost:5432/jsonplaceholder
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

### Environment-Specific Configuration

Create `application-dev.properties` or `application-prod.properties` for different environments.

## 🐛 Troubleshooting

### Common Issues

1. **Connection refused to PostgreSQL**
   - Ensure PostgreSQL is running: `sudo service postgresql status`
   - Check connection credentials in `application.properties`

2. **Port 8080 already in use**
   - Change port in `application.properties`: `server.port=8081`

3. **Compilation errors**
   - Ensure Java 17+ is installed: `java -version`
   - Run `mvn clean install -U` to force update dependencies

## 📝 License

This project is created for educational purposes.

## 👨‍💻 Author

Created as a JSONPlaceholder API clone with Spring Boot and PostgreSQL.
