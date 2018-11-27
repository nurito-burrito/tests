package com.whitebreadtest.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.whitebreadtest.pages.homeScreen;
import com.whitebreadtest.pages.productScreen;
import com.whitebreadtest.core.LaunchAppiumProgramatically;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Main {

	public AndroidDriver<WebElement> driver;

	public homeScreen hScreen;
	public productScreen pScreen;

	public AndroidDriver<WebElement> getCapabilities() throws MalformedURLException {

		AndroidDriver<WebElement> driver = null;
		DesiredCapabilities d = new DesiredCapabilities();
		d.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2 XL");
		d.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		d.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		d.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
		d.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.support.android.designlibdemo");
		d.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.support.android.designlibdemo.MainActivity");
		d.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		d.setCapability(MobileCapabilityType.APP, "C:\\Users\\Nurseda\\Desktop\\com.cheesesquare.android.apk");
		d.setCapability("noReset", true);
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:" + "4723" + "/wd/hub"), d);
		implicitWait(5);
		return driver;
	}

	@BeforeSuite
	public void startAppiumServer() {
		LaunchAppiumProgramatically.stopAppium();
		LaunchAppiumProgramatically.startAppium();
	}
	
	

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);

	}

	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "up");
		js.executeScript("mobile: scroll", scrollObject);

	}

	public void implicitWait(int sec) {
		try {
			driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void stop() {
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
		LaunchAppiumProgramatically.stopAppium();
	}
	

}
