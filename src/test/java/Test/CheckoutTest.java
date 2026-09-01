package Test;

import GenricUtility.BaseClass;
import WebdriverUtility.WebdriverHandel;

import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.CartPage;
import pageobject.CheckoutOverviewPage;
import pageobject.CheckoutPage;
import pageobject.InventoryPage;
import pageobject.LoginPage;

public class CheckoutTest extends BaseClass {
	WebdriverHandel web =new WebdriverHandel();
	
    @Test(priority = 1, description = "TC-013: Checkout Empty Fields")
    public void checkoutBlockedWhenEmptyFieldsTest() throws WebDriverException, IOException {
        System.out.println("Executing TC-013: Checkout Empty Fields");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendusername("standard_user");
        loginPage.sendpassword("secret_sauce");
        loginPage.clickloginbutton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.isErrorMessageDisplayed(), "Error message was not displayed for empty fields.");
        System.out.println("TC-013 Passed Successfully.");
        web.takeAScreenShot(driver, "checkoutBlockedWhenEmptyFieldsTest");
    }

    @Test(priority = 2, description = "TC-014: Missing First Name in Checkout")
    public void checkoutMissingFirstNameTest() throws WebDriverException, IOException {
        System.out.println("Executing TC-014: Missing First Name in Checkout");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendusername("standard_user");
        loginPage.sendpassword("secret_sauce");
        loginPage.clickloginbutton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterInformation("", "Kumar", "110001");
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"), "Error message mismatch for missing first name.");
        System.out.println("TC-014 Passed Successfully.");
        
        web.takeAScreenShot(driver, "checkoutMissingFirstNameTest");
    }

    @Test(priority = 3, description = "TC-017, TC-019: Valid Checkout Information & Complete Order")
    public void completeCheckoutSuccessfullyTest() throws WebDriverException, IOException {
        System.out.println("Executing TC-017, TC-019: Valid Checkout Information & Complete Order");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendusername("standard_user");
        loginPage.sendpassword("secret_sauce");
        loginPage.clickloginbutton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        // Add product on Inventory Page
        inventoryPage.addProductToCart("sauce-labs-backpack");

        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterInformation("Chandan", "Kumar", "110001");
        checkoutPage.clickContinue();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();

        Assert.assertEquals(overviewPage.getConfirmationMessage(), "Thank you for your order!", "Order completion message mismatch.");
        System.out.println("TC-017, TC-019 Passed Successfully.");
        
        web.takeAScreenShot(driver, "completeCheckoutSuccessfullyTest");
    }
}
