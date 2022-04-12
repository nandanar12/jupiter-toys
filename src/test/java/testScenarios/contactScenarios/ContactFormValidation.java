package testScenarios.contactScenarios;

import com.planittesting.Jupiter.pageObjects.home.LoadHomePage;
import com.planittesting.Jupiter.pageObjects.navigation.NavigationBarActions;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.planittesting.Jupiter.pageObjects.contact.ContactPageActions;
import org.openqa.selenium.WebDriver;
import com.planittesting.Jupiter.utilities.FileHandler;
import com.planittesting.Jupiter.utilities.testdata.TestDataHelper;

import java.io.IOException;
import java.util.List;


@ExtendWith(SerenityJUnit5Extension.class)
class ContactFormValidation {

    /**
     * Define the Web driver instance to be used for these tests
     */
    @Managed()
    WebDriver driver;

    LoadHomePage landingPage;
    NavigationBarActions navigationBarActions;
    ContactPageActions contactPageActions;

    @BeforeEach
    public void openApplication() {
        landingPage.toTheHomePage();
    }

    /**
     * Test to validate the Error during submit upon missing mandatory fields
     */
    @DisplayName("TC01 - Validate successful Error during submit upon missing mandatory fields")
    @Test
    void contactPageFieldValidation() throws IOException {
        TestDataHelper testDataHelper = new TestDataHelper(FileHandler.readFileString("target/test-classes/testData/contactdata.csv"));
        final List<String[]> shoppingList = testDataHelper.getShoppingListItems();
        navigationBarActions.clickContact();
        contactPageActions.clickSubmit();
        Assert.assertTrue(contactPageActions.verifyErrorMessage());
        contactPageActions.enterValues(shoppingList.get(1)[0], shoppingList.get(1)[1], shoppingList.get(1)[2]);
        Assert.assertFalse(contactPageActions.verifyErrorMessage());
    }
}
