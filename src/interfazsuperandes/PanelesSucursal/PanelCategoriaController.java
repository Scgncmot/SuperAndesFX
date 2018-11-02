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
public class PanelCategoriaController implements Initializable {

    @FXML
    private ListView<?> listViewProveedores;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void eliminarProveedor(ActionEvent event) {
    }

    @FXML
    private void crearProveedor(ActionEvent event) {
    }

    @FXML
    private void modificarProveedor(ActionEvent event) {
    }

    @FXML
    private void verProductosProveedor(ActionEvent event) {
    }

    @FXML
    private void agregarProductoProveedor(ActionEvent event) {
    }
    
}
