package interfazsuperandes;

import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import negocio.SuperAndesLogin;
import negocio.SuperAndesSucursal;

public class SuperAndesSucursalInterfaz implements Initializable {

	private SuperAndesSucursal sucursal;

	private static Logger log = Logger.getLogger(SuperAndesSucursalInterfaz.class.getName());


	@Override
	public void initialize(URL url, ResourceBundle rb) {	

		sucursal = new SuperAndesSucursal(this);

	}    		

	@FXML
	private FlowPane flowPaneProductos;

	@FXML
	private Button butCrearCarrito;

	@FXML
	private Button butVerProductosCarrito;

	@FXML
	private Button butDescartarCarrito;

	@FXML
	private Button butPagarCarrito;

	@FXML
	private Button butRegistrese;
	




	//....................................
	//.............. CARRITO .............
	//....................................

	@FXML
	public void mostrarProductos(){		

		List<String>  productos = sucursal.darListaProductos(); //ACA FALTA LO DE LAS SUCURSALES

		int i = 0;

		for (String string : productos) {			

			final Integer innerI = new Integer(i);

			TitledPane nuevo = new TitledPane();

			nuevo.setPrefWidth(170);

			nuevo.setPrefHeight(100);

			nuevo.setText(string);

			BorderPane border = new BorderPane();

			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);

			Label labCant = new Label("Cantidad: ");

			grid.add(labCant, 0, 0);

			Label cantidad = new Label("0");

			cantidad.setId("cantidad"+i);

			grid.add(cantidad, 1, 0);

			Button sumar = new Button("+");

			sumar.setStyle( "-fx-background-radius: 5em;"+ 
					"-fx-min-width: 25px;"        + 
					"-fx-min-height: 25px;"       +
					"-fx-max-width: 25px;"        + 
					"-fx-max-height: 25px;"       );

			sumar.setId("butSumar"+i);

			sumar.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					sumarEtiquetaCantidad(innerI);					
				}
			});

			grid.add(sumar, 2, 0);

			Button restar = new Button("-");

			restar.setStyle( "-fx-background-radius: 5em;"+ 
					"-fx-min-width: 25px;"        + 
					"-fx-min-height: 25px;"       +
					"-fx-max-width: 25px;"        + 
					"-fx-max-height: 25px;"       );

			restar.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {					

					restarEtiquetaCantidad(innerI);					
				}
			});

			restar.setId("butRestar"+i);

			grid.add(restar, 3, 0);


			border.setTop(grid);

			GridPane grid2 = new GridPane();
			grid2.setHgap(10);
			grid2.setVgap(10);


			Button botonAdicionar = new Button("Agregar");	

			botonAdicionar.setId("butAdicionar"+i);

			botonAdicionar.setStyle( "-fx-min-width: 90px;"        + 
					"-fx-min-height: 25px;"       +
					"-fx-max-width: 90px;"        + 
					"-fx-max-height: 25px;"       );

			grid2.add(botonAdicionar, 1, 1);

			botonAdicionar.setOnAction(new EventHandler<ActionEvent>() {


				@Override
				public void handle(ActionEvent event) {

					agregarProductoaCarrito(innerI);
				}
			});

			border.setCenter(grid2);

			nuevo.setContent(border);	

			flowPaneProductos.getChildren().add(nuevo);	

			i++;

		}

	}


	@FXML
	public void crearCarrito() {	

		Dialog dialog3 = new Dialog();
		dialog3.setTitle("Crear carrito");
		dialog3.setHeaderText("Ingreso de cliente");

		ButtonType buttonCreate3 = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog3.getDialogPane().getButtonTypes().addAll(buttonCreate3, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

		ComboBox<String> comboDocumento = new ComboBox<>();

		ObservableList<String> elements = FXCollections.observableArrayList("TI", "Cedula","Pasaporte","NIT");

		comboDocumento.setItems(elements);

		grid.add(new Label("Tipo de documento del cliente: "), 0, 0);

		grid.add(comboDocumento, 1, 0);			

		TextField documentocliente = new TextField();

		grid.add(new Label("Documento del cliente: "), 0, 1);

		grid.add(documentocliente, 1, 1);			

		dialog3.getDialogPane().setContent(grid);

		dialog3.showAndWait();

		sucursal.crearCarrito(comboDocumento.getSelectionModel().getSelectedItem(), documentocliente.getText());



	}

	@FXML
	public void descartarCarrito() {

		sucursal.eliminarCarrito();
	}

	@FXML
	public void darProductosCarrito() {

		List<Object[]> productosOb = sucursal.darProductosCarrito();

		List<String> lista = new ArrayList<>();

		for (Object[] objects : productosOb) {

			lista.add((String) objects[1]);			

		}

		ObservableList<String> listaO = FXCollections.observableList(lista);

		ListView<String> productos = new ListView<String>();

		productos.setItems(listaO);

		Dialog dialog3 = new Dialog();
		dialog3.setTitle("Ver productos carrito");

		ButtonType buttonCreate3 = new ButtonType("Aceptar", ButtonData.OK_DONE);
		dialog3.getDialogPane().getButtonTypes().addAll(buttonCreate3, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

		grid.add(productos, 0, 0);	

		dialog3.getDialogPane().setContent(grid);	

		dialog3.showAndWait();		

	}


	public void agregarProductoaCarrito(int i) {

		TitledPane principal = ((TitledPane) flowPaneProductos.getChildren().get(i));

		GridPane gridPaneNorte = (GridPane)((BorderPane) principal.getContent()).getTop();

		ObservableList<Node> olist = gridPaneNorte.getChildren();

		boolean encontrado = false;

		int cantidad = -1;

		for (Iterator iterator = olist.iterator(); !encontrado && iterator.hasNext();) {

			Node node = (Node) iterator.next();

			if(node.getId() != null && node.getId().equals("cantidad"+i)) {

				cantidad = Integer.parseInt(((Label) node).getText());

				((Label) node).setText("0");

				encontrado = true;
			}
		}

		String producto = principal.getText();

		System.out.println(producto+"-"+cantidad);

		//TODO: 

	}

	public void sumarEtiquetaCantidad(int i) {

		GridPane gridPaneNorte = (GridPane)((BorderPane)((TitledPane) flowPaneProductos.getChildren().get(i)).getContent()).getTop();

		ObservableList<Node> olist = gridPaneNorte.getChildren();

		boolean encontrado = false;

		for (Iterator iterator = olist.iterator(); !encontrado && iterator.hasNext();) {

			Node node = (Node) iterator.next();

			if(node.getId() != null && node.getId().equals("cantidad"+i)) {

				String texto = ((Label) node).getText();

				int numeroNuevo = Integer.parseInt(texto) + 1;

				String nuevotexto = ""+numeroNuevo;

				((Label) node).setText(nuevotexto);

				encontrado = true;
			}

		}
	}


	public void restarEtiquetaCantidad(int i) {

		GridPane gridPaneNorte = (GridPane)((BorderPane)((TitledPane) flowPaneProductos.getChildren().get(i)).getContent()).getTop();

		ObservableList<Node> olist = gridPaneNorte.getChildren();

		boolean encontrado = false;

		for (Iterator iterator = olist.iterator(); !encontrado && iterator.hasNext();) {

			Node node = (Node) iterator.next();

			if(node.getId() != null && node.getId().equals("cantidad"+i)) {

				String texto = ((Label) node).getText();

				if(!texto.equals("0")) {

					int numeroNuevo = Integer.parseInt(texto) - 1;

					String nuevotexto = ""+numeroNuevo;

					((Label) node).setText(nuevotexto);

					encontrado = true;
				}
			}
		}
	}


	public void registrese() {

		Dialog dialog = new Dialog();
		dialog.setTitle("Crear cliente");
		dialog.setHeaderText("Crear cliente");

		ButtonType buttonNext = new ButtonType("Siguiente", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonNext, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

		ComboBox<String> comboDocumento = new ComboBox<>();

		ObservableList<String> elements = FXCollections.observableArrayList("TI", "Cedula","Pasaporte","NIT");

		comboDocumento.setItems(elements);

		grid.add(new Label("Tipo de documento del cliente: "), 0, 0);

		grid.add(comboDocumento, 1, 0);		

		dialog.getDialogPane().setContent(grid);

		dialog.showAndWait();

		if(comboDocumento.getValue().equals("NIT")) {			

			Dialog dialogJur = new Dialog();
			dialogJur.setTitle("Crear cliente: Persona Jurdica");
			dialogJur.setHeaderText("Crear cliente: Persona Juridica");

			ButtonType butCreate = new ButtonType("Crear", ButtonData.OK_DONE);
			dialogJur.getDialogPane().getButtonTypes().addAll(butCreate, ButtonType.CANCEL);

			GridPane gridJ = new GridPane();
			gridJ.setHgap(10);
			gridJ.setVgap(10);

			TextField numDocumento = new TextField();
			gridJ.add(new Label("Numero de documento: "), 0, 0);
			gridJ.add(numDocumento, 1, 0);

			TextField nombre = new TextField();
			gridJ.add(new Label("Nombre: "), 0, 1);
			gridJ.add(nombre, 1, 1);

			TextField correo = new TextField();
			gridJ.add(new Label("Correo: "), 0, 2);
			gridJ.add(correo, 1, 2);

			TextField direccion = new TextField();
			gridJ.add(new Label("Direccion: "), 0, 3);
			gridJ.add(direccion, 1, 3);

			dialogJur.getDialogPane().setContent(gridJ);

			dialogJur.showAndWait();

			sucursal.crearClienteJuridico(comboDocumento.getValue(),numDocumento.getText(),nombre.getText(),correo.getText(),direccion.getText());
		}

		else if(comboDocumento.getValue().equals( "Cedula") || comboDocumento.getValue().equals("Pasaporte") || comboDocumento.getValue().equals("TI")) {

			Dialog dialogNat = new Dialog();
			dialogNat.setTitle("Crear cliente: Persona Natural");
			dialogNat.setHeaderText("Crear cliente: Persona Natural");

			ButtonType butCreate = new ButtonType("Crear", ButtonData.OK_DONE);
			dialogNat.getDialogPane().getButtonTypes().addAll(butCreate, ButtonType.CANCEL);	

			GridPane gridN = new GridPane();
			gridN.setHgap(10);
			gridN.setVgap(10);

			TextField numDocumento = new TextField();
			gridN.add(new Label("Numero de documento: "), 0, 0);
			gridN.add(numDocumento, 0, 1);

			TextField nombre = new TextField();
			gridN.add(new Label("Nombre: "), 1, 0);
			gridN.add(nombre, 1, 1);

			TextField correo = new TextField();
			gridN.add(new Label("Correo: "), 2, 0);
			gridN.add(correo, 2, 1);

			dialogNat.getDialogPane().setContent(gridN);

			dialogNat.showAndWait();

			sucursal.crearClienteNatural(comboDocumento.getValue(),numDocumento.getText(),nombre.getText(),correo.getText());

		}
	}


	public void setSucursal(long sucursal2) {
		
		sucursal.setSucursal(sucursal2);

	}
}
