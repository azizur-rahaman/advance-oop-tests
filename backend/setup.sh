#!/bin/bash

# JSONPlaceholder Backend Quick Start Script

echo "🚀 JSONPlaceholder Backend Setup"
echo "=================================="
echo ""

# Check if PostgreSQL is installed
if ! command -v psql &> /dev/null; then
    echo "❌ PostgreSQL is not installed. Please install PostgreSQL first."
    echo "   Install using: brew install postgresql"
    exit 1
fi

echo "✅ PostgreSQL found"

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 17 or higher."
    echo "   Install using: brew install openjdk@17"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "❌ Java version must be 17 or higher. Current version: $JAVA_VERSION"
    exit 1
fi

echo "✅ Java $JAVA_VERSION found"

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven."
    echo "   Install using: brew install maven"
    exit 1
fi

echo "✅ Maven found"
echo ""

# Database setup
echo "📦 Setting up PostgreSQL database..."
echo "Please enter your PostgreSQL password when prompted:"
echo ""

# Create database
psql -U postgres -c "CREATE DATABASE jsonplaceholder;" 2>/dev/null
if [ $? -eq 0 ]; then
    echo "✅ Database 'jsonplaceholder' created successfully"
else
    echo "⚠️  Database might already exist or creation failed"
fi

echo ""
echo "📝 Please update the database credentials in:"
echo "   src/main/resources/application.properties"
echo ""
read -p "Press Enter when you've updated the credentials..."

# Build the project
echo ""
echo "🔨 Building the project..."
mvn clean install

if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
    echo ""
    echo "🎉 Setup complete! You can now run the application with:"
    echo "   mvn spring-boot:run"
    echo ""
    echo "The API will be available at: http://localhost:8080"
else
    echo "❌ Build failed. Please check the error messages above."
    exit 1
fi
