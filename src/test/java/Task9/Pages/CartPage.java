package Task9.Pages;

import Task9.Entities.Application;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import Task9.Entities.Page;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends Page {
    static String cartPageUrl = "http://localhost/litecart/en/checkout";

    @FindBy(name="remove_cart_item")//xpath("//button[@name='remove_cart_item']")
    private static WebElement removeBtn;

    @FindBy(xpath="//table[@class='dataTable rounded-corners']")
    static WebElement table;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public static void open() {
        driver.get(cartPageUrl);
    }

    public static void removeNextProduct() {
        WebElement initTable = table; //get initial table state
        removeBtn.click();
        wait.until(ExpectedConditions.stalenessOf(initTable));
    }

    public static void removeAllProducts() {
        open();
        while (removeBtn != null) {
            CartPage.removeNextProduct();
        }
    }
}
