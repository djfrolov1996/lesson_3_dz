package guru.qa.dz;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;
import utils.RandomUtils;
import static java.lang.String.format;

public class MainRegistrationFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    Faker faker = new Faker();
    //переменные
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            gender = faker.demographic().sex(),
            currentAddress = faker.rickAndMorty().location(),
            mail = faker.internet().emailAddress(),
            mobile = RandomUtils.getRandomPhone(10),
            subjects = "English",
            state = "NCR",
            city = "Noida",
            image = "img/image.jpeg",
            expectedFullName = format("%s %s", firstName, lastName);

    @Test
    void fillFormTest(){
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        //открытие браузера
        registrationFormPage.openPage();

        //заполнение форм
        registrationFormPage.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(mail)
                .setGender(gender)
                .setDateOfBirth("May","1990","10")
                .setPhoneNumber(mobile)
                .setAddress(currentAddress)
                .setSubjects(subjects)
                .setHobbies()
                .setState(state)
                .setCity(city)
                .setFileUpload(image)
                .clickSubmit();

        //итоговая форма
        registrationFormPage.checkFinalFormTest("Student Name", expectedFullName)
                .checkFinalFormTest("Student Email", mail)
                .checkFinalFormTest("Gender", gender)
                .checkFinalFormTest("Date of Birth", "10 May,1990")
                .checkFinalFormTest("Mobile", mobile)
                .checkFinalFormTest("Address", currentAddress)
                .checkFinalFormTest("Subjects", subjects)
                .checkFinalFormTest("State and City", format("%s %s", state, city))
                .checkFinalFormTest("Picture", image.substring(4));
    }

}
