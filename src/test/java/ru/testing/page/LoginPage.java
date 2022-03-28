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

    public LoginPage(WebDriver driver) {
        super(driver, "Авторизация", By.xpath("//div[contains(@class,\"info-title\") and *[contains(text(),\"Вход\")]]"));
    }

    public void loginAs(String login, String password){
        setLoginInput(login);
        setPasswordInput(password);
        submitBtnClick();
    }

    public void setLoginInput(String login) {
        elementSetText(loginInput,login);
    }

    public void setPasswordInput(String password) {
        elementSetText(passwordInput,password);
    }

    public void submitBtnClick() {
        elementClick(submitBtn);
    }

    //устанавливаем видимость символов пароля
    public void setShowPasswordBtn(boolean isVisible) {
        /*
        Кликнуть на кнопку нужно когда тип кнопки не соответствует видимости элемента
        Возвращаемся из метода когда:
                  если тип данных в поле passwordInput - password и видимость установлена в false
                  если тип данных text и видимость true
                   */
        if ((passwordInput.getAttribute("type").equals("password") && !isVisible)
                || (passwordInput.getAttribute("type").equals("text") && isVisible)) {
            return;
        }
        elementClick(showPasswordBtn);
    }

    public String getLoginInputAttribute(String attributeName){
        return loginInput.getAttribute(attributeName);
    }

    public String getPasswordAttribute(String attributeName){
        return passwordInput.getAttribute(attributeName);
    }

}
