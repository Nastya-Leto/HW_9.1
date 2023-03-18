package letonastya;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Проверка количества результатов в поисковом запросе")
public class FirstTestWithParameters {

    @BeforeEach
    void setup() {
        open("https://kazanexpress.ru/");
        Configuration.holdBrowserOpen = true;
    }

    @ValueSource(strings = {
            "Корм для кошек", "Корм для собак"
    })
    @ParameterizedTest(name = "В поисковой выдаче на kazanexpress должно отоброжаться 10 результатов по запросу {0}")
    void simpleWithParameters(String testData) {

        $("[data-test-id=input__search]").setValue(testData);
        $("[data-test-id=button__search]").click();
        $$("[data-test-id=list__products]>div")
                .shouldHave(sizeGreaterThanOrEqual(10));
    }
}
