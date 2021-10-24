package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{
	WebDriver driver;
	
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(linkText = "Sign Up")
	WebElement signUpLink;
	
	@FindBy(xpath = "//body/div[2]/div[1]/div[1]/a[1]/img[1]")
	WebElement crmLogo;
	
	
	public WebElement getUsername() {
		return username;
	}


	public WebElement getPassword() {
		return password;
	}


	public WebElement getLoginBtn() {
		return loginBtn;
	}


	public WebElement getSignUpLink() {
		return signUpLink;
	}


	public WebElement getCrmLogo() {
		return crmLogo;
	}


	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*//Actions
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage Login(String userName, String pword){
		username.sendKeys(userName);
		password.sendKeys(pword);
		loginBtn.click();
		
		return new HomePage(driver);
	}
	*/
}
