package PageObjects;

import java.io.File;


import MapsObjects.MapObjectGoogleChrome;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PageObjectGoogleChrome extends MapObjectGoogleChrome
{

	public PageObjectGoogleChrome(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
		this.driver = (AppiumDriver<MobileElement>) driver;
	}

	// METODOS DE PRUEBA -->
	public void abrirPagina(String url, File rutaCarpeta, String generarEvidencia) throws Exception 
	{
		try {
			
			// CERRAR LA PESTAÑA DE GOOGLE 
			tiempoEspera(2);
			click(cerrarPestaña, rutaCarpeta, generarEvidencia, "SE PROCEDE A ABRIR UNA NUEVA VENTANA");
			
			// NUEVA PESTAÑA
			tiempoEspera(2);
			click(nuevaPestaña, rutaCarpeta, generarEvidencia, "SE ABRE LA NUEVA PESTAÑA");
			
			// ABRIR MERCADOLIBRE
			tiempoEspera(2);
			sendkey(url, busquedaGoogleChrome, rutaCarpeta, generarEvidencia, "SE ENVIA EL VALOR DE LA URL");
			
			// CLICK EN EL PRIMER LINK DE BUSQUEDA
			tiempoEspera(2);
			click(primerURL, rutaCarpeta, generarEvidencia, "SE SELECCIONA EL PRIMER RESULTADO DE BUSQUEDA");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
}
