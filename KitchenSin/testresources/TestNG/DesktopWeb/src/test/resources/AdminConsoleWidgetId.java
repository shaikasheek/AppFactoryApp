package test.AdminConsole;

import java.io.IOException;

import test.common.WidgetID;

public class AdminConsoleWidgetId {
	static WidgetID widgetIds;
	
	public AdminConsoleWidgetId() throws IOException{
		widgetIds = new WidgetID("src/test/resources/DesktopWidgetids.properties");
	}
	
	public static String getWidgetId(String key) throws Exception{
		if(widgetIds==null)
			widgetIds = new WidgetID("src/test/resources/DesktopWidgetids.properties");
		return widgetIds.getWidgetId(key);
	}

}
