package com.whitebreadtest.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.whitebreadtest.core.Main;
import com.whitebreadtest.pages.homeScreen;
import com.whitebreadtest.pages.productScreen;

public class Scenario2 extends Main {

	@BeforeClass
	public void launch() {

		hScreen = new homeScreen(driver);
		pScreen = new productScreen(driver);
	}

	@Test(priority = 1)
	public void assertSections() {

		hScreen.category3.click();

		scrollUp();

		Assert.assertTrue(pScreen.info.isDisplayed());

		Assert.assertTrue(pScreen.friends.isDisplayed());

		Assert.assertTrue(pScreen.related.isDisplayed());

	}

	@Test(priority = 2)
	public void tappingOnEllipsis() {

		// There are no radio button upon tapping on the ellipsis so this step is to
		// show that it's not there

		hScreen.ellipsis.click();
		implicitWait(5);
		hScreen.ellipsisSettings.click();
		implicitWait(5);
		pScreen.backButton.click();
		implicitWait(5);
	}

	@AfterClass
	public void closeApp() {
		driver.quit();
	}

}
