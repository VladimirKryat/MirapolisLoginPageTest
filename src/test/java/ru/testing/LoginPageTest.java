package ru.testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.testing.util.ConfPropertiesUtil;
import ru.testing.util.DriverSetupUtil;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginPageTest {
    private static Logger logger = LoggerFactory.getLogger(LoginPageTest.class);
    private WebDriver driver;

    @BeforeAll
    public void setup() {
        logger.trace("Start setup");
        //получаем инстанс драйвера
        driver = DriverSetupUtil.getDriver();
        driver.get(ConfPropertiesUtil.getProperty("login.page.url"));
    }

    @ParameterizedTest(name = "{index} Login {0} : Password {1}")
    @CsvFileSource(
            resources = "/userdata.csv",
            numLinesToSkip = 1,                                 //пропускаем первую строку файла (заголовки)
            ignoreLeadingAndTrailingWhitespace = true           //удаляем пробелы перед и после значением
    )
    public void loginTest(String login, String password){
        logger.trace("Start loginTest");
    }
    @AfterAll
    public void quit(){
        driver.quit();
    }
}
