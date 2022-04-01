package ru.testing.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Page {
    private final WebDriver driver;
    private String titlePage;
    private By keyElement;

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //проверяем title страницы
    public boolean isCorrectPageByTitle() {
        String title = new WebDriverWait(driver, Duration.ofSeconds(2)).until(WebDriver::getTitle);
        return title.equalsIgnoreCase(titlePage);
    }

    //ожидаем элемент в DOM
    public boolean isCorrectPageByKeyElement() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(keyElement)) != null;
    }

    public WebDriver getDriver() {
        return driver;
    }

    //ожидаем возможности кликнуть элемент и кликаем
    protected void elementClick(WebElement element) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5L))
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    protected void elementSetText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
