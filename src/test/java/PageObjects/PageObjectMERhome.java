package PageObjects;

import java.io.File;

import MapsObjects.MapObjectMERhome;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PageObjectMERhome extends MapObjectMERhome
{

	public PageObjectMERhome (AppiumDriver<MobileElement> driver) 
	{
		super(driver);
		this.driver = (AppiumDriver<MobileElement>) driver;
	}

	// METODOS DE PRUEBA -->
	public void buscarProducto(String busqueda, File rutaCarpeta, String generarEvidencia) throws Exception 
	{
		try {
						
			// BUSCAR PRODUCTO
			tiempoEspera(3);
			sendkey(busqueda, txtbuscarProducto, rutaCarpeta, generarEvidencia, "SE ESCRIBE EL PRODUCTO A BUSCAR");
			tiempoEspera(1);
			click(btnLupa, rutaCarpeta, generarEvidencia, "SE BUSCA EL PRODUCTO");
			
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
}
