package interfazsuperandes.PanelesSucursal;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import negocio.SuperAndesLogin;

public class PanelProveedorController implements Initializable {


	@FXML
	private ListView<String> listViewProveedores;

	@FXML
	private Button butEliminar;

	@FXML
	private Button butCrear;

	@FXML
	private Button butModificar;

	@FXML
	private Button butVerProductos;

	@FXML
	private Button butAgregarProductos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public ListView<String> getListViewProveedores() {
		return listViewProveedores;
	}

	public void setListViewProveedores(ListView<String> listViewProveedores) {
		this.listViewProveedores = listViewProveedores;
	}

	public Button getButEliminar() {
		return butEliminar;
	}

	public void setButEliminar(Button butEliminar) {
		this.butEliminar = butEliminar;
	}

	public Button getButCrear() {
		return butCrear;
	}

	public void setButCrear(Button butCrear) {
		this.butCrear = butCrear;
	}

	public Button getButModificar() {
		return butModificar;
	}

	public void setButModificar(Button butModificar) {
		this.butModificar = butModificar;
	}

	public Button getButVerProductos() {
		return butVerProductos;
	}

	public void setButVerProductos(Button butVerProductos) {
		this.butVerProductos = butVerProductos;
	}

	public Button getButAgregarProductos() {
		return butAgregarProductos;
	}

	public void setButAgregarProductos(Button butAgregarProductos) {
		this.butAgregarProductos = butAgregarProductos;
	}




	@FXML
	public void crearProveedor() {

		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Crear proveedor");
		dialog.setHeaderText("Crear proveedor");

		ButtonType loginButtonType = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField nit = new TextField();
		nit.setPromptText("00000000-0");
		TextField nombre = new TextField();
		nombre.setPromptText("A Datum Corp Inc.");

		grid.add(new Label("Ingrese el NIT del proveedor:"), 0, 0);
		grid.add(nit, 1, 0);
		grid.add(new Label("Ingrese el nombre del proveedor:"), 0, 1);
		grid.add(nombre, 1, 1);

		dialog.getDialogPane().setContent(grid);


		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(nit.getText(), nombre.getText());
			}
			return null;
		});

		String x = "";

		Optional<Pair<String, String>> result = dialog.showAndWait();

		x = result.get().getKey()+"/"+result.get().getValue();

		SuperAndesLogin.admin.crearProveedor(x);
	}


	@FXML
	public void eliminarProveedor() {


		String proveedor = listViewProveedores.getSelectionModel().getSelectedItem();

		String[] arreglo = proveedor.split(": ");

		String nombreStr = arreglo[1].trim();	

		SuperAndesLogin.admin.eliminarProveedor(nombreStr);

	}


	@FXML
	public void modificarProveedor() {

		String proveedor = listViewProveedores.getSelectionModel().getSelectedItem();

		String[] arreglo = proveedor.split(": ");

		String nombreStr = arreglo[1].trim();

		String nitStr = arreglo[3].trim();

		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Modificar proveedor");
		dialog.setHeaderText("Modificar proveedor");

		ButtonType loginButtonType = new ButtonType("Modificar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField nit = new TextField();
		nit.setText(nitStr);
		TextField nombre = new TextField();
		nombre.setText(nombreStr);

		grid.add(new Label("Ingrese el NIT del proveedor:"), 0, 0);
		grid.add(nit, 1, 0);
		grid.add(new Label("Ingrese el nombre del proveedor:"), 0, 1);
		grid.add(nombre, 1, 1);

		dialog.getDialogPane().setContent(grid);


		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(nit.getText(), nombre.getText());
			}
			return null;
		});

		String x = "";

		Optional<Pair<String, String>> result = dialog.showAndWait();

		x = result.get().getKey()+"/"+result.get().getValue();

		SuperAndesLogin.admin.modificarProveedor(nombreStr, nitStr, x);
	}



	@FXML
	public void verProductosProveedor() {

		String proveedor = listViewProveedores.getSelectionModel().getSelectedItem();

		String[] arreglo = proveedor.split(": ");

		String nitStr = arreglo[3].trim();

		List<String> lista = SuperAndesLogin.admin.darProductosProveedor(nitStr);
		

		if(lista.isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No existen productos");
			alert.setHeaderText(null);
			alert.setContentText("No existen productos para el proveedor seleccionado");

			alert.showAndWait();
		}

		else {


			Dialog dialogPane = new Dialog();
			ButtonType button = new ButtonType("Aceptar", ButtonData.OK_DONE);
			dialogPane.getDialogPane().getButtonTypes().addAll(button);
			ListView<String> inner = new ListView<String>();		
			ObservableList<String> listaO = FXCollections.observableList(lista);
			inner.setItems(listaO);
			dialogPane.getDialogPane().setContent(inner);

			dialogPane.show();

		}


	}
	
	
	@FXML
	public void agregarProductoProveedor() {

		List<String> lista = SuperAndesLogin.admin.darListaProductos();

		if(lista.isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No existen productos");
			alert.setHeaderText(null);
			alert.setContentText("No existen productos en la base de datos");

			alert.showAndWait();
		}

		else {
			
			String proveedor = listViewProveedores.getSelectionModel().getSelectedItem();			

			String[] arreglo = proveedor.split(": ");

			String nitStr = arreglo[3].trim();

			Dialog dialogPane = new Dialog();
			ButtonType button = new ButtonType("Registrar", ButtonData.OK_DONE);
			dialogPane.getDialogPane().getButtonTypes().addAll(button);			
			GridPane grid = new GridPane();
			
			grid.setHgap(10);
			grid.setVgap(10);

			ListView<String> productos = new ListView<String>();
			
			ObservableList<String> listaO = FXCollections.observableList(lista);

			productos.setItems(listaO);	
			
			grid.add(new Label("Seleccione el producto a asociar:"), 0, 0);
			
			grid.add(productos, 0, 1);			

			TextField precio = new TextField();
			TextField calificacioncalidad = new TextField();
			

			grid.add(new Label("Ingrese el precio de venta del producto:"), 0, 2);
			grid.add(precio, 1, 2);
			grid.add(new Label("Ingrese la calificacion de calidad del producto:"), 0, 3);
			grid.add(calificacioncalidad, 1, 3);
			
			dialogPane.getDialogPane().setContent(grid);
			
			dialogPane.showAndWait();
			
			
			SuperAndesLogin.admin.agregarProductoProveedor(productos.getSelectionModel().getSelectedItem(),nitStr,calificacioncalidad.getText(),precio.getText());
			
			
		
		}


	}






}
