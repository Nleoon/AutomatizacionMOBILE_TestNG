package PageObjects;


import java.io.File;

import MapsObjects.MapObjectCalculadora;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PageObjectCalculadora extends MapObjectCalculadora
{

	public PageObjectCalculadora(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
		this.driver = (AppiumDriver<MobileElement>) driver;
	}
	
	// 1. METODO DE PRUEBA - XPATH DINAMICO Y EXCEL
			public void operacionCalculadoraDinamica(String numeroUno, String operacion, String numeroDos, File rutaCarpeta, String generarEvidencia) throws Exception
			{
				try {
					
					// CLICK EN EL PRIMER NUMERO DE LA OPERACION
					separarNumeros(numeroUno, btnNumeros, rutaCarpeta, generarEvidencia, "SE PROCEDE A DIGITAR EL PRIMER NUMERO DE LA OPERACION");			
					
					// CLICK EN EL OPERADOR ARITMETICO
					click(localizadorVariable(btnOperadoresAritmeticos, operacion), rutaCarpeta, generarEvidencia, "SE SELECCIONA EL OPERADOR NUMERICO");
					
					// CLICK EN EL SEGUNDO NUMERO DE LA OPERACION
					separarNumeros(numeroDos, btnNumeros, rutaCarpeta, generarEvidencia, "SE PROCEDE A DIGITAR EL SEGUNDO NUMERO DE LA OPERACION");
					
					// CLICK EN IGUAL PARA EL RESULTADO DE LA OPERACION
					click(igual, rutaCarpeta, generarEvidencia, "SE PROCEDE CON EL RESULTADO DE LA OPERACIÓN");
					tiempoEspera(1);
					
					System.out.println("LA AUTOMATIZACION DE CALCULADORA HA FINALIZADO CON EXITO");
					
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			
			}	
}
