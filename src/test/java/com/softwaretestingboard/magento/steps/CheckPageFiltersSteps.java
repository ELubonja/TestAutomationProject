package com.softwaretestingboard.magento.steps;

import com.softwaretestingboard.magento.pages.HomePage;
import com.softwaretestingboard.magento.pages.JacketPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CheckPageFiltersSteps {
    private final WebDriver driver;
    private final Actions actions;
    private final HomePage homePage;
    private final JacketPage jacketPage;

    public CheckPageFiltersSteps() {
        this.driver = Hooks.driver;
        this.actions = new Actions(driver);
        this.homePage = new HomePage(driver, actions);
        this.jacketPage = new JacketPage(driver, new Actions(driver));
    }

    @When("I hover the Women dropdown on the store menu")
    public void i_hover_the_women_dropdown_on_the_store_menu() {
        homePage.hoverWomanCategory();
    }

    @And("I hover over the Tops dropdown")
    public void i_hover_over_the_tops_dropdown() {
        homePage.hoverTopsCategory();
    }

    @And("I click on the Jacket menu option")
    public void i_click_on_the_jacket_menu_option() {
        homePage.clickJacketCategory();
    }

    @And("I click on the Color menu option")
    public void i_click_on_the_color_menu_option() {
        jacketPage.clickOnColorElement();
    }

    @And("I choose a color")
    public void i_choose_a_color() {
        jacketPage.chooseRandomColor();
    }

    @Then("I should see all displayed products with the selected color bordered in red")
    public void i_should_see_all_displayed_products_with_the_selected_color_bordered_in_red() {
        jacketPage.verifyColorBorderedInRed();
    }

    @When("I click on the Price dropdown and select the first option")
    public void i_click_on_the_price_dropdown_and_select_the_first_option() {
        jacketPage.clickTheFirstPriceInPriceDropdown();
    }

    @And("I check each displayed product should have a price within the range selected")
    public void i_check_displayed_products_prices() {
        jacketPage.checkPriceWithinRange();
    }
}
