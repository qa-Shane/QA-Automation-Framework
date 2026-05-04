This project is a QA automation framework that includes:
<br>
UI testing for SauceDemo<br>
API testing for the Fake Store API (https://fakestoreapi.com
)<br>
<br>
It demonstrates end-to-end test automation including functional UI flows, API validation, and reusable test architecture.<br>
<br>
It uses:<br>
-Java<br>
-TestNG<br>
-Selenium WebDriver<br>
-REST Assured<br>
-Maven<br>
<br>
Structure<br>
src<br>
 ├── main/java<br>
 │    ├── api/                # API Main<br>
 │       ├── utils/           # Helpers and utilities<br>
             ├── auth/        # Authentication Helpers<br>
             ├── user/        # Records associated with the User<br>
 │       ├── menu/            # Terminal based menus<br>
 |       ├── input/           # Custom User Input Class for re-usability<br>
 ├── test/java<br>
 │    ├── qa/driver           # Base test setup<br>
 │    ├── qa/demos            # Test cases<br>
          ├── qa/main         # Main function to run test cases<br>
 │    ├── qa/error            # Catches errors<br>
 │    ├── qa/products         # Product helpers<br>
 │    ├── qa/utils            # Helpers for orders and users<br>
