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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

    @FXML
    void crearBodegaASucursal(ActionEvent event) {

    }

    @FXML
    void crearEstanteASucursal(ActionEvent event) {

    }

    @FXML
    void crearSucursal(ActionEvent event) {

    }

    @FXML
    void eliminarSucursal(ActionEvent event) {

    }

    @FXML
    void modificarSucursal(ActionEvent event) {

    }

    @FXML
    void mostrarDineroRecolectado(ActionEvent event) {

    }
    
}
