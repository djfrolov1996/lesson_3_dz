package guru.qa.dz;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FirstTestForm {
    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }
    //переменные
    String firstName = "Oleg";
    String lastName = "Popov";
    String mail = "olegpopov@gmail.com";
    String mobile = "9999999999";
    String currentAddress = "Moscow 2022";
    String gender = "Male";
    String subjects = "English";
    String state = "NCR";
    String city = "Noida";
    String image = "img/image.jpeg";

    @Test
    void fillFormTest(){

        //открытие браузера
        open("/automation-practice-form");

        //заполнение форм
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(mail);
        $("#userNumber").setValue(mobile);
        $("#currentAddress").setValue(currentAddress);

        //радиокнопка
        $("#genterWrapper").$(byText(gender)).click();

        //subjects
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue(subjects).pressEnter();

        //дата рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1910");
        $(".react-datepicker__month-container").$(byText("15")).click();

        //чекбоксы
        for (String s : Arrays.asList("label[for='hobbies-checkbox-1']",
                "label[for='hobbies-checkbox-2']",
                "label[for='hobbies-checkbox-3']")) {
            $(s).click();
        }

        //State and City
        $("div[class=' css-yk16xz-control'] div[class=' css-1hwfws3']").click();
        $("#react-select-3-input").setValue(state).pressEnter();
        $("div[id='city'] div[class=' css-tlfecz-indicatorContainer']").click();
        $("#react-select-4-input").setValue(city).pressEnter();

        //загрузка файла
        $("#uploadPicture").uploadFromClasspath(image);

        //submit
        $("#submit").click();
    }

    @Test
    void finalFormTest() {
        //итоговая форма
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(
                text("Label"),
                text("Values"),
                text("Student Name"),
                text(firstName + " " + lastName),
                text("Student Email"),
                text(mail),
                text("Gender"),
                text(gender),
                text("Mobile"),
                text(mobile),
                text("Date of Birth 15 May,1910"),
                text("Subjects"),
                text(subjects),
                text("Hobbies Sports, Reading, Music"),
                text("Picture"),
                text(image.substring(4)),
                text("Address"),
                text(currentAddress),
                text("State and City"),
                text(state + " " + city)
        );
    }
}
