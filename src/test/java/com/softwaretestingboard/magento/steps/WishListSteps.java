package com.softwaretestingboard.magento.steps;

import com.softwaretestingboard.magento.pages.CustomerAccountPage;
import com.softwaretestingboard.magento.pages.JacketPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class WishListSteps {
    private WebDriver driver;
    private final Actions actions;
    private JacketPage jacketPage;
    private CustomerAccountPage customerAccountPage;

    public WishListSteps() {
        this.driver = Hooks.driver;
        this.actions = new Actions(driver);
        this.jacketPage = new JacketPage(driver, new Actions(driver));
        this.customerAccountPage = new CustomerAccountPage(driver);
    }

    @Then("I remove the Price filter")
    public void i_remove_the_Price_filter() {
        jacketPage.removePriceFilter();
    }

    @Then("I check that the items number displayed is increased")
    public void the_items_number_displayed_is_increased() {
        jacketPage.verifyIncreasedItemCount();
    }

    @When("I add the first item to the Wish List")
    public void i_add_the_first_item_to_the_Wish_List() {
        jacketPage.addElementToWishList();
    }

    @Then("I see a successful message with text and icon")
    public void i_see_a_successful_message_with_text_and_icon() {
        customerAccountPage.verifySuccessMessage();
    }

    @Then("I check that the correct number of items is displayed in My Wish List")
    public void i_check_the_right_number_of_items_is_displayed_in_my_wish_list() {
        customerAccountPage.checkWishListItems();
    }
}
