package ClasesBase;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import Utilidades.GenerarReportePdf;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;



public class ClaseBase {

protected AppiumDriver<MobileElement> driver;
	
	// CONSTRUCTOR DE CLASE
	public ClaseBase(WebDriver driver) 
	{
		super();		
	}
	
	
	//METODO DE NAVEGADOR
	
			@SuppressWarnings("rawtypes")
			public static AppiumDriver appiumDriverConnetion(String platformName, String deviceName, String platformVersion, 
														String appPackage, String appActivity, String noReset, String autoGrantPermissions) 
			{
				AppiumDriver driver = null; 
				try
				{
					// CREARLAS CAPABILITYS DEL MOVIL
					DesiredCapabilities caps = new DesiredCapabilities();
					
					caps.setCapability("platformName",platformName);
					caps.setCapability("deviceName",deviceName);
					caps.setCapability("platformVersion",platformVersion);
					caps.setCapability("appPackage",appPackage);
					caps.setCapability("appActivity",appActivity);
					caps.setCapability("noReset",noReset);
					caps.setCapability("autoGrantPermissions",autoGrantPermissions);					

					
					//INSTANCIAR APPIUM DRIVER
					try 
					{
						System.out.println("cargando capability de appium, favor esperar ....");
						driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);					
						
					} 
					catch (MalformedURLException e) 
					{
						System.out.println(e.getMessage());
					}
				return driver;
				}
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}
				return driver;
			}
	
	
	
	// SCREENSHOT -->
	
	// METODOD PARA GUARDAR LA FECHA DEL SISTEMA
	public static String fechaHora() {
		// TOMAMOS LA FECHA DEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		// DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		// DAR FORMATO A LA FECHA DEL SISTEMA
		String formatFecha = fecha.format(fechaSistema);
		return formatFecha;
	}
	
	// GENERAR PDF -->
	// METODOD PARA GUARDAR LA FECHA DEL SISTEMA
	public static String fechaHora2() {
		// TOMAMOS LA FECHA DEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		// DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd- HH:mm:ss");
		// DAR FORMATO A LA FECHA DEL SISTEMA
		String formatFecha = fecha.format(fechaSistema);
		return formatFecha;
	}
	
	// METODOD PARA ELIMINAR LAS CAPTURAS DE PANTALLA
	public void eliminarArchivo(String rutaImagen) {

		File fichero = new File(rutaImagen);
		fichero.delete();
	}
	
		
	// METODO PARA GUARDAR LA HORA DEL SISTEMA
	public String HoraSistema() {
		// TOMAMOS LA HORA DEL SISTEMA
		LocalTime horaSistema = LocalTime.now();
		// DEFINIR FORMATO DE HORA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("HHmmss");
		// DAR FORMATO A LA HORA DEL SISTEMA
		String hora = fecha.format(horaSistema);
		return hora;
	}
	
	
	// METODO PARA TOMAR LA CAPTURA DE PANTALLA
	public void ScreenShot(File rutaCarpeta, By locator, String generarEvidencia, String steps) throws Exception {
		
		if(generarEvidencia.equals("SI")) {
			
			String hora = HoraSistema();
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(rutaCarpeta+"\\"+hora+".png"));
			
			String rutaImagen = new File(rutaCarpeta+"\\"+hora+".png").toString();
			
			// GENERAR PDF --->
			// INSTANCIAMOS LA CLASE DE GENERAR PDF
			GenerarReportePdf informePdf = new GenerarReportePdf();
			// SE PROCEDE A INSERTAR EL LOCALIZADOR DE LA IMAGEN DEL ENCABEZADO EN EL PDF
			informePdf.crearbody(locator, rutaImagen, steps);
			// ELIMINAR LA IMAGEN CREADA
			eliminarArchivo(rutaImagen);
			
		}

	}
	
	
	public void ScreenShotError(File rutaCarpeta, By locator, String generarEvidencia, String msnError)
            throws Exception {
        if (generarEvidencia.equals("SI")) {
            String hora = HoraSistema();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(rutaCarpeta + "\\" + hora + ".png"));
            String rutaImagen = new File(rutaCarpeta + "\\" + hora + ".png").toString();



          // INSTACIAMOS LA CLASE GENERAR PDF
            GenerarReportePdf informePdf = new GenerarReportePdf();
            // SE PROCEDE A INSERTAR LOCALIZADOR HE IMAGEN EN EL PDF
            informePdf.crearbodyError(locator, rutaImagen, msnError);
            // ELIMINAR IMAGEN CREADA
            eliminarArchivo(rutaImagen);
        }
    }
	
	// METODO PARA CREAR LA CARPETA DONDE SE ALMACENAN LOS SCREEN'S
	public File crearCarpeta(String nomTest) {
		// ALMACENAMOS LA FECHA DEL SISTEMA
		String fecha = fechaHora();
		// CREAMOS EL NOMBRE DE LA CARPETA
		String nomCarpeta = nomTest+"-"+fecha;
		// OBTENEMOS LA RETA DE ALOJAMIENTO DE SALIDA Y EL NOMBRE DEL TEST A EJECUTAR
		File directorio = new File("./output/"+nomCarpeta);
		// CREAMOS LA CARPETA
		directorio.mkdir();
		return directorio;
	}
	
	
	
	// METODOS BASE -->
	
	// METODO CLICK
	public void click(By locator, File rutaCarpeta, String generarEvidencia, String steps) throws Exception
	{
		try {
			
			driver.findElement(locator).click();
			tiempoEspera(2);
			ScreenShot(rutaCarpeta, locator, generarEvidencia, steps);
			
		} catch (Exception e) {
			
			ScreenShotError(rutaCarpeta, locator, generarEvidencia, e.toString());
			throw new InterruptedException();
			
		}

	}
	
	// METODO CLICK - CON EL SCREENSHOT ANTES DEL CLICK
	public void click2(By locator, File rutaCarpeta, String generarEvidencia, String steps) throws Exception
	{
		ScreenShot(rutaCarpeta, locator, generarEvidencia, steps);
		driver.findElement(locator).click();
		tiempoEspera(2);
	}
	
	// METODO BORRAR
	public void borrar(By locator, File rutaCarpeta, String generarEvidencia, String steps) throws Exception
	{
		driver.findElement(locator).clear();
		tiempoEspera(2);
		ScreenShot(rutaCarpeta, locator, generarEvidencia, steps);
	}
	
	
	
	// METODO ENVIAR-TEXTO (SENDKEY))
	public void sendkey(String inputText, By locator, File rutaCarpeta, String generarEvidencia, String steps) throws Exception
	{
		try {
			
			driver.findElement(locator).sendKeys(inputText);
			tiempoEspera(2);
			ScreenShot(rutaCarpeta, locator, generarEvidencia, steps);
			
		} catch (Exception e) {
			
			ScreenShotError(rutaCarpeta, locator, generarEvidencia, e.toString());
			throw new InterruptedException();
			
		}

	}
	
	
	
	// METODO ENTER (SUBMIT)
	public void submit(By locator, File rutaCarpeta, String generarEvidencia, String steps) throws Exception
	{
		driver.findElement(locator).submit();
		tiempoEspera(2);
		ScreenShot(rutaCarpeta, locator, generarEvidencia, steps);
	}
	
	
	
	// METODO TIEMPO DE ESPERA
	public void tiempoEspera(long tiempo) throws InterruptedException
	{
		Thread.sleep(tiempo*1000);
	}
	
	
	// METODO DE VALIDACION - VALIDA SI UN ELEMENTO SE ENCUENTRA O NO
	public void validacionElemento(By locator, File rutaCarpeta, String generarEvidencia, String steps) {
	    try {
	        driver.findElement(locator).isEnabled();
	        click(locator,rutaCarpeta,generarEvidencia, steps);
	    }catch (Exception e){
	        System.out.println(e);
	    }
	}
	
	// METODO SCROLL
	public void scrollWeb(int y, int numMovimiento) throws InterruptedException
	{
		// SCROLL DE PAGINA
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// EL FOR SIRVE PARA DESPLAZARNOS MUCHAS MAS VECES
		for (int i=0; i<=numMovimiento; i++) {
	    js.executeScript("window.scrollBy(0,"+y+")");
		}
		
	}
	
	// METODO ENTER
	public void enter(By locator, File rutaCarpeta, String generarEvidencia, String steps) throws Exception
	{
		driver.findElement(locator).sendKeys(Keys.chord(Keys.ENTER));
		//driver.findElement(locator).clear();
		tiempoEspera(2);
		ScreenShot(rutaCarpeta, locator, generarEvidencia, steps);
	}
	
	
	// METODO REEMPLAZAR
	public void reemplazar(By locator, File rutaCarpeta, String generarEvidencia, String steps) throws Exception
	{
		driver.findElement(locator).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		//driver.findElement(locator).clear();
		tiempoEspera(2);
		ScreenShot(rutaCarpeta, locator, generarEvidencia, steps);
	}
	
	
	
	// --> METODOS PARA EL DATE PICKER
	
	// METODO PARA GUARDAR LA FECHA DEL SISTEMA
	public String fechaSistema() {
		// TOMAMOS LA FECHA DEL SISTEMA
		LocalDate fechaSistema = LocalDate.now();
		// DEFINIR FORMATO DE HORA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		// DAR FORMATO A LA HORA DEL SISTEMA
		String fechaSistem = fecha.format(fechaSistema);
		return fechaSistem;
	}
	
	// METODO DE FECHA MENOS x TIEMPO
	public String widgetsPruebaFecha(int restaAño, int restaMes, int restaDia, File rutaCarpeta) throws Exception
	{    
		String fecha = fechaSistema();
		String[] fechaVector = fecha.split("-");
		
		int dia = Integer.parseInt(fechaVector[0]);
		int mes = Integer.parseInt(fechaVector[1]);	
		int year = Integer.parseInt(fechaVector[2]);
		
		dia = dia-restaDia;
		mes = mes-restaMes;
		year = year-restaAño;
		
		String fechaFinal = mes+"/"+dia+"/"+year;
		
		return fechaFinal;
	}
	
	
	// METODO DE FECHA MENOS x TIEMPO
	public String widgetsPruebaFechaSinMes(int restaAño, int restaDia, File rutaCarpeta) throws Exception
	{    
		String fecha = fechaSistema();
		String[] fechaVector = fecha.split("-");
		
		int dia = Integer.parseInt(fechaVector[0]);
		//int mes = Integer.parseInt(fechaVector[1]);	
		int year = Integer.parseInt(fechaVector[2]);
		
		dia = dia-restaDia;
		//mes = mes-restaMes;
		year = year-restaAño;
		
		String fechaFinal = "  "+dia+", "+year;
		
		return fechaFinal;
	}
	
	// XPATH DINAMICO -->
    public By localizadorVariable(By locator, String valor) throws Exception 
    {
    	String jj = locator.toString().replace("{0}", valor);
    	String kk = jj.replace("By.xpath:", "");
    	By localizador = By.xpath(kk);
    	
    	return localizador;
    }
	
	// METODO DE SELECCIONAR NUMEROS DE MAS DE 1 CIFRA
	public void separarNumeros(String numeros, By locator, File rutaCarpeta, String generarEvidencia, String steps) throws Exception
	{    
		String[] num = new String[numeros.length()];
		
		for (int i=0; i<numeros.length(); i++) {
			num[i]= String.valueOf(numeros.charAt(i));
			click(localizadorVariable(locator,num[i]), rutaCarpeta, generarEvidencia, steps);
		}
	}
	
	// SCROLL
	public void scrollVertical(File rutaCarpeta, int xini,int yini, int yfinal, int iteraciones, By locator, String generarEvidencia, String steps) throws Exception
    {
        
        for (int i = 1 ;i<=iteraciones;i++)
        {
            @SuppressWarnings("rawtypes")
            TouchAction touch = new TouchAction(driver);
            touch.press(PointOption.point(xini,yini))
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
            .moveTo(PointOption.point(xini,yfinal))
            .release().perform();            
        }
        
        ScreenShot(rutaCarpeta, locator, generarEvidencia, steps);
    }
	
	
	// TOCAR PANTALLA
	public void tocarPantalla(int x, int y, File rutaCarpeta, By locator, String generarEvidencia, String steps) throws Exception
    {
        @SuppressWarnings("rawtypes")
        TouchAction touch = new TouchAction(driver);
        touch.press(PointOption.point(x,y)).release().perform();
        ScreenShot(rutaCarpeta, locator, generarEvidencia, steps);
        
    }
}
