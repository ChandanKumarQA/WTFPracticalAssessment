package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(xpath="//button[text()='Open Menu']")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    // Add product using Product Name
    public void addProductToCart(String productName) {

        String productId = productName
                .toLowerCase()
                .replace(" ", "-");

        driver.findElement(
            By.id("add-to-cart-" + productId)
        ).click();
    }
    
    public int getCartCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
            return 0; 
        }
    }

    public void openCart() {
        cartLink.click();
    }

    public void sortProductsLowToHigh() {
        Select sort = new Select(sortDropdown);
        sort.selectByValue("lohi");
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : itemPrices) {
            prices.add(Double.parseDouble(priceElement.getText().replace("$", "")));
        }
        return prices;
    }

    public void clickMenu() {
        menuButton.click();
    }

    public void clickLogout() {
        logoutLink.click();
    }
}
