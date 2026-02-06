Hotel Management System
------------------------
A full-stack Hotel Management System built using Spring Boot, Thymeleaf, JPA, and MySQL.
This application allows users to manage hotel bookings, customers, rooms, payments, and hotel details efficiently.

Features
---------

Hotel Management
Room Management
Customer Management
Booking Management
Payment Integration
Validation and Error Handling
MVC Architecture Implementation

Technologies Used
-----------------

Java
Spring Boot
Spring MVC
Spring Data JPA
Thymeleaf
MySQL
Maven
HTML / CSS / JavaScript

How to Run the Application
--------------------------
Prerequisites
Make sure you have installed:
Java 17 or later
Maven
MySQL Server
IntelliJ IDEA / Eclipse

API Endpoints / Modules
------------------------
Hotel APIs
----------
Method	Endpoint	Description
GET	/hotel/list	View all hotels
GET	/hotel/new	Create new hotel form
POST	/hotel/save	Save hotel details
GET	/hotel/edit/{id}	Edit hotel
GET	/hotel/delete/{id}	Delete hotel

 Room APIs
 ---------
Method	Endpoint	Description
GET	/room/list	View all rooms
GET	/room/new	Create new room
POST	/room/save	Save room
GET	/room/edit/{id}	Edit room
GET	/room/delete/{id}	Delete room

 Customer APIs
 -------------
Method	Endpoint	Description
GET	/customer/list	View customers
GET	/customer/new	Create customer
POST	/customer/save	Save customer
GET	/customer/edit/{id}	Edit customer
GET	/customer/delete/{id}	Delete customer

 Booking APIs
 ------------
Method	Endpoint	Description
GET	/booking/list	View all bookings
GET	/booking/new	Create booking
POST	/booking/save	Save booking
GET	/booking/edit/{id}	Edit booking
GET	/booking/delete/{id}	Delete booking

Payment Handling
----------------

Booking is not allowed without payment.
Payment status validation is implemented.
Total price is calculated based on room price and booking days.

MVC Architecture
-----------------

Controller Layer
Handles user requests and responses.
Service Layer
Contains business logic like booking validation and price calculation.
Repository Layer
Handles database operations using JPA.

Validation Rules
------------------

Booking date cannot be in the past.
Payment must be completed before booking confirmation.
Duplicate hotel name and address validation implemented.

Future Improvements
-------------------

REST API Support
JWT Authentication
Admin Dashboard
Payment Gateway Integration
Room Availability Live Tracking
