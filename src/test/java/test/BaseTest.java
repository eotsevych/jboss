package test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;
import pages.RegisterNewHotelPage;
import utilities.BrowsersDriver;

abstract class BaseTest extends BrowsersDriver {

    HomePage homePage;
    RegisterNewHotelPage registerNewHotelPage;

    @BeforeTest
    public void setUp(){
        homePage = new HomePage(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
