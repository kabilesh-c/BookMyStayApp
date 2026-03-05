# 🏨 BookMyStayApp

## 📌 Overview

BookMyStayApp is a Java-based Hotel Booking Application developed as part of **placement exam training**.

This project focuses on strengthening:

- Core Java Concepts
- Object-Oriented Programming (OOP)
- Software Design Principles
- Structured Application Development

---

# 🚀 Use Case 1 (UC1) – Application Entry & Welcome Page

### Goal
Demonstrate how a Java program starts execution and prints structured console output.

### Concepts Used
- Class
- main() method
- static keyword
- Console output
- JavaDoc documentation
- Linear execution flow

---

# 🏗 Use Case 2 (UC2) – Object Modeling with Inheritance

### Goal
Introduce object modeling through abstraction and inheritance before introducing data structures.

---
# 📦 Use Case 3 (UC3) – Centralized Inventory Management

## 🎯 Goal
Replace scattered availability variables with a centralized HashMap to manage room inventory consistently.

---

## 🧠 Problem Solved

In UC2, availability was stored in separate variables:
- Poor scalability
- Inconsistent updates
- No single source of truth

UC3 introduces a dedicated `RoomInventory` class.

---

## 🧩 Key Concepts Used

### 🔹 HashMap
`HashMap<String, Integer>` maps:
Room Type → Available Count

### 🔹 O(1) Lookup
Fast get() and put() operations.

### 🔹 Single Source of Truth
All availability stored in one centralized structure.

### 🔹 Encapsulation
Inventory logic is isolated in the `RoomInventory` class.

### 🔹 Separation of Concerns
- Room → What a room is
- Inventory → How many rooms are available

---

## 🚀 Scalability

Adding a new room type requires:
```java
inventory.registerRoom("Deluxe Room", 4);

## 🧠 Key Concepts Used

### 🔹 Abstract Class
- `Room` class defines common structure for all room types.
- Cannot be instantiated directly.

### 🔹 Inheritance
- `SingleRoom`
- `DoubleRoom`
- `SuiteRoom`
  extend the abstract `Room` class.

### 🔹 Polymorphism
Room objects are referenced using the `Room` type.

### 🔹 Encapsulation
Room attributes are private and accessed via getters.

### 🔹 Static Availability Representation
Availability stored using simple variables.

### 🔹 Separation of Domain and State
- Room → what a room is
- Availability → current system state

## 🏗 Project Structure
BookMyStayApp
│── .gitignore
│── README.md
│── App/
└── src/
└── BookMyStayApp.java


---

## 🔀 Development Workflow

This project follows an industry-style branching strategy:

- `main` → Stable production base
- `dev` → Integration branch
- `feature/UC1-WelcomePage` → Feature implementation
- Pull Request → Merge feature into `dev`

This workflow reflects how real software teams manage features and releases.

---

## ⚙️ How to Run

### Compile

```bash
javac App/src/BookMyStayApp.java
Run
java App.src.BookMyStayApp
Or run directly from your IDE.

📊 Learning Outcomes
This project strengthens my understanding of:

Java program lifecycle

JVM execution process

Structured coding practices

Clean method separation

Professional documentation using JavaDoc

Git branching & pull request workflow

Writing maintainable and readable code

🚀 Purpose
This repository is part of my placement exam preparation training, aimed at strengthening:

Data Structures & Algorithms foundation

Core Java proficiency

Object-Oriented Programming concepts

Software development best practices

Version control discipline

👨‍💻 Author
Kabilesh C
📧 kabileshc.dev@gmail.com