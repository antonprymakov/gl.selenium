import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by prymakov on 10/19/2017.
 */
public class Task1 {

    @Test
    public void googleTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.quit();
    }

}
