package com.softwaretestingboard.magento.steps;

import com.softwaretestingboard.magento.pages.CustomerCreateAccountPage;
import com.softwaretestingboard.magento.pages.HomePage;
import com.softwaretestingboard.magento.utils.RandomEmailGenerator;
import com.softwaretestingboard.magento.utils.RandomPasswordGenerator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CreateAccountSteps {
    private final WebDriver driver;
    private final HomePage homePage;
    public String email;
    private final CustomerCreateAccountPage customerCreateAccountPage;

    public CreateAccountSteps() {
        this.driver = Hooks.driver;
        this.homePage = new HomePage(driver, new Actions(driver));
        this.customerCreateAccountPage = new CustomerCreateAccountPage(driver);
    }

    @When("I navigate to the create account page")
    public void i_navigate_to_the_create_account_page() {
        homePage.clickCreateAccountLink();
    }

    @Then("I should see the create account page title")
    public void i_should_see_the_create_account_page_title() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Create New Customer Account", "Title does not match");
    }

    @When("I fill in the account details")
    public void i_fill_in_the_account_details() {
        email=RandomEmailGenerator.getRandomEmail();
        customerCreateAccountPage.enterFirstName("Test");
        customerCreateAccountPage.enterLastName("Example");
        customerCreateAccountPage.enterEmail(email);
        customerCreateAccountPage.enterPassword(RandomPasswordGenerator.getGeneratedPassword());
        Assert.assertEquals(customerCreateAccountPage.getPasswordStrength(), "Very Strong", "Password should be stronger");
        customerCreateAccountPage.enterConfirmPassword(RandomPasswordGenerator.getGeneratedPassword());
    }

    @When("I click the create account button")
    public void i_click_the_create_account_button() {
        customerCreateAccountPage.clickCreateAccount();
    }

    @Then("I should see the success message")
    public void i_should_see_the_success_message() {
        Assert.assertTrue(customerCreateAccountPage.isSuccessMessageDisplayed());
        Assert.assertTrue(customerCreateAccountPage.getSuccessMessageText().contains("Thank you for registering with Main Website Store."));
    }

    @Then("I should be redirected to the account page")
    public void i_should_be_redirected_to_the_account_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("customer/account/"));
    }
}
