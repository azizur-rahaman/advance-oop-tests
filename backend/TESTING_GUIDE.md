# 🧪 Complete Testing Guide

This guide provides a complete workflow for testing all API endpoints.

## Test Sequence

Follow this sequence to test the complete application flow:

---

## Phase 1: Setup & Create Base Data

### Step 1: Create Users (Foundation)

```bash
# Create User 1
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Johnson",
    "username": "alice",
    "email": "alice@example.com",
    "phone": "555-0001",
    "website": "alice.dev"
  }'
# Note the returned ID (should be 1)

# Create User 2
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Bob Smith",
    "username": "bob",
    "email": "bob@example.com",
    "phone": "555-0002",
    "website": "bob.tech"
  }'
# Note the returned ID (should be 2)

# Verify users created
curl http://localhost:8080/users
```

### Step 2: Create Posts

```bash
# Create Post 1 by Alice
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Introduction to Spring Boot",
    "body": "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications."
  }'

# Create Post 2 by Alice
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Working with PostgreSQL",
    "body": "PostgreSQL is a powerful, open source object-relational database system."
  }'

# Create Post 3 by Bob
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 2},
    "title": "REST API Best Practices",
    "body": "Following best practices ensures your API is maintainable and scalable."
  }'

# Verify posts created
curl http://localhost:8080/posts
```

### Step 3: Add Comments

```bash
# Comment on Post 1
curl -X POST http://localhost:8080/comments \
  -H "Content-Type: application/json" \
  -d '{
    "post": {"id": 1},
    "name": "Bob Smith",
    "email": "bob@example.com",
    "body": "Great introduction! Very helpful."
  }'

# Another comment on Post 1
curl -X POST http://localhost:8080/comments \
  -H "Content-Type: application/json" \
  -d '{
    "post": {"id": 1},
    "name": "Charlie Brown",
    "email": "charlie@example.com",
    "body": "Thanks for sharing this!"
  }'

# Comment on Post 2
curl -X POST http://localhost:8080/comments \
  -H "Content-Type: application/json" \
  -d '{
    "post": {"id": 2},
    "name": "David Lee",
    "email": "david@example.com",
    "body": "PostgreSQL is indeed powerful!"
  }'

# Verify comments
curl http://localhost:8080/comments
```

### Step 4: Create Todos

```bash
# Todo for Alice
curl -X POST http://localhost:8080/todos \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Write documentation",
    "completed": false
  }'

# Another todo for Alice
curl -X POST http://localhost:8080/todos \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Review pull requests",
    "completed": true
  }'

# Todo for Bob
curl -X POST http://localhost:8080/todos \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 2},
    "title": "Update API tests",
    "completed": false
  }'

# Verify todos
curl http://localhost:8080/todos
```

### Step 5: Create Albums

```bash
# Album for Alice
curl -X POST http://localhost:8080/albums \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Conference 2024"
  }'

# Another album for Alice
curl -X POST http://localhost:8080/albums \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Team Building"
  }'

# Album for Bob
curl -X POST http://localhost:8080/albums \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 2},
    "title": "Project Screenshots"
  }'

# Verify albums
curl http://localhost:8080/albums
```

### Step 6: Add Photos

```bash
# Photos in Album 1
curl -X POST http://localhost:8080/photos \
  -H "Content-Type: application/json" \
  -d '{
    "album": {"id": 1},
    "title": "Opening Keynote",
    "url": "https://via.placeholder.com/600/1",
    "thumbnailUrl": "https://via.placeholder.com/150/1"
  }'

curl -X POST http://localhost:8080/photos \
  -H "Content-Type: application/json" \
  -d '{
    "album": {"id": 1},
    "title": "Panel Discussion",
    "url": "https://via.placeholder.com/600/2",
    "thumbnailUrl": "https://via.placeholder.com/150/2"
  }'

# Photo in Album 2
curl -X POST http://localhost:8080/photos \
  -H "Content-Type: application/json" \
  -d '{
    "album": {"id": 2},
    "title": "Team Lunch",
    "url": "https://via.placeholder.com/600/3",
    "thumbnailUrl": "https://via.placeholder.com/150/3"
  }'

# Verify photos
curl http://localhost:8080/photos
```

---

## Phase 2: Test GET Operations

### Retrieve All Resources

```bash
# Get all users
curl http://localhost:8080/users

# Get all posts
curl http://localhost:8080/posts

# Get all comments
curl http://localhost:8080/comments

# Get all todos
curl http://localhost:8080/todos

# Get all albums
curl http://localhost:8080/albums

# Get all photos
curl http://localhost:8080/photos
```

### Retrieve by ID

```bash
# Get specific user
curl http://localhost:8080/users/1

# Get specific post
curl http://localhost:8080/posts/1

# Get specific comment
curl http://localhost:8080/comments/1

# Get specific todo
curl http://localhost:8080/todos/1

# Get specific album
curl http://localhost:8080/albums/1

# Get specific photo
curl http://localhost:8080/photos/1
```

### Retrieve with Filters

```bash
# Get posts by user 1
curl http://localhost:8080/posts?userId=1

# Get posts by user 2
curl http://localhost:8080/posts?userId=2

# Get comments for post 1
curl http://localhost:8080/comments?postId=1

# Get todos for user 1
curl http://localhost:8080/todos?userId=1

# Get albums for user 1
curl http://localhost:8080/albums?userId=1

# Get photos for album 1
curl http://localhost:8080/photos?albumId=1
```

---

## Phase 3: Test PUT Operations (Full Update)

### Update User

```bash
curl -X PUT http://localhost:8080/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Johnson Updated",
    "username": "alice_updated",
    "email": "alice.new@example.com",
    "phone": "555-9999",
    "website": "alice-new.dev"
  }'

# Verify update
curl http://localhost:8080/users/1
```

### Update Post

```bash
curl -X PUT http://localhost:8080/posts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Updated: Introduction to Spring Boot",
    "body": "This is the updated content about Spring Boot with more details."
  }'

# Verify update (note the updated_at timestamp changed)
curl http://localhost:8080/posts/1
```

### Update Comment

```bash
curl -X PUT http://localhost:8080/comments/1 \
  -H "Content-Type: application/json" \
  -d '{
    "post": {"id": 1},
    "name": "Bob Smith Updated",
    "email": "bob.new@example.com",
    "body": "Updated comment: This is even more helpful now!"
  }'

# Verify update
curl http://localhost:8080/comments/1
```

### Update Todo

```bash
curl -X PUT http://localhost:8080/todos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Write comprehensive documentation",
    "completed": true
  }'

# Verify update
curl http://localhost:8080/todos/1
```

### Update Album

```bash
curl -X PUT http://localhost:8080/albums/1 \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Conference 2024 - Complete"
  }'

# Verify update
curl http://localhost:8080/albums/1
```

### Update Photo

```bash
curl -X PUT http://localhost:8080/photos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "album": {"id": 1},
    "title": "Opening Keynote - Updated",
    "url": "https://via.placeholder.com/600/updated",
    "thumbnailUrl": "https://via.placeholder.com/150/updated"
  }'

# Verify update
curl http://localhost:8080/photos/1
```

---

## Phase 4: Test PATCH Operations (Partial Update)

### Partially Update User

```bash
# Only update email
curl -X PATCH http://localhost:8080/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "email": "alice.patch@example.com"
  }'

# Verify - only email changed, other fields remain
curl http://localhost:8080/users/1
```

### Partially Update Post

```bash
# Only update title
curl -X PATCH http://localhost:8080/posts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "PATCHED: Spring Boot Guide"
  }'

# Verify - only title changed
curl http://localhost:8080/posts/1
```

### Partially Update Comment

```bash
# Only update body
curl -X PATCH http://localhost:8080/comments/1 \
  -H "Content-Type: application/json" \
  -d '{
    "body": "Just updating the comment text."
  }'

# Verify
curl http://localhost:8080/comments/1
```

### Partially Update Todo

```bash
# Only mark as completed
curl -X PATCH http://localhost:8080/todos/2 \
  -H "Content-Type: application/json" \
  -d '{
    "completed": true
  }'

# Verify - only completed status changed
curl http://localhost:8080/todos/2
```

### Partially Update Album

```bash
# Only update title
curl -X PATCH http://localhost:8080/albums/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Conference 2024 - Final"
  }'

# Verify
curl http://localhost:8080/albums/1
```

### Partially Update Photo

```bash
# Only update title
curl -X PATCH http://localhost:8080/photos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Keynote - Final Version"
  }'

# Verify
curl http://localhost:8080/photos/1
```

---

## Phase 5: Test DELETE Operations

### Delete in Reverse Order (to respect foreign keys)

```bash
# Delete a photo
curl -X DELETE http://localhost:8080/photos/3
# Verify deletion
curl http://localhost:8080/photos/3
# Should return 404

# Delete a comment
curl -X DELETE http://localhost:8080/comments/3
# Verify
curl http://localhost:8080/comments/3
# Should return 404

# Delete a todo
curl -X DELETE http://localhost:8080/todos/3
# Verify
curl http://localhost:8080/todos/3
# Should return 404

# Delete an album (will cascade delete its photos)
curl -X DELETE http://localhost:8080/albums/3
# Verify
curl http://localhost:8080/albums/3
# Should return 404

# Delete a post (will cascade delete its comments)
curl -X DELETE http://localhost:8080/posts/3
# Verify
curl http://localhost:8080/posts/3
# Should return 404

# Check if related comments were also deleted
curl http://localhost:8080/comments?postId=3
# Should return empty array

# Delete a user (will cascade delete all related data)
curl -X DELETE http://localhost:8080/users/2
# Verify
curl http://localhost:8080/users/2
# Should return 404

# Verify all Bob's data is gone
curl http://localhost:8080/posts?userId=2
curl http://localhost:8080/todos?userId=2
curl http://localhost:8080/albums?userId=2
# All should return empty arrays
```

---

## Phase 6: Error Handling Tests

### Test 404 Errors

```bash
# Non-existent user
curl http://localhost:8080/users/9999
# Should return 404

# Non-existent post
curl http://localhost:8080/posts/9999
# Should return 404
```

### Test 400 Errors

```bash
# Invalid foreign key
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 9999},
    "title": "Test",
    "body": "Test"
  }'
# Should return 400 or error
```

---

## Complete Test Script

Save this as `test-all.sh`:

```bash
#!/bin/bash

BASE_URL="http://localhost:8080"

echo "🧪 Starting complete API test..."

# Phase 1: Create data
echo "Creating users..."
curl -s -X POST $BASE_URL/users -H "Content-Type: application/json" -d '{"name":"Alice","username":"alice","email":"alice@test.com","phone":"555-0001","website":"alice.dev"}' > /dev/null
curl -s -X POST $BASE_URL/users -H "Content-Type: application/json" -d '{"name":"Bob","username":"bob","email":"bob@test.com","phone":"555-0002","website":"bob.dev"}' > /dev/null

echo "Creating posts..."
curl -s -X POST $BASE_URL/posts -H "Content-Type: application/json" -d '{"user":{"id":1},"title":"Test Post","body":"Test content"}' > /dev/null

echo "Creating todos..."
curl -s -X POST $BASE_URL/todos -H "Content-Type: application/json" -d '{"user":{"id":1},"title":"Test Todo","completed":false}' > /dev/null

# Test GET operations
echo "Testing GET operations..."
curl -s $BASE_URL/users | jq
curl -s $BASE_URL/posts | jq
curl -s $BASE_URL/todos | jq

echo "✅ Basic tests completed!"
```

---

## Success Criteria

All operations should:
- ✅ Return appropriate HTTP status codes
- ✅ Return valid JSON responses
- ✅ Maintain data consistency
- ✅ Respect foreign key relationships
- ✅ Update timestamps appropriately
- ✅ Handle errors gracefully

**Total Endpoints: 36**
**All should be functional and tested!**
