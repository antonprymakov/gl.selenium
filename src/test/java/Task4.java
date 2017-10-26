import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by prymakov on 10/26/2017.
 */
public class Task4 {
    static WebDriver driver = new ChromeDriver();
    static String url = "http://localhost/litecart";

    @BeforeClass
    public static void start() {
        driver.get(url);
    }

    @Test
    public void test() {
        WebElement mainPageProduct = driver.findElement(By.cssSelector("#box-campaigns > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1)"));
        WebElement mainPageRegularPrice = mainPageProduct.findElement(By.cssSelector(".regular-price"));
        WebElement mainPageDiscountPrice = mainPageProduct.findElement(By.cssSelector(".campaign-price"));
        String mainPageProductNameValue = mainPageProduct.findElement(By.cssSelector(".name")).getText();
        String mainPageRegularPriceValue = mainPageRegularPrice.getText();
        String mainPageDiscountPriceValue = mainPageDiscountPrice.getText();
        String mainPageRegularPriceColor = mainPageRegularPrice.getCssValue("color");
        String mainPageRegularPriceStyle = mainPageRegularPrice.getCssValue("text-decoration");
        String mainPageDiscountPriceColor = mainPageDiscountPrice.getCssValue("color");
        String mainPageDiscountPriceStyle = mainPageDiscountPrice.getCssValue("font-weight");

        mainPageProduct.click();

        WebElement itemPageRegularPrice = driver.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > s"));
        WebElement itemPageDiscountPrice = driver.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > strong"));
        String itemPageProductNameValue = driver.findElement(By.cssSelector("#box-product > div:nth-child(1) > h1")).getText();
        String itemPageRegularPriceValue = itemPageRegularPrice.getText();
        String itemPageDiscountPriceValue = itemPageDiscountPrice.getText();
        String itemPageRegularPriceColor = itemPageRegularPrice.getCssValue("color");
        String itemPageRegularPriceStyle = itemPageRegularPrice.getCssValue("text-decoration");
        String itemPageDiscountPriceColor = itemPageDiscountPrice.getCssValue("color");
        String itemPageDiscountPriceStyle = itemPageDiscountPrice.getCssValue("font-weight");

        Assert.assertEquals("Product name displayed on Main page differs from the one on Item Page.",
                mainPageProductNameValue, itemPageProductNameValue);
        Assert.assertEquals("Regular price displayed on Main page differs from the one on Item Page.",
                mainPageRegularPriceValue, itemPageRegularPriceValue);
        Assert.assertEquals("Discount price displayed on Main page differs from the one on Item Page.",
                mainPageDiscountPriceValue, itemPageDiscountPriceValue);
        Assert.assertEquals("Text color of the regular price displayed on Main page differs from expected one.",
                mainPageRegularPriceColor, "rgba(119, 119, 119, 1)");
        Assert.assertTrue("Regular price text value displayed on Main page is not strike.",
                mainPageRegularPriceStyle.contains("line-through"));
        Assert.assertEquals("Text color of the discount price displayed on Main page differs from expected one.",
                mainPageDiscountPriceColor, "rgba(204, 0, 0, 1)");
        Assert.assertEquals("Discount price text value displayed on Main page is not bold.",
                mainPageDiscountPriceStyle, "bold");
        Assert.assertEquals("Text color of the regular price displayed on Item page differs from expected one.",
                itemPageRegularPriceColor, "rgba(102, 102, 102, 1)");
        Assert.assertTrue("Regular price text value displayed on Item page is not strike.",
                itemPageRegularPriceStyle.contains("line-through"));
        Assert.assertEquals("Text color of the discount price displayed on Item page differs from expected one.",
                itemPageDiscountPriceColor, "rgba(204, 0, 0, 1)");
        Assert.assertEquals("Discount price text value displayed on Item page is not bold.",
                itemPageDiscountPriceStyle, "bold");
    }

    @AfterClass
    public static void finish() {
        driver.quit();
    }
}
