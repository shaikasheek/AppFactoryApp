package com.kony.adminConsole;

import java.io.IOException;

import test.common.AppResouceBundle;

public class AdminConsoleNames {
	
	static AppResouceBundle widgetNames;
	
	public AdminConsoleNames() throws IOException{
		widgetNames = new AppResouceBundle("src/test/java/com/kony/adminConsole/DesktopWidgetNames.properties");
	}
	
	public static String getWidgetName(String key) throws Exception{
		if(widgetNames==null)
			widgetNames = new AppResouceBundle("src/test/java/com/kony/adminConsole/DesktopWidgetNames.properties");
		return widgetNames.getProperty(key);
	}
}
