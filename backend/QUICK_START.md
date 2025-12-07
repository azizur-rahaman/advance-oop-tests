# 🚀 Quick Start Guide - JSONPlaceholder Backend

## Prerequisites Checklist
- [ ] Java 17+ installed
- [ ] Maven 3.6+ installed
- [ ] PostgreSQL 12+ installed and running
- [ ] Git installed

---

## Step 1: Database Setup (5 minutes)

### Option A: Using psql command line

```bash
# Start PostgreSQL (if not running)
# macOS with Homebrew:
brew services start postgresql

# Linux:
sudo service postgresql start

# Create database
psql -U postgres -c "CREATE DATABASE jsonplaceholder;"

# Run the schema
psql -U postgres -d jsonplaceholder -f /Users/azizurrahaman/Desktop/dorchata/Untitled.sql
```

### Option B: Using PostgreSQL GUI (pgAdmin, DBeaver, etc.)

1. Create a new database named `jsonplaceholder`
2. Execute the SQL from `Untitled.sql` file

---

## Step 2: Configure Database Credentials (2 minutes)

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

Replace `YOUR_USERNAME` and `YOUR_PASSWORD` with your PostgreSQL credentials.

---

## Step 3: Build & Run (3 minutes)

### Option A: Using Maven

```bash
# Navigate to project directory
cd /Users/azizurrahaman/Documents/GitHub/advance-oop-tests/backend

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Option B: Using IDE (IntelliJ IDEA, Eclipse, VS Code)

1. Open the project in your IDE
2. Wait for Maven to download dependencies
3. Run `JsonplaceholderApplication.java`

---

## Step 4: Verify Installation (1 minute)

### Check if server is running:

```bash
curl http://localhost:8080/users
```

**Expected Response:** JSON array (might be empty initially or contain sample data)

### Test with browser:
Open: http://localhost:8080/users

---

## Step 5: Test All Endpoints (5 minutes)

### Create your first user:

```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "username": "testuser",
    "email": "test@example.com",
    "phone": "123-456-7890",
    "website": "testuser.com"
  }'
```

### Get all users:

```bash
curl http://localhost:8080/users
```

### Create a post:

```bash
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "title": "Hello World",
    "body": "This is my first post!"
  }'
```

### Get all posts:

```bash
curl http://localhost:8080/posts
```

---

## Common Issues & Solutions

### ❌ "Port 8080 already in use"

**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

### ❌ "Connection refused" to PostgreSQL

**Solution 1:** Ensure PostgreSQL is running:
```bash
# Check status
brew services list | grep postgresql
# Or
sudo service postgresql status
```

**Solution 2:** Check connection details in `application.properties`

### ❌ "Table doesn't exist"

**Solution:** Run the SQL schema file:
```bash
psql -U postgres -d jsonplaceholder -f /Users/azizurrahaman/Desktop/dorchata/Untitled.sql
```

### ❌ Build fails with "mvn: command not found"

**Solution:** Install Maven:
```bash
# macOS
brew install maven

# Linux (Ubuntu/Debian)
sudo apt install maven
```

### ❌ Java version error

**Solution:** Check Java version:
```bash
java -version
```
If < 17, install Java 17:
```bash
# macOS
brew install openjdk@17

# Linux
sudo apt install openjdk-17-jdk
```

---

## Next Steps

✅ **Import Postman Collection**
   - File: `JSONPlaceholder_Postman_Collection.json`
   - Import into Postman for easy testing

✅ **Read API Documentation**
   - File: `API_DOCUMENTATION.md`
   - Contains all endpoint details

✅ **Enable Sample Data** (Optional)
   - Uncomment `DataInitializer.java` to auto-populate sample data on startup

---

## Stopping the Application

### If running with Maven:
Press `Ctrl + C` in the terminal

### If running as JAR:
```bash
# Find the process
ps aux | grep jsonplaceholder

# Kill it
kill -9 <PID>
```

---

## Project URLs

- **API Base URL:** http://localhost:8080
- **Users:** http://localhost:8080/users
- **Posts:** http://localhost:8080/posts
- **Comments:** http://localhost:8080/comments
- **Todos:** http://localhost:8080/todos
- **Albums:** http://localhost:8080/albums
- **Photos:** http://localhost:8080/photos

---

## Support

For detailed documentation:
- 📖 `README.md` - Full project documentation
- 📚 `API_DOCUMENTATION.md` - Complete API reference
- 🔧 Check logs in console for detailed error messages

---

**Happy Coding! 🎉**
