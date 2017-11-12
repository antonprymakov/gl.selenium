package Task9.Pages;

import Task9.Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import Task9.Entities.Page;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends Page {
    int initialQuantityInCart = currentQuantityInCart();

    @FindBy(name="options[Size]")
    WebElement sizeDropDown;

    @FindBy(name="add_cart_product")
    WebElement addBtn;

    @FindBy(xpath="//span[@class='quantity']")
    static WebElement quantity;

    public ProductPage() {
        super(driver);
    }

    public void open() {
        MainPage.openProductPage();
    }

    public void selectSizeIfApplicable() {
        if (sizeDropDown != null)
            new Select(sizeDropDown).selectByIndex(1);//new Select(driver.findElement(By.xpath("//select[@name='options[Size]']"))).selectByIndex(1);
    }

    public void clickAddProductButton() {
        addBtn.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='quantity']"),
                String.valueOf(initialQuantityInCart + 1)));
    }

    public static int currentQuantityInCart() {
        Tests.app.open();
        return Integer.parseInt(quantity.getText());
    }
}
