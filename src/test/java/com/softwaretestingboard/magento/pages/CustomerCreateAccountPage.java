package com.softwaretestingboard.magento.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerCreateAccountPage {
    public CustomerCreateAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="firstname")
    private WebElement firstNameField;
    @FindBy(id="lastname")
    private WebElement lastNameField;
    @FindBy(id="email_address")
    private WebElement emailField;
    @FindBy(id="password")
    private WebElement passwordField;
    @FindBy(id="password-strength-meter-label")
    private WebElement passwordMeterLabel;
    @FindBy(id="password-confirmation")
    private WebElement confirmPasswordField;
    @FindBy(xpath="//button[@title='Create an Account']")
    private WebElement createAccountButton;
    @FindBy(css=".message.message-success.success")
    private WebElement successMessage;

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public String getPasswordStrength() {
        return passwordMeterLabel.getText();
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickCreateAccount() {
        createAccountButton.click();
    }

    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }
}
