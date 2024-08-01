package com.softwaretestingboard.magento.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerSignInPage {
    public CustomerSignInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="email")
    private WebElement emailField;
    @FindBy(id="pass")
    private WebElement passwordField;
    @FindBy(xpath="//fieldset[@class='fieldset login']//button[@name='send']")
    private WebElement signInButton;

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignIn() {
        signInButton.click();
    }

    //css selector of username
    public String getDisplayedUsernamePath() {
        return ".header.panel > .header.links > .greet.welcome > .logged-in";
    }
}
