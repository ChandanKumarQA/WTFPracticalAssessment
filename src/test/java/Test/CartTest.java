package Test;

import GenricUtility.BaseClass;
import WebdriverUtility.WebdriverHandel;

import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.InventoryPage;
import pageobject.LoginPage;
import pageobject.CartPage;

public class CartTest extends BaseClass {
	
	WebdriverHandel web =new WebdriverHandel();

    @Test(priority = 1, description = "TC-009: Add Multiple Products to Cart")
    public void addMultipleProductsTest() throws WebDriverException, IOException {
        System.out.println("Executing TC-009: Add Multiple Products to Cart");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendusername("standard_user");
        loginPage.sendpassword("secret_sauce");
        loginPage.clickloginbutton();

        InventoryPage inventoryPage = new InventoryPage(driver);
       
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        
        
        inventoryPage.addProductToCart("Sauce Labs Bike Light");
        

        Assert.assertEquals(inventoryPage.getCartCount(), 2, "Cart count mismatch after adding products.");
        System.out.println("TC-009 Passed Successfully.");
        web.takeAScreenShot(driver, "addMultipleProductsTest");
        
    }

    @Test(priority = 2, description = "TC-010: Remove Product from Cart")
    public void removeProductTest() throws WebDriverException, IOException {
        System.out.println("Executing TC-010: Remove Product from Cart");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendusername("standard_user");
        loginPage.sendpassword("secret_sauce");
        loginPage.clickloginbutton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bike Light");
        
        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.removeProduct("Sauce Labs Backpack");

        // Navigate back to verify badge
        driver.navigate().back();
        Assert.assertEquals(inventoryPage.getCartCount(), 1, "Cart count mismatch after removing a product.");
        System.out.println("TC-010 Passed Successfully.");
        
        web.takeAScreenShot(driver, "removeProductTest");
    }
}
