package guru.qa;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class SimpleTest {

    @BeforeAll
    static void initDB() {
        System.out.println("###     @BeforeAll");
    }

    @BeforeEach
    void openYaPage() {
        System.out.println("###     @AfterEach");
        Selenide.open("https://ya.ru");

    }


    @AfterEach
    void close() {

        WebDriverRunner.closeWindow();

    }

    @Test
    void assertTest() {

    }

    @Test
    void assertTest2() {

    }

    @Test
    void assertTest3() {

    }




}
