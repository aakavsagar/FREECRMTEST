package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage{
	WebDriver driver;
	
	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(name = "title")
	WebElement title;
	
	@FindBy(id = "first_name")
	WebElement firstName;
	
	@FindBy(id = "surname")
	WebElement lastName;
	
	@FindBy(name = "client_lookup")
	WebElement company;
	
	@FindBy(xpath = "//input[@type='submit'and @ value='Save']")
	WebElement saveButton;
	
	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getCompany() {
		return company;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getTitle() {
		return title;
	}

	public WebElement getContactsLabel() {
		return contactsLabel;
	}
	
	public void selectContactsByName(String name) {
		driver.findElement(By.xpath("//td/a[contains(text(),'"+name+"')]//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
	}

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
}
