# Recurring Deposit Management System

## Project Overview

Recurring Deposit Management System is a full stack web application developed to manage recurring deposit accounts digitally.

The project is built using Java Spring Boot for the backend and React for the frontend.

This system helps banks or financial organizations manage customers, agents, recurring deposit schemes, installments, transactions, and reports from one place.

The application is still under development and more features will be added in future versions.

---

# Technologies Used

### Backend

* Java 21
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Swagger

### Frontend

* React
* React Router
* Axios
* React Toastify
* Bootstrap
* CSS
* Vite

### Database

* PostgreSQL

### Deployment

* Backend : Render
* Frontend : Render

---

# User Roles

## 1. Admin

Admin account is **not created from the application**.

Admin is created directly using a database query for security.

Admin has complete control over the system.

Admin Features

* Login
* View Dashboard
* View Total Users
* Delete Users
* View Customers
* View Agents
* Create RD Schemes
* Update RD Schemes
* Delete RD Schemes
* View Transactions
* View Reports
* View Active RD Accounts
* View Closed RD Accounts
* View Today's Collection
* View Monthly Collection
* View Today's Profit

---

## 2. Agent

Agent creates his own account.

After registration, the agent completes his profile.

Agent Features

* Register
* Login
* Complete Profile
* View Dashboard
* Register Customers
* Open RD Accounts
* Collect Monthly Installments
* View Customer Details
* View Transactions

---

## 3. Customer

Customer creates his own account.

Customer completes profile after registration.

Customer Features

* Register
* Login
* Complete Profile
* View Dashboard
* View RD Accounts
* View Installments
* View Transaction History
* View Maturity Details

Future

* Online payment
* Download receipt
* SMS notifications
* Email notifications

---

# Project Flow

User Registration

↓

Login

↓

JWT Token Generated

↓

Frontend stores JWT Token

↓

Every API sends JWT Token

↓

Spring Security verifies Token

↓

Requested API executes

↓

Data stored in PostgreSQL

↓

Frontend updates Dashboard

---

# Main Modules

## Authentication

* Registration
* Login
* JWT Authentication
* Role Based Authorization

---

## User Module

* User Registration
* User Login
* Profile Completion
* Delete User

---

## Agent Module

* Agent Profile
* Customer Registration
* RD Account Creation
* Installment Collection

---

## Customer Module

* Customer Profile
* View RD Accounts
* View Installments
* Transaction History

---

## Scheme Module

* Create Scheme
* Update Scheme
* Delete Scheme
* View Schemes

---

## RD Account Module

* Open RD Account
* View RD Details
* Maturity Amount
* Account Status

---

## Transaction Module

* Installment Collection
* Transaction History
* Reports

---

# Database Tables

Current Tables

* users
* admins
* agents
* customers
* schemes
* rd_accounts
* installments
* transactions

More tables will be added in future.

---

# Security

* JWT Authentication
* Password Encryption using BCrypt
* Role Based Authorization
* Protected APIs
* CORS Configuration

---

# Current Features

✔ User Authentication

✔ JWT Login

✔ Role Based Access

✔ Admin Dashboard

✔ Agent Dashboard

✔ Customer Dashboard

✔ RD Scheme Management

✔ RD Account Management

✔ Installment Tracking

✔ Transaction Module

✔ PostgreSQL Integration

✔ Swagger Documentation

✔ Responsive Frontend

✔ Live Deployment

---

# Future Improvements

The project is continuously improving.

Upcoming features include:

* Razorpay Payment Gateway
* UPI Payment
* Customer Online Installment Payment
* Automatic Receipt Generation (PDF)
* SMS Notification
* Email Notification
* WhatsApp Notification
* Monthly Reminder System
* Penalty Calculation
* Interest Calculation
* Maturity Alert
* Audit Logs
* Branch Management
* Employee Management
* Analytics Dashboard
* Charts and Reports
* Export Reports (Excel & PDF)
* Docker Support
* CI/CD Pipeline
* Unit Testing
* Integration Testing
* Redis Caching
* Microservices Architecture
* Mobile Application

---

# Learning Objectives

This project helped me learn

* Spring Boot
* Spring Security
* JWT Authentication
* Hibernate
* PostgreSQL
* REST API Development
* React
* React Router
* Axios
* Frontend and Backend Integration
* Deployment on Render
* Git and GitHub

---

# Project Status

Current Version : 
Version 1.0

project Link :
https://recurring-deposit-management.onrender.com 

Status
Development is active.

New features and improvements will continue to be added.


# Developer

Sayyed Mohammad Zunnurain

Java Full Stack Developer

Mumbai, India
