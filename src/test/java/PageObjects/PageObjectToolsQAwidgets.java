package PageObjects;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import MapsObjects.MapObjectToolsQAwidgets;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PageObjectToolsQAwidgets extends MapObjectToolsQAwidgets
{

	public PageObjectToolsQAwidgets(AppiumDriver<MobileElement> driver) 
	{
		super(driver);
		this.driver = (AppiumDriver<MobileElement>) driver;
	}

	// METODOS DE PRUEBA -->
		public void pruebaWidgets(File rutaCarpeta, String generarEvidencia) throws Exception 
		{
			try {
				
				// SCROLL
//				tiempoEspera(2);
//				scrollVertical(rutaCarpeta, 500, 1900, 1300, 1);
				
				// CLICK EN DATE PICKER
				tiempoEspera(2);
				click(btnDatePicker, rutaCarpeta, generarEvidencia, "SE SELECCIONA EL APARTADO DE DATE PICKER");
				
				// SELECT DATE -->
				// SE ALMACENA LA NUEVAFECHA
				String fechaFinal = widgetsPruebaFecha(1, 1, 1, rutaCarpeta);
				tiempoEspera(1);
//			System.out.println(fechaFinal);
			
				// SE ENVIA LA NUEVA FECHA 
				sendkey(fechaFinal, selectDate, rutaCarpeta, generarEvidencia,"SE REALIZA EL ENVÍO DE LA FECHA");
				// "ENTER" 
//				click(localizadorVariable(selectDate, fechaFinal), rutaCarpeta, generarEvidencia, "SE REALIZA EL ENVIO DE LA FECHA");
				tocarPantalla(832, 1246, rutaCarpeta, selectDate, generarEvidencia,"TOQUE DE PANTALLA");

				// DATE AND TIME -->
				tiempoEspera(2);
				// SE ALMACENA EL MES EN LETRA
				Month mesLetra= LocalDate.now().minusMonths(1).getMonth();
//			System.out.println(mesLetra);
						
				// SE ALMACENA EL DIA Y EL AÑO DE LA HORA LOCAL 
				String fechaDiaAño = widgetsPruebaFechaSinMes(1, 1, rutaCarpeta);
				// SE ALAMACENA LA HORA ACTUAL
				String horaActual = HoraSistema();
				// SE CONCADENA EL MES DE LETRA MAS EL DIA Y EL AÑO
				String fechaFinal2 = mesLetra + fechaDiaAño + " " + horaActual;
				tiempoEspera(1);
							
				// SE ENVIA LA FECHA CONCADENADA AL BOX DE LA SEGUNDA FECHA
//				click(dateAndTime, rutaCarpeta, generarEvidencia, "CLICK");
				sendkey(fechaFinal2, dateAndTime, rutaCarpeta, generarEvidencia,"SE REALIZA EL ENVÍO DE LA FECHA");
				tiempoEspera(1);
//				tocarPantalla(832, 1264, rutaCarpeta, dateAndTime, generarEvidencia,"TOQUE DE PANTALLA");
				
				// ESPERA PARA VISUALIZAR
				tiempoEspera(3);
				System.out.println("HA FINALIZADO LA AUTOMATIZACION DE WIDGETS CON EXITO");

			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
			
		}
	
}
