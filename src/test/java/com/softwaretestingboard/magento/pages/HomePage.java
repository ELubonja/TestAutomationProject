package com.softwaretestingboard.magento.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final Actions actions;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver, Actions actions) {
        this.actions = actions;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']")
    private WebElement createAccountLink;
    @FindBy(xpath="//a[@href='https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/']")
    private WebElement signInAccountLink;
    @FindBy(css="[data-action] > [role] > [role='presentation']:nth-of-type(2) > [tabindex] [class]")
    private WebElement womanCategory;
    @FindBy(css="[data-action] > [role] > [role='presentation']:nth-of-type(2) > [role] > [role='presentation']:nth-of-type(1) > .ui-corner-all:nth-child(1)")
    private WebElement topsCategory;
    @FindBy(css="li:nth-of-type(2) > ul[role='menu'] > li:nth-of-type(1) > ul[role='menu'] > li:nth-of-type(1) > a[role='menuitem']")
    private WebElement jacketCategory;


    public void clickCreateAccountLink() {
        createAccountLink.click();
    }

    public void clickSignInAccountLink() {
        signInAccountLink.click();
    }

    public void hoverWomanCategory() {
        wait.until(ExpectedConditions.visibilityOf(womanCategory));
        actions.moveToElement(womanCategory).perform();
    }

    public void hoverTopsCategory() {
        wait.until(ExpectedConditions.visibilityOf(topsCategory));
        actions.moveToElement(topsCategory).perform();
    }

    public void clickJacketCategory() {
        wait.until(ExpectedConditions.visibilityOf(jacketCategory));
        actions.moveToElement(jacketCategory).perform();
        jacketCategory.click();
    }

}
