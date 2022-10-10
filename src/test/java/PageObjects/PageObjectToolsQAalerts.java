package PageObjects;

import java.io.File;

import MapsObjects.MapObjectToolsQAalerts;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PageObjectToolsQAalerts extends MapObjectToolsQAalerts
{

	public PageObjectToolsQAalerts(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
		this.driver = (AppiumDriver<MobileElement>) driver;
	}

	// METODOS DE PRUEBA -->
	public void pruebaAlerts(String textPromptBox, File rutaCarpeta, String generarEvidencia) throws Exception 
	{
		try {
			// CLICK EN ALERTS 
			tiempoEspera(2);
			click(btnAlerts, rutaCarpeta, generarEvidencia,"SE SELECCIONA EL APARTADO DE ALERTS");
			
			// CLICK EN EL PRIMER BOTON
			tiempoEspera(2);
			click(btnAlert1, rutaCarpeta, generarEvidencia,"SE SELECCIONA EL PRIMER BOTON");
			tiempoEspera(1);
			click(popUpAceptar, rutaCarpeta, generarEvidencia,"SE ACEPTA EL POPUP");
			
			// CLICK EN EL SEGUNDO BOTON
			tiempoEspera(2);
			click(btnAlert2, rutaCarpeta, generarEvidencia,"SE SELECCIONA EL SEGUNDO BOTON");
			tiempoEspera(6);
			click(popUpAceptar, rutaCarpeta, generarEvidencia,"SE ACEPTA EL POPUP DESPUES DEL TIEMPO DE ESPERA");
			
			// CLICK EN EL TERCER BOTON - ACEPTAR
			tiempoEspera(2);
			click(btnAlert3, rutaCarpeta, generarEvidencia,"SE SELECCIONA EL TERCER BOTON");
			tiempoEspera(1);
			click(popUpAceptar, rutaCarpeta, generarEvidencia,"SE ACEPTA EL POPUP");
			
			// CLICK EN EL TERCER BOTON - CANCELAR
			tiempoEspera(2);
			click(btnAlert3, rutaCarpeta, generarEvidencia,"SE SELECCIONA EL TERCER BOTON");
			tiempoEspera(1);
			click(popUpCancelar, rutaCarpeta, generarEvidencia,"SE CANCELA EL POPUP");
			
			// CLICK EN EL CUARTO BOTON
			tiempoEspera(2);
			click(btnAlert4, rutaCarpeta, generarEvidencia,"SE SELECCIONA EL CUARTO BOTON");
			tiempoEspera(1);
			sendkey(textPromptBox, popUptext, rutaCarpeta, generarEvidencia,"SE ESCRIBE EL TEXTO DE PRUEBA");
			tiempoEspera(1);
			click(popUpAceptar, rutaCarpeta, generarEvidencia,"SE ACEPTA EL POPUP");
			
			// ESPERA PARA VISUALIZAR
			tiempoEspera(3);
			System.out.println("HA FINALIZADO LA AUTOMATIZACION DE ALERTS CON EXITO");

		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	
}
