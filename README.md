
# Mini Leave Management System


## Table of Contents
1. Project Overview
2. Setup Instructions
3. Assumptions
4. Edge Cases Handled
5. API Endpoints
6. High-Level Design
7. Potential Improvements
8. Screenshots
## Project Overview
This project is a Mini Leave Management System (LMS) for a startup with 50 employees. The system allows:

- Adding new employees

- Applying for leave

- Approving or rejecting leave requests

- Tracking leave balances

The backend is built with Spring Boot, Java, MySQL, and Swagger/OpenAPI documentation for API testing.
## Setup Instructions
1. Clone the repository:
git clone <repository-url>

2. Navigate to the project folder:
cd mini-leave-management

3. Update application.properties with your MySQL configuration:
spring.datasource.url=jdbc:mysql://localhost:3306/lms_db
spring.datasource.username=root
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update

4. Build and run the project using Maven:
mvn clean install 

mvn spring-boot:run

5. Access Swagger UI:
http://localhost:8080/swagger-ui/index.html

## Assumptions
- Each employee starts with a default leave balance (e.g., 20 days).

- Leave types (e.g., Sick, Casual) are not considered in MVP.

- Only HR/admin can approve or reject leave requests.

- Dates are in yyyy-MM-dd format.
## Edge Cases Handled
- Applying leave before joining date

- Applying for more days than available leave balance

- Overlapping leave requests

- Employee not found

- Invalid date ranges (end date before start date)

- Rejecting or approving a non-existent leave request
##  Api Endpoints
Employee APIs
1. Add Employee
POST /employee/add

Request Body:

{

  "name": "John Doe",

  "email": "john@example.com",

  "department": "IT",

  "joiningDate": "2025-08-01",

  "leaveBalance": 20

}

Response Body:

{

  "id": 1,

  "name": "John Doe",

  "email": "john@example.com",

  "department": "IT",

  "joiningDate": "2025-08-01",

  "leaveBalance": 20

}

Leave APIs
1. Apply Leave
POST /leave/apply


Request Body:

{

  "employeeId": 1,

  "startDate": "2025-08-20",

  "endDate": "2025-08-22"

}


Response Body:

{

  "id": 1,

  "employeeId": 1,

  "startDate": "2025-08-20",

  "endDate": "2025-08-22",

  "status": "Pending",

  "appliedOn": "2025-08-17"

}

2. Approve Leave

POST /leave/approve?leaveId=1


3. Reject Leave

POST /leave/reject?leaveId=1

4. Get All Leaves / Filter by Employee

5. GET /leave/all?employeeId=1



## High-Level Design
Architecture: 

Client (Swagger UI/Postman)

        |
        v
    
Spring Boot REST API
  - Controllers
  - Services (business rules & validations)
  - Repositories (Spring Data JPA)

        |
        v

      MySQL


## 📌 Project Architecture

![Architecture Diagram](./images/HLD_architecture_diagram.png)



Scaling:

- Use pagination for GET requests

- Add caching (e.g., Redis) for frequently accessed leave data

- Use a connection pool for DB access

- Modular microservices for HR, Leave Management if company grows
## Potential Improvements

- Add leave types (Sick, Casual, Paid)

- Add role-based access control (HR, Manager, Employee)

- Integrate email notifications for leave approval/rejection

- Add frontend UI with React or Angular

- Deploy on Heroku/Render for live access


## 📨 Submission

- **ZIP Contents**: Source code (`src/`), `pom.xml`, `README.md`, **HLD diagram** (`diagrams/architecture.png`), optional `docs/` with sample requests.
- **Setup Steps**: See “Setup Instructions” above (MySQL config + run commands).
- **Edge Cases**: Listed under “Edge Cases Handled”.
- **API Endpoints**: Listed under “API Endpoints” + accessible via Swagger at `/swagger-ui/index.html`.
- **HLD Diagram**: Included at “High-Level Design” section and image file in `diagrams/`.
- **Deployment**: Not included (optional bonus). Can be added later to Heroku/Render if required.
