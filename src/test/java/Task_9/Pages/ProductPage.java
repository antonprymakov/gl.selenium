package Task_9.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="options[Size]")
    WebElement sizeDropDown;

    @FindBy(name="add_cart_product")
    WebElement addProductButton;

    @FindBy(xpath="//span[@class='quantity']")
    WebElement quantity;

    public void addProductToCart() {
        setSizeIfApplicable();
        int initialQuantityInCart = currentQuantityInCart();
        addProductButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(quantity,
                String.valueOf(initialQuantityInCart + 1)));
    }

    public void setSizeIfApplicable() {
        if (isElementPresentAndVisible(sizeDropDown))
            new Select(sizeDropDown).selectByIndex(1);
    }

    private int currentQuantityInCart() {
        return Integer.parseInt(quantity.getText());
    }
}
