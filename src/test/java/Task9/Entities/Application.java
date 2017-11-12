package Task9.Entities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

public class Application {
    static String url = "http://localhost/litecart";
    public static WebDriver driver;
    public WebDriverWait wait;

    public Application() {
        //ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    public void open(){
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }
}
