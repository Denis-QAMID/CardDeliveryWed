import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {


    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");


    }

    @Test
    public void shouldTest() {
        String data = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("span[data-test-id='city'] input").setValue("Москва");
        $("span[data-test-id=date] [type=tel]").sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        $("span[data-test-id=date] [type=tel]").setValue(data);
        $x("//span[@data-test-id='name']//input[@class='input__control']").setValue("Денисов Денис");
        $x("//span[@data-test-id='phone']//input[@class='input__control']").setValue("+71231231212");
        $x("//span[@class='checkbox__box']").click();
        $x("//span[@class='button__text']").click();
        $x("//*[contains(text(), 'Встреча успешно забронирована на')]").shouldBe(exactText("Встреча успешно забронирована на " + data),Duration.ofSeconds(15));
    }

}
