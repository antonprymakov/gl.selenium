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
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void pageHeaderIsPresent() {
        List<WebElement> itemsList = driver.findElements(By.cssSelector("#box-apps-menu > *"));

        for (int i = 0; i < itemsList.size(); i++) {
            itemsList = driver.findElements(By.cssSelector("#box-apps-menu > *"));
            itemsList.get(i).click();
            List<WebElement> subItemsList = driver.findElements(By.cssSelector("li[id^=doc]"));

            if (!subItemsList.isEmpty()) {
                for (int j = 0; j < subItemsList.size(); j++) {
                    subItemsList.get(j).click();
                    subItemsList = driver.findElements(By.cssSelector("li[id^=doc]"));
                    Assert.assertTrue("Header of '" + subItemsList.get(j).getText() + "' section is not present on the page.",
                            driver.findElement(By.cssSelector("div.header")).isDisplayed());
                }
            }
            else {
                itemsList = driver.findElements(By.cssSelector("#box-apps-menu > *"));
                Assert.assertTrue("Header of '" + itemsList.get(i).getText() + "' section is not present on the page.",
                        driver.findElement(By.cssSelector("div.header")).isDisplayed());
            }
        }
    }

    @AfterClass
    public static void finish() {
        driver.quit();
    }
}
