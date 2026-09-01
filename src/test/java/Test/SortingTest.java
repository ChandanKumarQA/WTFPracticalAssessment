package Test;

import GenricUtility.BaseClass;
import WebdriverUtility.WebdriverHandel;

import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.InventoryPage;
import pageobject.LoginPage;

import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class SortingTest extends BaseClass {
	WebdriverHandel web =new WebdriverHandel();

    @Test(priority = 1, description = "TC-007: Sort Price Low to High")
    public void sortPriceLowToHighTest() throws WebDriverException, IOException {
        System.out.println("Executing TC-007: Sort Price Low to High");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendusername("standard_user");
        loginPage.sendpassword("secret_sauce");
        loginPage.clickloginbutton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortProductsLowToHigh();

        List<Double> actualPrices = inventoryPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted by price low to high.");
        System.out.println("TC-007 Passed Successfully.");
        web.takeAScreenShot(driver, "sortPriceLowToHighTest");
    }
    
    @Test(priority = 2, description = "TC-027: Sort Price High to Low")
    public void sortPriceHighToLowTest() throws WebDriverException, IOException {
        System.out.println("Executing TC-027: Sort Price High to Low");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendusername("standard_user");
        loginPage.sendpassword("secret_sauce");
        loginPage.clickloginbutton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        org.openqa.selenium.support.ui.Select sort = new org.openqa.selenium.support.ui.Select(driver.findElement(org.openqa.selenium.By.className("product_sort_container")));
        sort.selectByValue("hilo");

        List<Double> actualPrices = inventoryPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted by price high to low.");
        System.out.println("TC-027 Passed Successfully.");
        
        web.takeAScreenShot(driver, "sortPriceHighToLowTest");
    }
}
