# 🏨 BookMyStayApp

## 📌 Overview

BookMyStayApp is a Java-based Hotel Booking Application developed as part of **placement exam training**.

This project focuses on strengthening:

- Core Java Concepts
- Object-Oriented Programming (OOP)
- Software Design Principles
- Structured Application Development

---

# 💾 Use Case 12 (UC12) – Data Persistence & System Recovery

## 🎯 Goal
Introduce persistence and recovery concepts using object serialization, transitioning the application from an in-memory-only model to a durable system design.

---

## 🧠 Problem Solved
Without persistence:
- All room inventory and booking history was lost when the application was closed.
- The system could not recover state after a crash or restart.
- Manual data entry was required for each session.

---

## 🧩 Key Concepts Used

### 🔹 Object Serialization
Using `java.io.Serializable` to convert complex objects like `Reservation` into a byte stream for storage.

### 🔹 File I/O (Binary)
Saving and loading the entire `SystemState` snapshot using `ObjectOutputStream` and `ObjectInputStream`.

### 🔹 System Recovery Logic
On startup, the app checks for existing `system_state.ser` files to restore the last known stable state of the inventory and history.

### 🔹 Snapshot DTO Pattern
Using a static inner class `SystemState` within `PersistenceService` to bundle multiple data structures (Map for inventory, List for history) into a single serializable unit.

---

## 🏗 Folder Structure
New service added to handle the serialization logic:
```
App/
  src/
    PersistenceService.java    (NEW - Handles Save/Load)
    Reservation.java           (Modified - Implements Serializable)
    BookMyStayApp.java         (Modified - Recovery Logic in Main)
    ...
```

---

## 🔄 Flow
1. **Startup**: `BookMyStayApp` calls `PersistenceService.loadSystemState()`.
2. **Recovery**: If a save file exists, the `RoomInventory` and `BookingService` history are restored.
3. **Execution**: The app runs normally, performing searches, bookings, and cancellations.
4. **Shutdown**: Before exit, `PersistenceService.saveSystemState()` is called to create a fresh snapshot of the final system state.

---

## ✅ Outcome
- Data survives application restarts.
- System state is consistently maintained across sessions.
- Foundation for moving to a full-scale database (SQL/NoSQL) in the future.

## 🚀 Durability
The application is now capable of being used as a real-world tool where booking records must be permanently stored.

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
