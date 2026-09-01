package Test;


import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Test;

import GenricUtility.BaseClass;
import WebdriverUtility.WebdriverHandel;
import pageobject.LoginPage;

public class logintheuser extends BaseClass {
	
	WebdriverHandel web = new WebdriverHandel();

    @Test(dataProvider = "loginData", dataProviderClass = DataProvider.Logindata.class, description = "B3: Data-Driven Login Tests from CSV")
    public void dataDrivenLoginTest(String tcId, String username, String password, String expectedResult) throws WebDriverException, java.io.IOException {
        System.out.println("Executing " + tcId + ": Data-Driven Login with username: [" + username + "]");
        LoginPage lp = new LoginPage(driver);
        lp.sendusername(username);
        lp.sendpassword(password);
        lp.clickloginbutton();
        
        if (expectedResult.equals("success")) {
            org.testng.Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User did not navigate to inventory page.");
        } else {
            org.testng.Assert.assertTrue(lp.getErrorMessage().contains(expectedResult), "Error message mismatch. Expected to contain: " + expectedResult);
        }
        
        System.out.println(tcId + " Passed Successfully.");
        web.takeAScreenShot(driver, "login_" + tcId);
    }
}
