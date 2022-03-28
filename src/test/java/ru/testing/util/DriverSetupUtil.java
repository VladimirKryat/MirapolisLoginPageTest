package ru.testing.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

/*Данный класс позволяет инстанциировать драйвер в одном месте и использовать в тест классах.
Позволяет избежать жёсткой связанности тест классов от конкретной реализации драйвера.
*/
public final class DriverSetupUtil {
    static {
        System.setProperty("webdriver.chrome.driver", ConfPropertiesUtil.getProperty("chrome.driver"));
        System.setProperty("webdriver.gecko.driver", ConfPropertiesUtil.getProperty("firefox.driver"));
    }

    public static WebDriver getDriver() {
        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new FirefoxDriver();
        //устанавливаем размер окна
        driver.manage().window().maximize();
        //настраиваем неявное ожидание элементов на 10 секунд
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        return driver;
    }
}
