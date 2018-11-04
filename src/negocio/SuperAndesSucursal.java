package negocio;

import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import interfazsuperandes.SuperAndesSucursalInterfaz;
import persistencia.PersistenciaSuperAndes;

public class SuperAndesSucursal {

	private PersistenciaSuperAndes pp;

	private SuperAndesSucursalInterfaz interfaz;

	private static Logger log = Logger.getLogger(SuperAndesSucursal.class.getName());

	private long idcarro;

	private String tipodocumentoCliente;

	private String documentoCliente;


	public SuperAndesSucursal(SuperAndesSucursalInterfaz superAndesSucursalInterfaz) {

		interfaz = superAndesSucursalInterfaz;

		JsonObject tableConfig = openConfig ("Tablas BD", "./resources/TablasBD_A.json");

		pp =  PersistenciaSuperAndes.getInstance (tableConfig);	

	}	

	public void crearCarrito(String tipoDocumentoCliente, String numDocumentoCliente)
	{
		this.tipodocumentoCliente = tipoDocumentoCliente;

		this.documentoCliente = numDocumentoCliente;

		idcarro = pp.registrarCarrito(tipoDocumentoCliente, numDocumentoCliente);		
	}


	public void eliminarCarrito()
	{
		pp.eliminarCarrito(idcarro, tipodocumentoCliente, documentoCliente);
	}	

	//Es un join
	public List<Object[]> darProductosCarrito()
	{
		return pp.darProductosCarrito(idcarro);
	}

	public void registrarProductoCarrito(String codigoBarras)
	{
		pp.registrarProductoCarrito(idcarro, codigoBarras);
	}

	//TODO Falta el de Eliminar producto carrito de compras.	



	public void crearClienteJuridico(String tipoDocumento, String numDocumento, String nombre, String correo, String direccion) {

		pp.registrarPersonaJuridica(tipoDocumento, numDocumento, nombre, correo, direccion);

	}

	public void crearClienteNatural(String tipoDocumento, String numDocumento, String nombre, String correo) {

		pp.registrarCliente(tipoDocumento, numDocumento, nombre, correo);

	}


	//....................................
	//....... METODOS ADICIONALES ........
	//....................................




	public void mostrarProductos() {

		interfaz.mostrarProductos();

	}


	public List<String> darListaProductos() {

		List<Object[]> lista = pp.darElementos(PersistenciaSuperAndes.darTablaProducto());

		List<String> nomProductos = new ArrayList<>();

		for (Object[] objects : lista) {

			nomProductos.add((String) objects[1]);

		}

		return nomProductos;

	}

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




}