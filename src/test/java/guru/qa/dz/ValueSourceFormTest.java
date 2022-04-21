package guru.qa.dz;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.RegistrationFormPage;
import utils.RandomUtils;

import static java.lang.String.format;

public class ValueSourceFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    Faker faker = new Faker();
    //переменные
    String firstName = "Мага",
            lastName = "Магомедов",
            currentAddress = faker.rickAndMorty().location(),
            gender = faker.demographic().sex(),
            mobileNumber = RandomUtils.getRandomPhone(10),
            subjects = "English",
            state = "NCR",
            city = "Noida",
            image = "img/image.jpeg";

    @ValueSource(strings = {
            "ivanpetrov@gmail.com",
            "deniskozlov@gmail.com"
    })
    @ParameterizedTest(name = "ValueSource")
    void inputMail(String mailData) {
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        //открытие браузера
        registrationFormPage.openPage();

        //заполнение форм
        registrationFormPage.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(mailData)
                .setGender(gender)
                .setDateOfBirth("May","1990","10")
                .setPhoneNumber(mobileNumber)
                .setAddress(currentAddress)
                .setSubjects(subjects)
                .setHobbies()
                .setState(state)
                .setCity(city)
                .setFileUpload(image)
                .clickSubmit();

        //итоговая форма
        registrationFormPage.checkFinalFormTest("Student Name", format("%s %s", firstName, lastName))
                .checkFinalFormTest("Student Email", mailData)
                .checkFinalFormTest("Mobile", mobileNumber)
                .checkFinalFormTest("State and City", format("%s %s", state, city))
                .checkFinalFormTest("Address", currentAddress)
                .checkFinalFormTest("Gender", gender)
                .checkFinalFormTest("Date of Birth", "10 May,1990")
                .checkFinalFormTest("Subjects", subjects)
                .checkFinalFormTest("Picture", image.substring(4));

    }

}
