package com.planittesting.Jupiter.pageObjects.contact;

import ch.qos.logback.classic.Logger;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPageActions extends UIInteractions {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ContactPageActions.class);

    @FindBy(css = "body > div.container-fluid > div > form")
    private WebElementFacade contactForm;
    @FindBy(id="forename")
    private WebElementFacade forename;
    @FindBy(id="email")
    private WebElementFacade email;
    @FindBy(id="message")
    private WebElementFacade messageTxtBox;

    @FindBy(id="forename-err")
    private WebElementFacade forenameErr;
    @FindBy(id="email-err")
    private WebElementFacade emailErr;
    @FindBy(id="message-err")
    private WebElementFacade messageTxtErr;

    @FindBy(xpath = "//div[@class='form-actions']/a")
    private WebElementFacade submitBtn;

    @FindBy(xpath = "/html/body/div[2]/div/div")
    private WebElementFacade submitMsg;


    @Step("Click Submit Button")
    public void clickSubmit() {
        element(submitBtn).waitUntilClickable().click();
    }

    @Step("Enter Values into the Contact Form")
    public void enterValues(String name, String emailId, String message){

       forename.waitUntilClickable().sendKeys(name);
       email.waitUntilClickable().sendKeys(emailId);
       messageTxtBox.waitUntilClickable().sendKeys(message);
       LOGGER.info("Contact Form verification is successful");

    }

    @Step("Validate successful submission message")
    public void checkSubmitMessage() {
        LOGGER.info("Validate successful submission message");
        waitForCondition().until(
                ExpectedConditions.textToBePresentInElement(submitMsg, "we appreciate your feedback")
        );
    }


    @Step("Verify Error Message on Missing Mandatory fields")
    public boolean verifyErrorMessage(){
        return (checkErrorOnField(forenameErr) || checkErrorOnField(emailErr) || checkErrorOnField(messageTxtErr));
    }

    public boolean checkErrorOnField(WebElementFacade elementFacade ){
         return elementFacade.isCurrentlyEnabled();
    }

}
