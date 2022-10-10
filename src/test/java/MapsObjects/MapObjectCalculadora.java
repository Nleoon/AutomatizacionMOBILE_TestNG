package MapsObjects;

import org.openqa.selenium.By;

import ClasesBase.ClaseBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MapObjectCalculadora extends ClaseBase
{
 
	public MapObjectCalculadora(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
	}
	
	// MAPEO DE ELEMENTOS - CALCULADORA VIVO
		protected By btnNumeros = By.xpath("//android.widget.ImageButton[@content-desc='{0}']"); // XPATH DINAMICO DE LOS NUMEROS DE LA CALCULADORA
		protected By btnOperadoresAritmeticos = By.xpath("//android.widget.ImageButton[@content-desc='{0}']"); // XPATH DINAMICO DE LOS OPERADORES ARITMETICOS
		protected By igual = By.id("com.vivo.calculator:id/equal"); // IGUAL
		
		protected By resultadoOperacion = By.xpath("//android.widget.EditText[@content-desc='384']");
}
