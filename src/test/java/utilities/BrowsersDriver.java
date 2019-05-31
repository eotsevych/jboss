package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowsersDriver {

    protected WebDriver driver;

    {
        try {
            driver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private WebDriver returnDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new EventFiringWebDriver(new ChromeDriver());
            ((EventFiringWebDriver) driver).register(new EventsListener());
            driver.manage().window().maximize();
        }
        return driver;
    }
}
