package com.softwaretestingboard.magento.steps.common;

import com.softwaretestingboard.magento.pages.CustomerAccountPage;
import com.softwaretestingboard.magento.pages.CustomerSignInPage;
import com.softwaretestingboard.magento.pages.HomePage;
import com.softwaretestingboard.magento.steps.CreateAccountSteps;
import com.softwaretestingboard.magento.steps.Hooks;
import com.softwaretestingboard.magento.utils.RandomEmailGenerator;
import com.softwaretestingboard.magento.utils.RandomPasswordGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CommonSteps {
    protected WebDriver driver;
    protected HomePage homePage;
    protected CustomerAccountPage customerAccountPage;
    protected CustomerSignInPage customerSignInPage;


    public CommonSteps() {
        this.driver = Hooks.driver;
        this.homePage = new HomePage(driver, new Actions(driver));
        this.customerAccountPage = new CustomerAccountPage(driver);
        this.customerSignInPage = new CustomerSignInPage(driver);
    }

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @When("I sign in using {string} and {string}")
    public void i_sign_in(String email, String password) {
        homePage.clickSignInAccountLink();
        Assert.assertEquals(driver.getTitle(), "Customer Login", "Title does not match");
        customerSignInPage.enterEmail(email);
        customerSignInPage.enterPassword(password);
        customerSignInPage.clickSignIn();
    }

    @When("I sign in")
    public void i_sign_in() {
        homePage.clickSignInAccountLink();
        Assert.assertEquals(driver.getTitle(), "Customer Login", "Title does not match");
        customerSignInPage.enterEmail(RandomEmailGenerator.getRandomEmail());
        customerSignInPage.enterPassword(RandomPasswordGenerator.getGeneratedPassword());
        customerSignInPage.clickSignIn();
    }

    @Then("I sign out")
    public void i_sign_out() {
        customerAccountPage.clickUserProfile();
        customerAccountPage.clickSignOut();
    }
}
