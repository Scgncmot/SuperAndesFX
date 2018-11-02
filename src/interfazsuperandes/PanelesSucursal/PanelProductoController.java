/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazsuperandes.PanelesSucursal;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
		dialog.setTitle("Crear producto");
		dialog.setHeaderText("Crear producto");

		ButtonType loginButtonType = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

		List<String> lista = SuperAndesLogin.admin.darListaCategorias();

		if(lista.isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No existen categorias");
			alert.setHeaderText(null);
			alert.setContentText("No existen categorias, cree una categoria antes de continuar");
			alert.showAndWait();
		}

		else {

			TextField codigotf = new TextField();

			grid.add(new Label("Codigo de barras: "), 0, 0);

			grid.add(codigotf, 1, 0);
			

			TextField nombretf = new TextField();

			grid.add(new Label("Nombre: "), 0, 1);

			grid.add(nombretf, 1, 1);


			TextField presentaciontf = new TextField();

			grid.add(new Label("Presentacion: "), 0, 2);

			grid.add(presentaciontf, 1, 2);


			TextField marcatf = new TextField();

			grid.add(new Label("Marca: "), 0, 3);

			grid.add(marcatf, 1, 3);


			TextField cantidadtf = new TextField();

			grid.add(new Label("Cantidad: "), 0, 4);

			grid.add(cantidadtf, 1, 4);


			TextField unidadmedidatf = new TextField();

			grid.add(new Label("Unidad de medidad: "), 0, 5);

			grid.add(unidadmedidatf, 1, 5);


			TextField especificacionEmpacadotf = new TextField();

			grid.add(new Label("Especificaciones empacado: "), 0, 6);

			grid.add(especificacionEmpacadotf, 1, 6);


			ComboBox<String> combo = new ComboBox<>();

			ObservableList<String> listO = FXCollections.observableList(lista);

			combo.setItems(listO);

			grid.add(new Label("Categoria: "), 0, 7);

			grid.add(combo, 1, 7);			

			dialog.getDialogPane().setContent(grid);

			dialog.showAndWait();		
			
			boolean ex = false;
			
			try {
				
				Integer.parseInt(cantidadtf.getText());
			}
			
			catch (Exception e) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setHeaderText(null);
				alert.setContentText("Error: La cantidad debe ser un entero");
				alert.showAndWait();
				
				ex = true;			
							
			}		

			if(!ex)
			SuperAndesLogin.admin.crearProducto(codigotf.getText(), nombretf.getText(), presentaciontf.getText(), marcatf.getText(), cantidadtf.getText(), unidadmedidatf.getText(), especificacionEmpacadotf.getText(), combo.getValue().split("-")[0]);

		}

	}


	@FXML
	void eliminarProducto(ActionEvent event) {
		
		String barcode = listViewProductos.getSelectionModel().getSelectedItem().split(": ")[3].trim();
		
		SuperAndesLogin.admin.eliminarProducto(barcode);		

	}

	@FXML
	void modificarProducto(ActionEvent event) {

	}

	@FXML
	void verPromocionesProducto(ActionEvent event) {

	}

}
