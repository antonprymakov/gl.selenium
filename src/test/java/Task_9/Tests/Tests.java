package Task_9.Tests;

import org.junit.Assert;
import org.junit.Test;

public class Tests extends TestBase {

    @Test
    public void test() {
        app.addProducts(3);
        app.removeAllProducts();
        Assert.assertEquals(0, app.getCurrentProductAmountInCart());
    }

}
