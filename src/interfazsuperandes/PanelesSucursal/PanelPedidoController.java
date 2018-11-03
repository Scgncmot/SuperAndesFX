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
public class PanelPedidoController implements Initializable 
{

    @FXML
    private ListView<?> listViewProveedores;

    @FXML
    private Button butEliminarPedido;

    @FXML
    private Button butCrearPedido;

    @FXML
    private Button butVerProductos;

    @FXML
    private Button butAgregarProductosPedido;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

    @FXML
    void agregarProductoProveedor(ActionEvent event) 
    {

    }

    @FXML
    void crearPedido(ActionEvent event) {

    }

    @FXML
    void eliminarProveedor(ActionEvent event) {

    }

    @FXML
    void verProductosPedido(ActionEvent event) {

    }    
}
