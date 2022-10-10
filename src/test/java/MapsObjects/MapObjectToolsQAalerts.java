package MapsObjects;

import org.openqa.selenium.By;

import ClasesBase.ClaseBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MapObjectToolsQAalerts extends ClaseBase
{

	public MapObjectToolsQAalerts(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
	}

	// MAPEO DE ELEMENTOS
	
//	-- > TOOLS QA - ALERTS
	protected By btnAlerts = By.xpath("//android.view.View[@resource-id='item-1']/android.widget.TextView[@text='Alerts']"); // BOTON ALERTS
	protected By btnAlert1 = By.xpath("//android.widget.Button[@resource-id='alertButton']"); // PRIMER BOTON DE ALERTS
	protected By btnAlert2 = By.xpath("//android.widget.Button[@resource-id='timerAlertButton']"); // SEGUNDO BOTON DE ALERTS
	protected By btnAlert3 = By.xpath("//android.widget.Button[@resource-id='confirmButton']"); // TERCER BOTON DE ALERTS
	protected By popUpAceptar = By.id("com.android.chrome:id/positive_button"); // BOTON DE ACEPTAR POP UP ANDROID
	protected By popUpCancelar = By.id("com.android.chrome:id/negative_button"); // BOTON DE CANCELAR POP UP ANDROID
	protected By btnAlert4 = By.xpath("//android.widget.Button[@resource-id='promtButton']"); // CUARTO BOTON DE ALERTS
	protected By popUptext = By.id("com.android.chrome:id/js_modal_dialog_prompt"); // CAJA DE TEXTO POP UP ANDROID
	
}
