/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.apache.log4j.helpers.Loader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import interfazsuperandes.PanelesSucursal.PanelClienteController;
import interfazsuperandes.PanelesSucursal.PanelProductoController;
import interfazsuperandes.PanelesSucursal.PanelProveedorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import persistencia.PersistenciaSuperAndes;






/**
 * FXML Controller class
 *
 * @author s.carrero
 */
public class SuperAndesAdministrador implements Initializable {


	private static Logger log = Logger.getLogger(SuperAndesAdministrador.class.getName());

	private PersistenciaSuperAndes pp;

	private ObservableList<String> lista = FXCollections.observableArrayList();

	private PanelProveedorController panelProveedor;

	private PanelClienteController panelCliente;
	
	private PanelProductoController panelProducto;


	//Panel principal
	@FXML
	private BorderPane borderPanelPrincipal;
	@FXML
	private Button butProveedores;
	@FXML
	private Button butClientes;
	@FXML
	private Button butProductos;
	@FXML
	private Button butSucursales;
	@FXML
	private Button butPromociones;
	@FXML
	private Button butPedidos;
	@FXML
	private Button butVentas;
	@FXML
	private Button butCategorias;
	@FXML
	private Button butEstantes;
	@FXML
	private Button butBodegas;



	/**
	 * Initializes the controller class.
	 */

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		JsonObject tableConfig = openConfig ("Tablas BD", "./resources/TablasBD_A.json");

		pp =  PersistenciaSuperAndes.getInstance (tableConfig);

	}    	


	//....................................
	//........... PROVEEDORES ............
	//....................................

	@FXML
	public void actualizarPanelProveedores(Event actionEvent) throws IOException{

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/interfazsuperandes/PanelesSucursal/PanelProveedor.fxml"));

		Parent rightSide = loader.load();	

		panelProveedor = loader.getController();

		BorderPane.setAlignment(rightSide, Pos.CENTER);		

		cargarProveedores();

		borderPanelPrincipal.setRight( rightSide );

	}

	@FXML
	public void cargarProveedores(){

		lista.clear();    	

		List<Object[]> proveedores = pp.darElementos(PersistenciaSuperAndes.darTablaProveedor());

		for (Object[] objects : proveedores) {    		

			lista.add("Nombre: "+objects[1]+"      :      NIT: "+objects[0]);
		}    			

		panelProveedor.getListViewProveedores().setItems(lista);
	}	


	public void crearProveedor(String proveedor) {

		String nit = proveedor.split("/")[0];

		String nombre = proveedor.split("/")[1];

		pp.registrarProveedor(nit, nombre);

		cargarProveedores();

	}	

	public void eliminarProveedor(String proveedor) {

		pp.eliminarProveedorPorNombre(proveedor);

		cargarProveedores();

	}

	public void modificarProveedor(String nombreAntiguo, String nitAntiguo, String proveedor) {

		String nit = proveedor.split("/")[0];

		String nombre = proveedor.split("/")[1];


		pp.modificarProveedor(nombreAntiguo ,nitAntiguo ,nit, nombre);

		cargarProveedores();

	}

	public List<String> darProductosProveedor(String nitStr) {

		List<String> productosProveedor = pp.darProductosProveedor(nitStr);

		List<String> nombresProductos = new ArrayList<String>();	

		List<String> retorno = new ArrayList<>();

		for (String codigo : productosProveedor) {

			nombresProductos.add(pp.obtenerProductoPorCodigo(codigo));

		}

		for (int i = 0; i < productosProveedor.size(); i++) {

			retorno.add(nombresProductos.get(i)+" - "+productosProveedor.get(i));

		}

		return retorno;


	}


	public void agregarProductoProveedor(String nombreProducto, String nit, String calif, String prec) {

		Object[] codigo = pp.obtenerCodigoDeBarrasPorProducto(nombreProducto);

		String barCode = (String) codigo[0];	

		pp.registrarProductoAProveedor(barCode, nit, calif, prec);

	}


	//....................................
	//........... CLIENTES ...............
	//....................................

	@FXML
	public void actualizarPanelClientes(Event actionEvent) throws IOException{

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/interfazsuperandes/PanelesSucursal/PanelClientes.fxml"));

		Parent rightSide = loader.load();	

		panelCliente = loader.getController();

		BorderPane.setAlignment(rightSide, Pos.CENTER);		

		cargarClientes();

		borderPanelPrincipal.setRight( rightSide );

	}


	@FXML
	public void cargarClientes(){

		lista.clear();    	

		List<Object[]> clientes = pp.darElementos(PersistenciaSuperAndes.darTablaCliente());

		for (Object[] objects : clientes) {    		

			lista.add("Tipo de documento: "+objects[0]+"      :      Numero de documento: "+objects[1]+"      :     Nombre: "+objects[2]+"      :      Correo: "+objects[3]);
		}    			
		
		panelCliente.getListView().setItems(lista);
	}	




	//....................................
	//........... PRODUCTOS ..............
	//....................................
	
	
	@FXML
	public void actualizarPanelProductos(Event actionEvent) throws IOException{

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/interfazsuperandes/PanelesSucursal/PanelProductos.fxml"));

		Parent rightSide = loader.load();	

		panelProducto = loader.getController();

		BorderPane.setAlignment(rightSide, Pos.CENTER);		

		cargarProductos();

		borderPanelPrincipal.setRight( rightSide );

	}
	
	@FXML
	public void cargarProductos(){

		lista.clear();    	

		List<Object[]> productos = pp.darElementos(PersistenciaSuperAndes.darTablaProducto());

		for (Object[] objects : productos) {    		

			lista.add("Nombre: "+objects[1]+"     :    Codigo barras: "+objects[0]);
		}    			
		
		panelProducto.getListViewProductos().setItems(lista);
	}	



	public List<String> darListaProductos() {

		List<Object[]> lista = pp.darElementos(PersistenciaSuperAndes.darTablaProducto());

		List<String> nomProductos = new ArrayList<>();

		for (Object[] objects : lista) {

			nomProductos.add((String) objects[1]);

		}

		return nomProductos;

	}


	//....................................
	//........... SUCURSALES .............
	//....................................





	//....................................
	//........... PROMOCIONES ............
	//....................................




	//....................................
	//.............. PEDIDOS .............
	//....................................




	//....................................
	//........... VENTAS .................
	//....................................




	//....................................
	//........... CATEGORIAS .............
	//....................................














	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			//log.info ("No se encontro un archivo de configuracion valido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}
























}
