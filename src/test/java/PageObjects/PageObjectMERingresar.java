package PageObjects;

import java.io.File;

import MapsObjects.MapObjectMERingresar;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PageObjectMERingresar extends MapObjectMERingresar
{

	public PageObjectMERingresar (AppiumDriver<MobileElement> driver) 
	{
		super(driver);
		this.driver = (AppiumDriver<MobileElement>) driver;
	}

	// METODOS DE PRUEBA -->
		public void ingresarCuenta(String correo, File rutaCarpeta, String generarEvidencia) throws Exception 
		{
			try {
					
				// INGRESAR EN LA CUENTA
				tiempoEspera(2);
				click(btnIngresar, rutaCarpeta, generarEvidencia,"INGRESAR EN LA CUENTA"); 
									
				// ESCRIBIR EL EMAIL DE LA CUENTA
				tiempoEspera(2);
				sendkey(correo, txtEmail, rutaCarpeta, generarEvidencia,"ESCRIBIR EL EMAIL DE LA CUENTA");
				tiempoEspera(3);
					
	 				
			} catch (Exception e) {
					
				System.out.println(e.getMessage());
			}
			
		}
}
