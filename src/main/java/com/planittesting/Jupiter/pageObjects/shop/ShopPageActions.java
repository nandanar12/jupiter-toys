package com.planittesting.Jupiter.pageObjects.shop;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;
import java.util.List;


public class ShopPageActions extends UIInteractions {


    public static HashMap<String, String> productPrice = new HashMap<>();
    public static HashMap<String, Integer> productQty = new HashMap<>();

    SoftAssertions softAssertion = new SoftAssertions();
    @FindAll(@FindBy(className = "product-title"))
    List<WebElementFacade> itemList;
    @FindAll(@FindBy(className = "product-price"))
    List<WebElementFacade> itemPrice;
    @FindAll(@FindBy(css = ".btn.btn-success"))
    List<WebElementFacade> itemBuy;


    @Step("Find Items in the catalog")
    public void checkProductAndAddToCart(String product, int qty) {
        /* Check for Product in the Shopping List and if available add into the Cart
         * Else have soft assert failure to capture missing items
         */
        for (int i=0; itemList.size()>i; i++)
        {
            if (itemList.get(i).getTextValue().contentEquals(product))
            {
                productQty.put(itemList.get(i).getTextValue(), qty);
                productPrice.put(itemList.get(i).getTextValue(),itemPrice.get(i).getTextValue());
                while(qty>0){
                    itemBuy.get(i).click();
                    qty--;
                }
                break;
            }
        }
    }

}
