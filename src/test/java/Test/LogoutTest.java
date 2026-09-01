package Test;

import GenricUtility.BaseClass;
import WebdriverUtility.WebdriverHandel;

import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.InventoryPage;
import pageobject.LoginPage;

public class LogoutTest extends BaseClass {
	
	WebdriverHandel web =new WebdriverHandel();

    @Test(priority = 1, description = "TC-021: Logout from application")
    public void logoutTest() throws WebDriverException, IOException {
        System.out.println("Executing TC-021: Logout from application");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendusername("standard_user");
        loginPage.sendpassword("secret_sauce");
        loginPage.clickloginbutton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickMenu();
        
        // Add a small wait for the menu to open (better to use explicit wait in real scenarios)
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        
        inventoryPage.clickLogout();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/"), "User was not redirected to the login page after logout.");
        System.out.println("TC-021 Passed Successfully.");
        
        web.takeAScreenShot(driver, "logoutTest");
    }
}
