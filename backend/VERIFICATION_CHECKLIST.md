# ✅ API Endpoint Verification Checklist

Use this checklist to verify all endpoints are working correctly.

## 1. Users API (/users)

### GET - Retrieve all users
```bash
curl http://localhost:8080/users
```
- [ ] Returns 200 OK
- [ ] Returns array of users

### GET - Retrieve user by ID
```bash
curl http://localhost:8080/users/1
```
- [ ] Returns 200 OK with valid ID
- [ ] Returns 404 NOT FOUND with invalid ID

### POST - Create new user
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
- [ ] Returns 201 CREATED
- [ ] Returns created user with ID

### PUT - Update entire user
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
- [ ] Returns 200 OK
- [ ] Returns updated user

### PATCH - Partially update user
```bash
curl -X PATCH http://localhost:8080/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "email": "newemail@example.com"
  }'
```
- [ ] Returns 200 OK
- [ ] Only specified field is updated

### DELETE - Remove user
```bash
curl -X DELETE http://localhost:8080/users/1
```
- [ ] Returns 204 NO CONTENT
- [ ] User is deleted from database

---

## 2. Posts API (/posts)

### GET - Retrieve all posts
```bash
curl http://localhost:8080/posts
```
- [ ] Returns 200 OK
- [ ] Returns array of posts

### GET - Retrieve posts by user ID
```bash
curl http://localhost:8080/posts?userId=1
```
- [ ] Returns 200 OK
- [ ] Returns only posts for specified user

### GET - Retrieve post by ID
```bash
curl http://localhost:8080/posts/1
```
- [ ] Returns 200 OK
- [ ] Returns 404 NOT FOUND with invalid ID

### POST - Create new post
```bash
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Test Post",
    "body": "This is a test post."
  }'
```
- [ ] Returns 201 CREATED
- [ ] Returns created post with timestamps

### PUT - Update entire post
```bash
curl -X PUT http://localhost:8080/posts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Updated Title",
    "body": "Updated body content."
  }'
```
- [ ] Returns 200 OK
- [ ] updated_at timestamp is changed

### PATCH - Partially update post
```bash
curl -X PATCH http://localhost:8080/posts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "New Title Only"
  }'
```
- [ ] Returns 200 OK
- [ ] Only title is updated

### DELETE - Remove post
```bash
curl -X DELETE http://localhost:8080/posts/1
```
- [ ] Returns 204 NO CONTENT
- [ ] Post and related comments are deleted

---

## 3. Comments API (/comments)

### GET - Retrieve all comments
```bash
curl http://localhost:8080/comments
```
- [ ] Returns 200 OK
- [ ] Returns array of comments

### GET - Retrieve comments by post ID
```bash
curl http://localhost:8080/comments?postId=1
```
- [ ] Returns 200 OK
- [ ] Returns only comments for specified post

### POST - Create new comment
```bash
curl -X POST http://localhost:8080/comments \
  -H "Content-Type: application/json" \
  -d '{
    "post": {"id": 1},
    "name": "Commenter Name",
    "email": "commenter@example.com",
    "body": "Great post!"
  }'
```
- [ ] Returns 201 CREATED
- [ ] Returns created comment with timestamp

### PUT - Update entire comment
```bash
curl -X PUT http://localhost:8080/comments/1 \
  -H "Content-Type: application/json" \
  -d '{
    "post": {"id": 1},
    "name": "Updated Name",
    "email": "updated@example.com",
    "body": "Updated comment."
  }'
```
- [ ] Returns 200 OK
- [ ] All fields are updated

### PATCH - Partially update comment
```bash
curl -X PATCH http://localhost:8080/comments/1 \
  -H "Content-Type: application/json" \
  -d '{
    "body": "Edited comment text"
  }'
```
- [ ] Returns 200 OK
- [ ] Only body is updated

### DELETE - Remove comment
```bash
curl -X DELETE http://localhost:8080/comments/1
```
- [ ] Returns 204 NO CONTENT
- [ ] Comment is deleted

---

## 4. Todos API (/todos)

### GET - Retrieve all todos
```bash
curl http://localhost:8080/todos
```
- [ ] Returns 200 OK
- [ ] Returns array of todos

### GET - Retrieve todos by user ID
```bash
curl http://localhost:8080/todos?userId=1
```
- [ ] Returns 200 OK
- [ ] Returns only todos for specified user

### POST - Create new todo
```bash
curl -X POST http://localhost:8080/todos \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Complete testing",
    "completed": false
  }'
```
- [ ] Returns 201 CREATED
- [ ] completed defaults to false if not specified

### PUT - Update entire todo
```bash
curl -X PUT http://localhost:8080/todos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Updated task",
    "completed": true
  }'
```
- [ ] Returns 200 OK
- [ ] All fields are updated

### PATCH - Partially update todo
```bash
curl -X PATCH http://localhost:8080/todos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "completed": true
  }'
```
- [ ] Returns 200 OK
- [ ] Only completed status is updated

### DELETE - Remove todo
```bash
curl -X DELETE http://localhost:8080/todos/1
```
- [ ] Returns 204 NO CONTENT
- [ ] Todo is deleted

---

## 5. Albums API (/albums)

### GET - Retrieve all albums
```bash
curl http://localhost:8080/albums
```
- [ ] Returns 200 OK
- [ ] Returns array of albums

### GET - Retrieve albums by user ID
```bash
curl http://localhost:8080/albums?userId=1
```
- [ ] Returns 200 OK
- [ ] Returns only albums for specified user

### POST - Create new album
```bash
curl -X POST http://localhost:8080/albums \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Vacation Photos"
  }'
```
- [ ] Returns 201 CREATED
- [ ] Returns created album with timestamp

### PUT - Update entire album
```bash
curl -X PUT http://localhost:8080/albums/1 \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Updated Album Title"
  }'
```
- [ ] Returns 200 OK
- [ ] Album is updated

### PATCH - Partially update album
```bash
curl -X PATCH http://localhost:8080/albums/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "New Album Name"
  }'
```
- [ ] Returns 200 OK
- [ ] Only title is updated

### DELETE - Remove album
```bash
curl -X DELETE http://localhost:8080/albums/1
```
- [ ] Returns 204 NO CONTENT
- [ ] Album and related photos are deleted

---

## 6. Photos API (/photos)

### GET - Retrieve all photos
```bash
curl http://localhost:8080/photos
```
- [ ] Returns 200 OK
- [ ] Returns array of photos

### GET - Retrieve photos by album ID
```bash
curl http://localhost:8080/photos?albumId=1
```
- [ ] Returns 200 OK
- [ ] Returns only photos for specified album

### POST - Create new photo
```bash
curl -X POST http://localhost:8080/photos \
  -H "Content-Type: application/json" \
  -d '{
    "album": {"id": 1},
    "title": "Sunset",
    "url": "https://example.com/sunset.jpg",
    "thumbnailUrl": "https://example.com/thumb/sunset.jpg"
  }'
```
- [ ] Returns 201 CREATED
- [ ] Returns created photo with timestamp

### PUT - Update entire photo
```bash
curl -X PUT http://localhost:8080/photos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "album": {"id": 1},
    "title": "Updated Sunset",
    "url": "https://example.com/new-sunset.jpg",
    "thumbnailUrl": "https://example.com/thumb/new-sunset.jpg"
  }'
```
- [ ] Returns 200 OK
- [ ] All fields are updated

### PATCH - Partially update photo
```bash
curl -X PATCH http://localhost:8080/photos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Evening Sunset"
  }'
```
- [ ] Returns 200 OK
- [ ] Only title is updated

### DELETE - Remove photo
```bash
curl -X DELETE http://localhost:8080/photos/1
```
- [ ] Returns 204 NO CONTENT
- [ ] Photo is deleted

---

## Summary

Total Endpoints Tested: **36**

**By Resource:**
- Users: 6 endpoints
- Posts: 6 endpoints
- Comments: 6 endpoints
- Todos: 6 endpoints
- Albums: 6 endpoints
- Photos: 6 endpoints

**By Method:**
- GET: 12 endpoints (all resources + filtered queries)
- POST: 6 endpoints (create)
- PUT: 6 endpoints (full update)
- PATCH: 6 endpoints (partial update)
- DELETE: 6 endpoints (remove)

**Status:** _____ / 36 endpoints verified ✅

---

## Notes

- All timestamps (created_at, updated_at) should be in ISO 8601 format
- CORS is enabled for all origins (*)
- Foreign key relationships are maintained
- Cascade delete is enabled (deleting parent deletes children)
