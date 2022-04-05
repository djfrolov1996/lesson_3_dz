package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Text;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests<set> {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest(){

        String name = "Roman Coman";

        open("/text-box");

        $("[id=userName]").setValue(name);
        $("[id=userEmail]").setValue("sss@gmail.com");
        $("[id=currentAddress]").setValue("Moscow 33");
        $("[id=permanentAddress]").setValue("Pushkina 22");
        $("[id=submit]").click();


        $("[id=output]").shouldHave(text("name"),
                text("sss@gmail.com"),
                text("Moscow 33"),
                text("Pushkina 22"));

    }

}
