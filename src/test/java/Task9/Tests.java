package Task9;

import Task9.Entities.Application;
import Task9.Operations.Operations;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Tests {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public static Application app;

    @BeforeClass
    public static void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }
        app = new Application();
        tlApp.set(app);
        app.open();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {app.close(); app = null;}));
    }

    @Test
    public void test() {
        Operations.addProducts(3);
        Operations.removeAllProducts();
        Assert.assertEquals(0, Task9.Pages.ProductPage.currentQuantityInCart());
    }

    @AfterClass
    public static void finish() {
        app.close();
    }
}
