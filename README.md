# 🏨 BookMyStayApp

## 📌 Overview

BookMyStayApp is a Java-based Hotel Booking Application developed as part of **placement exam training**.

This project focuses on strengthening:

- Core Java Concepts
- Object-Oriented Programming (OOP)
- Software Design Principles
- Structured Application Development

---

# 🔍 Use Case 4 (UC4) – Room Search System (Read-Only)

## 🎯 Goal
Enable guests to view available rooms and their details without modifying system state, ensuring safe data access and clear separation of responsibilities.

---

## 🔄 Flow

- Guest initiates a room search
- System retrieves availability from inventory
- Room details are fetched from Room objects
- Unavailable rooms are filtered out
- Available rooms are displayed
- System state remains unchanged

---

## 🧠 Key Concepts Used

### 🔹 Read-Only Access
Search operations only retrieve data and do not modify inventory.

### 🔹 Defensive Programming
Filters out rooms with zero availability to ensure valid output.

### 🔹 Separation of Concerns
- SearchService → Handles search logic
- Inventory → Manages availability
- Room → Defines room details

### 🔹 Inventory as State Holder
Inventory is accessed only for reading availability data.

### 🔹 Domain Model Usage
Room objects provide pricing and details without duplicating data.

### 🔹 Validation Logic
Only rooms with availability > 0 are shown.

---

## ✅ Key Requirements Implemented

- Retrieve availability from centralized inventory
- Display only available rooms
- Show room details using Room objects
- No modification of inventory during search
- Clear separation between search and booking logic

---

## 📌 Example Output
===== AVAILABLE ROOMS =====

Room Type : Single Room
Beds : 1
Price : $120.0
Available : 5

Room Type : Double Room
Beds : 2
Price : $200.0
Available : 3

===========================


---

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