/**************************************************************
Project Name			:	Test Automation Sample
Package Name			:	com.kony.appiumtests.Forms
Class Name				:	Login
Purpose of the Class	:	To maintain the repository for the locators
 **************************************************************/

package com.kony.appiumtests.forms;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.remote.HideKeyboardStrategy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class FrmLogin extends BaseForm {

	/**
	 * The page structure is being used within this test in order to separate
	 * the page actions from the tests
	 * 
	 * Locators are saved with the help of @FindBy annotation, and can be used
	 * in the corresponding tests by extending the FrmHome class.
	 * 
	 * LoginIn() method is used to Login into the application. we can pass the
	 * user name, and password.
	 */

	public FrmLogin(RemoteWebDriver driver) {
		super(driver); // super() is used to invoke immediate parent class
						// constructor.
	}

	// Locators_PreLogin Screen
	/**
	 * @FindBy is just an alternate way of finding elements. It is better used
	 *         to support the PageObject pattern.
	 */
	@iOSFindBy(id="btnSignIn")
	@AndroidFindBy(className = "android.widget.Button")
	private WebElement lbl_SignIn;
	
	@iOSFindBy(id = "btnGoToFrm1")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Go to frm1']")
	public WebElement lbl_frm1;
	
	@iOSFindBy(id = "btnGoToFrm2")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Go to frm2']")
	public WebElement lbl_frm2;
	
	@iOSFindBy(id = "btnGoToFrm3")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Go to frm3']")
	public WebElement lbl_frm3;
	
	@iOSFindBy(id = "btnGoToFrm4")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Go to frm4']")
	public WebElement lbl_frm4;
	
	@iOSFindBy(id = "btnGoToFrm5")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Go to frm5']")
	public WebElement lbl_frm5;
	
	@iOSFindBy(id = "btnGoToFrmLogout")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Go to frmLogout']")
	public WebElement lbl_frmLogout;

	private MobileElement username;

	private MobileElement pass_word;

	private void initiaizeElements(){
		System.out.println("textBoxClass for the platform .."+platformName+".. is ... "+textBoxClass);
		try {
			Thread.sleep(2000);
			List<WebElement> textBoxList = driver.findElements(By.className(textBoxClass));
			this.username = (MobileElement) textBoxList.get(0);
			this.pass_word = (MobileElement) textBoxList.get(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@iOSFindBy(id="btnSignIn")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Login']")
	public WebElement btnLogin;

	public void loginIn(String userName, String password) throws InterruptedException {
		if(isDisplayed()){
		this.initiaizeElements();
		this.username.setValue(userName);
		//Thread.sleep(10000);
		if ("MAC".equalsIgnoreCase(platformName)) {
			iosdriver.getKeyboard().sendKeys("\n");
		} else {
			androiddriver.hideKeyboard();
		}
		this.pass_word.setValue(password);
		//Thread.sleep(10000);
		if ("MAC".equalsIgnoreCase(platformName)) {
			iosdriver.getKeyboard().sendKeys("\n");
		} else {
			androiddriver.hideKeyboard();
		}
		this.btnLogin.click();
		}
	}

	/**
	 * isDisplayed() is boolean method i.e, it returns true or false. Basically
	 * this method is used to find whether the element is being displayed.
	 */
	public boolean isDisplayed() {
		return (this.lbl_SignIn.isDisplayed() && this.btnLogin.isDisplayed());
	}
}
