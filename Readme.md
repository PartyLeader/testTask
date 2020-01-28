Test Automation Framework
-
---
**Currently supported features:**

- Truly parallel test execution (using **Maven Surefire**)
- Detailed and customizable reporting via **Allure 2**
- Built-in **Chrome** driver 78
- Screenshots on test failure

---

**Run test:**

- mvn test

**Run report:**

- mvn site

*Report folder:*
 ./target/site/allure-maven.html
 
 
---

**Possible errors and how to fix:**
- Org.openqa.selenium.SessionNotCreatedException: session not created:
    - Check browser version and update Chrome 
    - Download ChromeDriver with same Chrome version and save to ./src/main/drivers/chromedriver
    
**Test results:**
- Expected results: 2 Passed, 1 Skipped

- Possible results: 
    - Failed the sectionVerification test. It means that you open a site from a different location.
    Expected Poland location.
    
    - Failed the shoppingCartTest test. Can be error in selected item. 
    You cannot select the number of products to buy.
    
    
    
    