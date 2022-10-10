package MapsObjects;

import org.openqa.selenium.By;


import ClasesBase.ClaseBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MapObjectMERhome extends ClaseBase
{

	public MapObjectMERhome(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
	}

	// MAPEO DE ELEMENTOS
	protected By txtbuscarProducto =By.xpath("//android.widget.EditText[@resource-id='cb1-edit']"); //CAJA DE BUSQUEDA MERCADOLIBRE
	protected By btnLupa = By.xpath("//android.view.View/android.view.View[2]/android.widget.Button"); // BOTON DE LUPA PARA BUSCAR
}
