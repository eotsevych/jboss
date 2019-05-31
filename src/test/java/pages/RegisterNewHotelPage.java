package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterNewHotelPage extends BasePage {


    //region SELECTORS
    @FindBy(css = "h2")
    private WebElement pageHeader;

    @FindBy(css = "form[name=add_hotel]")
    private WebElement dataSection;

    @FindBy(id = "add_hotel:j_idt62")
    private WebElement saveButton;

    @FindBy(id = "add_hotel:name")
    private WebElement nameField;

    @FindBy(id = "add_hotel:rate")
    private WebElement globalRatingField;

    @FindAll(@FindBy(css = "div.ui-rating-star"))
    List<WebElement> stars;

    @FindAll(@FindBy(css = "div.ui-rating-star.ui-rating-star-on"))
    List<WebElement> selectedStars;

    @FindBy(id = "add_hotel:dateOfConstruction_input")
    private WebElement constructionDateField;

    @FindBy(id = "add_hotel:country")
    private WebElement countryField;

    @FindBy(id = "add_hotel:country_panel")
    private WebElement countryPanel;
    
    @FindBy(id = "add_hotel:city")
    private WebElement cityField;

    @FindBy(id = "add_hotel:city_panel")
    private WebElement cityPanel;

    @FindBy(id = "add_hotel:short_description")
    private WebElement shortDescriptionField;

    @FindBy(id = "add_hotel:description")
    private WebElement descriptionField;

    @FindBy(id = "add_hotel:notes")
    private WebElement notesField;

    //endregion

    //region HELPERS

    private boolean isElementDisplayedInDataSection(WebElement element) {
        return dataSection.findElement(By.id(element.getAttribute("id"))).isDisplayed();
    }

    private boolean isFieldMarkedWithAsterisk(WebElement element){
        try {
            return element.findElement(By.xpath("./../..//label")).getText().contains("*");
        } catch (NoSuchElementException exception) {
            return element.findElement(By.xpath("./../../..//label")).getText().contains("*");
        }
    }

    private String getEditedText(WebElement element){
        return String.valueOf(jse.executeScript("return arguments[0].value", element));
    }

    private boolean isErrorMessageDisplayed(WebElement element){
        try {
            return wait.until(ExpectedConditions.visibilityOf(
                    element.findElement(By.xpath("./../..//span[@class='ui-message-error-detail']"))))
                    .isDisplayed();
        } catch (NoSuchElementException exception) {
            return element.findElement(By.xpath("./../../..//span[@class='ui-message-error-detail']")).isDisplayed();
        }
    }

    private void setHotelRating(int starIndex) {
        if (starIndex > 5) starIndex = 5;
        else if (starIndex < 1) starIndex = 1;
        stars.get(starIndex-1).click();
    }

    private void selectFromPanel(WebElement elementTo, WebElement elementFrom, String value) {
        elementTo.click();
        wait.until(ExpectedConditions.visibilityOf(elementFrom));
        elementFrom.findElement(By.xpath(".//li[@data-label='" + value + "']")).click();
    }
    //endregion

    public RegisterNewHotelPage(WebDriver driver) {
        super(driver);
    }

    //region 1
    @Step("Verify that Register new Hotel page is displayed when user selects Article->New->Hotel")
    public void verifyNewHotelPageIsDisplayed(){
        assertThat(pageHeader.getText()).isEqualTo("Register new Hotel");
    }

    @Step("Verify that Data section is displayed on Register new Hotel")
    public void verifyThatDataSectionIsDisplayed(){
        assertThat(dataSection.isDisplayed()).isTrue();
    }

    @Step("Verify that save button is displayed on Register new Hotel")
    public void verifyThatSaveButtonIsDisplayed(){
        assertThat(saveButton.isDisplayed()).isTrue();
    }
    //endregion

    //region 2
    @Step("Verify that Name field is displayed in Data section of Register new Hotel page")
    public void verifyThatNameFieldIsDisplayedInDataSection(){
        assertThat(isElementDisplayedInDataSection(nameField)).isTrue();
    }

    @Step("Verify that Name field is marked with asterisk")
    public void verifyThatNameFieldIsMarkedWithAsterisk(){
        assertThat(isFieldMarkedWithAsterisk(nameField)).isTrue();
    }

    @Step("Verify that Name field is editable")
    public void verifyThatNameFieldIsEditable(String value){
        nameField.clear();
        nameField.sendKeys(value);
        assertThat(getEditedText(nameField)).isEqualTo(value);
    }

    @Step("Verify that Name field allows to input alphanumeric characters")
    public void verifyThatNameFieldAllowsToInputAlphanumericCharacters(String value){
        nameField.clear();
        nameField.sendKeys(value);
        assertThat(getEditedText(nameField)).isEqualTo(value);
    }

    @Step("Verify that it is not possible to save the empty Name field and a meaningful error message is displayed")
    public void verifyThatItIsNotPossibleToSaveEmptyNameFieldWithErrorMessage(){
        nameField.clear();
        saveButton.click();
        assertThat(isErrorMessageDisplayed(nameField)).isTrue();
    }

    @Step("Verify that it is possible to save the valid name field")
    public void verifyThatItIsPossibleToSaveTheValidNameField(String value){
        nameField.clear();
        nameField.sendKeys(value);
        saveButton.click();
        assertThat(nameField.getAttribute("value")).isEqualTo(value);
    }
    //endregion

    //region 3
    @Step("Verify that Global Rating field is displayed in Data section of Register new Hotel page")
    public void verifyThatGlobalRatingFieldIsDisplayedInDataSection(){
        assertThat(isElementDisplayedInDataSection(globalRatingField)).isTrue();
    }

    @Step("Verify that Global Rating field allows to rating of the hotel (0-5 stars)")
    public void verifyThatGlobalRatingFieldAllowsToRatingOfTheHotel(int stars){
        setHotelRating(stars);
        assertThat(selectedStars.size()).isEqualTo(stars);
    }

    @Step("Verify that it is possible to save the Global Rating field")
    public void verifyThatItIsPossibleToSaveGlobalRatingField(int stars){
        setHotelRating(stars);
        saveButton.click();
        assertThat(selectedStars.size()).isEqualTo(stars);
    }
    //endregion

    //region 4
    @Step("Verify that Date of Construction field is displayed in Data section of Register new Hotel page")
    public void verifyThatDateOfConstructionFieldIsDisplayedInDataSection() {
        assertThat(isElementDisplayedInDataSection(constructionDateField)).isTrue();
    }

    @Step("Verify that Date of Construction field is marked with asterisk")
    public void verifyThatDateOfConstructionFieldIsMarkedWithAsterisk() {
        assertThat(constructionDateField.findElement(By.xpath("./../../..//label")).getText()).contains("*");
    }

    @Step("Verify that Date of Construction is editable")
    public void verifyThatDateOfConstructionIsEditable(String date) {
        constructionDateField.clear();
        constructionDateField.sendKeys(date);
        assertThat(getEditedText(constructionDateField)).isEqualTo(date);
    }

    @Step("Verify Date of Construction field allows to input in date format")
    public void verifyDateOfConstructionFieldAllowsToInputInDateFormat(String date) {
        constructionDateField.clear();
        constructionDateField.sendKeys(date);
        assertThat(getEditedText(constructionDateField)).isEqualTo(date);
    }

    @Step("Verify that it is not possible to save incorrect  date format value Date of Construction field and a meaningful error message is displayed\n")
    public void verifyThatItIsNotPossibleToSaveIncorrectDateFormatForConstructiveDateWithErrorMessage(String date) {
        constructionDateField.clear();
        constructionDateField.sendKeys(date);
        saveButton.click();
        assertThat(isErrorMessageDisplayed(constructionDateField)).isTrue();
    }

    @Step("Verify that it is not possible to save the empty Date of Construction field and a meaningful error message is displayed")
    public void verifyThatItIsNotPossibleToSaveEmptyConstructiveDateWithErrorMessage() {
        constructionDateField.clear();
        saveButton.click();
        assertThat(isErrorMessageDisplayed(constructionDateField)).isTrue();
    }

    @Step("Verify that it is possible to save valid Date of Construction field")
    public void verifyThatItIsPossibleToSaveValidDateOfConstructionField(String date) {
        constructionDateField.clear();
        constructionDateField.sendKeys(date);
        saveButton.click();
        assertThat(constructionDateField.getAttribute("value")).isEqualTo(date);
    }
    //endregion

    //region 5
    @Step("Verify that Country field is displayed in Data section of Register new Hotel page")
    public void verifyThatCountryFieldIsDisplayedInDataSection() {
        assertThat(isElementDisplayedInDataSection(countryField)).isTrue();
    }

    @Step("Verify that Country fields is marked with asterisk")
    public void verifyThatCountryFieldsIsMarkedWithAsterisk() {
        assertThat(isFieldMarkedWithAsterisk(countryField)).isTrue();
    }

    @Step("Verify that Country field is editable")
    public void verifyThatCountryFieldIsEditable(Countries country) {
        selectFromPanel(countryField, countryPanel, country.getValue());
        assertThat(countryField.getText()).isEqualTo(country.getValue());
    }

    @Step("Verify that it is not possible to save the empty (with default value Select me) Country field and a meaningful error is displayed")
    public void verifyThatItIsNotPossibleToSaveTheEmpty() {
        selectFromPanel(countryField, countryPanel, Countries.DEFAULT.getValue());
        saveButton.click();
        assertThat(isErrorMessageDisplayed(countryField)).isTrue();
    }

    @Step("Verify that it is possible to save the valid Country field")
    public void verifyThatItIsPossibleToSaveTheValidCountryField(Countries country) {
        selectFromPanel(countryField, countryPanel, country.getValue());
        saveButton.click();
        assertThat(countryField.getText()).isEqualTo(country.getValue());
    }
    //endregion

    //region 6
    @Step("Verify that City field is displayed in Data section of Register new Hotel page")
    public void verifyThatCityFieldIsDisplayedInDataSection() {
        assertThat(isElementDisplayedInDataSection(cityField)).isTrue();
    }

    @Step("Verify that City field is marked with asterisk")
    public void verifyThatCityFieldIsMarkedWithAsterisk() {
        assertThat(isFieldMarkedWithAsterisk(cityField)).isTrue();
    }

    @Step("Verify that City field is editable")
    public void verifyThatCityFieldIsEditable(Cities city) throws InterruptedException {
        if (countryField.getText().equals(Countries.DEFAULT.getValue())) selectFromPanel(countryField, countryPanel, Countries.UKRAINE.getValue());
        Thread.sleep(1000);

        selectFromPanel(cityField, cityPanel, city.getValue());
        assertThat(cityField.getText()).isEqualTo(city.getValue());
    }

    @Step("Verify that it is not possible to save the empty (with default value Select me) City field and a meaningful error message is displayed")
    public void verifyThatItIsNotPossibleToSaveTheEmptyCityField() {
        if (countryField.getText().equals(Countries.DEFAULT.getValue())) selectFromPanel(countryField, countryPanel, Countries.UKRAINE.getValue());
        selectFromPanel(cityField, cityPanel, Cities.DEFAULT.getValue());
        saveButton.click();
        assertThat(isErrorMessageDisplayed(cityField)).isTrue();
    }

    @Step("Verify that it is possible to save the valid City field")
    public void verifyThatItIsPossibleToSaveTheValidCityField(Cities city) {
        if (countryField.getText().equals(Countries.DEFAULT.getValue())) selectFromPanel(countryField, countryPanel, Countries.UKRAINE.getValue());
        selectFromPanel(cityField, cityPanel, city.getValue());
        saveButton.click();
        assertThat(cityField.getText()).isEqualTo(city.getValue());
    }
    //endregion

    //region 7
    @Step("Verify that Short Description field is displayed in Data section  of Register new Hotel\n")
    public void verifyThatShortDescriptionFieldIsDisplayedInDataSectionOfRegisterNewHotel() {
        assertThat(isElementDisplayedInDataSection(shortDescriptionField)).isTrue();
    }

    @Step("Verify that Short Description field is marked with asterisk")
    public void verifyThatShortDescriptionFieldIsMarkedWithAsterisk() {
        assertThat(isFieldMarkedWithAsterisk(shortDescriptionField)).isTrue();
    }

    @Step("Verify that Short Description field is editable")
    public void verifyThatShortDescriptionFieldIsEditable(String value) {
        shortDescriptionField.clear();
        shortDescriptionField.sendKeys(value);
        assertThat(getEditedText(shortDescriptionField)).isEqualTo(value);
    }

    @Step("Verify that Short Description field allows to input alphanumeric characters")
    public void verifyThatShortDescriptionFieldAllowsToInputAlphanumericCharacters(String value) {
        shortDescriptionField.clear();
        shortDescriptionField.sendKeys(value);
        assertThat(getEditedText(shortDescriptionField)).isEqualTo(value);
    }

    @Step("Verify that it is not possible to save the empty Short Description field and a meaningful error message is displayed")
    public void verifyThatItIsNotPossibleToSaveTheEmptyShortDescriptionFieldWithErrorMessage() {
        shortDescriptionField.clear();
        saveButton.click();
        assertThat(isErrorMessageDisplayed(shortDescriptionField)).isTrue();
    }

    @Step("Verify that it is possible to save the valid Short Description field")
    public void verifyThatItIsPossibleToSaveTheValidShortDescriptionField(String value) {
        shortDescriptionField.clear();
        shortDescriptionField.sendKeys(value);
        saveButton.click();
        assertThat(shortDescriptionField.getAttribute("value")).isEqualTo(value);
    }
    //endregion

    //region 8
    @Step("Verify that  Description field is displayed in Data section  of Register new Hotel")
    public void verifyThatDescriptionFieldIsDisplayedInDataSection() {
        assertThat(isElementDisplayedInDataSection(descriptionField)).isTrue();
    }

    @Step("Verify that Description field is marked with asterisk")
    public void verifyThatDescriptionFieldIsMarkedQWithAsterisk() {
        assertThat(isFieldMarkedWithAsterisk(descriptionField)).isTrue();
    }

    @Step("Verify that Description field is editable\n")
    public void verifyThatDescriptionFieldIsEditable(String value) {
        descriptionField.clear();
        descriptionField.sendKeys(value);
        assertThat(getEditedText(descriptionField)).isEqualTo(value);
    }

    @Step("Verify that Description field allows to input alphanumeric characters")
    public void verifyThatDescriptionFieldAllowsToInputAlphanumericCharacters(String value) {
        descriptionField.clear();
        descriptionField.sendKeys(value);
        assertThat(getEditedText(descriptionField)).isEqualTo(value);
    }

    @Step("Verify that it is not possible to save the empty Description field and a meaningful error message is displayed")
    public void verifyThatItIsNotPossibleToSaveTheEmptyDescriptionFieldWithErrorMessage() {
        descriptionField.clear();
        saveButton.click();
        assertThat(isErrorMessageDisplayed(descriptionField)).isTrue();
    }

    @Step("Verify that it is possible to save the valid Description field")
    public void verifyThatItIsPossibleToSaveTheValidDescriptionField(String value) {
        descriptionField.clear();
        descriptionField.sendKeys(value);
        saveButton.click();
        assertThat(descriptionField.getText()).isEqualTo(value);
    }
    //endregion

    //region 9
    @Step("Verify that  Notes field is displayed in Data section  of Register new Hotel")
    public void verifyThatNotesFieldIsDisplayedInDataSection() {
        assertThat(isElementDisplayedInDataSection(notesField)).isTrue();
    }

    @Step("Verify that Notes field is editable")
    public void verifyThatNotesFieldIsEditable(String value) {
        notesField.clear();
        notesField.sendKeys(value);
        assertThat(getEditedText(notesField)).isEqualTo(value);
    }

    @Step("Verify that Notes field allows to input alphanumeric characters")
    public void verifyThatNotesFieldAllowsToInputAlphanumericCharacters(String value) {
        notesField.clear();
        notesField.sendKeys(value);
        assertThat(getEditedText(notesField)).isEqualTo(value);
    }

    @Step("Verify that it is possible to save the empty Notes field")
    public void verifyThatItIsNotPossibleToSaveTheEmptyNotesFieldWithErrorMessage() {
        notesField.clear();
        saveButton.click();
        assertThat(isErrorMessageDisplayed(notesField)).isTrue();
    }

    @Step("Verify that it is possible to save the valid Notes field")
    public void verifyThatItIsPossibleToSaveTheValidNotesField(String value) {
        notesField.clear();
        notesField.sendKeys(value);
        saveButton.click();
        assertThat(notesField.getText()).isEqualTo(value);
    }
    //endregion

    public enum Countries {
        UKRAINE("Ukraine"),
        USA("USA"),
        UK("UK"),
        DEFAULT("Select one");

        private String value;

        Countries(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Cities {
        KYIV("Kyiv"),
        LVIV("Lviv"),
        KHARKIV("Kharkiv"),
        DNIPROPETROVSK("Dnipropetrovsk"),
        ODESA("Donetsk"),
        ZAPORIZHIA("Zaporizhia"),
        KRYVYI_RIH("Kryvyi Rih"),
        MYKOLAIV("Mykolaiv"),
        DEFAULT("Select one");


        private String value;

        Cities(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
