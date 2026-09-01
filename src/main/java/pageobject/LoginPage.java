package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password_textfield;

    @FindBy(id = "login-button")
    private WebElement login_button;
    
    @FindBy(css = "[data-test='error']")
    private WebElement Errormessage;
    
    
    
    
 public void sendusername(String email) {
        username.sendKeys(email);
    }


    public void sendpassword(String password) {
        password_textfield.sendKeys(password);
    }


    public void clickloginbutton() {
        login_button.click();
    }
    
    public String getErrorMessage() {
        return Errormessage.getText();
    }
}