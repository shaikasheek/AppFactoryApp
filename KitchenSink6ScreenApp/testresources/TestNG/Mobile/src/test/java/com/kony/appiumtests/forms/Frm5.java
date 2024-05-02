/**************************************************************
Project Name			:	Test Automation Sample
Package Name			:	com.kony.appiumtests.Forms
Class Name				:	Frmsix
Purpose of the Class	:	To maintain the repository for the locators
 **************************************************************/


package com.kony.appiumtests.forms;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * extends is the keyword used to inherit the properties of a class.
 * In this we are using extends to inherit the properties from BaseForm class.
 */
public class Frm5 extends BaseForm
{
	public Frm5(RemoteWebDriver driver)
	{
		super(driver); //super() is used to invoke immediate parent class constructor.
	}

	//Locators used in Form Six
	@iOSFindBy(id = "lblPickerView")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Picker View']")
	public WebElement lbl_pickerview;


	@AndroidFindBy(xpath = "//android.widget.TextView[@text='2010']")
	public WebElement select_year;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mar']")
	public WebElement select_month;

	@iOSFindBy(id = "lblMoreWidgets")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Click for more widgets']")
	public WebElement navigation_link;

	
	//@iOSFindBy(xpath="//UIApplication[1]//UIAWindow[2]//UIAScrollView[1]//UIAElement[1]//UIAPicker[1]//UIAPickerWheel[1]")
	@iOSFindBy(className = "UIAPicker")
	public WebElement sel_year;
	
	@iOSFindBy(className = "UIAPicker")
	public WebElement sel_month;
	

	//Method that performs operations in Form Six
	public void selectionDate() throws InterruptedException
	{
		if ("MAC".equalsIgnoreCase(platformName)) {	
			Thread.sleep(3000);
		        sel_year.click();
			sel_month.click();
			navigation_link.click();
		} else {
			this.select_year.click();
			this.select_month.click();
			this.navigation_link.click();
		}

	}

	/**
	 * isDisplayed() is boolean method i.e, it returns true or false.
	 * Basically this method is used to find whether the element is being displayed.
	 */
	public boolean isDisplayed()
	{
		return (lbl_pickerview.isDisplayed());
	}
}


