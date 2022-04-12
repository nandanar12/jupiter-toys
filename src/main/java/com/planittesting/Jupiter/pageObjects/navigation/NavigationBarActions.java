package com.planittesting.Jupiter.pageObjects.navigation;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationBarActions extends UIInteractions {
    //Element Locator for the Homepage

    @FindBy(id="nav-contact")
    private WebElementFacade contactBtn;

    @FindBy(id="nav-shop")
    private WebElementFacade shopBtn;

    @FindBy(id="nav-login")
    private WebElementFacade loginBtn;

    @FindBy(id="nav-cart")
    private WebElementFacade cartBtn;

    @FindBy(className = "cart-count")
    public WebElementFacade cartCount;

    @FindBy(id="nav-home")
    private WebElementFacade homeBtn;

    @Step("Click Contact Button")
    public void clickContact()  {
        element(contactBtn).waitUntilClickable().click();
        }

    @Step("Click Home Button")
    public void clickHome() {
        element(homeBtn).waitUntilClickable().click();
    }

    @Step("Click Shop Button")
    public void clickShop() {
        element(shopBtn).waitUntilClickable().click();
    }

    public void clickCart() {
        element(cartBtn).waitUntilClickable().click();
    }

    public void checkCartItemHasBeenUpdated(String totalItems) {
        Serenity.reportThat("Failure to add the Item into the cart",
                () -> assertThat(cartCount.getTextValue().equals(totalItems) ).isEqualTo(true)
        );
    }
}
