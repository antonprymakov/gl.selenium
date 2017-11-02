import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Task7 {
    static WebDriver driver = new ChromeDriver();
    static String url = "http://localhost/litecart/admin";
    static String login = "admin";
    static String password = "admin";
    List<String> urlList = new ArrayList<>(Arrays.asList(
            "https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2",
            "https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3",
            "https://en.wikipedia.org/wiki/Regular_expression",
            "https://www.informatica.com/products/data-quality/data-as-a-service/address-verification/address-formats.html",
            "https://en.wikipedia.org/wiki/Regular_expression",
            "https://en.wikipedia.org/wiki/List_of_countries_and_capitals_with_currency_and_language",
            "https://en.wikipedia.org/wiki/List_of_country_calling_codes"));

    @BeforeClass
    public static void start() {
        driver.get(url);
        if (isElementPresentAndVisible("//*[@id='box-login']")) {
            driver.findElement(By.name("username")).sendKeys(login);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.name("login")).click();
        }
    }

    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.xpath("//span[text()='Countries']")).click();
        driver.findElement(By.xpath("//a[text()[contains(.,'Add New Country')]]")).click();

        List<WebElement> externalLinksList = driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));
        String originalWindow = driver.getWindowHandle();
        for (int i = 0; i < externalLinksList.size(); i++) {
            externalLinksList.get(i).click();
            String newWindow = wait.until(anyWindowOtherThan(originalWindow));
            driver.switchTo().window(newWindow);
            Assert.assertEquals("Page URL opened in new window differs from expected one.",
                    urlList.get(i), driver.getCurrentUrl());
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }

    @AfterClass
    public static void finish() {
        driver.quit();
    }

    public ExpectedCondition<String> anyWindowOtherThan(String window) {
        return input -> {
            Set<String> handles = driver.getWindowHandles();
            handles.remove(window);
            return handles.size() > 0 ? handles.iterator().next() : null;
        };
    }

    private static boolean isElementPresentAndVisible(String xpath) {
        try {
            if (driver.findElement(By.xpath(xpath)).isDisplayed())
                return true;
            return false;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
