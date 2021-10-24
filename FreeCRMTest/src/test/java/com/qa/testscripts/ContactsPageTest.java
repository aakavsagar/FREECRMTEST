package com.qa.testscripts;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.utility.ExcelUtility;

public class ContactsPageTest extends TestBase{
	
	@Test(dataProvider = "getData")
	public void contactsPageTest(String title, String firstName, String lastName, String company) throws InterruptedException {
		//to open contacts link page
		switchToFrame();
		homePageOR.getContactsLink().click();
		
		//verify contacts label
		Assert.assertTrue(contactsPageOR.getContactsLabel().isDisplayed(),"Contacts label is missing on the page");
		
		// select multiple contacts
		contactsPageOR.selectContactsByName("David Cris");
		contactsPageOR.selectContactsByName("David Billa");
		
		// open new contact
		act.moveToElement(homePageOR.getContactsLink()).build().perform();
		act.moveToElement(homePageOR.getNewContactLink()).click().build().perform();
		
		createNewContact(title, firstName, lastName, company);
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
	}
	
	public static void createNewContact(String title, String firstName, String lastName, String company) {
		Select select = new Select(contactsPageOR.getTitle());
		select.selectByVisibleText(title);
		contactsPageOR.getFirstName().sendKeys(firstName);
		contactsPageOR.getLastName().sendKeys(lastName);
		contactsPageOR.getCompany().sendKeys(company);
		contactsPageOR.getSaveButton().click();
	}
	
	@DataProvider
	public String[][] getData() throws Throwable {
		int rows = 0;
		int cols = 0;
		String path = "D:\\Automation Testing\\FreeCRMTest\\src\\test\\java\\com\\qa\\testdata\\FreeCrmTestData.xlsx";
		String sheetName = "contacts";
		ExcelUtility eu = new ExcelUtility(path, sheetName);

		rows = eu.getRowCount();
		String[][] dataObj = new String[rows][];
		for (int i = 1; i <= eu.getRowCount(); i++) {
			cols = eu.getColCount(i);
			dataObj[i - 1] = new String[cols];

			for (int j = 0; j < cols; j++) {
				dataObj[i - 1][j] = eu.getCellData(i, j);
			}
		}
		eu.finalize();
		return dataObj;
	}
}
