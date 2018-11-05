package checkoutTestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class mitsubishiCheckoutTestNg {

	public String baseUrl = "https://checkout.pod-point.com";
	String driverPath = "C:\\geckodriver.exe";
	public WebDriver driver;

	@BeforeTest
	public void startBrowser() throws InterruptedException {

		System.out.println("Launching firefox browser");
		System.setProperty("webdriver.gecko.driver", driverPath);

		driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.className("cookie__btn")).click();
	}

	@Test(priority = 1)
	public void verifyHomepageTitle() throws InterruptedException {

		String expectedTitle = "POD Point";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("Landed on https://checkout.pod-point.com");

	}

	@Test(priority = 2)
	public void selectCarAndModel() throws InterruptedException {

		Select selectMake = new Select(driver.findElement(By.id("vehicleBrand")));
		selectMake.selectByVisibleText("Mitsubishi");

		Thread.sleep(2000);

		Select selectModel = new Select(driver.findElement(By.id("vehicleId")));
		selectModel.selectByValue("mitsubishiOutlander");

	}

	@Test(priority = 3)
	public void checkTheBox() throws InterruptedException {

		driver.findElement(By.id("optOut")).click();
		Thread.sleep(2000);

	}

	@Test(priority = 4)

	public void selectConnectionType() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('variant-universal').click()");

		Thread.sleep(2000);

	}

	@Test(priority = 5)
	public void selectPowerRating() throws InterruptedException {

		driver.findElement(
				By.xpath("/html/body/div[1]/article/main/div[4]/section/div/div/div[2]/div/label/div/p[1]/span[1]"))
				.isDisplayed();
		Assert.assertEquals("£859.00",
				driver.findElement(By.xpath(
						"/html/body/div[1]/article/main/div[4]/section/div/div/div[2]/div/label/div/p[1]/span[1]"))
						.getText());
		System.out.println("The £859 full price for the 7kw unit is matching");

		driver.findElement(
				By.xpath("/html/body/div[1]/article/main/div[4]/section/div/div/div[2]/div/label/div/p[2]/span[1]"))
				.isDisplayed();
		Assert.assertEquals("£359.00",
				driver.findElement(By.xpath(
						"/html/body/div[1]/article/main/div[4]/section/div/div/div[2]/div/label/div/p[2]/span[1]"))
						.getText());
		System.out.println("The £359 with OLEV Grant price for the 7kw unit is matching");

		Thread.sleep(4000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('model-S7').click()");

	}

	@Test(priority = 6)
	public void checkCompatibleExtras() throws InterruptedException {

		boolean chargingCablePresence = driver
				.findElement(By.xpath("/html/body/div[1]/article/main/div[5]/section/div/div/div/div[1]/div/label/h4"))
				.isDisplayed();
		if (chargingCablePresence == true) {
			System.out.println("Charging Cable is available to purchase with this connection type");
		} else {
			System.out.println("Charging Cable can't be purchased with this connection type");
		}

		boolean keyLockPresence = driver
				.findElement(By.xpath("/html/body/div[1]/article/main/div[5]/section/div/div/div/div[2]/div/label/h4"))
				.isDisplayed();
		if (keyLockPresence == true) {
			System.out.println("Key lock is available to purchase with this connection type");
		} else {
			System.out.println("Key lock can't be purchased with this connection type");
		}

		boolean extendedWarrantyPresence = driver
				.findElement(By.xpath("/html/body/div[1]/article/main/div[5]/section/div/div/div/div[3]/div/label/h4"))
				.isDisplayed();
		if (extendedWarrantyPresence == true) {
			System.out.println("Extended warranty is available to purchase with this connection type");
		} else {
			System.out.println("Extended warrant can't be purchased with this connection type");
		}

		/**
		 * Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/article/main/div[5]/section/div/div/div/div[1]/div/label/h4")).isDisplayed());
		 * Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/article/main/div[5]/section/div/div/div/div[2]/div/label/h4")).isDisplayed());
		 * Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/article/main/div[5]/section/div/div/div/div[3]/div/label/h4")).isDisplayed());
		 */
	}

	@Test(priority = 7)
	public void selectCompatibleExtras() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('chargingCableType1').click()");

		Thread.sleep(2000);

	}

	@Test(priority = 8)
	public void testTotalPrice() throws InterruptedException {

		driver.findElement(By.cssSelector(".p-b-none")).isDisplayed();
		Assert.assertEquals("£1,028.00", driver.findElement(By.cssSelector(".p-b-none")).getText());
		System.out.println("£859 + £169 = £1,028.00");

		Thread.sleep(10000);

	}

	@AfterTest
	public void terminateBrowser() {

		driver.manage().deleteAllCookies();
		driver.quit();
	}
}