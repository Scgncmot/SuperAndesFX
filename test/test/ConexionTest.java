package test;

import static org.junit.Assert.fail;

import java.io.FileReader;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import negocio.SuperAndesAdministrador;

public class ConexionTest 
{
	//-----------------------------------------------------------
	//------------------------Constantes-------------------------
	//-----------------------------------------------------------
	/**
	 * Logger para escribir la traza de la ejecucion
	 */
	private static Logger log = Logger.getLogger(ConexionTest.class.getName());
	
	/**
	 * Ruta al archivo de configuracion de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD tambien
	 */
	private static final String CONFIG_TABLAS_A = "./resources/config/TablasBD_A.json"; 
	
	/**
	 * Ruta al archivo de configuracion de los nombres de tablas de la base de datos: La unidad de persistencia existe, pero el esquema de la BD no se ha creado
	 */
	private static final String CONFIG_TABLAS_B = "./resources/config/TablasBD_B.json"; 
	
	/**
	 * Ruta al archivo de configuraci�n de los nombres de tablas de la base de datos: La unidad de persistencia NO existe
	 */
	private static final String CONFIG_TABLAS_ERR_DS = "./resources/config/TablasBD_ErrorDataStore.json"; 
	
	/**
	 * Ruta al archivo de configuraci�n de los nombres de tablas de la base de datos: La unidad de persistencia NO existe
	 */
	private static final String CONFIG_TABLAS_ERR_USER = "./resources/config/TablasBD_ErrorInvalidUser.json"; 
	
	//-----------------------------------------------------------
	//-------------------------Atributos-------------------------
	//-----------------------------------------------------------
	 /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
	/**
	 * La clase que se quiere probar
	 */
    private SuperAndesAdministrador superAndes;
	
	//-----------------------------------------------------------
	//--------------Metodos de prueba acceso a la BD-------------
	//-----------------------------------------------------------
    /**
     * M�todo de prueba para acceso correcto a una base de datos
     */
    @Test
    public void normalAccessTest ()
  	{
  	  	try
		{
			log.info ("Probando el acceso a la base de datos con datos v�lidos (BD, credenciales, esquema");
			superAndes = new SuperAndesAdministrador(openConfig(CONFIG_TABLAS_A));
			log.info ("Conexi�n realizada correstamente");
			log.info ("Cerrando la conexi�n");			
			superAndes.cerrarUnidadPersistencia();
			log.info ("Conexi�n cerrada");
		}
		catch (Exception e)
		{
			log.info ("Prueba de acceso normal FALLO !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de acceso normal a la base de datos fall� !! Revise persistence.xml.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepci�n";
			fail (msg);
		}
  	}
	
	//-----------------------------------------------------------
	//-----------------Metodos de Configuracion------------------
	//-----------------------------------------------------------
    private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontro un archivo de configuracion valido: " + tipo);
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			//log.info ("No se encontro un archivo de configuracion valido");			
			JOptionPane.showMessageDialog(null, "No se encontro un archivo de configuracion de interfaz valido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}	
    
    private JsonObject openConfig (String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontr� un archivo de configuraci�n de tablas v�lido");
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			log.info ("NO se encontr� un archivo de configuraci�n v�lido");			
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de tablas v�lido: ", "TipoBebidaTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
