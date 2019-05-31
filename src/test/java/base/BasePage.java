package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegisterNewHotelPage;

public abstract class BasePage {

    @FindBy(xpath = "//span[text()='Article']/..")
    private WebElement articleMenu;

    @FindBy(xpath = "//span[text()='New']/..")
    private WebElement newSubMenu;

    @FindBy(xpath = "//span[text()='Hotel']/..")
    private WebElement newHotel;

    private final int TIME_OUT_SEC = 5;
    private final int SLEEP_MILLIS = 500;
    private WebDriver driver;
    protected WebDriverWait wait;
    private Actions action;
    protected JavascriptExecutor jse;

    protected BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, TIME_OUT_SEC, SLEEP_MILLIS);
        action = new Actions(driver);
        jse = (JavascriptExecutor) driver;
    }

    public BasePage openArticleMenu(){
        action.moveToElement(articleMenu).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(newSubMenu));
        return this;
    }

    public BasePage openNewSubMenu(){
        action.moveToElement(newSubMenu).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(newHotel));
        return this;
    }

    public RegisterNewHotelPage openRegisterNewHotel(){
        newHotel.click();
        wait.until(ExpectedConditions.urlContains("hotel."));
        return new RegisterNewHotelPage(driver);
    }

}
