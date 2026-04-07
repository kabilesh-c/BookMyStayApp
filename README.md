# 🏨 BookMyStayApp

## 📌 Overview

BookMyStayApp is a Java-based Hotel Booking Application developed as part of **placement exam training**.

This project focuses on strengthening:

- Core Java Concepts
- Object-Oriented Programming (OOP)
- Software Design Principles
- Structured Application Development

---

# 🧾 Use Case 9 (UC9) – Error Handling & Validation

## 🎯 Goal
Strengthen system reliability by introducing structured validation and error handling, ensuring that invalid inputs and inconsistent states are detected and handled early.

---

## 🧠 Problem Solved
Without structured error handling:
- Invalid room types could enter the system.
- Empty guest names could be processed.
- Inventory could reach inconsistent states.
- Support for complex debugging was limited due to lack of explicit error messages.

---

## 🧩 Key Concepts Used

### 🔹 Custom Exceptions (`BookingException`)
Domain-specific exceptions represent invalid booking scenarios explicitly, improving readability and error tracing.

### 🔹 Fail-Fast Design
The system detects errors (like invalid room types or empty names) at the start of the processing loop, preventing wasted resources and cascading failures.

### 🔹 Input Validation
Guarding the system from processing corrupted or incomplete `Reservation` objects.

### 🔹 Graceful Failure Handling
Try-catch blocks allow the system to report a failure for one request and move safely to the next without crashing.

---

## 🏗 Folder Structure
The folder structure has been updated with new core classes:
```
App/
  src/
    BookingException.java       (Custom Exception)
    BookingValidator.java       (Business Logic Validation)
    BookingQueue.java
    BookingService.java         (Updated to use Validator)
    BookMyStayApp.java          (Integrated UC9 flow)
    ...
```

---

## 🔄 Flow
1. Booking request is pulled from the queue.
2. `BookingValidator` checks:
   - Is the reservation object valid?
   - Is the guest name provided?
   - Is the room type supported?
   - Is there enough inventory?
3. If any check fails, a `BookingException` is thrown.
4. `BookingService` catches the exception and displays a meaningful error message.
5. The loop continues to the next reservation request.

---

## ✅ Outcome
- Early detection of invalid inputs.
- Robust inventory management preventing negative values.
- Informative feedback for administrators and guests.
- System stability maintained even under unexpected input conditions.

## 🚀 Scalability
New validation rules (e.g., age checks, payment verification) can be added to `BookingValidator` without modifying the core allocation logic in `BookingService`.

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
