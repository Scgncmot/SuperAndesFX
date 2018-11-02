/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazsuperandes.PanelesSucursal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

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

	@FXML
	void crearProducto(ActionEvent event) {

	}

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
	void eliminarProducto(ActionEvent event) {

	}

	@FXML
	void modificarProducto(ActionEvent event) {

	}

	@FXML
	void verPromocionesProducto(ActionEvent event) {

	}

}
