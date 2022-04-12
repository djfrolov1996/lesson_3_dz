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
    SelenideElement emailInput = $("#userEmail");

    //actions:

    //открытие браузера
    public RegistrationFormPage setOpenPage() {
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

    public RegistrationFormPage setDateOfBirth() {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1910");
        $(".react-datepicker__month-container").$(byText("15")).click();
        return this;
    }

    public RegistrationFormPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setPhoneNumber(String value) {
        $("#userNumber").setValue(value);
        return  this;
    }

    public RegistrationFormPage setAddress(String value) {
        $("#currentAddress").setValue(value);
        return  this;
    }

    public RegistrationFormPage setSubjects(String value) {
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue(value).pressEnter();
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
        $("div[class=' css-yk16xz-control'] div[class=' css-1hwfws3']").click();
        $("#react-select-3-input").setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage setCity(String value) {
        $("div[id='city'] div[class=' css-tlfecz-indicatorContainer']").click();
        $("#react-select-4-input").setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage setFileUpload(String value) {
        $("#uploadPicture").uploadFromClasspath(value);
        return this;

    }
    public RegistrationFormPage setSubmit() {
        $("#submit").click();
        return this;

    }

    //итоговая форма
    public RegistrationFormPage setFinalFormTest(String key, String value) {
        $(".modal-body").$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }

}
