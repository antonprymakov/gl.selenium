package Task9.Pages;

import Task9.Entities.Application;
import Task9.Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import Task9.Entities.Page;

public class MainPage extends Page {
    @FindBy(xpath="//*[@id='box-most-popular']/div/ul/li[1]")
    static WebElement product;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public static void openProductPage() {
        Tests.app.open();
        driver.findElement(By.xpath("//*[@id='box-most-popular']/div/ul/li[1]/a[1]"));//product.click();
    }
}
