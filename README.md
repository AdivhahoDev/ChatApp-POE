# ChatApp-POE

## Project Description

This project is a Java console application developed as Part 1 of a Chat App POE.

The application allows a user to register and log in using a username, password and South African cell phone number.

The project uses Java, Maven and JUnit 5.

## Features

The application includes the following features:

- User registration
- Username validation
- Password complexity validation
- South African cell phone number validation
- User login
- Login status messages
- JUnit 5 unit tests
- Maven project management
- Console-based interaction

## Technologies Used

- Java 21
- Maven 3.9.16
- JUnit 5
- Git
- GitHub
- Visual Studio Code

## Username Requirements

The username must:

- Contain an underscore (_)
- Be no more than 5 characters long

Example:

`kyl_1` - Valid

`kyle!!!!!!!` - Invalid

## Password Requirements

The password must:

- Contain at least 8 characters
- Contain at least one capital letter
- Contain at least one number
- Contain at least one special character

Example:

`Ch&sec@ke99` - Valid

`password` - Invalid

## Cell Phone Number Requirements

The cell phone number must start with `+27` and be followed by 9 digits.

Example:

`+27838968976` - Valid

`08966553` - Invalid

The phone number is validated using a Regular Expression.

## Project Structure

```text
ChatApp-POE/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Login.java
│   │       └── Main.java
│   │
│   └── test/
│       └── java/
│           └── LoginTest.java
│
├── pom.xml
└── README.md

How to Run the Project

Step 1: Open the Project

Open the ChatApp-POE folder in Visual Studio Code.

Step 2: Compile the Project

Open the terminal in Visual Studio Code and run:

mvn compile

If everything is correct, Maven will display:

BUILD SUCCESS

Step 3: Run the Unit Tests

mvn test

The project contains 8 JUnit 5 tests.

The tests check:

* Valid username
* Invalid username
* Valid password
* Invalid password
* Valid cell phone number
* Invalid cell phone number
* Successful login
* Failed login

All 8 tests pass successfully.

Step 4: Run the Application

java -cp target/classes Main

The application will display a menu:

1. Register
2. Login
3. Exit

The user can register their details and then use those details to log in.

Example Registration

Enter your choice: 1

Enter your first name: Adivhaho
Enter your last name: Ramaano
Enter username: kyl_1
Enter password: Ch&sec@ke99
Enter phone number (+27...): +27838968976

If the information is valid, the application displays:

Username successfully captured.
Password successfully captured.
Cell number successfully captured.
Registration successful!

Example Login

After registration, select option 2:

Enter your choice: 2
Enter username: kyl_1
Enter password: Ch&sec@ke99

If the credentials are correct, the application displays:

Welcome Adivhaho Ramaano it is great to see you again.

If the credentials are incorrect, it displays:

Username or password incorrect, please try again.

Testing

JUnit 5 is used to test the methods in the Login class.

The test class is:

LoginTest.java

There are 8 unit tests in total.

The tests were successfully executed using Maven.

Version Control

Git and GitHub are used for version control.

The project contains at least 6 commits showing the development of the application.

Example commit history:

1. Initial project setup
2. Initial commit
3. Merge GitHub repository
4. Add project documentation
5. Improve Login validation comments
6. Add final project status

Project Status

* Registration and login functionality completed.
* Maven compilation successful.
* All 8 JUnit 5 tests passed successfully.
* Project uploaded to GitHub.
* Git version control implemented with at least 6 commits.

References

Regular-Expressions.info (n.d.) Regular expressions. Available at: https://www.regular-expressions.info/ (Accessed: 18 August 2026).

Author

Adivhaho Ramaano

Note

This project was developed for educational purposes as part of a university programming assignment.