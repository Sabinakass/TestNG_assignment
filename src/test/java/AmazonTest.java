

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
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
        takeScreenshot("HomePage");

        logger.info("Verifying homepage title");
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Amazon"), "Homepage title does not contain 'Amazon'");
        takeScreenshot("HomePageTitleVerified");

        test.pass("Homepage title verified successfully");
    }

    @Test(priority = 2)
    public void searchTest() {
        logger.info("Searching for 'laptop'");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        takeScreenshot("SearchBoxLocated");

        searchBox.sendKeys("laptop");
        takeScreenshot("SearchKeywordEntered");

        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();
        takeScreenshot("SearchResultsLoaded");

        logger.info("Verifying search results");
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("laptop"), "Page title does not contain 'laptop'");
        takeScreenshot("SearchResultsVerified");

        test.pass("Search test passed successfully");
    }

    @Test(priority = 3)
    public void filterResultsByBrand() {
        logger.info("Applying filter for 'HP' brand");

        WebElement brandFilter = driver.findElement(By.xpath("//span[text()='HP']"));
        takeScreenshot("HPFilterLocated");

        brandFilter.click();
        takeScreenshot("HPFilterApplied");

        logger.info("Verifying filtered results contain 'HP'");
        List<WebElement> results = driver.findElements(By.cssSelector(".s-title-instructions-style"));
        for (WebElement result : results) {
            Assert.assertTrue(result.getText().toLowerCase().contains("hp"), "Result does not contain 'HP'");
        }
        takeScreenshot("HPFilterResultsVerified");

        test.pass("Filter by brand test passed successfully");
    }

    @Test(priority = 4)
    public void verifyProductNavigation() {
        logger.info("Clicking on the first product");

        List<WebElement> productLinks = driver.findElements(By.cssSelector(".s-title-instructions-style"));
        Assert.assertTrue(productLinks.size() > 0, "No products found on the page");
        WebElement firstProduct = productLinks.get(0);
        String expectedProductTitle = firstProduct.getText();
        takeScreenshot("FirstProductLocated");

        firstProduct.click();
        takeScreenshot("FirstProductPageLoaded");

        logger.info("Verifying product page title");
        String productPageTitle = driver.getTitle();
        Assert.assertTrue(productPageTitle.contains(expectedProductTitle),
                "Product page title does not match the selected product");
        takeScreenshot("ProductPageTitleVerified");

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

    private void takeScreenshot(String stepName) {
        String screenshotPath = "screenshots/" + stepName + ".png";
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File(screenshotPath));
            test.info("Screenshot taken: " + stepName).addScreenCaptureFromPath("/"+screenshotPath);
            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            logger.error("Failed to take screenshot for step: " + stepName, e);
        }
    }
}
