package guru.qa.dz;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.RegistrationFormPage;
import static java.lang.String.format;

public class CsvSourceFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    Faker faker = new Faker();
    //переменные
    String
            gender = faker.demographic().sex(),
            subjects = "English",
            state = "NCR",
            city = "Noida",
            image = "img/image.jpeg";

    @CsvSource(value = {
            "Ivan | Petrov | ivanpetrov@gmail.com | 8888888888 | Pushkina 33",
            "Denis | Kozlov | deniskozlov@gmail.com | 7777777777 | Kolotushkina 55"
    },
            delimiter = '|'
    )
    @ParameterizedTest(name = "CsvSource")
    void fillFormTest(String testFirstName,
                      String testLastName,
                      String testEmail,
                      String testMobileNumber,
                      String testCurrentAddress){
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        //открытие браузера
        registrationFormPage.openPage();

        //заполнение форм
        registrationFormPage.setFirstName(testFirstName)
                .setLastName(testLastName)
                .setEmail(testEmail)
                .setGender(gender)
                .setDateOfBirth("May","1990","10")
                .setPhoneNumber(testMobileNumber)
                .setAddress(testCurrentAddress)
                .setSubjects(subjects)
                .setHobbies()
                .setState(state)
                .setCity(city)
                .setFileUpload(image)
                .clickSubmit();

        //итоговая форма
        registrationFormPage.checkFinalFormTest("Student Name", format("%s %s", testFirstName, testLastName))
                .checkFinalFormTest("Student Email", testEmail)
                .checkFinalFormTest("Mobile", testMobileNumber)
                .checkFinalFormTest("State and City", format("%s %s", state, city))
                .checkFinalFormTest("Address", testCurrentAddress)
                .checkFinalFormTest("Gender", gender)
                .checkFinalFormTest("Date of Birth", "10 May,1990")
                .checkFinalFormTest("Subjects", subjects)
                .checkFinalFormTest("Picture", image.substring(4));
    }

}
