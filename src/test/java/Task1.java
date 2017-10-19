import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by anton.prymakov on 10/19/2017.
 */
public class Task1 {

    @Test
    public void googleTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.findElement(By.id("lst-ib")).sendKeys("test");
        driver.quit();
    }

}
