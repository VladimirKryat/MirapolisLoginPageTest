package ru.testing.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public abstract class Page {
    //служит для проверки корректности страницы
    private final String TITLE_PAGE;
    //для вспомогательной проверки по элементу
    private By keyLocator;
    private WebDriver driver;
    public Page(WebDriver driver, String title){
        PageFactory.initElements(driver,this);
        this.driver=driver;
        TITLE_PAGE=title;
    }
    //проверяем title страницы
    public boolean isCorrectPageByTitle(){
        String title = new WebDriverWait(driver, Duration.ofSeconds(2)).until(WebDriver::getTitle);
        return title.equalsIgnoreCase(TITLE_PAGE);
    }
    //ожидаем элемент
    public boolean isCorrectPageByKeyElement(){
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(keyLocator))!=null;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setKeyLocator(By keyLocator) {
        this.keyLocator = keyLocator;
    }
}
