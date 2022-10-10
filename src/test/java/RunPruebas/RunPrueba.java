package RunPruebas;

import org.testng.annotations.Test;

import ClasesBase.ClaseBase;
import PageObjects.PageObjectCalculadora;
import PageObjects.PageObjectGoogleChrome;
import PageObjects.PageObjectMERhome;
import PageObjects.PageObjectMERingresar;
import PageObjects.PageObjectMERproductos;
import PageObjects.PageObjectToolsQAalerts;
import PageObjects.PageObjectToolsQAhome;
import PageObjects.PageObjectToolsQAwidgets;
import Utilidades.ExcelUtilidades;
import Utilidades.GenerarReportePdf;
import Utilidades.MyScreenRecorder;
import io.appium.java_client.AppiumDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterClass;

public class RunPrueba {

	// INSTANCIAS
	@SuppressWarnings("rawtypes")
	private AppiumDriver driver;
	ClaseBase clasebase;
	
	// CALCULADORA -->
	PageObjectCalculadora calculadoraVivo;

	// GOOGLE CHROME
	PageObjectGoogleChrome googleChrome;
	
	// MERCADOLIBRE -->
	PageObjectMERhome mercadolibreHome;
	PageObjectMERproductos mercadolibreProductos;
	PageObjectMERingresar mercadolibreIngresar;
	
	// TOOLS QA -->
	PageObjectToolsQAhome toolsQAhome;
	PageObjectToolsQAalerts toolsQAalerts;
	PageObjectToolsQAwidgets toolsQAwidgets;
	
	// PDF -->
	GenerarReportePdf generarPDF;
	String logoCalculadora;
	String rutaImagenMercury;
	String rutaImagenMER;
	String rutaImagenTOOLS;
	
	// CAPABILITYES
	String platformName;
	String deviceName;
	String platformVersion;
	String noReset;
	String autoGrantPermissions;
	
	

  
  @BeforeClass
  @Parameters({"platformName","deviceName","platformVersion","noReset","autoGrantPermissions","logoCalculadora","logoMER","logoTOOLS"})
  public void beforeClass(@Optional("default") String platformNameParameter,
		  				  @Optional("default") String deviceNameParameter,
		  				  @Optional("default") String platformVersionParameter,
		  				  @Optional("default") String noResetParameter,
		  				  @Optional("default") String autoGrantPermissionsParameter,
		  				  @Optional("default") String logoCalculadoraParameter,
		  				  @Optional("default") String logoMERParameter,
		  				  @Optional("default") String logoTOOLSParameter) throws IOException
  {	  
	  // INSTANCIAR LAS CLASES -->
	 	  	  
	  // INSTANCIAR LA CLASE DE GENERAR PDF
	  generarPDF = new GenerarReportePdf();	  
	  //SETIAR LA IMAGEN DEL ENCABEZADO PARA REPORTE PDF
	  logoCalculadora = logoCalculadoraParameter;
	  rutaImagenMER = logoMERParameter;
	  rutaImagenTOOLS = logoTOOLSParameter;
	  
	  //CAPABILITYES
	  platformName = platformNameParameter;
	  deviceName = deviceNameParameter;
	  platformVersion = platformVersionParameter;
	  noReset = noResetParameter;
	  autoGrantPermissions = autoGrantPermissionsParameter;
	  
  }
  
 

  // PRIMER TEST ->>
  @DataProvider(name = "busquedaCalculadora")
  public Object[][] datosCalculadora() throws Exception
  {
	  Object[][] arreglo = ExcelUtilidades.getTableArray("./Data/datosTestNGmobile.xlsx", "pruebaCalculadora");
	  return arreglo;
  }
  
 
  @Test(priority = 1, dataProvider = "busquedaCalculadora", description="Automatización de calculadora, operaciones de mas de una cifra")
  public void operacionCalculadora(String ejecutarCaso, String generarEvidencia, String appPackage, String appActivity, String numeroUno, String operacion, String numeroDos, String resultadoAssert) throws Exception 
  {
	  if(ejecutarCaso.equals("SI")) {
		  		  
		  // ASIGNAMOS LAS OPCIONES Y LA CONFIGURACION DEL NAVEGADOR A LA VARIABLE DRIVER
		  driver = ClaseBase.appiumDriverConnetion(platformName, deviceName, platformVersion, appPackage, appActivity, noReset, autoGrantPermissions);
		  // CLASE BASE - (ScreenShots)
		  clasebase = new ClaseBase(driver);
		  // CALCULADORA -->
		  calculadoraVivo = new PageObjectCalculadora(driver);
		  	 
		  	// CONFIRMACION
		    System.out.println("COMIENZA EL PROCESO DE AUTOMATIZACION DE CALCULADORA");
		  
			// OBTENER EL NOMBRE DEL TEST - (ScreenShot's)
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
			// CREAR CARPETA PARA ALMACENAMIENTO DE IMAGENES
			File rutaCarpeta = clasebase.crearCarpeta(nomTest);
			
			if(generarEvidencia.equals("SI")) {
				
				  // ENVIAMOS LA IMAGEN DE ENCABEZADO DEL PDF
				  generarPDF.setRutaImagen(logoCalculadora);				
				  // INICIAR LA CREACION DE LA PLANTILLA PDF
				  generarPDF.crearPlantilla(nomTest, rutaCarpeta);
				  
				  
				  // INICIAR LA GRABACION DE VIDEO
				  MyScreenRecorder.startRecording(nomTest, rutaCarpeta);
				  				  
				  
				  // LLAMADO DE METODOS ->>
				  calculadoraVivo.operacionCalculadoraDinamica(numeroUno, operacion, numeroDos, rutaCarpeta, generarEvidencia);
				  
				  
				  // FINALIZAR LA GRABACION DE VIDEO
				  MyScreenRecorder.stopRecording();
				  
				  // TERMINAR LA CREACION DE LA PLANTILLA
				  generarPDF.cerrarPlantilla();
				  
				  // TERMINAR
//				  driver.close();
				
				} else {
				
					// CONFIRMACION
					System.out.println("LA AUTOMATIZACION DE CALCULADORA SE EJECUTARA SIN GENERAR EVIDENCIA");
					
					// LLAMADO DE METODOS ->>
					calculadoraVivo.operacionCalculadoraDinamica(numeroUno, operacion, numeroDos, rutaCarpeta, generarEvidencia);
					
					// TERMINAR
					driver.close();
				}
			  			
	  } else {
		  System.out.println("LA AUTOMATIZACION DE CALCULADORA NO SE EJECUTARA, VALIDAR EL CAMPO 'EJECUTAR CASO' EN EL EXCEL");
	  }	
  }
 
  
  
  
  // SEGUNDO TEST ->>
  @DataProvider(name = "busquedaMercadolibreWeb")
  public Object[][] datosMercadolibre() throws Exception
  {
	  Object[][] arreglo = ExcelUtilidades.getTableArray("./Data/datosTestNGmobile.xlsx", "mercadolibreWeb");
	  return arreglo;
  }
  
 
  @Test(priority = 2, dataProvider = "busquedaMercadolibreWeb", description="Automatización de Mercadolibre en web mobile, Seleccionar primer resultado de busqueda, agregar al carrito e ingresar en la cuenta")
  public void pruebaMercadolibreWeb(String ejecutarCaso, String generarEvidencia, String appPackage, String appActivity, String url, String busqueda, String correo) throws Exception 
  {
	  if(ejecutarCaso.equals("SI")) {
		  		  
		  // ASIGNAMOS LAS OPCIONES Y LA CONFIGURACION DEL NAVEGADOR A LA VARIABLE DRIVER
		  driver = ClaseBase.appiumDriverConnetion(platformName, deviceName, platformVersion, appPackage, appActivity, noReset, autoGrantPermissions);
		  // CLASE BASE - (ScreenShots)
		  clasebase = new ClaseBase(driver);
		  // GOOGLE Y MERCADOLIBRE -->
		  googleChrome = new PageObjectGoogleChrome(driver);
		  mercadolibreHome = new PageObjectMERhome(driver); 
		  mercadolibreProductos = new PageObjectMERproductos(driver); 
		  mercadolibreIngresar = new PageObjectMERingresar(driver);
		  	 
		  
		  	// CONFIRMACION
		    System.out.println("COMIENZA EL PROCESO DE AUTOMATIZACION DE MERCADOLIBRE WEB - MOBILE");
		  
			// OBTENER EL NOMBRE DEL TEST - (ScreenShot's)
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
			// CREAR CARPETA PARA ALMACENAMIENTO DE IMAGENES
			File rutaCarpeta = clasebase.crearCarpeta(nomTest);
			
			if(generarEvidencia.equals("SI")) {
				
				try {
					
					  // ENVIAMOS LA IMAGEN DE ENCABEZADO DEL PDF
					  generarPDF.setRutaImagen(rutaImagenMER);				
					  // INICIAR LA CREACION DE LA PLANTILLA PDF
					  generarPDF.crearPlantilla(nomTest, rutaCarpeta);
					  
					  
					  // INICIAR LA GRABACION DE VIDEO
					  MyScreenRecorder.startRecording(nomTest, rutaCarpeta);
					  
					  				  				  
					  // LLAMADO DE METODOS ->>
					  googleChrome.abrirPagina(url, rutaCarpeta, generarEvidencia);
					  mercadolibreHome.buscarProducto(busqueda, rutaCarpeta, generarEvidencia);
					  mercadolibreProductos.seleccionarPrimerProducto(rutaCarpeta, generarEvidencia);
					  mercadolibreIngresar.ingresarCuenta(correo, rutaCarpeta, generarEvidencia);
					  
					  
					  // FINALIZAR LA GRABACION DE VIDEO
					  MyScreenRecorder.stopRecording();
					  
					  // TERMINAR LA CREACION DE LA PLANTILLA
					  generarPDF.cerrarPlantilla();
					
					} catch (Exception e) {
						
						  // FINALIZAR LA GRABACION DE VIDEO
						  MyScreenRecorder.stopRecording();
						  
						  // TERMINAR LA CREACION DE LA PLANTILLA
						  generarPDF.cerrarPlantilla();
						  
					}
								  
				
				} else {
				
					// CONFIRMACION
					System.out.println("LA AUTOMATIZACION DE MERCADOLIBRE WEB - MOBILE SE EJECUTARA SIN GENERAR EVIDENCIA");
					
				  // LLAMADO DE METODOS ->>
				  googleChrome.abrirPagina(url, rutaCarpeta, generarEvidencia);
				  mercadolibreHome.buscarProducto(busqueda, rutaCarpeta, generarEvidencia);
				  mercadolibreProductos.seleccionarPrimerProducto(rutaCarpeta, generarEvidencia);
				  mercadolibreIngresar.ingresarCuenta(correo, rutaCarpeta, generarEvidencia);
					
					// TERMINAR
					driver.close();
				}
			  			
	  } else {
		  System.out.println("LA AUTOMATIZACION DE MERCADOLIBRE WEB - MOBILE, NO SE EJECUTARA, VALIDAR EL CAMPO 'EJECUTAR CASO' EN EL EXCEL");
	  }	
  }
  
 
  
  
  // TERCER TEST ->>
  @DataProvider(name = "busquedaToolsQAalerts")
  public Object[][] datosAlerts() throws Exception
  {
	  Object[][] arreglo = ExcelUtilidades.getTableArray("./Data/datosTestNGmobile.xlsx", "toolsQAalerts");
	  return arreglo;
  }
  
 
  @Test(priority = 3, dataProvider = "busquedaToolsQAalerts", description="Automatización de TOOLSQA alerts mobile")
  public void pruebaAlerts(String ejecutarCaso, String generarEvidencia, String appPackage, String appActivity, String url, String textPromptBox) throws Exception 
  {
	  if(ejecutarCaso.equals("SI")) {
		  		  
		  // ASIGNAMOS LAS OPCIONES Y LA CONFIGURACION DEL NAVEGADOR A LA VARIABLE DRIVER
		  driver = ClaseBase.appiumDriverConnetion(platformName, deviceName, platformVersion, appPackage, appActivity, noReset, autoGrantPermissions);
		  // CLASE BASE - (ScreenShots)
		  clasebase = new ClaseBase(driver);
		  // GOOGLE Y TOOLSQA -->
		  googleChrome = new PageObjectGoogleChrome(driver);
		  toolsQAhome = new PageObjectToolsQAhome(driver);
		  toolsQAalerts = new PageObjectToolsQAalerts(driver); 

		  	 
		  
		  	// CONFIRMACION
		    System.out.println("COMIENZA EL PROCESO DE AUTOMATIZACION DE TOOLS QA - ALERTS");
		  
			// OBTENER EL NOMBRE DEL TEST - (ScreenShot's)
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
			// CREAR CARPETA PARA ALMACENAMIENTO DE IMAGENES
			File rutaCarpeta = clasebase.crearCarpeta(nomTest);
			
			if(generarEvidencia.equals("SI")) {
				
				  // ENVIAMOS LA IMAGEN DE ENCABEZADO DEL PDF
				  generarPDF.setRutaImagen(rutaImagenTOOLS);				
				  // INICIAR LA CREACION DE LA PLANTILLA PDF
				  generarPDF.crearPlantilla(nomTest, rutaCarpeta);
				  
				  
				  // INICIAR LA GRABACION DE VIDEO
				  MyScreenRecorder.startRecording(nomTest, rutaCarpeta);
				  
				  				  				  
				  // LLAMADO DE METODOS ->>
				  googleChrome.abrirPagina(url, rutaCarpeta, generarEvidencia);
				  toolsQAhome.abrirAlertsFrameWindows(rutaCarpeta, generarEvidencia);
				  toolsQAalerts.pruebaAlerts(textPromptBox, rutaCarpeta, generarEvidencia);
				  
				  
				  // FINALIZAR LA GRABACION DE VIDEO
				  MyScreenRecorder.stopRecording();
				  
				  // TERMINAR LA CREACION DE LA PLANTILLA
				  generarPDF.cerrarPlantilla();
				  
				  // TERMINAR
//				  driver.close();
				
				} else {
				
					// CONFIRMACION
					System.out.println("LA AUTOMATIZACION DE TOOLS QA - ALERTS SE EJECUTARA SIN GENERAR EVIDENCIA");
					
					  // LLAMADO DE METODOS ->>
					  googleChrome.abrirPagina(url, rutaCarpeta, generarEvidencia);
					  toolsQAhome.abrirAlertsFrameWindows(rutaCarpeta, generarEvidencia);
					  toolsQAalerts.pruebaAlerts(textPromptBox, rutaCarpeta, generarEvidencia);
					
					// TERMINAR
					driver.close();
				}
			  			
	  } else {
		  System.out.println("LA AUTOMATIZACION DE DE TOOLS QA - ALERTS, NO SE EJECUTARA, VALIDAR EL CAMPO 'EJECUTAR CASO' EN EL EXCEL");
	  }	
  }
 
  
//CUARTO TEST ->>
 @DataProvider(name = "busquedaToolsQAwidgets")
 public Object[][] datosWidgets() throws Exception
 {
	  Object[][] arreglo = ExcelUtilidades.getTableArray("./Data/datosTestNGmobile.xlsx", "toolsQAwidgets");
	  return arreglo;
 }
 

 @Test(priority = 4, dataProvider = "busquedaToolsQAwidgets", description="Automatización de TOOLSQA widgets mobile")
 public void pruebaWidgets(String ejecutarCaso, String generarEvidencia, String appPackage, String appActivity, String url) throws Exception 
 {
	  if(ejecutarCaso.equals("SI")) {
		  		  
		  // ASIGNAMOS LAS OPCIONES Y LA CONFIGURACION DEL NAVEGADOR A LA VARIABLE DRIVER
		  driver = ClaseBase.appiumDriverConnetion(platformName, deviceName, platformVersion, appPackage, appActivity, noReset, autoGrantPermissions);
		  // CLASE BASE - (ScreenShots)
		  clasebase = new ClaseBase(driver);
		  // GOOGLE Y TOOLSQA -->
		  googleChrome = new PageObjectGoogleChrome(driver);
		  toolsQAhome = new PageObjectToolsQAhome(driver);
		  toolsQAwidgets = new PageObjectToolsQAwidgets(driver);

		  	 
		  
		  	// CONFIRMACION
		    System.out.println("COMIENZA EL PROCESO DE AUTOMATIZACION DE TOOLS QA - WIDGETS");
		  
			// OBTENER EL NOMBRE DEL TEST - (ScreenShot's)
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
			// CREAR CARPETA PARA ALMACENAMIENTO DE IMAGENES
			File rutaCarpeta = clasebase.crearCarpeta(nomTest);
			
			if(generarEvidencia.equals("SI")) {
				
				  // ENVIAMOS LA IMAGEN DE ENCABEZADO DEL PDF
				  generarPDF.setRutaImagen(rutaImagenTOOLS);				
				  // INICIAR LA CREACION DE LA PLANTILLA PDF
				  generarPDF.crearPlantilla(nomTest, rutaCarpeta);
				  
				  
				  // INICIAR LA GRABACION DE VIDEO
				  MyScreenRecorder.startRecording(nomTest, rutaCarpeta);
				  
				  				  				  
				  // LLAMADO DE METODOS ->>
				  googleChrome.abrirPagina(url, rutaCarpeta, generarEvidencia);
				  toolsQAhome.abrirWidgets(rutaCarpeta, generarEvidencia);
				  toolsQAwidgets.pruebaWidgets(rutaCarpeta, generarEvidencia);
				  
				  
				  // FINALIZAR LA GRABACION DE VIDEO
				  MyScreenRecorder.stopRecording();
				  
				  // TERMINAR LA CREACION DE LA PLANTILLA
				  generarPDF.cerrarPlantilla();
				  
				  // TERMINAR
//				  driver.close();
				
				} else {
				
					// CONFIRMACION
					System.out.println("LA AUTOMATIZACION DE TOOLS QA - WIDGETS SE EJECUTARA SIN GENERAR EVIDENCIA");
					
					  // LLAMADO DE METODOS ->>
					  googleChrome.abrirPagina(url, rutaCarpeta, generarEvidencia);
					  toolsQAhome.abrirWidgets(rutaCarpeta, generarEvidencia);
					  toolsQAwidgets.pruebaWidgets(rutaCarpeta, generarEvidencia);
					  			
					// TERMINAR
					driver.close();
				}
			  			
	  } else {
		  System.out.println("LA AUTOMATIZACION DE DE TOOLS QA - WIDGETS, NO SE EJECUTARA, VALIDAR EL CAMPO 'EJECUTAR CASO' EN EL EXCEL");
	  }	
 }
    
  @AfterClass
  public void afterClass() 
  {
	  driver.quit();
  }

}
