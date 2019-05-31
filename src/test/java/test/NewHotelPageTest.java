package test;

import fake_data.FakeData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static fake_data.FakeData.fakeData;
import static pages.RegisterNewHotelPage.Cities.KHARKIV;
import static pages.RegisterNewHotelPage.Cities.LVIV;
import static pages.RegisterNewHotelPage.Countries.UK;
import static pages.RegisterNewHotelPage.Countries.UKRAINE;

public class NewHotelPageTest extends BaseTest {

    @BeforeMethod(description = "Open Register New Hotel Page")
    public void isRegisterNewHotelPageOpened(){
        registerNewHotelPage = homePage.openArticleMenu().openNewSubMenu().openRegisterNewHotel();
    }

    @Test(description = "Verify that user can open New Hotel page")
    void verifyThatUserCanOpenNewHotelPage() {
        registerNewHotelPage.verifyNewHotelPageIsDisplayed();
        registerNewHotelPage.verifyThatDataSectionIsDisplayed();
        registerNewHotelPage.verifyThatSaveButtonIsDisplayed();
    }

    @Test(description = "Verify that user can edit Name field")
    void verifyThatUserCanEditNameField() {
        registerNewHotelPage.verifyThatNameFieldIsDisplayedInDataSection();
        registerNewHotelPage.verifyThatNameFieldIsMarkedWithAsterisk();
        registerNewHotelPage.verifyThatNameFieldIsEditable(fakeData.name().name());
        registerNewHotelPage.verifyThatNameFieldAllowsToInputAlphanumericCharacters(fakeData.lorem().fixedString(10));
        registerNewHotelPage.verifyThatItIsNotPossibleToSaveEmptyNameFieldWithErrorMessage();
        registerNewHotelPage.verifyThatItIsPossibleToSaveTheValidNameField(fakeData.name().name());
    }


    @Test(description = "Verify that user can edit Global Rating field")
    void verifyThatUserCanEditGlobalEatingField() {
        registerNewHotelPage.verifyThatGlobalRatingFieldIsDisplayedInDataSection();
        registerNewHotelPage.verifyThatGlobalRatingFieldAllowsToRatingOfTheHotel(2);
        registerNewHotelPage.verifyThatItIsPossibleToSaveGlobalRatingField(5);
    }

    @Test(description = "Verify that user can add Date of Construction of new hotel")
    void verifyThatUserCanAddDateOfConstructionOfNewHotel() {
        registerNewHotelPage.verifyThatDateOfConstructionFieldIsDisplayedInDataSection();
        registerNewHotelPage.verifyThatDateOfConstructionFieldIsMarkedWithAsterisk();
        registerNewHotelPage.verifyThatDateOfConstructionIsEditable(FakeData.getCurrentDate());
        registerNewHotelPage.verifyDateOfConstructionFieldAllowsToInputInDateFormat(FakeData.getCurrentDate());
        registerNewHotelPage.verifyThatItIsNotPossibleToSaveIncorrectDateFormatForConstructiveDateWithErrorMessage(FakeData.getCurrentDateWithIncorrectFormat());
        registerNewHotelPage.verifyThatItIsNotPossibleToSaveEmptyConstructiveDateWithErrorMessage();
        registerNewHotelPage.verifyThatItIsPossibleToSaveValidDateOfConstructionField(FakeData.getCurrentDate());
    }

    @Test(description = "Verify that user can add Country of new hotel")
    void verifyThatUserCanAddCountryOfNewHotel()  {
        registerNewHotelPage.verifyThatCountryFieldIsDisplayedInDataSection();
        registerNewHotelPage.verifyThatCountryFieldsIsMarkedWithAsterisk();
        registerNewHotelPage.verifyThatCountryFieldIsEditable(UK);
        registerNewHotelPage.verifyThatItIsNotPossibleToSaveTheEmpty();
        registerNewHotelPage.verifyThatItIsPossibleToSaveTheValidCountryField(UKRAINE);
    }

    @Test(description = "Verify that user can add City of new hotel")
    void verifyThatUserCanAddCityOfNewHotel() throws InterruptedException {
        registerNewHotelPage.verifyThatCityFieldIsDisplayedInDataSection();
        registerNewHotelPage.verifyThatCityFieldIsMarkedWithAsterisk();
        registerNewHotelPage.verifyThatCityFieldIsEditable(KHARKIV);
        registerNewHotelPage.verifyThatItIsNotPossibleToSaveTheEmptyCityField();
        registerNewHotelPage.verifyThatItIsPossibleToSaveTheValidCityField(LVIV);
    }

    @Test(description = "Verify that user can add Short Description of new hotel ")
    void verifyThatUserCanAddShortDescriptionOfNewHotel() {
        registerNewHotelPage.verifyThatShortDescriptionFieldIsDisplayedInDataSectionOfRegisterNewHotel();
        registerNewHotelPage.verifyThatShortDescriptionFieldIsMarkedWithAsterisk();
        registerNewHotelPage.verifyThatShortDescriptionFieldIsEditable(fakeData.lorem().characters());
        registerNewHotelPage.verifyThatShortDescriptionFieldAllowsToInputAlphanumericCharacters(fakeData.lorem().fixedString(10));
        registerNewHotelPage.verifyThatItIsNotPossibleToSaveTheEmptyShortDescriptionFieldWithErrorMessage();
        registerNewHotelPage.verifyThatItIsPossibleToSaveTheValidShortDescriptionField(fakeData.lorem().characters());
    }

    @Test(description = "Verify that user can add Description of new hotel")
    void verifyThatUserCanAddDescriptionOfNewHotel() {
        registerNewHotelPage.verifyThatDescriptionFieldIsDisplayedInDataSection();
        registerNewHotelPage.verifyThatDescriptionFieldIsMarkedQWithAsterisk();
        registerNewHotelPage.verifyThatDescriptionFieldIsEditable(fakeData.lorem().characters());
        registerNewHotelPage.verifyThatDescriptionFieldAllowsToInputAlphanumericCharacters(fakeData.lorem().fixedString(10));
        registerNewHotelPage.verifyThatItIsNotPossibleToSaveTheEmptyDescriptionFieldWithErrorMessage();
        registerNewHotelPage.verifyThatItIsPossibleToSaveTheValidDescriptionField(fakeData.lorem().characters());
    }

    @Test(description = "Verify that user can add Notes of new hotel")
    void verifyThatUserCanAddNotesOfNewHotel() {
        registerNewHotelPage.verifyThatNotesFieldIsDisplayedInDataSection();
        registerNewHotelPage.verifyThatNotesFieldIsEditable(fakeData.lorem().characters());
        registerNewHotelPage.verifyThatNotesFieldAllowsToInputAlphanumericCharacters(fakeData.lorem().fixedString(10));
        registerNewHotelPage.verifyThatItIsNotPossibleToSaveTheEmptyNotesFieldWithErrorMessage();
        registerNewHotelPage.verifyThatItIsPossibleToSaveTheValidNotesField(fakeData.lorem().characters());
    }
}
