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
        registrationFormPage.setOpenPage();

        //заполнение форм
        registrationFormPage.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(mail)
                .setGender(gender)
                .setDateOfBirth()
                .setPhoneNumber(mobile)
                .setAddress(currentAddress)
                .setSubjects(subjects)
                .setHobbies()
                .setState(state)
                .setCity(city)
                .setFileUpload(image)
                .setSubmit();

        //итоговая форма
        registrationFormPage.setFinalFormTest("Student Name", expectedFullName)
                .setFinalFormTest("Student Email", mail)
                .setFinalFormTest("Gender", gender)
                .setFinalFormTest("Date of Birth", "15 May,1910")
                .setFinalFormTest("Mobile", mobile)
                .setFinalFormTest("Address", currentAddress)
                .setFinalFormTest("Subjects", subjects)
                .setFinalFormTest("State and City", format("%s %s", state, city))
                .setFinalFormTest("Picture", image.substring(4));
    }

}
