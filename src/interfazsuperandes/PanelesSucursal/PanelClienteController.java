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
public class PanelClienteController implements Initializable {

	@FXML
	private ListView<String> listView;

	@FXML
	private Button butEliminar;

	@FXML
	private Button butCrear;

	@FXML
	private Button butModificar;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {		
		
	}    	
	
	
	public ListView<String> getListView() {
		return listView;
	}





	public void setListView(ListView<String> listView) {
		this.listView = listView;
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





	@FXML
	private void crearCliente(ActionEvent event) {
		
	}

	@FXML
	private void eliminarCliente(ActionEvent event) {
		
	}
	

	@FXML
	private void modificarCliente(ActionEvent event) {
		
	}

}
