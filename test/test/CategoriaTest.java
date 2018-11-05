package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import negocio.SuperAndesAdministrador;

public class CategoriaTest {

	//-----------------------------------------------------------
	//------------------------Constantes-------------------------
	//-----------------------------------------------------------
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(BodegaTest.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./resources/config/TablasBD_A.json"; 
	
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
  	//----------------------Metodos de prueba--------------------
  	//-----------------------------------------------------------
    @Test
	public void CRDBodegaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Bodega");
			superAndes = new SuperAndesAdministrador (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Bodega incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Bodega incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de SuperAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}		
    	try
		{
			// Lectura vaica			
    		List<Object[]> lista = superAndes.darTabla("Bodega");
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura con 1 dato			
			
			Double volumenMaximo = 1000.0, pesoMaximo = 1000.0;
			superAndes.crearCategoria2("Comida");
			superAndes.crearSucursal2("Sucursal1/segmentacion1/1000/bogota/ak72");	
			
			List<Object[]> cate = superAndes.darTabla("Categoria");
			long categoria =  ((BigDecimal) cate.get(0)[0]).longValue();
			String nombreSuc = superAndes.darListaSucursales().get(0);
			System.out.println(nombreSuc);
			long a =  ((BigDecimal) superAndes.darSucursalPorNombre(nombreSuc)[0]).longValue();
			System.out.println(superAndes.darSucursalPorNombre(nombreSuc)[0]);
			System.out.println(a);
			
			superAndes.agregarBodegasSucursal(a, categoria, volumenMaximo, pesoMaximo);
			Object[] bodega = new Object[3];
			bodega[0] = a;
			bodega[1] = volumenMaximo;
			bodega[2] = pesoMaximo;			
			
		
			lista = superAndes.darTabla("Bodega");
			assertEquals ("Debe haber un tipo de bebida creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", bodega[0] , ((BigDecimal)lista.get(0)[1]).longValue());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", 1, ((BigDecimal)lista.get(0)[2]).longValue());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", bodega[1], ((BigDecimal)lista.get(0)[3]).longValue());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", bodega[2], ((BigDecimal)lista.get(0)[4]).longValue());
			

			// Lectura con 2 datos			
			superAndes.agregarBodegasSucursal(a, categoria, volumenMaximo, pesoMaximo);
			Object[] bodega2 = new Object[3];
			bodega2[0] = a;
			bodega2[1] = volumenMaximo;
			bodega2[2] = pesoMaximo;			
			lista = superAndes.cargarBodegasSucursal(a);
			assertEquals ("Debe haber un tipo de bebida creado !!", 2, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", bodega2[0] , lista.get(1)[0]);
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", 2, lista.get(1)[0]);
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", bodega2[1], lista.get(1)[2]);
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", bodega2[2], lista.get(1)[3]);

			// Eliminar un dato por Id
			/*long tbEliminados = parranderos.eliminarTipoBebidaPorId (tipoBebida1.getId ());
			assertEquals ("Debe haberse eliminado un tipo de bebida !!", 1, tbEliminados);
			lista = parranderos.darVOTiposBebida();
			assertEquals ("Debe haber un solo tipo de bebida !!", 1, lista.size ());
			assertFalse ("El primer tipo de bebida adicionado NO debe estar en la tabla", tipoBebida1.equals (lista.get (0)));
			assertTrue ("El segundo tipo de bebida adicionado debe estar en la tabla", tipoBebida2.equals (lista.get (0)));*/
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Bodega.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Bodega");
		}
		finally
		{
			superAndes.limpiarSuperAndes();
			superAndes.cerrarUnidadPersistencia();    		
		}
	}
//
//    /**
//     * Método de prueba de la restricción de unicidad sobre el nombre de TipoBebida
//     */
//	@Test
//	public void unicidadTipoBebidaTest() 
//	{
//    	// Probar primero la conexión a la base de datos
//		try
//		{
//			log.info ("Probando la restricción de UNICIDAD del nombre del tipo de bebida");
//			superAndes = new SuperAndesAdministrador (openConfig (CONFIG_TABLAS_A));
//		}
//		catch (Exception e)
//		{
////			e.printStackTrace();
//			log.info ("Prueba de UNICIDAD de Tipobebida incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
//			log.info ("La causa es: " + e.getCause ().toString ());
//
//			String msg = "Prueba de UNICIDAD de Tipobebida incompleta. No se pudo conectar a la base de datos !!.\n";
//			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
//			System.out.println (msg);
//			fail (msg);
//		}
//		
//		// Ahora si se pueden probar las operaciones
//		try
//		{
//			// Lectura de los tipos de bebida con la tabla vacía
//			List <VOTipoBebida> lista = parranderos.darVOTiposBebida();
//			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
//
//			// Lectura de los tipos de bebida con un tipo de bebida adicionado
//			String nombreTipoBebida1 = "Vino tinto";
//			VOTipoBebida tipoBebida1 = parranderos.adicionarTipoBebida (nombreTipoBebida1);
//			lista = parranderos.darVOTiposBebida();
//			assertEquals ("Debe haber un tipo de bebida creado !!", 1, lista.size ());
//
//			VOTipoBebida tipoBebida2 = parranderos.adicionarTipoBebida (nombreTipoBebida1);
//			assertNull ("No puede adicionar dos tipos de bebida con el mismo nombre !!", tipoBebida2);
//		}
//		catch (Exception e)
//		{
////			e.printStackTrace();
//			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla TipoBebida.\n";
//			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
//			System.out.println (msg);
//
//    		fail ("Error en las pruebas de UNICIDAD sobre la tabla TipoBebida");
//		}    				
//		finally
//		{
//			parranderos.limpiarParranderos ();
//    		parranderos.cerrarUnidadPersistencia ();    		
//		}
//	}
	
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
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "TipoBebidaTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
}
