package Task9.App;

import Task9.Pages.CheckoutPage;
import Task9.Pages.MainPage;
import Task9.Pages.ProductPage;
import org.openqa.selenium.WebDriver;

public class Application {
    private WebDriver driver;

    public MainPage mainPage;
    private ProductPage productPage;
    private final CheckoutPage checkoutPage;

    public Application(WebDriver driver) {
        this.driver = driver;
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    public void addProducts(int amount) {
        for (int i = 0; i < amount; i++) {
            mainPage.open();
            mainPage.openFirstProductFromList();
            productPage.addProductToCart();
        }
    }

    public void removeAllProducts() {
        checkoutPage.open();
        while (checkoutPage.removeBtnIsAvailable()){
            checkoutPage.removeProduct();
        }
    }

    public int getCurrentProductAmountInCart () {
        mainPage.open();
        return mainPage.currentQuantityInCart();
    }

    public void close() {
        driver.quit();
    }

}
