package testScenarios.contactScenarios;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import com.planittesting.Jupiter.pageObjects.home.LoadHomePage;
import com.planittesting.Jupiter.pageObjects.navigation.NavigationBarActions;
import com.planittesting.Jupiter.pageObjects.contact.ContactPageActions;
import org.openqa.selenium.WebDriver;
import com.planittesting.Jupiter.utilities.FileHandler;
import com.planittesting.Jupiter.utilities.testdata.TestDataHelper;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.RepeatedTest.SHORT_DISPLAY_NAME;

@ExtendWith(SerenityJUnit5Extension.class)
public class ContactFormSuccessfulSubmit {
    /**
     * Define the Web driver instance to be used for these tests
     */
    @Managed()
    WebDriver driver;

    /**
     * Actions related to search. This is a UIInteraction class, so it will be instantiated automatically by Serenity.
     */
    LoadHomePage landingPage;
    NavigationBarActions navigationBarActions;
    ContactPageActions contactPageActions;

    @BeforeEach
    public void openApplication() {
        landingPage.toTheHomePage();
    }


    @DisplayName("TC02 - Validate successful submission message")
    @RepeatedTest(value=5, name= SHORT_DISPLAY_NAME)
    void contactPageSubmitValidation() throws IOException {

        TestDataHelper testDataHelper = new TestDataHelper(FileHandler.readFileString("src/test/resources/testData/contactdata.csv"));
        final List<String[]> shoppingList = testDataHelper.getShoppingListItems();
        navigationBarActions.clickContact();
        contactPageActions.enterValues(shoppingList.get(1)[0], shoppingList.get(1)[1], shoppingList.get(1)[2]);
        Assertions.assertFalse(contactPageActions.verifyErrorMessage());
        contactPageActions.clickSubmit();
        contactPageActions.checkSubmitMessage();
        navigationBarActions.clickHome();

    }
}
