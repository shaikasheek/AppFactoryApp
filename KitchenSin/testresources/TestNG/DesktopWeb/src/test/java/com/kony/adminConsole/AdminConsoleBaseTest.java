package com.kony.adminConsole;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.kony.adminConsole.dbSetup.DbConnection;

import test.common.AppBaseTest;
import test.common.AppElement;
import test.common.SgConfiguration;

public class AdminConsoleBaseTest extends AppBaseTest {
	SgConfiguration sg = SgConfiguration.getInstance();

	@BeforeSuite
	public void beforeSuite() throws Exception {
		//super.beforeSuite();
		super.beforeSuite(sg.getKeyValue("appname"), sg.getKeyValue("apppackage"),
                        sg.getKeyValue("launchactivity"),sg.getKeyValue("bundle_id"));
		try {
			//DbConnection dbInstance = new DbConnection();
			//dbInstance.resetData();
		} catch (Exception e) {
			System.out.println("Error occurred while reset data");
			e.printStackTrace();
		}
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		//super.beforeClass(sg.getKeyValue("appname"), sg.getKeyValue("apppackage"), sg.getKeyValue("launchactivity"),
			//	sg.getKeyValue("bundle_id"));
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Object[] inputParamsOfTestMethod, Method method) throws Exception {
		super.beforeMethod(method);

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		super.afterMethod(result);
		
		if(result.getName().equals("resetPasswordAfterLogin")) {
			//new DbConnection().resetPassword();
		}
		
		if(result.getStatus() == ITestResult.FAILURE) {
			super.relaunchApp();

		}
	}

}
