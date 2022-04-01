package ru.testing.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    @FindBy(xpath = "//input[@name=\"user\"]")
    private WebElement loginInput;
    @FindBy(xpath = "//input[@name=\"password\"]")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@id=\"show_password\"]")
    private WebElement showPasswordBtn;
    @FindBy(xpath = "//button[@id=\"button_submit_login_form\"]")
    private WebElement submitBtn;
    private final By keyElement = By.xpath("//div[contains(@class,\"info-title\") and *[contains(text(),\"Вход\")]]");
    private final String titlePage = "Авторизация";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage loginAs(String login, String password) {
        setLoginInput(login);
        setPasswordInput(password);
        return submitBtnClick();
    }

    public void setLoginInput(String login) {
        elementSetText(loginInput, login);
    }

    public void setPasswordInput(String password) {
        elementSetText(passwordInput, password);
    }

    public MainPage submitBtnClick() {
        elementClick(submitBtn);
        return new MainPage(getDriver());
    }

    //    устанавливаем видимость символов пароля
    public void setShowPasswordBtn(boolean isVisible) {
//        Кликнуть на кнопку нужно когда тип кнопки не соответствует видимости элемента
//        Возвращаемся из метода когда:
//                  если тип данных в поле passwordInput - password и видимость установлена в false
//                  если тип данных text и видимость true
        String valueTypeAttribute = passwordInput.getAttribute("type");
        if ((valueTypeAttribute.equals("password") && !isVisible)
                || (valueTypeAttribute.equals("text") && isVisible)) {
            return;
        }
        elementClick(showPasswordBtn);
    }

    public String getLoginInputAttribute(String attributeName) {
        return loginInput.getAttribute(attributeName);
    }

    public String getPasswordInputAttribute(String attributeName) {
        return passwordInput.getAttribute(attributeName);
    }
}
