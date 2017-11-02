import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.UUID;

public class Task5 {
    static WebDriver driver = new ChromeDriver();
    static String url = "http://localhost/litecart/admin";
    static String login = "admin";
    static String password = "admin";

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
        driver.findElement(By.xpath("//span[text()='Catalog']")).click();
        driver.findElement(By.xpath("//a[text()[contains(.,'Add New Product')]]")).click();

        String productName = UUID.randomUUID().toString().substring(0, 5);
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(productName);
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("0001");
        driver.findElement(By.xpath("//input[@value='1-3']")).click();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("10");
        new Select(driver.findElement(By.xpath("//select[@name='sold_out_status_id']"))).selectByIndex(0);
        File file = new File(getClass().getClassLoader().getResource("image.png").getFile());
        WebElement fileField = driver.findElement(By.xpath("//input[@name='new_images[]']"));
        fileField.sendKeys(file.getAbsolutePath().replace("%20", " "));
        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("10102017");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("10102018");

        driver.findElement(By.xpath("//a[text()='Information']")).click();
        new Select(driver.findElement(By.xpath("//select[@name='manufacturer_id']"))).selectByIndex(1);
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("test_keywords");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("test_short_description");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("test_description");
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("test_head_title");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("test_meta_description");

        driver.findElement(By.xpath("//a[text()='Prices']")).click();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys("99");
        new Select(driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']"))).selectByIndex(1);
        driver.findElement(By.xpath("//input[@name='gross_prices[USD]']")).sendKeys("11");
        driver.findElement(By.xpath("//input[@name='gross_prices[EUR]']")).sendKeys("12");
        driver.findElement(By.xpath("//button[@name='save']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//table[@class='dataTable']")).getText().contains(productName));
    }

    @AfterClass
    public static void finish() {
        driver.quit();
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
