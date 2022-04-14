package pages;
import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class RegistrationFormPage {

    //locators:
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement dateOfBirth = $("#dateOfBirthInput");
    SelenideElement dateOfBirthMonth = $(".react-datepicker__month-select");
    SelenideElement dateOfBirthDay = $(".react-datepicker__month-container");
    SelenideElement dateOfBirthYear = $(".react-datepicker__year-select");
    SelenideElement emailInput = $("#userEmail");
    SelenideElement genderInput = $("#genterWrapper");
    SelenideElement phoneNumberInput = $("#userNumber");
    SelenideElement addressInput = $("#currentAddress");
    SelenideElement subjectsClick = $("#subjectsContainer");
    SelenideElement subjectsInput = $("#subjectsInput");
    SelenideElement stateClick = $("#state");
    SelenideElement stateInput = $("#react-select-3-input");
    SelenideElement cityClick = $("#city");
    SelenideElement cityInput = $("#react-select-4-input");
    SelenideElement uploadPictureInput = $("#uploadPicture");
    SelenideElement submitClick = $("#submit");
    SelenideElement checkFinalForm = $(".modal-body");


    //actions:

    //открытие браузера
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setDateOfBirth(String month, String year, String day) {
        dateOfBirth.click();
        dateOfBirthMonth.selectOption(month);
        dateOfBirthYear.selectOption(year);
        dateOfBirthDay.$(byText(day)).click();
        return this;
    }

    public RegistrationFormPage setGender(String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setPhoneNumber(String value) {
        phoneNumberInput.setValue(value);
        return  this;
    }

    public RegistrationFormPage setAddress(String value) {
        addressInput.setValue(value);
        return  this;
    }

    public RegistrationFormPage setSubjects(String value) {
        subjectsClick.click();
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage setHobbies() {
        for (String s : Arrays.asList("label[for='hobbies-checkbox-1']",
                "label[for='hobbies-checkbox-2']",
                "label[for='hobbies-checkbox-3']")) {
            $(s).click();
        }
        return this;
    }

    public RegistrationFormPage setState(String value) {
        stateClick.click();
        stateInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage setCity(String value) {
        cityClick.click();
        cityInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage setFileUpload(String value) {
        uploadPictureInput.uploadFromClasspath(value);
        return this;

    }
    public RegistrationFormPage clickSubmit() {
        submitClick.click();
        return this;

    }

    //итоговая форма
    public RegistrationFormPage checkFinalFormTest(String key, String value) {
        checkFinalForm.$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }

}
