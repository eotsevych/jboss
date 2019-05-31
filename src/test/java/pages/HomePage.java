package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("http://localhost:80/article/faces/welcome.xhtml");
    }
}
