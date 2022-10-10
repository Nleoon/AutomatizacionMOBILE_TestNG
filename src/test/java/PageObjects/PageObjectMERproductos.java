package PageObjects;

import java.io.File;

import MapsObjects.MapObjectMERproductos;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PageObjectMERproductos extends MapObjectMERproductos
{

	public PageObjectMERproductos (AppiumDriver<MobileElement> driver) 
	{
		super(driver);
		this.driver = (AppiumDriver<MobileElement>) driver;
	}

	// METODOS DE PRUEBA -->
	public void seleccionarPrimerProducto(File rutaCarpeta, String generarEvidencia) throws Exception 
	{
		try {
			
			// SELECCIONAR PRIMER RESULTADO DE BUSQUEDA
			tiempoEspera(2);
			click(selectPrimerProductoError, rutaCarpeta, generarEvidencia,"SELECCIONAR EL PRIMER RESULTADO DE BUSQUEDA");
			
			// SCROLL
			scrollVertical(rutaCarpeta, 500, 2150, 450, 1, btnAgregarCarrito, generarEvidencia, "SCROLL");
			tiempoEspera(1);
			
			// AGREGAR AL CARRITO
			tiempoEspera(2);
			click(btnAgregarCarrito, rutaCarpeta, generarEvidencia,"AGREGAR AL CARRITO");
			
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
}
