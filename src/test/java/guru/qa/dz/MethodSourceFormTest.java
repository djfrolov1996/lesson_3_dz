package guru.qa.dz;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.RegistrationFormPage;
import utils.RandomUtils;
import java.util.stream.Stream;

import static java.lang.String.format;

public class MethodSourceFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    Faker faker = new Faker();
    //переменные
    String currentAddress = faker.rickAndMorty().location(),
            gender = faker.demographic().sex(),
            mobileNumber = RandomUtils.getRandomPhone(10),
            subjects = "English",
            state = "NCR",
            city = "Noida",
            image = "img/image.jpeg";


    static Stream<Arguments> inputMail() {
        return Stream.of(
                Arguments.of("Иван", "Петров", "ivanpetrov@gmail.com"),
                Arguments.of("Денис", "Козлов", "deniskozlov@gmail.com")
        );
    }

    @MethodSource
    @ParameterizedTest
    void inputMail(String fstName, String lstName, String email) {
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        //открытие браузера
        registrationFormPage.openPage();

        //заполнение форм
        registrationFormPage.setFirstName(fstName)
                .setLastName(lstName)
                .setEmail(email)
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
        registrationFormPage.checkFinalFormTest("Student Name", format("%s %s", fstName, lstName))
                .checkFinalFormTest("Student Email", email)
                .checkFinalFormTest("Mobile", mobileNumber)
                .checkFinalFormTest("State and City", format("%s %s", state, city))
                .checkFinalFormTest("Address", currentAddress)
                .checkFinalFormTest("Gender", gender)
                .checkFinalFormTest("Date of Birth", "10 May,1990")
                .checkFinalFormTest("Subjects", subjects)
                .checkFinalFormTest("Picture", image.substring(4));

    }

}
