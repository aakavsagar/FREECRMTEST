package com.qa.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase{
	
	@Test
	public void homePageTest() throws InterruptedException {
		//verify Home page title
		String homePageTitle = "CRMPRO";
		Assert.assertEquals(driver.getTitle(), homePageTitle, "Home Page title is not matched so it is not on home page");
		
		//verify User Name label
		switchToFrame();
		Assert.assertTrue(homePageOR.getUserNameLabel().isDisplayed(),"User Name Label is not showed in Home Page");
		
		//verify contacts link
		homePageOR.getContactsLink().click();
		
		Thread.sleep(3000);
	}
}
