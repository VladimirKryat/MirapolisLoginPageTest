package ru.testing.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Page{
    @FindBy(xpath = "//div[contains(@class,\"full-name\")]")
    WebElement fullNameDiv;
    @FindBy(xpath = "//div[@class=\"mira-user-info-logout\"]")
    WebElement logoutBtn;

    public MainPage(WebDriver driver){
        super(driver, "Главная страница");
        setKeyLocator(By.xpath("//span[contains(@class,\"mira-label-text\") and text()=\"Главная страница\"]"));
    }

    public String getFullName(){
        return fullNameDiv.getText();
    }
    public void dropDownContainerClick(){
        fullNameDiv.click();
    }
    public void logoutBtnClick(){
        logoutBtn.click();
    }
    public void logout(){
        dropDownContainerClick();
        logoutBtnClick();
    }

}