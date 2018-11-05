package checkoutTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class mitsubishiCheckoutTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver;
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		System.out.println("Launching firefox browser");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://checkout.pod-point.com");
		driver.findElement(By.className("cookie__btn")).click();
		
		
		
		//2)Select Mitsubishi and Outlander in the Select Your Car fields
		Select selectMake = new Select(driver.findElement(By.id("vehicleBrand")));
		selectMake.selectByVisibleText("Mitsubishi");

		Thread.sleep(2000);

		Select selectModel = new Select(driver.findElement(By.id("vehicleId")));
		selectModel.selectByValue("mitsubishiOutlander");
		
		
		//3)Check the box ‘I am NOT eligible to claim the dealership discount as I did not come through a dedicated dealership’
		driver.findElement(By.id("optOut")).click();
		Thread.sleep(2000);
		
		//4)Under Select your connection type, select the ‘Universal Socket’ option
		Actions actionConnectionType = new Actions(driver);
		actionConnectionType.moveToElement(driver.findElement(By.className("selection"))).click().perform();
		
		Thread.sleep(3000);
			
		
		//5)Under Select your power rating, check the prices for the 7kw unit displayed 
		//driver.findElement(By.id("model-S7")).click();
		
		//Actions actionPowerRating = new Actions(driver);
		//ActionPowerRating.moveToElement(driver.findElement(By.cssSelector("#model-S7"))).click().perform();
		//Thread.sleep(3000);
		
		 
		 Thread.sleep(5000);
		 driver.close(); 
		 driver.quit();
		 
	}
}
