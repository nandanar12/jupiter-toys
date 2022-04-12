package testScenarios.shoppingScenarios;

import com.planittesting.Jupiter.pageObjects.cart.CartPageActions;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import com.planittesting.Jupiter.pageObjects.home.LoadHomePage;
import com.planittesting.Jupiter.pageObjects.navigation.NavigationBarActions;
import com.planittesting.Jupiter.pageObjects.shop.ShopPageActions;
import com.planittesting.Jupiter.utilities.FileHandler;
import com.planittesting.Jupiter.utilities.testdata.TestDataHelper;

import java.io.IOException;
import java.util.List;

@ExtendWith(SerenityJUnit5Extension.class)
class ShopItemsAndCheckTotals {
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
    ShopPageActions shopPageActions;
    CartPageActions cartPageActions;
    int cartCount = 0;

    @BeforeEach
    public void openApplication() {
        landingPage.toTheHomePage();
    }

    @Test
    @DisplayName("TC03 - Check Cart totals of product selected from the Test data input")
    void selectItemsFromShoppingListAndValidateCart() throws IOException {

        navigationBarActions.clickShop();

        addShoppingItemsIntoTheCart();

        navigationBarActions.clickCart();

        //cartPage Validation of Price for each Product.
        cartPageActions.verifyPriceOfEachProduct(ShopPageActions.productPrice);

        //cartPage Validation of Subtotal for each product
        cartPageActions.verifySubTotalOfProduct(ShopPageActions.productQty);

        //cartPAge Validation of Total = sum of all subtotals
        cartPageActions.verifyTotalOfAllProducts();


    }

    private void addShoppingItemsIntoTheCart() throws IOException {
        TestDataHelper testDataHelper = new TestDataHelper(FileHandler.readFileString("src/test/resources/testData/shoppingItems.csv"));
        final List<String[]> shoppingList = testDataHelper.getShoppingListItems();
        for (int i=1; i<shoppingList.size(); i++)
        {
            shopPageActions.checkProductAndAddToCart(shoppingList.get(i)[0], Integer.parseInt(shoppingList.get(i)[1]));
            cartCount = cartCount + Integer.parseInt(shoppingList.get(i)[1]);
            navigationBarActions.checkCartItemHasBeenUpdated(String.valueOf(cartCount));
        }
    }
}
