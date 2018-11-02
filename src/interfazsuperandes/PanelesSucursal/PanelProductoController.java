/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazsuperandes.PanelesSucursal;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import negocio.SuperAndesLogin;

/**
 * FXML Controller class
 *
 * @author s.carrero
 */
public class PanelProductoController implements Initializable {

	@FXML
	private ListView<String> listViewProductos;

	@FXML
	private Button butEliminar;

	@FXML
	private Button butCrear;

	@FXML
	private Button butModificar;

	@FXML
	private Button butVerPromociones;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}



	public ListView<String> getListViewProductos() {
		return listViewProductos;
	}

	public void setListViewProductos(ListView<String> listViewProductos) {
		this.listViewProductos = listViewProductos;
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

	public Button getButVerPromociones() {
		return butVerPromociones;
	}

	public void setButVerPromociones(Button butVerPromociones) {
		this.butVerPromociones = butVerPromociones;
	}


	@FXML
	void crearProducto(ActionEvent event) {

		Dialog dialog = new Dialog();
		dialog.setTitle("Crear cliente");
		dialog.setHeaderText("Crear cliente");

		ButtonType loginButtonType = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

		ComboBox<String> combo = new ComboBox<>();
		
		
		
		TextField nit = new TextField();
		nit.setPromptText("00000000-0");
		TextField nombre = new TextField();
		nombre.setPromptText("A Datum Corp Inc.");

		grid.add(new Label("Ingrese el NIT del proveedor:"), 0, 0);
		grid.add(nit, 1, 0);
		grid.add(new Label("Ingrese el nombre del proveedor:"), 0, 1);
		grid.add(nombre, 1, 1);

		dialog.getDialogPane().setContent(grid);

		String x = "";

		Optional<Pair<String, String>> result = dialog.showAndWait();

		x = result.get().getKey()+"/"+result.get().getValue();

		SuperAndesLogin.admin.crearProveedor(x);

	}


	@FXML
	void eliminarProducto(ActionEvent event) {

	}

	@FXML
	void modificarProducto(ActionEvent event) {

	}

	@FXML
	void verPromocionesProducto(ActionEvent event) {

	}

}
