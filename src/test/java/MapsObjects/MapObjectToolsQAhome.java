package MapsObjects;

import org.openqa.selenium.By;

import ClasesBase.ClaseBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MapObjectToolsQAhome extends ClaseBase
{

	public MapObjectToolsQAhome(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
	}

	// MAPEO DE ELEMENTOS
	
//	-- > TOOLS QA - HOME
	protected By btnAlertsFrameWindows = By.xpath("//android.view.View/android.widget.TextView[@text='Alerts, Frame & Windows']"); // BOTON DE ALERTS FRAME Y WINDOWS   
	protected By btnWidgets = By.xpath("//android.view.View/android.widget.TextView[@text='Widgets']"); // BOTON DE WIDGETS
}
