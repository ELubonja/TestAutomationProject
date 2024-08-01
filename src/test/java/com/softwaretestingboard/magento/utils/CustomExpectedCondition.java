package com.softwaretestingboard.magento.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedCondition {

    public static ExpectedCondition<Boolean> waitForSeconds(final int seconds) {
        return new ExpectedCondition<>() {
            private final long endTime = System.currentTimeMillis() + seconds * 1000L;

            @Override
            public Boolean apply(WebDriver driver) {
                return System.currentTimeMillis() >= endTime;
            }

            @Override
            public String toString() {
                return String.format("wait for %d seconds", seconds);
            }
        };
    }
}
