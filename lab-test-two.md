# Lab Test Two: Student Management System using Java Collections (Spring Boot)

**Course:** Advanced Object-Oriented Programming  
**Duration:** 30 hour  
**Total Marks:** 10

# Student Management System

## Problem Statement

You are given a **Spring Boot project** for managing students using **Java Collection Framework**.

Your task is to complete the required features using **Collections, Comparator/Comparable, Iterator, and I/O**.

---

## Given

### Student Attributes

- `id` (int)
- `name` (String)
- `cgpa` (double)

### Sample CSV File

```csv
id,name,cgpa
101,Arafat Rahman,3.75
102,Nusrat Jahan,3.90
103,Tanvir Hasan,3.40
104,Mehedi Ahmed,3.60
104,Mehedi Ahmed,3.60
105,Sadia Islam,3.85
```

> **Note:** The CSV contains a duplicate entry (ID 104) to demonstrate deduplication handling.

---

## Tasks

### Task 1: Load Students from CSV

- Read the given CSV file
- Store students in an `ArrayList`
- Ignore duplicate IDs using a `HashSet`

### Task 2: Sorting

- Implement `Comparable` to sort students by `id`
- Implement `Comparator` to sort students by `cgpa` (descending order)

### Task 3: Delete Using Iterator

- Remove a student by `id`
- Use `Iterator` for safe removal during iteration

### Task 4: REST API Exposure

Implement the following endpoints:

- **GET** `/students` → Return all students
- **GET** `/students/sort/cgpa` → Return students sorted by CGPA (descending)