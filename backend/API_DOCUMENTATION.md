# API Documentation - JSONPlaceholder Clone

## Base URL
```
http://localhost:8080
```

## Response Codes

| Code | Description |
|------|-------------|
| 200 | OK - Successful GET, PUT, PATCH |
| 201 | Created - Successful POST |
| 204 | No Content - Successful DELETE |
| 400 | Bad Request - Invalid request data |
| 404 | Not Found - Resource doesn't exist |

---

## 1. Users API

### Get All Users
```http
GET /users
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "username": "johndoe",
    "email": "john@example.com",
    "phone": "123-456-7890",
    "website": "johndoe.com"
  }
]
```

### Get User by ID
```http
GET /users/{id}
```

### Create User
```http
POST /users
Content-Type: application/json

{
  "name": "John Doe",
  "username": "johndoe",
  "email": "john@example.com",
  "phone": "123-456-7890",
  "website": "johndoe.com"
}
```

### Update User (Full)
```http
PUT /users/{id}
Content-Type: application/json

{
  "name": "John Smith",
  "username": "johnsmith",
  "email": "johnsmith@example.com",
  "phone": "098-765-4321",
  "website": "johnsmith.com"
}
```

### Update User (Partial)
```http
PATCH /users/{id}
Content-Type: application/json

{
  "email": "newemail@example.com"
}
```

### Delete User
```http
DELETE /users/{id}
```

---

## 2. Posts API

### Get All Posts
```http
GET /posts
```

### Get Posts by User ID
```http
GET /posts?userId={userId}
```

### Get Post by ID
```http
GET /posts/{id}
```

### Create Post
```http
POST /posts
Content-Type: application/json

{
  "user": {"id": 1},
  "title": "My First Post",
  "body": "This is the content of my first post."
}
```

### Update Post (Full)
```http
PUT /posts/{id}
Content-Type: application/json

{
  "user": {"id": 1},
  "title": "Updated Post Title",
  "body": "Updated post content."
}
```

### Update Post (Partial)
```http
PATCH /posts/{id}
Content-Type: application/json

{
  "title": "New Title"
}
```

### Delete Post
```http
DELETE /posts/{id}
```

---

## 3. Comments API

### Get All Comments
```http
GET /comments
```

### Get Comments by Post ID
```http
GET /comments?postId={postId}
```

### Get Comment by ID
```http
GET /comments/{id}
```

### Create Comment
```http
POST /comments
Content-Type: application/json

{
  "post": {"id": 1},
  "name": "John Doe",
  "email": "john@example.com",
  "body": "Great post!"
}
```

### Update Comment (Full)
```http
PUT /comments/{id}
Content-Type: application/json

{
  "post": {"id": 1},
  "name": "John Doe",
  "email": "john@example.com",
  "body": "Updated comment."
}
```

### Update Comment (Partial)
```http
PATCH /comments/{id}
Content-Type: application/json

{
  "body": "Edited comment text"
}
```

### Delete Comment
```http
DELETE /comments/{id}
```

---

## 4. Todos API

### Get All Todos
```http
GET /todos
```

### Get Todos by User ID
```http
GET /todos?userId={userId}
```

### Get Todo by ID
```http
GET /todos/{id}
```

### Create Todo
```http
POST /todos
Content-Type: application/json

{
  "user": {"id": 1},
  "title": "Complete documentation",
  "completed": false
}
```

### Update Todo (Full)
```http
PUT /todos/{id}
Content-Type: application/json

{
  "user": {"id": 1},
  "title": "Complete documentation",
  "completed": true
}
```

### Update Todo (Partial)
```http
PATCH /todos/{id}
Content-Type: application/json

{
  "completed": true
}
```

### Delete Todo
```http
DELETE /todos/{id}
```

---

## 5. Albums API

### Get All Albums
```http
GET /albums
```

### Get Albums by User ID
```http
GET /albums?userId={userId}
```

### Get Album by ID
```http
GET /albums/{id}
```

### Create Album
```http
POST /albums
Content-Type: application/json

{
  "user": {"id": 1},
  "title": "My Photo Album"
}
```

### Update Album (Full)
```http
PUT /albums/{id}
Content-Type: application/json

{
  "user": {"id": 1},
  "title": "Updated Album Title"
}
```

### Update Album (Partial)
```http
PATCH /albums/{id}
Content-Type: application/json

{
  "title": "New Album Title"
}
```

### Delete Album
```http
DELETE /albums/{id}
```

---

## 6. Photos API

### Get All Photos
```http
GET /photos
```

### Get Photos by Album ID
```http
GET /photos?albumId={albumId}
```

### Get Photo by ID
```http
GET /photos/{id}
```

### Create Photo
```http
POST /photos
Content-Type: application/json

{
  "album": {"id": 1},
  "title": "Sunset Photo",
  "url": "https://example.com/photos/sunset.jpg",
  "thumbnailUrl": "https://example.com/photos/thumbs/sunset.jpg"
}
```

### Update Photo (Full)
```http
PUT /photos/{id}
Content-Type: application/json

{
  "album": {"id": 1},
  "title": "Updated Photo Title",
  "url": "https://example.com/photos/updated.jpg",
  "thumbnailUrl": "https://example.com/photos/thumbs/updated.jpg"
}
```

### Update Photo (Partial)
```http
PATCH /photos/{id}
Content-Type: application/json

{
  "title": "New Photo Title"
}
```

### Delete Photo
```http
DELETE /photos/{id}
```

---

## Testing with cURL Examples

### Complete Workflow Example

1. **Create a User:**
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Doe","username":"janedoe","email":"jane@example.com","phone":"555-1234","website":"janedoe.com"}'
```

2. **Create a Post for that User:**
```bash
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{"user":{"id":1},"title":"Hello World","body":"My first post content"}'
```

3. **Add a Comment to the Post:**
```bash
curl -X POST http://localhost:8080/comments \
  -H "Content-Type: application/json" \
  -d '{"post":{"id":1},"name":"John","email":"john@example.com","body":"Nice post!"}'
```

4. **Create a Todo:**
```bash
curl -X POST http://localhost:8080/todos \
  -H "Content-Type: application/json" \
  -d '{"user":{"id":1},"title":"Write tests","completed":false}'
```

5. **Create an Album:**
```bash
curl -X POST http://localhost:8080/albums \
  -H "Content-Type: application/json" \
  -d '{"user":{"id":1},"title":"Vacation Photos"}'
```

6. **Add a Photo to Album:**
```bash
curl -X POST http://localhost:8080/photos \
  -H "Content-Type: application/json" \
  -d '{"album":{"id":1},"title":"Beach Sunset","url":"https://example.com/beach.jpg","thumbnailUrl":"https://example.com/beach_thumb.jpg"}'
```

---

## Important Notes

1. **CORS is enabled** for all origins (`*`) - Update in production
2. **Timestamps** are automatically managed for `created_at` and `updated_at` fields
3. **Foreign Keys** are maintained - Deleting a parent will cascade delete children
4. **Validation** is minimal - Add `@Valid` and validation annotations as needed
5. **Pagination** is not implemented - Add using Spring Data's Pageable
