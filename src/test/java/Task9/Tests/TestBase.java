package Task9.Tests;

import Task9.App.Application;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    private static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    protected Application app;

    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application(new ChromeDriver());
        tlApp.set(app);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { app.close(); app = null; }));
    }

    @After
    public void stop() {
        app.close();
        app = null;
    }
}
