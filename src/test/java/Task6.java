import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task6 {
    static WebDriver driver = new ChromeDriver();
    static String url = "http://localhost/litecart";

    @BeforeClass
    public static void start() {
        driver.get(url);
    }

    @Test
    public void test() {
        int initialQuantityInCart = currentQuantityInCart();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        for (int i = 1; i <= 3; i++) {
            driver.findElement(By.xpath("//*[@id='box-most-popular']/div/ul/li[1]")).click();
            if (isElementPresentAndVisible("//select[@name='options[Size]']"))
                new Select(driver.findElement(By.xpath("//select[@name='options[Size]']"))).selectByIndex(1);
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='quantity']"),
                    String.valueOf(initialQuantityInCart + i)));
            driver.get(url);
        }

        driver.findElement(By.xpath("//a[@class='content']")).click();
        while (isElementPresentAndVisible("//button[@name='remove_cart_item']")) {
            WebElement table = driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']"));
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(table));
        }
        driver.get(url);
        Assert.assertEquals(0, currentQuantityInCart());
    }

    @AfterClass
    public static void finish() {
        driver.quit();
    }

    private static int currentQuantityInCart() {
        return Integer.parseInt(driver.findElement(By.xpath("//span[@class='quantity']")).getText());
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
