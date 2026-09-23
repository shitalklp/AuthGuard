# 🔐 AuthGuard

AuthGuard is a Spring Boot authentication and user verification application built to implement secure user registration with email OTP verification.

This project focuses on backend authentication concepts such as password encryption, OTP generation and verification, email delivery, validation, and database persistence.

## 🚀 Features

- User registration using REST APIs
- Password encryption using BCrypt
- Email-based OTP verification
- 6-digit OTP generation
- OTP expiry of approximately 5 minutes
- Resend OTP functionality
- Prevents already verified users from requesting verification again
- Deletes the previous OTP before generating a new OTP
- Email delivery using Spring Mail
- PostgreSQL database persistence
- Input validation
- Spring Security integration
- RESTful API architecture

## 🛠️ Technology Used

- Java 21
- Spring Boot 4.1.1
- Spring Security 7.1.1
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Spring Mail
- Bean Validation
- Lombok
- Maven
- REST APIs

## 🔄 Registration & OTP Verification Flow

1. User submits name, email, and password.
2. Password is encrypted using BCrypt.
3. User information is stored in PostgreSQL.
4. A 6-digit OTP is generated.
5. OTP is sent to the user's email.
6. User submits the OTP.
7. OTP and expiry are validated.
8. After successful verification, the account is marked as verified.
9. Used OTP is deleted.

### 🔁 Resend OTP

1. Existing OTP is deleted.
2. A new 6-digit OTP is generated.
3. New expiry time is assigned.
4. New OTP is sent to the user's email.
