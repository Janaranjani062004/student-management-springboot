# 🚀 Student Management System

A Spring Boot based RESTful application for managing student information.  
This project demonstrates full CRUD operations using a clean layered architecture.

---

## 📌 Features

- ✅ Create, Read, Update, Delete (CRUD) operations  
- ✅ RESTful API design  
- ✅ Layered architecture (Controller, Service, Repository)  
- ✅ Exception handling with meaningful responses  
- ✅ MySQL database integration  
- ✅ Data persistence using Spring Data JPA  

---

## 🏗️ Architecture Overview

This project follows a 3-layer architecture:

- Controller Layer → Handles HTTP requests and responses  
- Service Layer → Contains business logic  
- Repository Layer → Interacts with the database  

This ensures separation of concerns and better maintainability.

---

## 🛠️ Tech Stack

- Java  
- Spring Boot  
- Spring Data JPA  
- MySQL  
- Maven  
- Git & GitHub  

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /students        | Get all students |
| GET    | /students/{id}   | Get student by ID |
| POST   | /students        | Create new student |
| PUT    | /students/{id}   | Update student |
| DELETE | /students/{id}   | Delete student |

---

## ▶️ How to Run the Project

1. Clone the repository  

git clone https://github.com/Janaranjani062004/student-management-springboot.git


2. Open the project in your IDE (Eclipse / IntelliJ)

3. Configure MySQL in `application.properties`

4. Run the application  

mvn spring-boot:run


5. Application will start at:  

http://localhost:8080


---

## 📷 Sample API Response

```json
{
  "id": 1,
  "name": "John Doe",
  "age": 20,
  "course": "Computer Science"
}
💡 What I Learned
Building REST APIs using Spring Boot
Implementing layered architecture
Handling exceptions effectively
Integrating MySQL with Spring Data JPA
🔗 GitHub Repository

👉 https://github.com/Janaranjani062004/student-management-springboot

 Author

Janaranjani







