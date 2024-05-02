package com.kony.appiumtests.tests;

import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {
    public static String awsPlatformName;
    public static AndroidDriver<WebElement> androiddriver;
    public static IOSDriver<WebElement> iosdriver;
    public static RemoteWebDriver driver;

    @BeforeSuite
    public void beforeSuite() throws Exception {
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        if(System.getenv("DEVICEFARM_DEVICE_PLATFORM_NAME") != null) {
            
            /*Driver initialization for Custom Test Environment*/
            if ("Android".equalsIgnoreCase(System.getenv("DEVICEFARM_DEVICE_PLATFORM_NAME"))) {
                androiddriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                driver = androiddriver;
            } else {
                capabilities.setCapability("automationName", "XCUITest");
                iosdriver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                driver = iosdriver;

            }

        } else {
            
            if (driver == null) {
                /*Driver initialization for Standard Test Environment*/
                boolean andDriverState = false;
                System.out.println("Initializing Appium driver session.............!");
                if (androiddriver == null) {
                    andDriverState = isAndDriverInitSuccess(capabilities);
                }
                
                /*getPlatform() is used to know the platform on which the app is running
                 *LINUX for Android and MAC for iOS for AWS Device farm*/
                
                if(andDriverState) {
                    System.out.println("Assigning the created Android driver............!");
                    driver = androiddriver;
                } else {
                    if (driver != null) {
                        driver.quit();
                        driver = null;
                    }
                    if (androiddriver != null) {
                        androiddriver.quit();
                        androiddriver = null;
                    }
                    if (iosdriver != null) {
                        iosdriver.quit();
                        iosdriver = null;
                    }

                    System.out.println("Inside platform IOS............!");
                    iosdriver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                    driver = iosdriver;
                }
            }
        }
    }
    
    public boolean isAndDriverInitSuccess(DesiredCapabilities capabilities) {
        boolean driverState = false;
        try {
            System.out.println("Trying with Android driver session..........!");
            androiddriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            if (androiddriver != null && "LINUX".equalsIgnoreCase(androiddriver.getCapabilities().getPlatform().toString())){
                driverState = true;
            }
        } catch(Exception e) {
            System.out.println("Couldn't create the driver session with Android platform will try with iOS..............!");
        }
        return driverState;
    }

    @AfterSuite
    public void tearDownAppium() {
        if (driver != null)
            driver.quit();
        if (androiddriver != null)
            androiddriver.quit();
        if (iosdriver != null)
            iosdriver.quit();
    }

    public static RemoteWebDriver getDriver() {
        return driver;
    }
}
