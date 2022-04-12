package com.planittesting.Jupiter.pageObjects.home;

import ch.qos.logback.classic.Logger;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.support.FindBy;
import org.slf4j.LoggerFactory;
import com.planittesting.Jupiter.utilities.config.TestConfiguration;

/**
 * UIInteractionSteps let us define action classes which regroup related actions.
 * The @Step annotation is used to indicate that this action will appear as a step in the reports.
 */
public class LoadHomePage extends UIInteractions {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LoadHomePage.class);


    @FindBy(className = "hero-unit")
    WebElementFacade widgetLoad;

    @Step("Navigate to the home page")
    public void toTheHomePage() {
        openUrl(TestConfiguration.instance.getStringNotNull("applicationUrl"));
        element(widgetLoad).waitUntilPresent();
        LOGGER.info("Jupiter UI Launched Successfully");
    }

}
