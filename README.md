# 🏨 BookMyStayApp

## 📌 Overview

BookMyStayApp is a Java-based Hotel Booking Application developed as part of **placement exam training**.

This project focuses on strengthening:

- Core Java Concepts
- Object-Oriented Programming (OOP)
- Software Design Principles
- Structured Application Development

---

# 🧾 Use Case 8 (UC8) – Booking History & Reporting

## 🎯 Goal
Introduce historical tracking of confirmed bookings to provide operational visibility, enable audits, and support reporting.

---

## 🧠 Problem Solved
Without historical tracking:
- No record of past successful bookings
- Admins cannot perform audits or view usage trends
- Loss of operational visibility once the processing is complete

---

## 🧩 Key Concepts Used

### 🔹 List Data Structure (ArrayList)
Used to store confirmed reservations while preserving the chronological insertion order.

### 🔹 Historical Tracking (Audit Trail)
Confirmed bookings are moved to a separate storage to form an audit trail for later review.

### 🔹 Separation of Storage and Reporting
Decoupled logic: `BookingService` handles storage into history, while a dedicated `BookingReportService` handles report generation.

### 🔹 Persistence Mindset
Even with in-memory storage, treating data as persistent prepares the foundation for future database integration.

---

## 🏗 Folder Structure
The folder structure has been updated with a new core class:
```
App/
  src/
    BookingReportService.java   (New Reporting Logic)
    BookingQueue.java
    BookingService.java         (Updated to maintain History)
    BookMyStayApp.java
    Reservation.java
    SearchService.java
```

---

## 🔄 Flow
1. Booking is successfully confirmed in `BookingService`.
2. The confirmed reservation is automatically added to the internal **Booking History**.
3. Admin requests a report via `BookingReportService`.
4. Summary and type-based reports are generated from the stored history.

---

## ✅ Outcome
- Clear visibility into system usage.
- Audit readiness for tracking all historical transactions.
- Clean separation of concerns between processing and reporting.

## 🚀 Scalability
Additional reports (e.g., revenue-based) can be added to `BookingReportService` without affecting the core booking flow.

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
