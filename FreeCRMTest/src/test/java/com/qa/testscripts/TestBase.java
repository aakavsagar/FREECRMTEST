package com.qa.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.qa.pages.ContactsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.utility.TestUtil;
import com.qa.utility.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public Properties prop;
	public static LoginPage loginPageOR;
	public static HomePage homePageOR;
	public static ContactsPage contactsPageOR;
	
	public static Actions act;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	// @Parameteprop({ "AmazonURL", "Browser" })
	// public void setUp(String URL, String browserName) throws InterruptedException
	// {
	@BeforeClass
	public void setUp() throws InterruptedException {
		try {
			FileInputStream fis = new FileInputStream(
					"D:\\Automation Testing\\FreeCRMTest\\src\\test\\java\\com\\qa\\config\\config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String browserName = prop.getProperty("browser");
		String URL = prop.getProperty("url");

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			// Reporter.log("<br>Chrome Browser Execution<br>");
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			// Reporter.log("<br>FireFox Browser Execution<br>");
		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			// Reporter.log("<br>Edge Browser Execution<br>");
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListenerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.get(URL);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		loginPageOR = new LoginPage(driver);
		homePageOR = new HomePage(driver);
		contactsPageOR = new ContactsPage(driver);
		
		act = new Actions(driver);

		// validate title
		String expectedTitle = "CRMPRO - CRM software for customer relationship management, sales, and support.";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Login Page is not opened with expected Title");
		// validate crm logo
		Assert.assertTrue(loginPageOR.getCrmLogo().isDisplayed());

		loginPageOR.getUsername().sendKeys(prop.getProperty("username"));
		loginPageOR.getPassword().sendKeys(prop.getProperty("password"));
		loginPageOR.getLoginBtn().click();
		Thread.sleep(3000);
	}
	
	public static void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		FileUtils.copyFile(srcFile, new File(currentDir+"/screenshots/"+System.currentTimeMillis()+".png"));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
