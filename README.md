Mini Leave Management System
Project Overview

This project is a Mini Leave Management System designed for a startup with 50 employees. It allows the HR team to:

Add employees with their details

Apply, approve, and reject leave requests

Track leave balances for each employee

The system handles edge cases like overlapping leaves, insufficient leave balance, invalid dates, and more.

Tech Stack

Backend: Java, Spring Boot

Database: MySQL

API Documentation: Swagger (springdoc-openapi)

Build Tool: Maven

Java Version: 17

Setup Instructions

Clone the project:

git clone <your-repo-url>
cd mini-leave-management


Create MySQL database:

CREATE DATABASE leave_management;


Update application.properties with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/leave_management
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


Run the project:

mvn spring-boot:run


Access Swagger UI:

http://localhost:8080/swagger-ui.html

API Endpoints
Endpoint	Method	Description	Request Body / Params
/employee/add	POST	Add a new employee	JSON with name, email, department, joiningDate, leaveBalance
/leave/apply	POST	Apply for leave	JSON with employeeId, startDate, endDate
/leave/approve	POST	Approve leave	leaveId as request param
/leave/reject	POST	Reject leave	leaveId as request param
/leave/all	GET	Fetch all leave requests	Optional employeeId as request param
/leave/balance	GET	Get leave balance	employeeId as request param
Sample Request – Apply Leave
{
  "employeeId": 1,
  "startDate": "2025-08-20",
  "endDate": "2025-08-22"
}

Sample Response – Leave Applied
{
  "id": 1,
  "employeeId": 1,
  "startDate": "2025-08-20",
  "endDate": "2025-08-22",
  "status": "Pending",
  "appliedOn": "2025-08-17"
}

Edge Cases Handled

Leave applied before joining date

Leave days exceeding balance

Overlapping leave requests

Employee not found

End date before start date

Invalid leave requests

Assumptions

Each employee has a fixed leave balance at the start.

Only HR can approve or reject leave.

Leave requests cannot overlap with already approved leaves.

Potential Improvements

Add authentication & authorization (Spring Security + JWT)

Add frontend UI

Email notifications for leave approval/rejection

Role-based access control for managers and employees

Support for half-day leaves

High-Level Design (HLD)

Frontend: Can be a simple React app consuming APIs

Backend: Spring Boot REST APIs

Database: MySQL storing Employee and LeaveRequest tables

Interaction: Frontend → Backend API → DB

(You can attach a simple architecture diagram here)