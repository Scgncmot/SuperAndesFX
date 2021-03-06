package negocio;

import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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

	private long sucursalId;

	public SuperAndesSucursal(SuperAndesSucursalInterfaz superAndesSucursalInterfaz) {

		interfaz = superAndesSucursalInterfaz;

		JsonObject tableConfig = openConfig ("Tablas BD", "./resources/config/TablasBD_A.json");

		pp =  PersistenciaSuperAndes.getInstance (tableConfig);	
	}	

	public SuperAndesSucursal()
	{
		JsonObject tableConfig = openConfig ("Tablas BD", "./resources/config/TablasBD_A.json");
		pp =  PersistenciaSuperAndes.getInstance (tableConfig);
	}

	public SuperAndesSucursal(JsonObject json)
	{
		pp =  PersistenciaSuperAndes.getInstance (json);
	}

	public void crearCarrito(String tipoDocumentoCliente, String numDocumentoCliente)
	{
		this.tipodocumentoCliente = tipoDocumentoCliente;

		this.documentoCliente = numDocumentoCliente;

		idcarro = pp.registrarCarrito(tipoDocumentoCliente, numDocumentoCliente);		
	}


	public void eliminarCarrito()
	{
		List<Object[]> prodOb = darProductosCarrito(); 

		List<String> productosId = new ArrayList<>();

		for (Object[] objects : prodOb) {

			productosId.add((String) objects[0]);

		}

		for (String idproducto : productosId) {

			pp.devolverProducto(idcarro, idproducto, sucursalId);					
		}	


		pp.eliminarCarrito(idcarro, tipodocumentoCliente, documentoCliente);
	}	

	//Es un join
	public List<Object[]> darProductosCarrito()
	{
		return pp.darProductosCarrito(idcarro);
	}

	public void registrarProductoCarrito(String producto, String cantidad)
	{
		String codigoBarras = (String) pp.obtenerCodigoDeBarrasPorProducto(producto)[0];

		pp.registrarProductoCarrito(idcarro, codigoBarras, cantidad, sucursalId);		

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


	public List<String> darClientesConAlMenosUnaCompra(String codigoBarras, Date fechaInicial, Date fechaFinal, String ordenamiento) {

		List<Object[]> clientes = pp.darClientesConAlMenosUnaCompra(codigoBarras, fechaInicial, fechaFinal, this.sucursalId, ordenamiento);	
		List<String> clientesString = new ArrayList<>();	

		for (Object[] objects : clientes)
			clientesString.add("Tipo de Documento: " + objects[0]+", Numero de Documento: " + objects[1]+ 
					", Nombre: " + objects[2]+", Correo: " + objects[3]);	

		return clientesString;	
		
	}
	
	public List<String> darClientesSinCompras(String codigoBarras, Date fechaInicio, Date fechaFinal,String ordenamiento) {
		
		List<Object[]> clientes = pp.darClientesSinCompras(codigoBarras, fechaInicio, fechaFinal, this.sucursalId, ordenamiento);	
		List<String> clientesString = new ArrayList<>();	

		for (Object[] objects : clientes) 
			clientesString.add("Tipo de Documento: " + objects[0]+", Numero de Documento: " + objects[1]+ 
					", Nombre: " + objects[2]+", Correo: " + objects[3]);		

		return clientesString;	
	}



	public void setSucursal(long sucursal2) {

		sucursalId = sucursal2;		
	}



	public void mostrarProductos() {

		interfaz.mostrarProductos();

	}


	public List<String> darListaProductos() {

		List<Object[]> lista = pp.darProductosSucursal(sucursalId);

		List<String> nomProductos = new ArrayList<>();

		for (Object[] objects : lista) {

			nomProductos.add((String) objects[7]);
		}

		return nomProductos;

	}


	public List<String> darListaProductos2() {

		List<Object[]> lista = pp.darProductosSucursal(sucursalId);

		List<String> nomProductos = new ArrayList<>();

		for (Object[] objects : lista) {

			nomProductos.add(objects[1]+"-"+objects[7]);
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

	public void pagarCarrito() {
		// TODO Auto-generated method stub

	}

	

}