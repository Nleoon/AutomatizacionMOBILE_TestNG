package PageObjects;

import java.io.File;

import MapsObjects.MapObjectToolsQAhome;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PageObjectToolsQAhome extends MapObjectToolsQAhome
{

	public PageObjectToolsQAhome(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
		this.driver = (AppiumDriver<MobileElement>) driver;
	}

	// METODOS DE PRUEBA -->
	public void abrirAlertsFrameWindows(File rutaCarpeta, String generarEvidencia) throws Exception 
	{
		try {
			
			// SCROLL
			tiempoEspera(2);
			scrollVertical(rutaCarpeta, 500, 2250, 700, 2, btnAlertsFrameWindows, generarEvidencia, "SCROLL");
			
			// CLICK EN ALERTS FRAME Y WINDOWS
			tiempoEspera(2);
			click(btnAlertsFrameWindows, rutaCarpeta, generarEvidencia,"SE SELECCIONA EL CARD DE ALERTS, FRAME Y WINDOWS");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public void abrirWidgets(File rutaCarpeta, String generarEvidencia) throws Exception 
	{
		try {
			
			// SCROLL
			tiempoEspera(2);
			scrollVertical(rutaCarpeta, 500, 2250, 500, 2, btnWidgets, generarEvidencia,"SCROLL");
			
			// CLICK EN WIDGETS
			tiempoEspera(2);
			click(btnWidgets, rutaCarpeta, generarEvidencia,"SE SELECCIONA EL CARD DE WIDGETS");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
}
