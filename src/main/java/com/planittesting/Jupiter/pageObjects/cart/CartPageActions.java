package com.planittesting.Jupiter.pageObjects.cart;


import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CartPageActions extends UIInteractions {

    @FindBy(xpath ="/html/body/div[2]/div/form/table/tbody/tr/td")
    List<WebElementFacade> allCellItems;

    @FindAll(@FindBy(className = "cart-item"))
    List<WebElementFacade> cartRowItems;

    @FindBy(css = ".total.ng-binding")
    WebElementFacade totalCost;

    @Step("cartPage Validation of Price for each Product")
    public void verifyPriceOfEachProduct(HashMap<String, String> productPrice){

        for(int i=0; allCellItems.size()>i ; )
        {
            String nameValue = allCellItems.get(i).getTextValue().toString();
            String priceValue = allCellItems.get(i+1).getTextValue().toString();
            assertThat(priceValue, is(productPrice.get(nameValue)));
            i=i+5;
        }

    }

    @Step("cartPage Validation of Subtotal for each product")
    public void verifySubTotalOfProduct(HashMap<String, Integer> productQty){

        for(int i=0; allCellItems.size()>i ; )
        {
            Float priceValue = Float.parseFloat(allCellItems.get(i+1).getTextValue().substring(1));
            Integer prdQty = productQty.get(allCellItems.get(i).getTextValue().toString());
            String subTotal = allCellItems.get(i+3).getTextValue().substring(1);
            assertThat(subTotal, is(new DecimalFormat("0.00").format(priceValue*prdQty).toString()));
            i=i+5;
        }

    }

    @Step("cartPage Validation of Total Price")
    public void verifyTotalOfAllProducts() {
        Float subTotal = 0.0f;
        for(int i=0; allCellItems.size()>i ; )
        {
            subTotal = Float.parseFloat(allCellItems.get(i+3).getTextValue().substring(1)) + subTotal;
            i=i+5;
        }
        assertThat(totalCost.getTextValue().toString(), is("Total: "+ new DecimalFormat("#.##").format(subTotal).toString()));
    }
}
