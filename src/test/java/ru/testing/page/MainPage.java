package ru.testing.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends Page {
    @FindBy(xpath = "//div[contains(@class,\"full-name\")]")
    private WebElement fullNameDiv;
    @FindBy(xpath = "//div[@class=\"mira-user-info-logout\"]")
    private WebElement logoutBtn;
    //локатор для сообщения о загрузке
    private final By loadingMessageLocator = By.xpath("//div [contains(@class,\"loading\") and *[contains(text(),\"Загружаем\")]]");
    private final By keyElement = By.xpath("//span[contains(@class,\"mira-label-text\") and text()=\"Главная страница\"]");
    private final String titlePage = "Главная страница";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getFullName() {
        return fullNameDiv.getText();
    }

    public void dropDownContainerClick() {
        //дополнительно настроим ожидание исчезновения сообщения о загрузке
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10L));
        Boolean result = wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingMessageLocator));
        elementClick(fullNameDiv);
    }

    public void logoutBtnClick() {
        elementClick(logoutBtn);
    }

    public void logout() {
        dropDownContainerClick();
        logoutBtnClick();
    }

}