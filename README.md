# **Journal App API**  

## **Overview**  
The **Journal App API** is a **Spring Boot-based RESTful service** that allows users to **write and manage journal entries**. Each journal entry is linked to a user. Currently, basic CRUD operations for journal entries are implemented.  

---

## **Current Progress**  

### **✅ Implemented Features:**  
- **Spring Boot setup completed**  
- **MongoDB connection established**  
- **Basic Journal Entry APIs (GET, POST, PUT, DELETE) created**  
- **User and Journal Entry database linked via `userId`**  

### **🛠️ In Progress:**  
- User authentication (Login & Register)  
- API security (JWT authentication)  
- Enhancing error handling and validations  

---

## **Database Schema**  

### **1. User Collection** (Planned)  
Each user will have multiple journal entries.  

| Field     | Type     | Description                         |  
|-----------|---------|--------------------------------------|  
| `_id`     | ObjectId | Unique user ID (To be implemented)  |  
| `username` | String  | User’s unique username              |  

### **2. JournalEntry Collection** (Implemented)  
Journal entries linked to users.  

| Field     | Type     | Description                                |  
|-----------|---------|---------------------------------------------|  
| `_id`     | ObjectId | Unique identifier for the journal entry    |  
| `userId`  | ObjectId | Reference to the User collection (Planned) |  
| `title`   | String   | Title of the journal entry                 |  
| `content` | String   | Body of the journal entry                  |  
| `date`    | Date     | Date of creation/update                    |  

---

## **Current API Endpoints**  

### **Journal Entry APIs**  

| Method | Endpoint          | Description                                   |  
|--------|------------------|------------------------------------------------|  
| `POST` | `/journal/{username}` | Create a journal entry (User linking WIP) |  
| `GET`  | `/journal/{id}`   | Retrieve a journal entry by ID                |  
| `PUT`  | `/journal/{id}`   | Update an existing journal entry              |  
| `DELETE` | `/journal/{id}` | Delete a journal entry by ID                  |  

---

## **Setup Instructions**  

### **1. Clone the Repository**  
```bash
git clone <repository-url>
cd journal-app
```

### **2. Configure MongoDB Connection**  
Modify `src/main/resources/application.properties`:  
```
spring.data.mongodb.uri=mongodb://localhost:27017/journaldb
```

### **3. Run the Application**  
```bash
mvn spring-boot:run
```

---

## **Next Steps**  
- Implement **User Authentication**  
- Secure APIs using **JWT Token**  
- Add **frontend integration**  

---
