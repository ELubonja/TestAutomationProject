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
import java.util.NoSuchElementException;
import java.util.Random;

public class JacketPage {
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;
    private String colorName;
    private String colorUrl;
    private String selectedPriceOptionText;
    private int productsDisplayedWhilePriceFilterAdded;
    public static int elementToWishListCounter;

    public JacketPage(WebDriver driver, Actions actions) {
        this.actions = actions;
        this.driver = Hooks.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="div#layered-filter-block  strong[role='heading']")
    private WebElement sideBarMenu;
    @FindBy(css="div:nth-of-type(4) > div[role='tab']")
    private WebElement sideBarColorElement;
    @FindBy(xpath = "//div[@class='filter-options-title' and contains(text(), 'Color')]/following-sibling::div[@class='filter-options-content']//div[@class='swatch-attribute-options clearfix']/a")
    private List<WebElement> colorSideBarElementOptions;
    @FindBy(css="li.item.product.product-item")
    private List<WebElement> allDisplayedProducts;
    @FindBy(css="div:nth-of-type(10) > div[role='tab']")
    private WebElement productPriceElement;
    @FindBy(css="div[data-role='content'].filter-options-content ol.items li.item a[href*='price']")
    private List<WebElement> priceSideBarElementOptions;
    @FindBy(css = "div:nth-of-type(1) > ol > li:nth-of-type(2)")
    private WebElement priceFilter;
    @FindBy(css="div[aria-describedby*='option-label-size-']")
    private List<WebElement> sizeOptions;
    @FindBy(css="div[role='alert'] > div > div")
    private WebElement addedToCartSuccessMessage;

    public void clickOnColorElement() {
        Assert.assertNotNull(sideBarMenu);
        Assert.assertNotNull(sideBarColorElement);
        sideBarColorElement.click();
    }

    public void chooseRandomColor() {
        try {
            Random random = new Random();
            wait.until(ExpectedConditions.visibilityOfAllElements(colorSideBarElementOptions));
            WebElement randomColor = colorSideBarElementOptions.get(random.nextInt(colorSideBarElementOptions.size()));

            wait.until(ExpectedConditions.visibilityOf(randomColor));
            wait.until(ExpectedConditions.elementToBeClickable(randomColor));

            colorName = randomColor.getAttribute("aria-label");
            colorUrl = randomColor.getAttribute("href");

            randomColor.findElement(By.tagName("div")).click();
            Assert.assertEquals(colorUrl, driver.getCurrentUrl());
        } catch (NoSuchElementException e) {
            Assert.fail("There is no such element.", e);
        }
    }

    public void verifyColorBorderedInRed() {
        for (WebElement product : allDisplayedProducts) {
            boolean colorFound = false;
            List<WebElement> colorElements = product.findElements(By.className("swatch-option"));

            for (WebElement colorElement : colorElements) {
                if (colorElement.getAttribute("aria-label").equals(colorName) && colorElement.getAttribute("aria-checked").equals("true")) {
                    String borderColor = colorElement.getCssValue("outline-color");
                    Assert.assertEquals(borderColor, "rgba(255, 85, 1, 1)");
                    colorFound = true;
                    break;
                }
            }

            Assert.assertTrue(colorFound, "Color " + colorName + " not found in product.");
        }
    }

    public void clickTheFirstPriceInPriceDropdown() {
        WebElement firstPriceOption;
        Assert.assertNotNull(sideBarMenu);
        productPriceElement.click();

        if (!priceSideBarElementOptions.isEmpty()) {
            firstPriceOption = priceSideBarElementOptions.get(0);
            if (firstPriceOption != null) {
                selectedPriceOptionText = firstPriceOption.getText();
                String firstPriceOptionUrl = firstPriceOption.getAttribute("href");
                firstPriceOption.click();
                Assert.assertEquals(firstPriceOptionUrl, driver.getCurrentUrl());
                Assert.assertTrue(allDisplayedProducts.size()>0, "There is no element displayed.");
                productsDisplayedWhilePriceFilterAdded = allDisplayedProducts.size();
            }
        } else {
            System.err.println("No price options were found.");
        }

    }

    public void checkPriceWithinRange() {
        String[] parts = selectedPriceOptionText.split(" - ");
        double lowPrice = Double.parseDouble(parts[0].replaceAll("[^\\d.]", ""));
        double highPrice = parts.length > 1 ? Double.parseDouble(parts[1].replaceAll("[^\\d.]", "")) : Double.MAX_VALUE;

        wait.until(ExpectedConditions.visibilityOfAllElements(allDisplayedProducts));

        if (!priceSideBarElementOptions.isEmpty()) {
            for (WebElement product : allDisplayedProducts) {
                String priceText = product.findElement(By.className("price")).getText().replace("$", "");
                double price = Double.parseDouble(priceText);
                Assert.assertTrue(price >= lowPrice && price <= highPrice, "Price " + price + " is out of the selected range");
            }
        } else {
            System.err.println("No price options were found.");
        }
    }

    public void removePriceFilter() {
        wait.until(ExpectedConditions.visibilityOf(priceFilter));
        WebElement removeFilterButton = priceFilter.findElement(By.cssSelector("a.action.remove"));
        removeFilterButton.click();
    }

    public void verifyIncreasedItemCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(allDisplayedProducts));
        Assert.assertTrue(allDisplayedProducts.size() > productsDisplayedWhilePriceFilterAdded, "The number of items did not increase after removing the price filter.");
    }

    public void addElementToWishList() {
        actions.moveToElement(allDisplayedProducts.get(elementToWishListCounter)).perform();
        allDisplayedProducts.get(elementToWishListCounter).findElement(By.cssSelector("li:nth-of-type(1) > .product-item-info .actions-secondary > a:nth-of-type(1)")).click();
        elementToWishListCounter++;
    }

    public void addAllDisplayedItems() {
        for(WebElement product : allDisplayedProducts) {
            actions.moveToElement(product).perform();
            product.findElements(By.cssSelector("div[aria-describedby*='option-label-size-']")).get(0).click();
            product.findElement(By.xpath(".//button[@title='Add to Cart']")).click();
        }
    }

    public void checkSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(addedToCartSuccessMessage));
        Assert.assertTrue(addedToCartSuccessMessage.isDisplayed());
    }

    public void openShoppingCartByLinkOnSuccessMessage() {
        addedToCartSuccessMessage.findElement(By.tagName("a")).click();
    }
}
