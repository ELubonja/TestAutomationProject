package com.softwaretestingboard.magento.steps;

import com.softwaretestingboard.magento.pages.CustomerSignInPage;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInSteps {
    private final CustomerSignInPage customerSignInPage;
    private final WebDriverWait wait;

    public SignInSteps() {
        WebDriver driver = Hooks.driver;
        this.customerSignInPage = new CustomerSignInPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Then("I should see my username displayed")
    public void i_should_see_my_username_displayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(customerSignInPage.getDisplayedUsernamePath())));
    }
}
