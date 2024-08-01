package com.softwaretestingboard.magento.steps;

import com.softwaretestingboard.magento.pages.JacketPage;
import com.softwaretestingboard.magento.pages.ShoppingCartPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartSteps {
    private final WebDriverWait wait;
    private final WebDriver driver;
    private final JacketPage jacketPage;
    private final ShoppingCartPage shoppingCartPage;

    public ShoppingCartSteps() {
        this.driver = Hooks.driver;
        this.jacketPage = new JacketPage(driver, new Actions(driver));
        this.shoppingCartPage = new ShoppingCartPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @When("I add all displayed items to the shopping cart selecting appropriate sizes")
    public void i_add_all_displayed_items_by_selecting_appropriate_sizes() {
        jacketPage.addAllDisplayedItems();
    }

    @Then("I check a success message with a confirmation icon should be displayed")
    public void i_check_success_message() {
        jacketPage.checkSuccessMessage();
    }

    @When("I click on the shopping cart link in the success message")
    public void i_click_on_the_shopping_cart_link_in_the_success_message() {
        jacketPage.openShoppingCartByLinkOnSuccessMessage();
    }

    @Then("I check that the Shopping Cart page should be displayed")
    public void i_check_displayed_shopping_cart_page() {
        shoppingCartPage.displayedShoppingCartPage();
    }

    @Then("I check that the sum of the prices for all items should equal the Order Total in the Summary section")
    public void i_check_total_item_prices_equal_order_total() {
        shoppingCartPage.checkTotalItemSum();
    }
}
