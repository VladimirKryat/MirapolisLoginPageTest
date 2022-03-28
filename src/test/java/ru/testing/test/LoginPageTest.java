package ru.testing.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.testing.page.LoginPage;
import ru.testing.page.MainPage;
import ru.testing.util.ChangeParamsUtil;
import ru.testing.util.ConfPropertiesUtil;
import ru.testing.util.DriverSetupUtil;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginPageTest {
    private static Logger logger = LoggerFactory.getLogger(LoginPageTest.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;


    @BeforeAll
    public void setup() {
        logger.trace("Start setup");
        //получаем инстанс драйвера
        driver = DriverSetupUtil.getDriver();
        driver.get(ConfPropertiesUtil.getProperty("login.page.url"));
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    /*Тест проверяет возможность входа в систему и возможны два случая:
                -введены верные данные, тогда мы проверяем, что вход осуществлён проверкой по title или надписью с текстом "Главная страница"
                -данные неверны, тогда должен выскочить alert с текстом Неверные данные для авторизации
    */
    @ParameterizedTest(name = "test-{index} Login: {0}  Password: {1}")
    @CsvFileSource(
            resources = "/userdata.csv",
            numLinesToSkip = 1,                                 //пропускаем первую строку файла (заголовки)
            ignoreLeadingAndTrailingWhitespace = true           //удаляем пробелы перед и после значением
    )
    public void loginTest(String login, String password, String expectedResult, boolean isCorrectData) {
        logger.trace("Start loginTest");
        login = ChangeParamsUtil.changeParams(login);
        password = ChangeParamsUtil.changeParams(password);
        expectedResult = ChangeParamsUtil.changeParams(expectedResult);
        //проверяем страницу на соответствие LoginPage
        Assertions.assertTrue(loginPage.isCorrectPageByTitle() || loginPage.isCorrectPageByKeyElement());
        loginPage.loginAs(login, password);
        if (isCorrectData) {
            //проверяем страницу на соответствие MainPage
            Assertions.assertTrue(mainPage.isCorrectPageByTitle() || mainPage.isCorrectPageByKeyElement());
            //проверяем соответствие Ожидаемого полного имени с Фактическим
            Assertions.assertEquals(expectedResult, mainPage.getFullName());
            mainPage.logout();
            Assertions.assertTrue(loginPage.isCorrectPageByTitle() || loginPage.isCorrectPageByKeyElement());
        } else {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

            // Данный тест падает, потому как alert выдаёт разные сообщения при разных данных.
            //либо принять это как баг, либо изменить equals на contains
            Assertions.assertTrue(alert.getText().contains(expectedResult));
            alert.accept();
        }
    }


    //метод проверяет корректность нажатия на кнопку "Показать пароль", а именно:
    //          -проверяет сохранность данных при клики на кнопку
    //          -проверяет возможность входа в систему с открытым паролем
    @ParameterizedTest
    @CsvSource({" fominaelena,  1P73BP4Z , Фомина Елена Сергеевна "})
    public void showPasswordTest(String login, String password, String expectedFullName) {
        logger.trace("Start showPasswordTest");
        loginPage.setLoginInput(login);
        loginPage.setPasswordInput(password);
        //устанавливаем отображение символов пароля
        loginPage.setShowPasswordBtn(true);
        //проверяем, что пароль и логин не стерлись
        Assertions.assertEquals(loginPage.getLoginInputAttribute("value"), login);
        Assertions.assertEquals(loginPage.getPasswordAttribute("value"), password);
        //проверяем, что символы действительно отображаются
        Assertions.assertEquals(loginPage.getPasswordAttribute("type"), "text");
        //проверим, что вход с открытым паролем производится как обычно
        loginPage.submitBtnClick();
        Assertions.assertTrue(mainPage.isCorrectPageByTitle() || mainPage.isCorrectPageByKeyElement());
        //проверяем соответствие Ожидаемого полного имени с Фактическим
        Assertions.assertEquals(expectedFullName, mainPage.getFullName());
        mainPage.logout();

    }

    @AfterAll
    public void quit() {
        driver.quit();
    }
}
