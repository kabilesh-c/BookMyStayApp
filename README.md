# 🏨 BookMyStayApp

## 📌 Overview

BookMyStayApp is a Java-based Hotel Booking Application developed as part of **placement exam training**.

This project focuses on strengthening:

- Core Java Concepts
- Object-Oriented Programming (OOP)
- Software Design Principles
- Structured Application Development

---

# 🧾 Use Case 10 (UC10) – Booking Cancellation & Inventory Rollback

## 🎯 Goal
Enable safe cancellation of confirmed bookings by correctly reversing system state changes, ensuring inventory consistency and predictable recovery behavior.

---

## 🧠 Problem Solved
Without cancellation logic:
- Confirmed bookings were final and could not be undone.
- Inventory would remain depleted even if a guest decided not to stay.
- System state could become inconsistent if attempted manually.

---

## 🧩 Key Concepts Used

### 🔹 Stack Data Structure (LIFO)
Used to track released room IDs. Stacks are ideal for rollback operations because they naturally reverse the last action performed.

### 🔹 State Reversal (Rollback)
The system performs a controlled sequence of operations to undo a booking: updating reservation status, logging the released ID, and incrementing inventory.

### 🔹 Controlled Mutation
State changes are performed in a strict order to prevent partial successes that could leave the system in an invalid state.

### 🔹 Inventory Restoration
Inventory counts are accurately incremented immediately, making the room available for future searches and bookings.

---

## 🏗 Folder Structure
The folder structure has been updated with a new core class:
```
App/
  src/
    CancellationService.java    (Cancellation & Rollback Logic)
    BookingReportService.java
    BookingQueue.java
    BookingService.java
    BookMyStayApp.java
    Reservation.java            (Updated for Cancel Status)
    SearchService.java
```

---

## 🔄 Flow
1. Guest initiates a cancellation request.
2. `CancellationService` validates that the reservation exists and is active.
3. The reservation is marked as `cancelled`.
4. The allocated `roomId` is pushed onto the **Rollback Stack**.
5. Inventory count for that `roomType` is incremented.
6. The system displays a confirmation of the state reversal.

---

## ✅ Outcome
- Full flexibility for guests to manage their bookings.
- Perfect inventory accuracy through automated restoration.
- Clear audit trail of released room IDs via the Rollback Stack.

## 🚀 Scalability
The rollback logic can be extended to handle refunds or notification triggers when a cancellation occurs.

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
