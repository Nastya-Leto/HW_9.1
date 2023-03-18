package letonastya;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import letonastya.data.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Проверка количества результатов в поисковом запросе")
public class FourthWithEnumTest {

    @BeforeEach
    void config() {
        Configuration.holdBrowserOpen = true;
    }

    static Stream<Arguments> testSectionContent() {
        return Stream.of(
                Arguments.of(Section.Одежда, List.of("Все категории", "Одежда",
                        "Детская одежда", "Женская одежда", "Мужская одежда", "Спецодежда")),
                Arguments.of(Section.Обувь, List.of("Все категории", "Обувь", "Аксессуары для обуви",
                        "Специализированная обувь", "Женская обувь", "Мужская обувь",
                        "Обувь для девочек", "Обувь для мальчиков"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Для раздела {0} должен отражаться список подразделов {1}")
    void testSectionContent(Section section, List<String> expectedButtons) {

        open("https://kazanexpress.ru/");
        $$("#bottom-header li").find(Condition.text(section.name())).click();
        sleep(10000);
        $$(".category-list li").filter(visible).shouldHave(CollectionCondition.texts(expectedButtons));

    }
}
