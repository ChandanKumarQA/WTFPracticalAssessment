package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public void removeProduct(String productName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='cart_item']//button", productName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickCheckout() {
        checkoutButton.click();
    }
}
