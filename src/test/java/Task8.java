import com.google.common.io.Files;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class Task8 {
    static EventFiringWebDriver driver;
    static String url = "http://192.168.56.1/litecart/admin";
    static String login = "admin";
    static String password = "admin";

    @BeforeClass
    public static void start() throws MalformedURLException {
        DesiredCapabilities caps =  new DesiredCapabilities();
        caps.setBrowserName("firefox");
        driver = new EventFiringWebDriver(new RemoteWebDriver(new URL("http://192.168.56.101:4444/wd/hub"), caps));
        driver.register(new MyListener());

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

        for (int i = 1; i < itemsList.size() + 1; i++) {
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

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println("Start searching: " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            File tempFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(tempFile, new File ("screenshots/screen" + new Random().nextInt(50) + 1 + ".png"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
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

