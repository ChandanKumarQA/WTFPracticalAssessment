package GenricUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {

    public WebDriver driver;

    String url = "https://www.saucedemo.com/";

    @BeforeMethod
    public void bm() {

        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(20));

        driver.get(url);
    }

    @AfterMethod
    public void am(org.testng.ITestResult result) {
        if (driver != null) {
            try {
                WebdriverUtility.WebdriverHandel wh = new WebdriverUtility.WebdriverHandel();
                wh.takeAScreenShot(driver, result.getMethod().getMethodName() + "_");
            } catch (Exception e) {
                System.out.println("Failed to take screenshot: " + e.getMessage());
            }
            driver.quit();
        }
    }
}