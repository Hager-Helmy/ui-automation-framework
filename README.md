# ui-automation-framework
OrangeHRM Automation Testing Project:
This repository contains the automation testing framework for the orangeHRM website "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" 
This framework is built using Selenium Webdriver with Java, TestNG and Maven build automation tool following the Page Object Model(POM) design pattern.
Prerequisites:
1- Java Development Kit installed (version 8 or above).
2- InteliJ IDE or similar IDE (Eclipse for example) installed.
3- Maven installed and configured.
4- Supported web browsers (Chrome, Edge).
5- Allure Tool installed for generating reports.
Framework Structure:
- src/main/java contains the classes for the webpages (it has locators and actions required for each page).
- src/test/java contains the TestNG classes, test data and test configurations.
- testng.xml file: Manages test suite configurations for parallel execution and cross browser testing, also it has allure listener configured for generating 
allure-results. 
Getting Started:
1- Clone the Repository:
  - git clone https://github.com/Hager-Helmy/ui-automation-framework.git
  - cd ui-automation-framework
2- Install Maven Dependencies:
  - mvn clean install 
3- Run tests
  - mvn test
4- Generate Allure Report
   - mvn allure:serve
