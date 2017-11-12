package Task9.Operations;

import Task9.Pages.CartPage;
import Task9.Pages.MainPage;
import Task9.Pages.ProductPage;

public class Operations {

    public static void addProducts(int amount) {
        for (int i = 0; i < amount; i++) {
            MainPage.openProductPage();
            ProductPage productPage = new ProductPage();
            productPage.selectSizeIfApplicable();
            productPage.clickAddProductButton();
        }
        Task9.Tests.app.open();
    }

    public static void removeAllProducts() {
        CartPage.removeAllProducts();
    }

}
