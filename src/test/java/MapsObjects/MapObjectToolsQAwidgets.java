package MapsObjects;

import org.openqa.selenium.By;

import ClasesBase.ClaseBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MapObjectToolsQAwidgets extends ClaseBase
{

	public MapObjectToolsQAwidgets(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
	}

	// MAPEO DE ELEMENTOS	
//	-- > TOOLS QA - WIDGETS
	protected By btnDatePicker = By.xpath("//android.widget.TextView[@text='Date Picker']"); // BOTON DATE PICKER
	protected By selectDate = By.xpath("//android.widget.EditText[@resource-id='datePickerMonthYearInput']"); // SELECCIONADOR DE DATE
	protected By dateAndTime = By.xpath("//android.widget.EditText[@resource-id='dateAndTimePickerInput']"); // SELECCIONADOR DE DATE AND TIME
	
}
