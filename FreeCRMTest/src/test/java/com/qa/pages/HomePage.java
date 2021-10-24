package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	@FindBy(xpath = "//td[contains(text(),'User: Gayatri das')]")
	@CacheLookup
	WebElement userNameLabel;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;

	public WebElement getNewContactLink() {
		return newContactLink;
	}

	public WebElement getTasksLink() {
		return tasksLink;
	}

	public WebElement getDealsLink() {
		return dealsLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getUserNameLabel() {
		return userNameLabel;
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/*
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactsPage(driver);
	}
	
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage(driver);
	}
	
	public TasksPage clickOnTasksLink() {
		dealsLink.click();
		return new TasksPage(driver);
	}

	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed();
	}*/
}
