package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {
    	String ProductName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingpage = new LandingPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("saurabhbhange1010@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("9359504143");
        driver.findElement(By.id("login")).click();
        
        WebDriverWait Wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        System.out.println(products.size());
       
      WebElement prod =  products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
      prod.findElement(By.xpath("(//button[@class='btn w-10 rounded'])")).click();
      
      
       Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
       Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
       driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
       
       List<WebElement> CartProducts = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
       Boolean match = CartProducts.stream().anyMatch(CartProduct -> CartProduct.getText().equalsIgnoreCase(ProductName));
       Assert.assertTrue(match);
       driver.findElement(By.xpath("//button[text()='Checkout']")).click();
       driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
       driver.findElement(By.xpath("//button[@type='button'][2]")).click();
       driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
       
       String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
       Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
       


      
      
        
       
    

	
	}
}

