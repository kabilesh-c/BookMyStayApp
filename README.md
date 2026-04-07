# 🏨 BookMyStayApp

## 📌 Overview

BookMyStayApp is a Java-based Hotel Booking Application developed as part of **placement exam training**.

This project focuses on strengthening:

- Core Java Concepts
- Object-Oriented Programming (OOP)
- Software Design Principles
- Structured Application Development

---

# 🧾 Use Case 11 (UC11) – Concurrent Booking Simulation

## 🎯 Goal
Demonstrate how concurrent access to shared resources can lead to inconsistent system state and show how synchronization ensures correctness under multi-user conditions.

---

## 🧠 Problem Solved
Without synchronization in a multi-threaded environment:
- Multiple threads could decrement inventory simultaneously, leading to overbooking.
- Race conditions would cause inconsistent state in shared queues and maps.
- System reliability would fail under high traffic.

---

## 🧩 Key Concepts Used

### 🔹 Thread Safety
Ensures that shared resources (Inventory, Queue) behave correctly when accessed by multiple threads simultaneously.

### 🔹 synchronized Keywords
Used to define critical sections where only one thread can execute at a time. This protects shared mutable state from corruption.

### 🔹 Race Conditions & Critical Sections
Identifying parts of the code where interleaving operations cause issues and wrapping them in protective locks.

### 🔹 Multithreading (Thread Class)
Using `Thread` objects to simulate simultaneous booking requests from multiple users.

---

## 🏗 Folder Structure
The logic for UC11 is integrated into the core engine to demonstrate thread-safe state management:
```
App/
  src/
    BookMyStayApp.java          (Integrated Concurrent Simulation)
    BookingService.java         (Thread-safe logic)
    BookingQueue.java           (Synchronized methods)
    ...
```

---

## 🔄 Flow
1. Multiple threads are spawned to represent different guests.
2. Threads concurrently add requests to the `BookingQueue`.
3. `BookingService` processes these requests using synchronized access to avoid race conditions.
4. Inventory is decremented safely within a locked context.
5. The final inventory matches the total number of processed bookings exactly.

---

## ✅ Outcome
- System remains consistent even under high load.
- No race conditions or data corruption in the inventory.
- Real-world readiness for handling multiple users simultaneously.

## 🚀 Scalability
This foundation allows the application to be scaled to a web-based environment where many users interact with the same database.

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
