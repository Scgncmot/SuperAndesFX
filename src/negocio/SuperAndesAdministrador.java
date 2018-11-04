/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.apache.log4j.helpers.Loader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import interfazsuperandes.PanelesSucursal.PanelCategoriaController;
import interfazsuperandes.PanelesSucursal.PanelClienteController;
import interfazsuperandes.PanelesSucursal.PanelProductoController;
import interfazsuperandes.PanelesSucursal.PanelPromocionController;
import interfazsuperandes.PanelesSucursal.PanelProveedorController;
import interfazsuperandes.PanelesSucursal.PanelSucursalController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

	private PanelSucursalController panelSucursal;

	private PanelPromocionController panelPromocion;
	
	private PanelCategoriaController panelCategoria;


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
	private Button butCategorias;




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

	public void cargarClientes(){

		lista.clear();    	

		List<Object[]> clientes = pp.darElementos(PersistenciaSuperAndes.darTablaCliente());

		for (Object[] objects : clientes) {    		

			lista.add("Tipo de documento: "+objects[0]+"      :      Numero de documento: "+objects[1]+"      :     Nombre: "+objects[2]+"      :      Correo: "+objects[3]);
		}    			

		panelCliente.getListView().setItems(lista);
	}	

	public void crearClienteJuridico(String tipoDocumento, String numDocumento, String nombre, String correo, String direccion) {

		pp.registrarPersonaJuridica(tipoDocumento, numDocumento, nombre, correo, direccion);

		cargarClientes();

	}

	public void crearClienteNatural(String tipoDocumento, String numDocumento, String nombre, String correo) {

		pp.registrarCliente(tipoDocumento, numDocumento, nombre, correo);

		cargarClientes();

	}


	public void eliminarCliente(String tipoDocumento, String numDocumento) {

		pp.eliminarCliente(tipoDocumento, numDocumento);

		cargarClientes();

	}


	public String buscarDireccionPersonaJuridica(String numDoc) {

		return pp.buscarDireccionPersonaJuridica(numDoc);

	}


	public void modificarClienteJuridico(String numDocAntiguo, String numDocNuevo, String nombre, String correo, String direccion) {

		pp.modificarClienteJuridico(numDocAntiguo,numDocNuevo, nombre, correo, direccion);

		cargarClientes();
	}


	public void modificarClienteNatural(String tipoDocAntiguo,String tipoDoc,String numDocAntiguo,String numDoc,String nombre,String correo) {

		pp.modificarClienteNatural(tipoDocAntiguo,tipoDoc,numDocAntiguo,numDoc,nombre,correo);

		cargarClientes();

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

	public List<Object[]> darProductos() 
	{
		return pp.darElementos(PersistenciaSuperAndes.darTablaProducto());
	}

	public List<Object[]> darProductosSucursal(long idSucursal) 
	{
		return pp.darProductosSucursal(idSucursal);
	}


	public void crearProducto(String barcode, String nombre, String presentacion, String marca, String cantidad, String medida,
			String especificacion, String categoria) {

		pp.registrarProducto(barcode, nombre, presentacion, marca, Integer.parseInt(cantidad), medida, especificacion, categoria);

		cargarProductos();


	}

	public String obtenerCodigoDeBarrasPorNombreProducto(String nomProducto) {

		return (String) pp.obtenerCodigoDeBarrasPorProducto(nomProducto)[0];
	}



	public void eliminarProducto(String barcode) {

		pp.eliminarProducto(barcode);

		cargarProductos();

	}




	//....................................
	//........... SUCURSALES .............
	//....................................
	@FXML
	public void actualizarPanelSucursales(Event actionEvent) throws IOException{

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/interfazsuperandes/PanelesSucursal/PanelSucursales.fxml"));

		Parent rightSide = loader.load();	

		panelSucursal = loader.getController();

		BorderPane.setAlignment(rightSide, Pos.CENTER);	

		cargarSucursales();

		borderPanelPrincipal.setRight( rightSide );

	}

	@FXML
	public void cargarSucursales(){

		lista.clear();    	

		List<Object[]> sucursales = pp.darElementos(PersistenciaSuperAndes.darTablaSucursal());

		for (Object[] objects : sucursales) {    		

			lista.add("Id: "+objects[0]+"      :      Nombre: "+objects[1]);
		}    			

		panelSucursal.getListViewSucursales().setItems(lista);
	}


	public List<Object[]> cargarBodegasSucursal(long idSucursal)
	{
		return pp.darBodegaSucursalPorId(idSucursal);		
	}

	public List<Object[]> darClientes()
	{
		return pp.darClientes();		
	}

	public List<Object[]> cargarEstantesSucursal(long idSucursal)
	{
		return pp.darEstanteSucursalPorId(idSucursal);
	}

	public void crearSucursal(String sucursal) 
	{	
		String nombre = sucursal.split("/")[0],
				segmentacion = sucursal.split("/")[1],
				tamanio = sucursal.split("/")[2],
				ciudad = sucursal.split("/")[3],
				direccion = sucursal.split("/")[4];		

		pp.registrarSucursal(nombre, segmentacion, Double.valueOf(tamanio), ciudad, direccion);

		cargarSucursales();
	}	

	public void eliminarSucursal(long id, String nombre) 
	{
		pp.eliminarSucursal(id, nombre);
		cargarSucursales();
	}

	public void eliminarBodegaPorIdSucursal(long idBodega, long idSucursal) 
	{
		pp.eliminarBodegaPorSucursalId(idBodega, idSucursal);
	}

	public void eliminarEstantePorIdSucursal(long idBodega, long idSucursal) 
	{
		pp.eliminarEstantePorSucursalId(idBodega, idSucursal);
	}

	public void eliminarProductoSucursalPorIds(long idSucursal, String codigoBarras) 
	{
		pp.eliminarProductoSucursalPorIds(idSucursal, codigoBarras);
	}

	public void registrarVenta(long sucursal, String tipodocumento, String documento, String[] codigosProductos,
			String[] cantidad, Double precioTotal, Date fecha)
	{
		pp.registrarVenta(sucursal, tipodocumento, documento, codigosProductos, cantidad, precioTotal, fecha);
	}

	public void modificarSucursal(String sucursal, String nombreActual ) 
	{	
		/*String[] arreglo = sucursal.split("/");
		for(String a : arreglo)
			System.out.println("Esto es: " + a);*/

		String nombreNuevo = sucursal.split("/")[0];
		String segmentacion = sucursal.split("/")[1];
		String tamano = sucursal.split("/")[2];
		String ciudad = sucursal.split("/")[3];
		String direccion = sucursal.split("/")[4];

		pp.modificarSucursalPorNombre(nombreActual, nombreNuevo, segmentacion, Double.valueOf(tamano), ciudad, direccion);
		cargarSucursales();

	}

	public Object[] darSucursalPorNombre(String nombre)
	{
		return pp.darSucursalPorNombre(nombre);
	}

	public List<String> darListaSucursales() 
	{

		List<Object[]> lista = pp.darElementos(PersistenciaSuperAndes.darTablaSucursal());

		List<String> nombreSucursal = new ArrayList<>();

		for (Object[] objects : lista) 
			nombreSucursal.add((String) objects[1]);	

		return nombreSucursal;
	}

	public List<Object[]> darDineroRecolectado(Date fechaInicio,  Date fechaFin)
	{
		return pp.dineroRecolectado(fechaInicio, fechaFin);
	}

	public void agregarBodegasSucursal(long idSucursal, long idCategoria, Double volumenMaximo, Double pesoMaximo)
	{	
		pp.registrarBodega(idSucursal, idCategoria, volumenMaximo, pesoMaximo);
	}

	public void agregarEstantesSucursal(long idSucursal, long idCategoria, Double volumenMaximo, Double pesoMaximo,
			Integer nivelDeAbastecimiento)
	{		
		pp.registrarEstante(idSucursal, idCategoria, volumenMaximo, pesoMaximo, nivelDeAbastecimiento);
	}

	public void agregarProductosSucursal(long idSucursal, String codigoBarras, Double precioUnitario, Double precioUnidadMedida,
			Integer nivelDeReorden, Integer cantidadRecompra)
	{		
		pp.registrarProductoSucursal(idSucursal, codigoBarras, precioUnitario, precioUnidadMedida, nivelDeReorden, cantidadRecompra);
	}


	//....................................
	//........... PROMOCIONES ............
	//....................................


	@FXML
	public void actualizarPanelPromociones(Event actionEvent) throws IOException{

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/interfazsuperandes/PanelesSucursal/PanelPromociones.fxml"));

		Parent rightSide = loader.load();	

		panelPromocion = loader.getController();

		BorderPane.setAlignment(rightSide, Pos.CENTER);		

		cargarPromociones();

		borderPanelPrincipal.setRight( rightSide );

	}

	public void cargarPromociones(){

		lista.clear();    	

		List<Object[]> promociones = pp.darElementos(PersistenciaSuperAndes.darTablaPromocion());

		for (Object[] objects : promociones) {    		

			lista.add("Codigo: "+objects[0]+"     :    Tipo: "+objects[1]+"     :    Fecha vencimiento: "+objects[2]);
		}    			

		panelPromocion.getListViewPromocion().setItems(lista);
	}	

	public void crearPromocion(String tipoPromocion, String[] datos, Date fecha) {

		Timestamp fechaVencimientoPromocion = new Timestamp(fecha.getTime());

		switch (tipoPromocion) {

		case PanelPromocionController.PAGUENLLEVEM:

			pp.registrarPromocionPagueNLleveM(datos[0], fechaVencimientoPromocion, Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));

			break;

		case PanelPromocionController.PAGUEXLLEVEY:

			pp.registrarPromocionPagueXLleveY(datos[0], fechaVencimientoPromocion, Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));

			break;

		case PanelPromocionController.PAQUETEPRODUCTOS:
			
			pp.registrarPromocionPaqueteProductos(fechaVencimientoPromocion, datos[0], datos[1], datos[2], datos[3], Integer.parseInt(datos[4]), datos[5], datos[6], Integer.parseInt(datos[7]), datos[8]);

			break;

		case PanelPromocionController.PAGUE1LLEVE2CONDESCUENTO:

			pp.registrarPromocionPague1Lleve2doDesc(datos[0], fechaVencimientoPromocion, Double.parseDouble(datos[1]));

			break;

		}

		cargarPromociones();

	}



	//....................................
	//.............. PEDIDOS .............
	//....................................




	//....................................
	//........... VENTAS .................
	//....................................




	//....................................
	//........... CATEGORIAS .............
	//....................................
	@FXML
	public void cargarCategorias(){

		lista.clear();    	

		List<Object[]> categoria = pp.darElementos(PersistenciaSuperAndes.darTablaCategoria());

		for (Object[] objects : categoria) {    		

			lista.add("Id: "+objects[0]+"      :      Tipo Categoria: "+objects[1]);
		}    			

		panelCategoria.getListViewCategoria().setItems(lista);
	}	
	
	@FXML
	public void actualizarPanelCategoria(Event event) throws IOException{

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/interfazsuperandes/PanelesSucursal/PanelCategorias.fxml"));

		Parent rightSide = loader.load();	

		panelCategoria = loader.getController();

		BorderPane.setAlignment(rightSide, Pos.CENTER);		

		cargarCategorias();

		borderPanelPrincipal.setRight( rightSide );

	}

	public List<String> darListaCategorias() {

		List<Object[]> lista = pp.darElementos(PersistenciaSuperAndes.darTablaCategoria());

		List<String> retorno = new ArrayList<>();

		for (Object[] objects : lista) 
			retorno.add(objects[0]+"-"+objects[1]);

		return retorno;
	}
	
	public void crearCategoria(String tipoCategoria)
	{
		pp.registrarCategoria(tipoCategoria);
		cargarCategorias();
	}
	
	public void modificarCategoria(long id, String nombreNuevo, String nombreViejo)
	{
		pp.modificarCategoria(id, nombreNuevo, nombreViejo);
		cargarCategorias();
	}
	
	public List<Object[]> darProductosCategoria(long idCategoria)
	{
		return pp.darProductosCategoria(idCategoria);
	}
	
	public void eliminarCategoria(String tipoCategoria)
	{
		pp.eliminarCategoria(tipoCategoria);
		cargarCategorias();
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