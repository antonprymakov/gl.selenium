package Task9.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[@id='box-most-popular']/div/ul/li[1]")
    WebElement firstProductInList;

    @FindBy(xpath="//span[@class='quantity']")
    WebElement quantity;

    public int currentQuantityInCart() {
        return Integer.parseInt(quantity.getText());
    }

    public void openFirstProductFromList() {
        firstProductInList.click();
    }

    public void open() {
        driver.get(baseUrl);
    }

}

