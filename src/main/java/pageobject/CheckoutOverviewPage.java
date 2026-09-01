package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {

    public CheckoutOverviewPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    public void clickFinish() {
        finishButton.click();
    }

    public String getConfirmationMessage() {
        return completeHeader.getText();
    }
}
