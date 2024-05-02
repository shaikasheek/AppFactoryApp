package com.kony.adminConsole.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kony.adminConsole.AdminConsoleBaseTest;
import com.kony.adminConsole.AdminConsoleNames;
import com.kony.adminConsole.AdminConsoleWidgetId;
import com.kony.adminConsole.forms.AppSpecificFunctions;
import com.kony.adminConsole.forms.FrmAccountSuspended;
import com.kony.adminConsole.forms.FrmChangePassword;
import com.kony.adminConsole.forms.FrmChangePasswordSuccess;
import com.kony.adminConsole.forms.FrmDashboardKA;
import com.kony.adminConsole.forms.FrmDownTime;
import com.kony.adminConsole.forms.FrmLoginKA;

import test.common.AppElement;
import test.common.SgConfiguration;
public class LoginModule4 {

//SgConfiguration sg = SgConfiguration.getInstance();

	@BeforeMethod
	public void setupBeforeTest() throws Exception {
		

	}


	@Test(description = "HeadlessChromeDriverTest")
	  public void HeadlessChromeDriverTest() throws IOException {
	  
	  System.setProperty("webdriver.chrome.driver", "/usr/local/chromedriver");
	  ChromeOptions chromeOptions = new ChromeOptions();
	  chromeOptions.addArguments("--headless");
	 // chromeOptions.addArguments("--no-sandbox");
	  // chromeOptions.setBinary("/usr/bin/my_project/node_modules/puppeteer/.local-chromium/linux-579032/chrome-linux/chrome");
		  chromeOptions.setBinary("/opt/google/chrome/chrome");
	  //chromeOptions.setBinary(sg.getKeyValue(browserPath));
	  //System.out.println("****************8"+sg.getKeyValue(browserPath));
	  WebDriver driver = new ChromeDriver(chromeOptions);
		driver.navigate().to("https://www.avenuecode.com/");
	  
	  String pageTitle = driver.getTitle();
	 // log.info("Page opened: {}", pageTitle);
	  
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  //copying the file into /screenshots directory
	  FileUtils.copyFile(scrFile, new File("output/screenshots/homepage.png"));
	  
	  Assert.assertTrue(pageTitle.contains("Avenue Code"));
	  System.out.println("Quitting driver");
	  driver.quit();
	  }
}
