package Task_9.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends Page {

    private String checkoutPageUrl = baseUrl + "/checkout";

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="remove_cart_item")
    WebElement removeButton;

    @FindBy(name="remove_cart_item")
    List<WebElement> table;

    public void removeProduct() {
        WebElement initTable = table.get(0);
        removeButton.click();
        wait.until(ExpectedConditions.stalenessOf(initTable));
    }

    public boolean removeBtnIsAvailable(){
        return isElementPresentAndVisible(removeButton);
    }

    public void open() {
        driver.get(checkoutPageUrl);
    }
}
