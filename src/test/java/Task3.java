import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by prymakov on 10/23/2017.
 */
public class Task3 {
    static WebDriver driver = new ChromeDriver();
    static String url = "http://localhost/litecart/admin";
    static String login = "admin";
    static String password = "admin";

    @BeforeClass
    public static void start() {
        driver.get(url);
        if (isElementPresentAndVisible("#box-login")) {
            driver.findElement(By.name("username")).sendKeys(login);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.name("login")).click();
        }
    }

    @Test
    public void pageHeaderIsPresent() {
        List<WebElement> itemsList = driver.findElements(By.cssSelector("#box-apps-menu > *"));

        for (int i = 1; i < itemsList.size()+1; i++) {
            driver.findElement(By.xpath("//*[@id='app-'] [" + (i) + "]")).click();
            Assert.assertTrue("Header of category '" + getCurrentSelectedCategory().get(0).getText()
                    + "' is not present on the page.", isElementPresentAndVisible("#content > h1"));

            List<WebElement> subItemsList = driver.findElements(By.cssSelector("li[id^=doc]"));

            for (int j = 1; j < subItemsList.size()+1; j++) {
                driver.findElement(By.xpath("//li[contains(@id, 'doc-')] [" + (j) + "]")).click();
                Assert.assertTrue("Header of subcategory '" + getCurrentSelectedCategory().get(1).getText()
                        + "' is not present on the page.", isElementPresentAndVisible("#content > h1"));
            }
        }
    }

    @AfterClass
    public static void finish() {
        driver.quit();
    }

    private static boolean isElementPresentAndVisible (String cssSelector) {
        try {
            if (driver.findElement(By.cssSelector(cssSelector)).isDisplayed())
                return true;
            return false;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    //returns the list of WebElements - currently selected Category (index [0]) and Subcategory (optional, index [1])
    private List<WebElement> getCurrentSelectedCategory() {
        return driver.findElements(By.xpath("//li[contains(@class, 'selected')]"));
    }
}
