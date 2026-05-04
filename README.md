This project is a QA automation framework that includes:

UI testing for SauceDemo
API testing for the Fake Store API (https://fakestoreapi.com
)

It demonstrates end-to-end test automation including functional UI flows, API validation, and reusable test architecture.

It uses:
-Java
-TestNG
-Selenium WebDriver
-REST Assured
-Maven

Structure
src
 ├── main/java
 │    ├── api/                # API Main
 │       ├── utils/           # Helpers and utilities
             ├── auth/        # Authentication Helpers
             ├── user/        # Records associated with the User
 │       ├── menu/            # Terminal based menus
 |       ├── input/           # Custom User Input Class for re-usability
 ├── test/java
 │    ├── qa/driver           # Base test setup
 │    ├── qa/demos            # Test cases
          ├── qa/main         # Main function to run test cases
 │    ├── qa/error            # Catches errors
 │    ├── qa/products         # Product helpers
 │    ├── qa/utils            # Helpers for orders and users


 
 
