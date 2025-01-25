# **Amazon Test Automation Project**

![Java](https://img.shields.io/badge/Java-11-blue.svg) ![TestNG](https://img.shields.io/badge/TestNG-7.8.0-brightgreen.svg) ![Selenium](https://img.shields.io/badge/Selenium-4.12.0-orange.svg) ![ExtentReports](https://img.shields.io/badge/ExtentReports-5.0.9-red.svg) ![Log4j](https://img.shields.io/badge/Log4j-2.20.0-yellow.svg)

## **Overview**
This project automates tests for Amazon's website using **Selenium WebDriver**, **TestNG**, and **ExtentReports**. It demonstrates essential web automation tasks, such as verifying the homepage title, performing searches, filtering results by brand, and navigating product pages. Logs are managed using **Log4j**, and detailed reports are generated with ExtentReports.

---

## **Features**
- Automated testing of Amazon's website functionality.
- Test cases:
  1. **Verify Homepage Title**: Ensures the homepage title contains "Amazon".
  2. **Search Test**: Searches for a product (e.g., "laptop") and verifies the results.
  3. **Filter Results by Brand**: Applies a brand filter (e.g., "HP") and verifies the filtered results.
  4. **Verify Product Navigation**: Clicks on a product and verifies the product page title.
- Screenshots captured at every significant step.
- Logs generated for every test execution.
- Detailed HTML reports for test results.

---

## **Project Structure**
```
TestNG_assignment/
├── src/
│   └── test/
│       └── java/
│           └── test/
│               └── java/
│                   └── AmazonTest.java      # Main test class
├── logs/                                    # Directory for log files
├── target/                                  # Maven output directory
│   ├── classes/                             # Compiled .class files
│   ├── screenshots/                         # Screenshots from tests
│   └── ExtentReport.html                    # HTML report for test results
├── pom.xml                                  # Maven configuration file
```

---

## **Technologies Used**
- **Java**: Programming language for writing tests.
- **Selenium WebDriver**: For browser automation.
- **TestNG**: Testing framework for managing and executing tests.
- **ExtentReports**: Generates detailed HTML reports.
- **Log4j**: Logging framework for tracking test execution.
- **Maven**: Build automation and dependency management.

---

## **Setup and Installation**

### **Prerequisites**
1. **Java 11**: Ensure Java Development Kit (JDK) 11 is installed.
2. **Maven**: Install Maven for dependency management and build.
3. **ChromeDriver**: Download the appropriate ChromeDriver version for your browser.

### **Steps**
1. Clone the repository:
   ```bash
   git clone https://github.com/Sabinakass/TestNG_assignment.git
   ```
2. Navigate to the project directory:
   ```bash
   cd TestNG_assignment
   ```
3. Install dependencies:
   ```bash
   mvn clean install
   ```

---

## **Usage**

### **Run Tests**
To execute the tests, use the following Maven command:
```bash
mvn test
```

### **Test Report**
After running the tests:
- The detailed HTML report is generated at:
  ```
  target/ExtentReport.html
  ```
- Open this file in a browser to view test results with screenshots.

---

## **Key Files**
- **`AmazonTest.java`**:
  - Contains test methods for automating Amazon's functionality.
- **`log4j2.xml`**:
  - Configures logging behavior (e.g., log format, output locations).
- **`ExtentReport.html`**:
  - A detailed test execution report with screenshots.

---

## **Customization**

### **1. Changing the Test URL**
To test a different website, modify the following line in `AmazonTest.java`:
```java
driver.get("https://www.amazon.com");
```

### **2. Adjusting Test Parameters**
You can update search terms, filters, or expected results by modifying test methods in `AmazonTest.java`.

---

## **Issues and Debugging**

### **Common Issues**
1. **WebDriver Not Found**:
   - Ensure `chromedriver` is installed and added to your system PATH.
   - Update the WebDriver path in `AmazonTest.java`:
     ```java
     System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
     ```

2. **Tests Failing**:
   - Inspect the log files (`logs/test-log.log`) for error details.
   - Review screenshots in the `target/screenshots/` folder.

3. **Extent Report Missing Screenshots**:
   - Ensure the `target/screenshots/` directory exists.
   - Check the screenshot file paths in the `takeScreenshot` method of `AmazonTest.java`.

---

## **Contributing**
1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature-branch
   ```
3. Commit changes:
   ```bash
   git commit -m "Your descriptive message"
   ```
4. Push changes and create a pull request.

---

## **License**
This project is licensed under the [MIT License](LICENSE).

---

## **Contact**
For any questions or feedback, feel free to reach out:
- **Author**: Sabina Kassymova
- **GitHub**: [Sabinakass](https://github.com/Sabinakass)
