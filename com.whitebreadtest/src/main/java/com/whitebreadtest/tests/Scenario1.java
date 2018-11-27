package com.whitebreadtest.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.whitebreadtest.core.Main;
import com.whitebreadtest.pages.homeScreen;

public class Scenario1 extends Main {

	@BeforeClass
	public void startTestingScenario1() {

		hScreen = new homeScreen(driver);
		
	}
	

	@Test(priority = 1)
	public void assertCategories() {
		
		Assert.assertTrue(hScreen.category1.isDisplayed());
	
		Assert.assertTrue(hScreen.category2.isDisplayed());
	
		Assert.assertTrue(hScreen.category3.isDisplayed());
	}

	@AfterClass
	public void stopAppium() {
		driver.quit();
	}

}
