package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AmazonTest {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private static final Logger logger = LogManager.getLogger(AmazonTest.class);

    @BeforeClass
    public void setUp() {
        logger.info("Setting up WebDriver and Extent Reports");
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        test = extent.createTest("Amazon Test Suite");
    }

    @Test(priority = 1)
    public void verifyHomePageTitle() {
        logger.info("Navigating to Amazon website");
        driver.get("https://www.amazon.com");

        logger.info("Verifying homepage title");
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Amazon"), "Homepage title does not contain 'Amazon'");

        test.pass("Homepage title verified successfully");
    }

    @Test(priority = 2)
    public void searchTest() {
        logger.info("Searching for 'laptop'");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop");
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        logger.info("Verifying search results");
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("laptop"), "Page title does not contain 'laptop'");

        test.pass("Search test passed successfully");
    }

    @Test(priority = 3)
    public void filterResultsByBrand() {
        logger.info("Applying filter for 'HP' brand");

        // Assuming the filter link for 'HP' is visible in the left sidebar
        WebElement brandFilter = driver.findElement(By.xpath("//span[text()='HP']"));
        brandFilter.click();

        logger.info("Verifying filtered results contain 'HP'");
        List<WebElement> results = driver.findElements(By.cssSelector(".s-title-instructions-style"));
        for (WebElement result : results) {
            Assert.assertTrue(result.getText().toLowerCase().contains("hp"), "Result does not contain 'HP'");
        }

        test.pass("Filter by brand test passed successfully");
    }

    @Test(priority = 4)
    public void verifyProductNavigation() {
        logger.info("Clicking on the first product");

        List<WebElement> productLinks = driver.findElements(By.cssSelector(".s-title-instructions-style"));
        Assert.assertTrue(productLinks.size() > 0, "No products found on the page");
        WebElement firstProduct = productLinks.get(0);
        String expectedProductTitle = firstProduct.getText();
        firstProduct.click();

        logger.info("Verifying product page title");
        String productPageTitle = driver.getTitle();
        Assert.assertTrue(productPageTitle.contains(expectedProductTitle),
                "Product page title does not match the selected product");

        test.pass("Product navigation test passed successfully");
    }

    @AfterClass
    public void tearDown() {
        logger.info("Closing WebDriver and finalizing Extent Reports");
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }
}
