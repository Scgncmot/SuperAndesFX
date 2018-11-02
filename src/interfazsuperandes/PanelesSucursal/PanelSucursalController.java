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
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Crear Sucursal");
		dialog.setHeaderText("Crear Sucursal");

		ButtonType loginButtonType = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

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
		dialog.showAndWait();
		
		String sucursal = nombre.getText() + "/" + segmentacion.getText() + "/" +
				tamano.getText() + "/" + ciudad.getText() + "/" + direccion.getText();	

		SuperAndesLogin.admin.crearSucursal(sucursal);
    }

    @FXML
    void eliminarSucursal(ActionEvent event) 
    {
    	String nombre = listViewSucursales.getSelectionModel().getSelectedItem();    	
		String[] arreglo = nombre.split(": ");		
		String nombreStr = arreglo[3].trim();
		SuperAndesLogin.admin.eliminarSucursal(nombreStr);
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
		
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Modificar proveedor");
		dialog.setHeaderText("Modificar proveedor");

		ButtonType loginButtonType = new ButtonType("Modificar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

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
		dialog.showAndWait();
		
		String sucursalNueva = nombreNuevo.getText() + "/" + segmentacion.getText() + "/" +
				tamano.getText() + "/" + ciudad.getText() + "/" + direccion.getText();	

		SuperAndesLogin.admin.modificarSucursal(sucursalNueva, nombreActual);
    }

    @FXML
    void mostrarDineroRecolectado(ActionEvent event) {

    }

	@FXML
    void crearBodegaASucursal(ActionEvent event) 
	{

    }

    @FXML
    void crearEstanteASucursal(ActionEvent event) {

    }   
}
