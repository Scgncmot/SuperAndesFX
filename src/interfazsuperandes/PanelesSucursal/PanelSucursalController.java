/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazsuperandes.PanelesSucursal;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Pair;
import negocio.SuperAndesAdministrador;
import negocio.SuperAndesLogin;
import persistencia.PersistenciaSuperAndes;

/**
 * FXML Controller class
 *
 * @author s.carrero
 */
public class PanelSucursalController implements Initializable {

	@FXML
    private ListView<String> listViewSucursales;

    @FXML
    private Button butEliminar;

    @FXML
    private Button butCrear;

    @FXML
    private Button butModificar;

    @FXML
    private Button butCrearBodega;

    @FXML
    private Button butCrearEstante;

    @FXML
    private Button butDarDinero;

    @FXML
    private Button butEliminarBodegas;

    @FXML
    private Button butAnadirProductos;

    @FXML
    private Button butEliminarEstantes;

    @FXML
    private Button butEliminarProductosSucursal;

    @FXML
    private Button butVerPedidosSucursal;

    @FXML
    private Button butCrearPedidoSucursal;

    @FXML
    private Button butRealizarVentaSucursal;

    @FXML
    private Button butVerVentasSucursal;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}


	public ListView<String> getListViewSucursales() {return listViewSucursales;}

	public void setListViewSucursales(ListView<String> listViewSucursales) 
		{this.listViewSucursales = listViewSucursales;	}	

	public Button getButEliminar() {return butEliminar;}

	public void setButEliminar(Button butEliminar) {this.butEliminar = butEliminar;}

	public Button getButCrear() {return butCrear;}

	public void setButCrear(Button butCrear) {this.butCrear = butCrear;}

	public Button getButModificar() {return butModificar;}

	public void setButModificar(Button butModificar) 
		{this.butModificar = butModificar;}

	public Button getButCrearBodega() {return butCrearBodega;}

	public void setButCrearBodega(Button butCrearBodega) 
		{this.butCrearBodega = butCrearBodega;}

	public Button getButCrearEstante() {return butCrearEstante;}

	public void setButCrearEstante(Button butCrearEstante) 
		{this.butCrearEstante = butCrearEstante;}

	public Button getButDarDinero() {return butDarDinero;}

	public void setButDarDinero(Button butDarDinero) 
		{this.butDarDinero = butDarDinero;}

	@FXML
	void crearSucursal(ActionEvent event) 
	{
		Dialog<?> dialog = new Dialog<>();
		dialog.setTitle("Crear Sucursal");
		dialog.setHeaderText("Crear Sucursal");
		dialog.initStyle(StageStyle.UTILITY);

		ButtonType crear = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(crear , ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField nombre = new TextField();
		nombre.setPromptText("Landcadia Holdings, Inc.");
		TextField segmentacion = new TextField();
		segmentacion.setPromptText("Familiar");
		TextField tamano = new TextField();
		tamano.setPromptText("1000");
		TextField ciudad = new TextField();
		ciudad.setPromptText("Madrid");
		TextField direccion = new TextField();
		direccion.setPromptText("9221 Fallview Street");

		grid.add(new Label("Ingrese el nombre de la sucursal:"), 0, 0);
		grid.add(nombre, 1, 0);
		grid.add(new Label("Ingrese la segmentacion de la sucursal"), 0, 1);
		grid.add(segmentacion, 1, 1);
		grid.add(new Label("Ingrese el tamano en m2 de la sucursal"), 0, 2);
		grid.add(tamano, 1, 2);
		grid.add(new Label("Ingrese en que ciudad se encuentra ubicada la sucursal"), 0, 3);
		grid.add(ciudad, 1, 3);
		grid.add(new Label("Ingrese la direccion de la sucursal"), 0, 4);
		grid.add(direccion, 1, 4);

		dialog.getDialogPane().setContent(grid);
		dialog.showAndWait().ifPresent(response -> {
		     if (response == crear) 
		     {
		    	 String sucursal = nombre.getText() + "/" + segmentacion.getText() + "/" +
		 				tamano.getText() + "/" + ciudad.getText() + "/" + direccion.getText();			

		 		SuperAndesLogin.admin.crearSucursal(sucursal);	
		     }
		 });
	}

	@FXML
	void eliminarSucursal(ActionEvent event) 
	{
		String nombre = listViewSucursales.getSelectionModel().getSelectedItem(); 
		//[1] = Id , [3] = Nombre
		String[] arreglo = nombre.split(": ");		
		/*for(String a : arreglo)
			System.out.println("Esto es: " + a);*/
		
		SuperAndesLogin.admin.eliminarSucursal(Long.parseLong(arreglo[1].trim()), arreglo[3]);
	}

	@FXML
	void modificarSucursal(ActionEvent event) 
	{
		String sucursal = listViewSucursales.getSelectionModel().getSelectedItem();
		String[] arreglo = sucursal.split(": ");	
		String nombreActual = arreglo[3].trim();

		//----------------------------------------------------------------
		//Se obtiene la informacion anterior para ponerla en el texto.
		Object[] datos = SuperAndesLogin.admin.darSucursalPorNombre(nombreActual);			
		Object segmentacionActual = datos[2];
		Object tamanoActual = datos[3];
		Object ciudadActual = datos[4];
		Object direccionActual = datos[5];	
		//----------------------------------------------------------------

		Dialog<?> dialog = new Dialog<>();
		dialog.setTitle("Modificar proveedor");
		dialog.setHeaderText("Modificar proveedor");
		dialog.initStyle(StageStyle.UTILITY);

		ButtonType modificar = new ButtonType("Modificar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(modificar, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField nombreNuevo = new TextField();
		nombreNuevo.setText((String) nombreActual); 
		TextField segmentacion = new TextField();
		segmentacion.setText((String) segmentacionActual);
		TextField tamano = new TextField();
		tamano.setText(tamanoActual + ""); /*That machete tho*/
		TextField ciudad = new TextField();
		ciudad.setText((String) ciudadActual);
		TextField direccion = new TextField();
		direccion.setText((String) direccionActual);

		grid.add(new Label("Ingrese el nuevo nombre:"), 0, 0);
		grid.add(nombreNuevo, 1, 0);
		grid.add(new Label("Ingrese la nueva segmentacion"), 0, 1);
		grid.add(segmentacion, 1, 1);
		grid.add(new Label("Ingrese el nuevo tamano en m2"), 0, 2);
		grid.add(tamano, 1, 2);
		grid.add(new Label("Ingrese la nueva ciudad"), 0, 3);
		grid.add(ciudad, 1, 3);
		grid.add(new Label("Ingrese la nueva direccion"), 0, 4);
		grid.add(direccion, 1, 4);

		dialog.getDialogPane().setContent(grid);
		dialog.showAndWait().ifPresent(response -> {
		     if (response == modificar) 
		     {
		    	 String sucursalNueva = nombreNuevo.getText() + "/" + segmentacion.getText() + "/" +
		 				tamano.getText() + "/" + ciudad.getText() + "/" + direccion.getText();	

		 		SuperAndesLogin.admin.modificarSucursal(sucursalNueva, nombreActual);
		     }
		});
	}

	@FXML
	void mostrarDineroRecolectado(ActionEvent event)
	{
		List<String> lista = SuperAndesLogin.admin.darListaSucursales();

		if(lista.isEmpty()) 
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No existen productos");
			alert.setHeaderText(null);
			alert.initStyle(StageStyle.UTILITY);
			alert.setContentText("No existen sucursales");
			alert.showAndWait();
		}
		else 
		{		
			Dialog<?> dialogPane = new Dialog();
			ButtonType button = new ButtonType("Siguiente", ButtonData.OK_DONE);
			dialogPane.setTitle("Dinero recolectado entre las sucursales");
			dialogPane.getDialogPane().getButtonTypes().addAll(button);	
			dialogPane.initStyle(StageStyle.UTILITY);
			GridPane grid = new GridPane();

			grid.setHgap(10);
			grid.setVgap(10);		

			DatePicker dPFechaInicio = new DatePicker();
			DatePicker dpFechaFinal = new DatePicker();			

			grid.add(new Label("Ingrese la fecha inicial:"), 0, 2);
			grid.add(dPFechaInicio, 1, 2);
			grid.add(new Label("Ingrese la fecha final:"), 0, 3);
			grid.add(dpFechaFinal, 1, 3);

			dialogPane.getDialogPane().setContent(grid);

			dialogPane.showAndWait();	

			LocalDate lDFechaInicio = dPFechaInicio.getValue();
			LocalDate lDFechaFinal = dpFechaFinal.getValue();

			Date fechaInicio = Date.from(lDFechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date fechaFinal = Date.from(lDFechaFinal.atStartOfDay(ZoneId.systemDefault()).toInstant());

			Dialog<?> dialogPane2 = new Dialog();
			ButtonType button2 = new ButtonType("OK", ButtonData.OK_DONE);
			dialogPane2.setTitle("Dinero recolectado entre las sucursales");
			dialogPane2.getDialogPane().getButtonTypes().addAll(button2);
			GridPane grid2 = new GridPane();
			grid2.setHgap(10);
			grid2.setVgap(10);	

			List<Object[]> valor = SuperAndesLogin.admin.
					darDineroRecolectado(fechaInicio , fechaFinal);

			List<String> mostrarDatos = new ArrayList<String>();

			for (Object[] objects : valor) 				
				mostrarDatos.add("Id de la sucursal: "+objects[0]+"  /   Total: "+objects[1]);			
			
			ObservableList<String> listaO = FXCollections.observableList(mostrarDatos);			
			ListView<String> vista = new ListView<String>();			
			vista.setItems(listaO);		
			
			grid2.add(vista, 0, 0);
			dialogPane2.getDialogPane().setContent(grid2);
			dialogPane2.showAndWait();
		}
	}

	@FXML
	void agregarBodegaSucursal(ActionEvent event) 
	{
		String sucursal = listViewSucursales.getSelectionModel().getSelectedItem();	
		//[1] = Id , [3] = Nombre
		String[] arreglo = sucursal.split(": ");
		
		long idSucursal = Long.valueOf(arreglo[1].trim());
		String nombreSucursal = arreglo[3].trim();

		Dialog<?> dialogPane = new Dialog();
		ButtonType registrar = new ButtonType("Registrar bodega", ButtonData.OK_DONE);
		dialogPane.getDialogPane().getButtonTypes().addAll(registrar);		
		dialogPane.setTitle("Agregar bodega a una sucursal");	
		dialogPane.initStyle(StageStyle.UTILITY);
		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);			

		GridPane grid2= new GridPane();
		grid.add(grid2, 1, 1);

		TextField categoria = new TextField();
		TextField volumenMaximo = new TextField();
		TextField pesoMaximo = new TextField();	

		grid2.add(new Label("Ingrese el id de la categoria de los productos que se almacenaran"), 1, 1);
		grid2.add(categoria, 2, 1);
		grid2.add(new Label("Ingrese el volumen maximo de la bodega"), 1, 2);
		grid2.add(volumenMaximo, 2, 2);		
		grid2.add(new Label("Ingrese el peso maximo de la bodega"), 1, 3);
		grid2.add(pesoMaximo, 2, 3);		

		dialogPane.getDialogPane().setContent(grid);
		dialogPane.showAndWait().ifPresent(response -> {
		     if (response == registrar) 
		     {
		    	 SuperAndesLogin.admin.
					agregarBodegasSucursal
						(idSucursal, Long.valueOf(categoria.getText()), Double.valueOf(volumenMaximo.getText()), Double.valueOf(pesoMaximo.getText()));
		     }
		});
	}

	@FXML
	void agregarEstanteSucursal(ActionEvent event) 
	{
		//Se obtiene la informacion de la sucursal
		String sucursal = listViewSucursales.getSelectionModel().getSelectedItem();	
		//[1] = Id , [3] = Nombre
		String[] arreglo = sucursal.split(": ");

		long idSucursal = Long.valueOf(arreglo[1].trim());
		String nombreSucursal = arreglo[3].trim();

		Dialog<?> dialogPane = new Dialog();
		ButtonType registrar = new ButtonType("Registrar estante", ButtonData.OK_DONE);
		dialogPane.getDialogPane().getButtonTypes().addAll(registrar);		
		dialogPane.initStyle(StageStyle.UTILITY);
		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);
		
		GridPane grid2= new GridPane();
		grid.add(grid2, 1, 1);

		TextField categoria = new TextField();
		TextField volumenMaximo = new TextField();
		TextField pesoMaximo = new TextField();	
		TextField nivelAbastecimiento = new TextField();	

		grid2.add(new Label("Ingrese el id de la categoria de los productos que se almacenaran"), 1, 1);
		grid2.add(categoria, 2, 1);
		grid2.add(new Label("Ingrese el volumen maximo del estante"), 1, 2);
		grid2.add(volumenMaximo, 2, 2);		
		grid2.add(new Label("Ingrese el peso maximo del estante"), 1, 3);
		grid2.add(pesoMaximo, 2, 3);	
		grid2.add(new Label("Ingrese nivel de abastecimiento"), 1, 4);
		grid2.add(nivelAbastecimiento, 2, 4);

		dialogPane.getDialogPane().setContent(grid);
		dialogPane.showAndWait().ifPresent(response -> {
		     if (response == registrar) 
		     {	
		    	 SuperAndesLogin.admin.
					agregarEstantesSucursal
						(idSucursal, Long.valueOf(categoria.getText()), Double.valueOf(volumenMaximo.getText()),
								Double.valueOf(pesoMaximo.getText()), Integer.valueOf(nivelAbastecimiento.getText()));
		     }
		});
	}  
	
	@FXML
    void eliminarBodegasSucursal(ActionEvent event) 
	{
		//Se obtiene la informacion de la sucursal
		String sucursal = listViewSucursales.getSelectionModel().getSelectedItem();	
		//[1] = Id , [3] = Nombre
		String[] arreglo = sucursal.split(": ");

		long idSucursal = Long.valueOf(arreglo[1].trim());
		String nombreSucursal = arreglo[3].trim();
		
		Dialog<?> dialogPane = new Dialog();
		ButtonType button = new ButtonType("Borrar seleccion", ButtonData.OK_DONE);
		dialogPane.setTitle("Bodegas");
		dialogPane.getDialogPane().getButtonTypes().addAll(button, ButtonType.CANCEL);	
		dialogPane.initStyle(StageStyle.UTILITY);
		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);		
		
		List<Object[]> valor = SuperAndesLogin.admin.cargarBodegasSucursal(idSucursal);
		List<String> mostrarDatos = new ArrayList<String>(); /*Informacion que se muestra*/
			
		/*Agrega a la lista los datos de la bodega*/
		for (Object[] objects : valor)	
			mostrarDatos.add("Id de la bodega:   "+ objects[0] + "   |   Volumen Actual:   " + objects[3] +
					"   |   Volumen Maximo:   " + objects[4] + "   |   Peso Actual:   " + objects[5] + 
					"   |   Peso Maximo:   " + objects[6]);			
		
		ObservableList<String> datos = FXCollections.observableList(mostrarDatos);		
		ListView<String> vista = new ListView<String>();		
		vista.setItems(datos);/*Lista de Bodegas*/	
		grid.add(vista, 0, 0);
		
		dialogPane.getDialogPane().setContent(grid);	
		dialogPane.showAndWait().ifPresent(response -> {
		     if (response == button) {
		    	String bodega = vista.getSelectionModel().getSelectedItem();
		 		String[] arreglo2 = bodega.split("   ");
		 		/*for(String a: arreglo2)
		 			System.out.println("Esto es: " + a);*/
		 		
		 		SuperAndesLogin.admin.eliminarBodegaPorIdSucursal(Long.valueOf(arreglo2[1].trim()), idSucursal);	
		     }
		 });
    }

    @FXML
    void eliminarEstantesSucursal(ActionEvent event) 
    {
    	//Se obtiene la informacion de la sucursal
		String sucursal = listViewSucursales.getSelectionModel().getSelectedItem();	
		//[1] = Id , [3] = Nombre
		String[] arreglo = sucursal.split(": ");
		
		long idSucursal = Long.valueOf(arreglo[1].trim());
		String nombreSucursal = arreglo[3].trim();
		
		Dialog<?> dialogPane = new Dialog();
		ButtonType eliminar = new ButtonType("Borrar seleccion", ButtonData.OK_DONE);
		dialogPane.setTitle("Estantes");
		dialogPane.getDialogPane().getButtonTypes().addAll(eliminar, ButtonType.CANCEL);	
		dialogPane.initStyle(StageStyle.UTILITY);
		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);		
		
		List<Object[]> valor = SuperAndesLogin.admin.cargarEstantesSucursal(idSucursal);
		List<String> mostrarDatos = new ArrayList<String>(); /*Informacion que se muestra*/
			
		/*Agrega a la lista los datos del estante*/
		for (Object[] objects : valor)	
			mostrarDatos.add("Id del estante:   "+ objects[0] + "   |   Volumen Actual:   " + objects[3] +
					"   |   Volumen Maximo:   " + objects[4] + "   |   Peso Actual: " + objects[5] + 
					"   |   Peso Maximo: " + objects[6] + "   |   Nivel de abastecimiento: " + objects[7]);			
		
		ObservableList<String> datos = FXCollections.observableList(mostrarDatos);		
		ListView<String> vista = new ListView<String>();		
		vista.setItems(datos);/*Lista de Estantes*/			
		grid.add(vista, 0, 0);
		
		dialogPane.getDialogPane().setContent(grid);		
		dialogPane.showAndWait().ifPresent(response -> {
		     if (response == eliminar) {
		    	String estante = vista.getSelectionModel().getSelectedItem();
		 		String[] arreglo2 = estante.split("   ");
		 		/*for(String a: arreglo2)
		 			System.out.println("Esto es: " + a);*/
		 		
		 		SuperAndesLogin.admin.eliminarEstantePorIdSucursal(Long.valueOf(arreglo2[1].trim()), idSucursal);
		     }
		 });
    }
    
    @FXML
    void anadirProductosSucursal(ActionEvent event) 
    {		
		List<String> lista = SuperAndesLogin.admin.darListaProductos();

		if(lista.isEmpty()) 
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No existen productos");
			alert.setHeaderText(null);
			alert.setContentText("No existen productos en la base de datos");

			alert.showAndWait();
		}
		else 
		{			
			//Se obtiene la informacion de la sucursal
			String sucursal = listViewSucursales.getSelectionModel().getSelectedItem();	
			//[1] = Id , [3] = Nombre
			String[] arreglo = sucursal.split(": ");

			long idSucursal = Long.valueOf(arreglo[1].trim());
			String nombreSucursal = arreglo[3].trim();

			Dialog<?> dialogPane = new Dialog();
			ButtonType anadirProducto = new ButtonType("Registrar producto", ButtonData.OK_DONE);
			dialogPane.getDialogPane().getButtonTypes().addAll(anadirProducto, ButtonType.CANCEL);			
			GridPane grid = new GridPane();
			
			grid.setHgap(10);
			grid.setVgap(10);		
			
			grid.add(new Label("Seleccione el producto a asociar:"), 0, 0);			
			List<Object[]> valor = SuperAndesLogin.admin.darProductos();
			List<String> mostrarDatos = new ArrayList<String>(); /*Informacion que se muestra*/
			
			/*Agrega a la lista los datos del estante*/
			for (Object[] objects : valor)	
				mostrarDatos.add(objects[1] + "   |   Codigo de Barras:   " + objects[0]);	
			
			ObservableList<String> datos = FXCollections.observableList(mostrarDatos);	
			ListView<String> vista = new ListView<String>();	
			vista.setItems(datos);/*Lista de Productos*/	
			
			grid.add(vista, 0, 1);
			
			TextField precioUnitario = new TextField();
			grid.add(new Label("Ingrese el precio unitario"), 0, 2);
			grid.add(precioUnitario, 1, 2);
			TextField precioUnidadMedida = new TextField();
			grid.add(new Label("Ingrese el precio por unidad de medida"), 0, 3);
			grid.add(precioUnidadMedida, 1, 3);
			TextField nivelDeReorden = new TextField();
			grid.add(new Label("Ingrese el nivel de reorden"), 0, 4);
			grid.add(nivelDeReorden, 1, 4);
			TextField cantidadRecompra = new TextField();
			grid.add(new Label("Ingrese la cantidad de recompra"), 0, 5);
			grid.add(cantidadRecompra, 1, 5);		
			
			dialogPane.getDialogPane().setContent(grid);			
			dialogPane.showAndWait().ifPresent(response -> 
			{
				if(response == anadirProducto)
				{
					String producto = vista.getSelectionModel().getSelectedItem();
					/*[0] = Nombre, [3] = Codigo de barras*/
					String[] arreglo2 = producto.split("   ");	
					/*for(String a: arreglo2)
						System.out.println("Esto es: " + a);*/
					
					SuperAndesLogin.admin.
						agregarProductosSucursal
							(idSucursal, arreglo2[3], Double.valueOf(precioUnitario.getText()), Double.valueOf(precioUnidadMedida.getText()),
									Integer.valueOf(nivelDeReorden.getText()), Integer.valueOf(cantidadRecompra.getText()));	
				}			
			});				
		}
    }
    
    @FXML
    void eliminarProductosSucursal(ActionEvent event) 
    {
    	//Se obtiene la informacion de la sucursal
		String sucursal = listViewSucursales.getSelectionModel().getSelectedItem();
		//[1] = Id , [3] = Nombre
		String[] arreglo = sucursal.split(": ");

		long idSucursal = Long.valueOf(arreglo[1].trim());
		String nombreSucursal = arreglo[3].trim();
		
		Dialog<?> dialogPane = new Dialog();
		ButtonType eliminar = new ButtonType("Borrar seleccion", ButtonData.OK_DONE);
		dialogPane.setTitle("Productos");
		dialogPane.getDialogPane().getButtonTypes().addAll(eliminar, ButtonType.CANCEL);	
		dialogPane.initStyle(StageStyle.UTILITY);
		dialogPane.setHeight(600);
		dialogPane.setWidth(200);
		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);		
		
		List<Object[]> valor = SuperAndesLogin.admin.darProductosSucursal(idSucursal);
		List<String> mostrarDatos = new ArrayList<String>(); /*Informacion que se muestra*/
		
		/*Agrega a la lista los datos del estante*/
		for (Object[] objects : valor)	
			mostrarDatos.add(objects[7] + "   |   Codigo de Barras:   " + objects[1]);	
		
		ObservableList<String> datos = FXCollections.observableList(mostrarDatos);	
		ListView<String> vista = new ListView<String>();	
		vista.setItems(datos);/*Lista de Productos*/	
		
		grid.add(vista, 0, 0);
		
		dialogPane.getDialogPane().setContent(grid);		
		dialogPane.showAndWait().ifPresent(response -> {
		     if (response == eliminar) 
		     {
		    	String producto = vista.getSelectionModel().getSelectedItem();
		 		/*El arreglo tiene 4 elementos.[0] = Nombre, [3] = Codigo de barras*/
		 		String[] arreglo2 = producto.split("   ");	
		 		/*for(String a: arreglo2)
		 			System.out.println("Esto es: " + a);*/
		 		
		 		SuperAndesLogin.admin.eliminarProductoSucursalPorIds(idSucursal, arreglo2[3]);
		     }
		 });
	}	
    
    @FXML
    void realizarVentaSucursal(ActionEvent event) 
    {
    	String sucursalActual = listViewSucursales.getSelectionModel().getSelectedItem();	
		//[1] = Id , [3] = Nombre
		String[] arreglo = sucursalActual.split(": ");
		
		long idSucursal = Long.valueOf(arreglo[1].trim());
		
		//Solo necesito el TD y el numero documento
		List<Object[]> clientes = SuperAndesLogin.admin.darClientes();
		List<String> infoCliente = new ArrayList<String>();		
		//Hacer lista clientes
		for(Object[] a : clientes)
			infoCliente.
				add("Nombre cliente:   "+ a[2] + "   |   Tipo Documento:   " + a[0]  + "   |   NumeroDocumento:   " + a[1]);
		
		ObservableList<String> datosCliente = FXCollections.observableList(infoCliente);	
		ListView<String> vistaCliente = new ListView<String>();
		vistaCliente.setItems(datosCliente);
		
		//Solo necesito Nombre y Codigo de Barras
		List<Object[]> productosSucursal = SuperAndesLogin.admin.darProductosSucursal(idSucursal);
		List<String> infoProductoSucursal = new ArrayList<String>();
		//Hacer lista de productos
		for(Object[] a : productosSucursal)
			infoProductoSucursal.
				add("Nombre:   " + a[7] + "   |   Codigo de Barras:   " + a[1] + "   |   PrecioUnitario:   " + a[2]);
		
		ObservableList<String> datosProducto = FXCollections.observableArrayList(infoProductoSucursal);
		ListView<String> vistaProducto = new ListView<String>();
		vistaProducto.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		vistaProducto.setItems(datosProducto);
		
		Dialog<?> dialogPane = new Dialog();
		ButtonType button = new ButtonType("Aceptar", ButtonData.OK_DONE);
		dialogPane.getDialogPane().getButtonTypes().addAll(button , ButtonType.CANCEL);		
		dialogPane.setTitle("Seleccionar cliente y productos a vender");	
		dialogPane.initStyle(StageStyle.UTILITY);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);	
		
		grid.add(new Label("Clientes"), 0, 0);
		grid.add(vistaCliente, 0, 1);	
		grid.add(new Label("Productos ofrecidos"), 1, 0);
		grid.add(vistaProducto, 1, 1);	
		grid.add(new Label("Ingrese la cantidad de los productos, separados por comas"), 0, 2);
		TextField cantidades = new TextField();
		grid.add(cantidades, 1, 2);

		//Informacion productos
		List<String> codigosDeBarrasList = new ArrayList<>();;
		List<String> nombreProd = new ArrayList<>();
		List<String> preciosUni = new ArrayList<>();
		
		dialogPane.getDialogPane().setContent(grid);				
		dialogPane.showAndWait().ifPresent(response -> 
		{
			if (response == button) 
			{
				String clienteSeleccionado = vistaCliente.getSelectionModel().getSelectedItem();

				// [1] = Nombre, [4] = Tipo Doc, [7] = Numero Doc
				String[] infoClienteSeleccionada = clienteSeleccionado.split("   ");

				// [1] = Nombre, [4] = Codigo de Barras , [7] = Precio Unitario
				ObservableList<String> productos = vistaProducto.getSelectionModel().getSelectedItems();
				for (int i = 0; i < productos.size() ; i++) 
				{
					//codigosDeBarras.add(a.split("   ")[3]);
					codigosDeBarrasList.add(productos.get(i).split("   ")[4]);
					nombreProd.add(productos.get(i).split("   ")[1]);
					preciosUni.add(productos.get(i).split("   ")[7]);					
				}			
				Date fechaActual = new Date();
				//System.out.println("Fecha actual: " + fechaActual);
				
				String[] cantidadProductoSelec = cantidades.getText().split(",") ;
				/*for(String a : cantidadProductoSelec)
					System.out.println(a);*/		
				
				String[] codigosDeBarras = codigosDeBarrasList.toArray(new String [0]);
				
				Double precioTotal = 0.0;					
				//Los precios y la cantidad deben tener el mismo tamaño de array, asi que no hay problema.
				for (int i = 0; i < preciosUni.size(); i++) 				
					precioTotal += Double.valueOf(preciosUni.get(i)) * Double.valueOf(cantidadProductoSelec[i]);				
				
				SuperAndesLogin.admin.
					registrarVenta(idSucursal, infoClienteSeleccionada[4], infoClienteSeleccionada[7], 
							codigosDeBarras, cantidadProductoSelec, precioTotal, fechaActual);
			}
		});		
    }  
    
    @FXML
    void verVentasSucursal(ActionEvent event) 
    {
    	//Se obtiene la informacion de la sucursal
		String sucursal = listViewSucursales.getSelectionModel().getSelectedItem();	
		//[1] = Id , [3] = Nombre
		String[] arreglo = sucursal.split(": ");

		long idSucursal = Long.valueOf(arreglo[1].trim());
		String nombreSucursal = arreglo[3].trim();
		
		Dialog<?> dialogPane = new Dialog();
		ButtonType button = new ButtonType("Ver productos vendidos", ButtonData.OK_DONE);
		dialogPane.setTitle("Ventas que ha realizado la sucursal");
		dialogPane.getDialogPane().getButtonTypes().addAll(button, ButtonType.CANCEL);	
		dialogPane.initStyle(StageStyle.UTILITY);
		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);		
		
		List<Object[]> valor = SuperAndesLogin.admin.darVentasSucursal(idSucursal);
		List<String> mostrarDatos = new ArrayList<String>(); /*Informacion que se muestra*/
			
		/*Agrega a la lista los datos de la venta*/
		for (Object[] objects : valor)	
		{
			mostrarDatos.add("Numero de venta:   "+ objects[0] + "   |   Documento del cliente:   " + objects[2] +
					"   |   Fecha de la venta:   " + objects[4] + "   |   Total de la venta:   " + objects[5]);	
		}
		
		ObservableList<String> datos = FXCollections.observableList(mostrarDatos);		
		ListView<String> vista = new ListView<String>();		
		vista.setItems(datos);/*Lista de ventas*/	
		vista.setMinWidth(500);
		grid.add(vista, 0, 0);
		
		dialogPane.getDialogPane().setContent(grid);	
		dialogPane.showAndWait().ifPresent(response -> 
		{
		     if (response == button) {
		    	String venta = vista.getSelectionModel().getSelectedItem();
		    	//[1] = Id venta
		 		String[] arreglo2 = venta.split("   ");
		 		
		 		Dialog<?> dialogPane2 = new Dialog();
				ButtonType button2 = new ButtonType("OK", ButtonData.OK_DONE);
				dialogPane2.setTitle("Productos vendidos");
				dialogPane2.getDialogPane().getButtonTypes().addAll(button2);	
				dialogPane2.initStyle(StageStyle.UTILITY);
				GridPane grid2 = new GridPane();

				grid2.setHgap(10);
				grid2.setVgap(10);		
				
				List<Object[]> productos = SuperAndesLogin.admin.darProductosVendidosSucursal(Long.valueOf(arreglo2[1].trim()));
				List<String> datosProductos = new ArrayList<String>(); /*Informacion que se muestra*/
					
				/*Agrega a la lista los datos de la venta*/
				for (Object[] dato : productos)	
				{
					datosProductos.add("Nombre:   " + dato[1] + "   |   Codigo de Barras:   " +  dato[0]);	
				}
				
				ObservableList<String> obsProductos = FXCollections.observableList(datosProductos);		
				ListView<String> vista1 = new ListView<String>();		
				vista1.setItems(obsProductos);/*Lista de ventas*/	
				vista1.setMinWidth(500);
				grid2.add(vista1, 0, 0);
				
				dialogPane2.getDialogPane().setContent(grid2);					
				dialogPane2.showAndWait();
		     }
		 });
    }
    
    @FXML
    void crearPedidoSucursal(ActionEvent event) 
    {    	
    	String sucursalActual = listViewSucursales.getSelectionModel().getSelectedItem();	
		//[1] = Id , [3] = Nombre
		String[] arreglo = sucursalActual.split(": ");
		
		long idSucursal = Long.valueOf(arreglo[1].trim());
		
		List<Object[]> proveedores = SuperAndesLogin.admin.darProveedores();
		List<String> infoProveedor = new ArrayList<String>();		
		//Hacer lista clientes
		for(Object[] a : proveedores) 		
			infoProveedor.
				add("NIT:   "+ a[0] + "   |   Nombre:   " + a[1]);
		
		
		ObservableList<String> datosProveedor = FXCollections.observableList(infoProveedor);	
		ListView<String> vistaProveedor = new ListView<String>();
		vistaProveedor.setItems(datosProveedor);		
		vistaProveedor.setMinWidth(330);		
		
		Dialog<?> dialogPane = new Dialog();
		ButtonType button = new ButtonType("Aceptar", ButtonData.OK_DONE);
		dialogPane.getDialogPane().getButtonTypes().addAll(button , ButtonType.CANCEL);		
		dialogPane.setTitle("Seleccionar cliente y productos a vender");	
		dialogPane.initStyle(StageStyle.UTILITY);
		dialogPane.setWidth(Region.USE_PREF_SIZE);;
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);	
		
		grid.add(new Label("Proveedores"), 0, 0);
		grid.add(vistaProveedor, 0, 1);	

		dialogPane.getDialogPane().setContent(grid);				
		dialogPane.showAndWait().ifPresent(response -> 
		{
			if(response == button)
			{
				String[] nit = vistaProveedor.getSelectionModel().getSelectedItem().split("   ");
				
				//Datos del producto proveedor
				List<Object[]> productosProveedor = SuperAndesLogin.admin.darInfoProductosProveedor(nit[1]);
				List<String> infoProductosProveedor = new ArrayList<String>();
				//Hacer lista de productos
				for(Object[] a : productosProveedor)
					infoProductosProveedor.
						add("Nombre:   " + a[1] + "   |   Codigo de Barras:   " + a[0] + "   |   Precio:   " + a[10] + "   |   Calidad:   " + a[11]);
				
				ObservableList<String> datosProducto = FXCollections.observableArrayList(infoProductosProveedor);
				ListView<String> vistaProducto = new ListView<String>();
				vistaProducto.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				vistaProducto.setItems(datosProducto);
				vistaProducto.setMinWidth(600);
				
				Dialog<?> dialogPane2 = new Dialog();
				ButtonType button2 = new ButtonType("Aceptar", ButtonData.OK_DONE);
				dialogPane2.getDialogPane().getButtonTypes().addAll(button2 , ButtonType.CANCEL);		
				dialogPane2.setTitle("Seleccionar cliente y productos a vender");	
				dialogPane2.initStyle(StageStyle.UTILITY);
				dialogPane2.setWidth(Region.USE_PREF_SIZE);;
				
				GridPane grid2 = new GridPane();
				grid2.setHgap(10);
				grid2.setVgap(10);	
				
				grid2.add(new Label("Productos ofrecidos"), 0, 0);
				grid2.add(vistaProducto, 0, 1);		
				grid2.add(new Label("Ingrese la cantidad de los productos, separados por comas"), 0, 2);
				TextField cantidades = new TextField();
				grid2.add(cantidades, 1, 2);	
				DatePicker fecha = new DatePicker();	
				grid2.add(new Label("Ingrese la fecha esperada"), 0, 3);
				grid2.add(fecha, 1, 3);
			
				//Informacion productos
				List<String> codigosDeBarrasList = new ArrayList<>();;
				List<String> nombreProd = new ArrayList<>();
				List<String> preciosUni = new ArrayList<>();	
				
				dialogPane2.getDialogPane().setContent(grid2);				
				dialogPane2.showAndWait().ifPresent(response2 -> 
				{
					if (response2 == button2) 
					{
						// [1] = Nombre, [4] = Codigo de Barras , [7] = Precio Unitario
						ObservableList<String> productos = vistaProducto.getSelectionModel().getSelectedItems();
						for (int i = 0; i < productos.size() ; i++) 
						{
							codigosDeBarrasList.add(productos.get(i).split("   ")[4]);
							nombreProd.add(productos.get(i).split("   ")[1]);
							preciosUni.add(productos.get(i).split("   ")[7]);						
						}			
						
						String[] cantidadProductoSelec = cantidades.getText().split(",") ;
						/*for(String a : cantidadProductoSelec)
							System.out.println(a);*/
						
						String[] codigosDeBarras = codigosDeBarrasList.toArray(new String [0]);
						
						Double precioTotal = 0.0;					
						//Los precios y la cantidad deben tener el mismo tamaño de array, asi que no hay problema.
						for (int i = 0; i < preciosUni.size(); i++) 				
							precioTotal += Double.valueOf(preciosUni.get(i)) * Double.valueOf(cantidadProductoSelec[i]);
						
						String[] precios = preciosUni.toArray(new String [0]);
						
						LocalDate fechaPrevista = fecha.getValue();			
						Date fechaEsperada = Date.from(fechaPrevista.atStartOfDay(ZoneId.systemDefault()).toInstant());
									
						SuperAndesLogin.admin.
							registrarPedido(idSucursal, codigosDeBarras, cantidadProductoSelec, precios, nit[1], fechaEsperada, precioTotal);							
					}
				});
			}
		});		
		
    }

    @FXML
    void verPedidosSucursal(ActionEvent event) 
    {
    	//Se obtiene la informacion de la sucursal
		String sucursal = listViewSucursales.getSelectionModel().getSelectedItem();	
		//[1] = Id , [3] = Nombre
		String[] arreglo = sucursal.split(": ");

		long idSucursal = Long.valueOf(arreglo[1].trim());
		String nombreSucursal = arreglo[3].trim();
		
		Dialog<?> dialogPane = new Dialog();
		ButtonType button = new ButtonType("Ver productos ordenados", ButtonData.OK_DONE);
		dialogPane.setTitle("Pedidos realizados por la sucursal");
		dialogPane.getDialogPane().getButtonTypes().addAll(button, ButtonType.CANCEL);	
		dialogPane.initStyle(StageStyle.UTILITY);
		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);		
		
		List<Object[]> valor = SuperAndesLogin.admin.darPedidosSucursal(idSucursal);
		List<String> mostrarDatos = new ArrayList<String>(); /*Informacion que se muestra*/
			
		/*Agrega a la lista los datos del pedido*/
		for (Object[] objects : valor)		
			mostrarDatos.add("Id Pedido:   "+ objects[0] + "   |   NIT Proveedor:   " + objects[4] +
					"   |   Fecha de entrega:   " + objects[1] + "   |   Estado:   " + objects[3]);		
		
		ObservableList<String> datos = FXCollections.observableList(mostrarDatos);		
		ListView<String> vista = new ListView<String>();		
		vista.setItems(datos);/*Lista de pedidos*/	
		vista.setMinWidth(500);
		grid.add(vista, 0, 0);
		
		dialogPane.getDialogPane().setContent(grid);	
		dialogPane.showAndWait().ifPresent(response -> 
		{
		     if (response == button) {
		    	String venta = vista.getSelectionModel().getSelectedItem();
		    	//[1] = Id pedido
		 		String[] arreglo2 = venta.split("   ");
		 		/*for(String a : arreglo2)
		 			System.out.println(a);*/
		 		
		 		Dialog<?> dialogPane2 = new Dialog();
				ButtonType button2 = new ButtonType("OK", ButtonData.OK_DONE);
				dialogPane2.setTitle("Productos ordenados");
				dialogPane2.getDialogPane().getButtonTypes().addAll(button2);	
				dialogPane2.initStyle(StageStyle.UTILITY);
				GridPane grid2 = new GridPane();

				grid2.setHgap(10);
				grid2.setVgap(10);		
				
				List<Object[]> productos = SuperAndesLogin.admin.darProductosPedidosSucursal(Long.valueOf(arreglo2[1].trim()));
				List<String> datosProductos = new ArrayList<String>(); /*Informacion que se muestra*/
					
				/*Agrega a la lista los datos de los productos*/
				for (Object[] dato : productos)					
					datosProductos.add("Nombre:   " + dato[1] + "   |   Codigo de Barras:   " +  dato[0]);					
				
				ObservableList<String> obsProductos = FXCollections.observableList(datosProductos);		
				ListView<String> vista1 = new ListView<String>();		
				vista1.setItems(obsProductos);/*Lista de productos*/	
				vista1.setMinWidth(500);
				grid2.add(vista1, 0, 0);
				
				dialogPane2.getDialogPane().setContent(grid2);					
				dialogPane2.showAndWait();
		     }
		 });
    }
}
