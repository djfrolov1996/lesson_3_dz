package guru.qa.dz;

import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import guru.qa.dz.domain.MenuItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class EnumSourceFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.ozon.ru";
        Configuration.browserSize = "1920x1080";
    }

    @EnumSource(MenuItem.class)
    @ParameterizedTest()
    void menuCheckSports(MenuItem testData) {
        open(Configuration.baseUrl);

        $("input[placeholder='Искать на Ozon']").click();
        $("input[placeholder='Искать на Ozon']").sendKeys("Перчатки");
        $("input[placeholder='Искать на Ozon']").submit();
        $(".st0").$(byText(testData.rusName)).click();

        Assertions.assertEquals(
                1,
                WebDriverRunner.getWebDriver().getWindowHandles().size()
        );

    }
    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}
