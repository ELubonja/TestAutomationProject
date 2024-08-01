package com.softwaretestingboard.magento.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CustomerAccountPage {
    private final WebDriverWait wait;
    private final JacketPage jacketPage;

    public CustomerAccountPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jacketPage = new JacketPage(driver, new Actions(driver));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".panel [tabindex='-1']")
    private WebElement userProfileDropDown;
    @FindBy(css=".panel.wrapper .header.links .customer-menu > .header.links > .authorization-link > a")
    private WebElement signOut;
    @FindBy(css="div[role='alert'] > div > div")
    private WebElement successMessage;
    @FindBy(css="span[class=counter]")
    private WebElement myWishList;

    public void clickUserProfile() {
        userProfileDropDown.click();
    }

    public void clickSignOut() {
        signOut.click();
    }

    public void verifySuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        Assert.assertTrue(successMessage.getText().contains("added to your Wish List"),
                "The success message did not appear or was incorrect.");
    }

    public void checkWishListItems() {
        wait.until(ExpectedConditions.visibilityOf(myWishList));
        String wishListItemsText = myWishList.getText();
        Assert.assertTrue(wishListItemsText.contains(String.valueOf(jacketPage.elementToWishListCounter)));
    }
}
