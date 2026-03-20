# 🏨 BookMyStayApp

## 📌 Overview

BookMyStayApp is a Java-based Hotel Booking Application developed as part of **placement exam training**.

This project focuses on strengthening:

- Core Java Concepts
- Object-Oriented Programming (OOP)
- Software Design Principles
- Structured Application Development

---

# 🧾 Use Case 7 (UC7) – Booking Confirmation & Safe Allocation

## 🎯 Goal
Ensure safe room allocation while preventing double booking and maintaining inventory consistency.

---

## 🧠 Problem Solved
Without controlled allocation:
- Same room may be assigned multiple times
- Inventory becomes inconsistent

---

## 🧩 Key Concepts Used

### 🔹 Set (Uniqueness)
Ensures no duplicate room IDs.

### 🔹 HashMap + Set
Tracks:
Room Type → Allocated Room IDs

### 🔹 FIFO Processing
Requests are processed in order from the queue.

### 🔹 Atomic Allocation
Room assignment + inventory update happen together.

---

## 🔄 Flow
1. Dequeue booking request
2. Check availability
3. Generate unique room ID
4. Assign room
5. Update inventory
6. Confirm booking

---

## ✅ Outcome
- No double booking
- Consistent inventory
- Fair processing (FIFO)

## 🚀 Scalability

Adding a new room type requires:

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
```

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
