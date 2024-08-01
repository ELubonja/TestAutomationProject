package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.steps.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage {
    private final WebDriverWait wait;
    private final WebDriver driver;


    public ShoppingCartPage(WebDriver driver) {
        this.driver = Hooks.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "page-title")
    private WebElement shoppingCartTitle;

    @FindBy(css = "[class='item-info']")
    private List<WebElement> itemList;

    @FindBy(css = "strong span.price")
    private WebElement summaryTotalSum;

    public void displayedShoppingCartPage() {
        Assert.assertTrue(shoppingCartTitle.getText().contains("Shopping Cart"));
        Assert.assertTrue(driver.getCurrentUrl().contains("cart/"));
    }

    public void checkTotalItemSum() {
        wait.until(ExpectedConditions.visibilityOf(summaryTotalSum));
        Double expectedSum = Double.parseDouble(summaryTotalSum.getText().replace("$", ""));
        Double totalItemSum = 0.0;
        for (WebElement item : itemList) {
            Double price = Double.parseDouble(item.findElement(By.cssSelector(".subtotal .cart-price")).getText().replace("$", ""));
            totalItemSum += price;
        }

        Assert.assertEquals(expectedSum, totalItemSum);
    }
}
