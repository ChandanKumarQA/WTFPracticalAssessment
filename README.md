# WTF GYMS QA Engineer Practical Assessment

Hey there! 👋 Welcome to my submission for the QA Engineer Practical Assessment. 

This repository contains everything needed to validate the SauceDemo e-commerce checkout flow. I've broken this down into two main parts: the manual testing documentation (because strategy matters!) and a robust, scalable Selenium automation framework to handle the heavy lifting.

## What's Inside?
Here is a quick look at what I've put together:
* **Test Plan** – My strategy for how I approach testing this application.
* **Test_Cases.csv** – A thorough list of manual test cases covering edge cases and happy paths.
* **Bug_Reports.csv** – A log of issues found during exploratory testing.
* **Risk & Product Observations** – My thoughts on UX gaps and potential areas for improvement.
* **Written Responses** – Answers to the assessment questions regarding strategy, API testing, and handling "by design" pushback.
* **Selenium Automation** – A clean, Java-based automation suite using TestNG and the Page Object Model (POM).

## Tech Stack
* **UI Automation:** Selenium WebDriver (Java)
* **Build Tool:** Maven
* **Test Runner & Assertions:** TestNG
* **Design Pattern:** Page Object Model (POM)
* **CI/CD:** GitHub Actions (ready for automated runs!)

## Getting Started
If you want to run this on your local machine, it's super easy.

1. Clone this repository:
```bash
git clone <repository-url>
```
2. Navigate into the project directory:
```bash
cd WTFPracticalAssessment
```
3. Kick off the test suite using Maven:
```bash
mvn clean test
```

## How the Code is Organized
I believe in keeping test code clean and maintainable. Here’s how I’ve structured the project:

```
WTFPracticalAssessment/
│
├── src/main/java/
│   ├── DataProvider/
│   │   └── Logindata.java (Centralized DataProvider logic)
│   ├── GenricUtility/
│   │   └── BaseClass.java (Handles driver setup and teardown)
│   ├── WebdriverUtility/
│   │   ├── JavaUtility.java
│   │   └── WebdriverHandel.java (Handles screenshots!)
│   └── pageobject/ (All the Page Object Models)
│       ├── LoginPage.java
│       ├── InventoryPage.java
│       ├── CartPage.java
│       ├── CheckoutPage.java
│       └── CheckoutOverviewPage.java
│
├── src/test/
│   ├── java/Test/ (The actual TestNG test classes)
│   │   ├── logintheuser.java (Uses DataProvider)
│   │   ├── CartTest.java
│   │   ├── CheckoutTest.java
│   │   ├── SortingTest.java
│   │   └── LogoutTest.java
│   └── resources/
│       └── login_data.csv (External data source for testing)
│
├── Test Plan
├── Test_Cases.csv
├── Bug_Reports.csv
├── Risk & Product Observations
├── Written Responses
│
├── pom.xml
└── testng.xml (The suite runner)
```

## What's Automated?
I've focused the automation effort on the highest-value regression scenarios:
* **Data-Driven Login Testing:** A fully parameterized login test fetching 5 scenarios (valid, locked-out, empty fields, invalid credentials) directly from an external `login_data.csv` file using a TestNG `@DataProvider`.
* Adding and removing multiple products, and verifying the cart badge updates correctly.
* Sorting inventory (Price: Low to High, High to Low).
* The complete checkout funnel (including what happens when users skip required fields like First Name).
* Secure logout and session termination.

All tests output to the console with detailed `System.out.println` statements and generate clean Extent/Allure reports through TestNG. Every run is designed to be traceable!

Thanks for checking out my work! Let me know if you have any questions.
