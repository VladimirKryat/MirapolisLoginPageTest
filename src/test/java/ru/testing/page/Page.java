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
    public Page(WebDriver driver, String title, By keyLocator){
        PageFactory.initElements(driver,this);
        this.driver=driver;
        TITLE_PAGE=title;
        this.keyLocator = keyLocator;
    }
    //проверяем title страницы
    public boolean isCorrectPageByTitle(){
        String title = new WebDriverWait(driver, Duration.ofSeconds(2)).until(WebDriver::getTitle);
        return title.equalsIgnoreCase(TITLE_PAGE);
    }
    //ожидаем элемент
    public boolean isCorrectPageByKeyElement(){
        if (keyLocator==null) return false;
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(keyLocator))!=null;
    }

    public WebDriver getDriver() {
        return driver;
    }

    //ожидаем возможности кликнуть элемент и кликаем
    protected void elementClick(WebElement element){
        new WebDriverWait(getDriver(), Duration.ofSeconds(5L))
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }
    protected void elementSetText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
}
