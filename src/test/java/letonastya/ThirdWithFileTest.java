package letonastya;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static com.codeborne.selenide.Selenide.*;

@DisplayName("Проверка количества результатов в поисковом запросе")
public class ThirdWithFileTest {

    @BeforeEach
    void setup() {
        open("https://kazanexpress.ru/");
        Configuration.holdBrowserOpen = true;
    }

    @CsvFileSource(resources = "/testdata/thirdTestSearchResult.csv",
            delimiter = '|'
    )

    @ParameterizedTest(name = "В первом результате выдачи для {0} должен отражаться текст {1}")
    void thirdTestSearchResult(String testData, String expectedText) {

        $("[data-test-id=input__search]").setValue(testData);
        $("[data-test-id=button__search]").click();
        $$("[data-test-id=list__products]").first()
                .shouldHave(Condition.text(expectedText));
    }
}
