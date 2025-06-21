package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
    
	 WebDriver driver;
     
	 @FindBy(xpath = "//button[contains(text(),'Checkout')]")
	 WebElement checkoutEle;
     
     @FindBy(css=".cartSection h3")
 	private List<WebElement> cartProducts;
     
     public CartPage(WebDriver driver) {
 		super(driver);
 		this.driver = driver;
 		PageFactory.initElements(driver, this);
 	}
     
     public Boolean VerifyProductDisplay(String productName)
     {
    	 Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    	 return match;
     }
     
     public CheckoutPage goToCheckout() {
    	    //waitForElementToAppear(By.xpath("//button[contains(text(),'Checkout')]"));
    	    checkoutEle.click();
    	    return new CheckoutPage(driver);
    	}
           
           

}
