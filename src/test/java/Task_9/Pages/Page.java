package Task_9.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    WebDriver driver;
    WebDriverWait wait;
    protected final String baseUrl = "http://localhost/litecart";

    protected Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public static boolean isElementPresentAndVisible(WebElement element) {
        try {
            if (element.isDisplayed())
                return true;
            return false;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
