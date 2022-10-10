package MapsObjects;

import org.openqa.selenium.By;

import ClasesBase.ClaseBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MapObjectGoogleChrome extends ClaseBase {

	public MapObjectGoogleChrome(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
	}

	// MAPEO DE ELEMENTOS
	
//	-- > GOOGLE CHROME
	protected By cerrarPestaña = By.id("com.android.chrome:id/tab_switcher_button"); // CERRAR PESTAÑA
	protected By nuevaPestaña = By.xpath("//android.widget.ImageView[@content-desc='Nueva pestaña']"); // NUEVA PESTAÑA - CHROME
	protected By busquedaGoogleChrome = By.id("com.android.chrome:id/search_box_text"); // CAJA DE BUSQUEDA GOOGLE
	protected By primerURL = By.xpath("//android.view.ViewGroup/android.widget.LinearLayout[1]"); // PRIMER URL DE BUSQUEDA
}
