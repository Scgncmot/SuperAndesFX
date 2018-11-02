/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazsuperandes.PanelesSucursal;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

			SuperAndesLogin.admin.crearClienteJuridico(comboDocumento.getValue(),numDocumento.getText(),nombre.getText(),correo.getText(),direccion.getText());


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

			SuperAndesLogin.admin.crearClienteNatural(comboDocumento.getValue(),numDocumento.getText(),nombre.getText(),correo.getText());

		}


	}

	@FXML
	private void eliminarCliente(ActionEvent event) {

		String cliente = listView.getSelectionModel().getSelectedItem();

		String tipoDocumento = cliente.split(": ")[1].trim();

		String numDocumento = cliente.split(": ")[3].trim();		

		SuperAndesLogin.admin.eliminarCliente(tipoDocumento, numDocumento);		

	}


	@FXML
	private void modificarCliente(ActionEvent event) {

		String cliente = listView.getSelectionModel().getSelectedItem();

		String tipoDoc = cliente.split(": ")[1].trim();

		String numDoc = cliente.split(": ")[3].trim();
		
		String nom = cliente.split(": ")[5].trim();
		
		String corr = cliente.split(": ")[7].trim();
		
		
		if(tipoDoc.equals("NIT")) {
			
			String direc = SuperAndesLogin.admin.buscarDireccionPersonaJuridica(numDoc);
			
			Dialog dialogJur = new Dialog();
			dialogJur.setTitle("Modificar cliente: Persona Jurdica");
			dialogJur.setHeaderText("Modificar cliente: Persona Juridica");

			ButtonType butCreate = new ButtonType("Crear", ButtonData.OK_DONE);
			dialogJur.getDialogPane().getButtonTypes().addAll(butCreate, ButtonType.CANCEL);

			GridPane gridJ = new GridPane();
			gridJ.setHgap(10);
			gridJ.setVgap(10);

			TextField numDocumento = new TextField();
			numDocumento.setText(numDoc);
			gridJ.add(new Label("Numero de documento: "), 0, 0);
			gridJ.add(numDocumento, 1, 0);

			TextField nombre = new TextField();
			nombre.setText(nom);
			gridJ.add(new Label("Nombre: "), 0, 1);
			gridJ.add(nombre, 1, 1);

			TextField correo = new TextField();
			correo.setText(corr);
			gridJ.add(new Label("Correo: "), 0, 2);
			gridJ.add(correo, 1, 2);

			TextField direccion = new TextField();
			direccion.setText(direc);
			gridJ.add(new Label("Direccion: "), 0, 3);
			gridJ.add(direccion, 1, 3);

			dialogJur.getDialogPane().setContent(gridJ);

			dialogJur.showAndWait();

			SuperAndesLogin.admin.modificarClienteJuridico(numDoc, numDocumento.getText(),nombre.getText(),correo.getText(),direccion.getText());
	
			
		}
		
		else {
			
			Dialog dialog = new Dialog();
			dialog.setTitle("Modificar cliente");
			dialog.setHeaderText("Modificar cliente");

			ButtonType buttonNext = new ButtonType("Siguiente", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(buttonNext, ButtonType.CANCEL);

			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);

			ComboBox<String> comboDocumento = new ComboBox<>();

			ObservableList<String> elements = FXCollections.observableArrayList("TI", "Cedula","Pasaporte");

			comboDocumento.setItems(elements);

			grid.add(new Label("Nuevo tipo de documento del cliente: "), 0, 0);

			grid.add(comboDocumento, 1, 0);		

			dialog.getDialogPane().setContent(grid);

			dialog.showAndWait();
			
			String tipoDocN = comboDocumento.getValue();
			
			Dialog dialogNat = new Dialog();
			dialogNat.setTitle("Modificar cliente: Persona Natural");
			dialogNat.setHeaderText("Modificar cliente: Persona Natural");

			ButtonType butCreate = new ButtonType("Crear", ButtonData.OK_DONE);
			dialogNat.getDialogPane().getButtonTypes().addAll(butCreate, ButtonType.CANCEL);	

			GridPane gridN = new GridPane();
			gridN.setHgap(10);
			gridN.setVgap(10);

			TextField numDocumento = new TextField();
			numDocumento.setText(numDoc);
			gridN.add(new Label("Numero de documento: "), 0, 0);
			gridN.add(numDocumento, 0, 1);

			TextField nombre = new TextField();
			nombre.setText(nom);
			gridN.add(new Label("Nombre: "), 1, 0);
			gridN.add(nombre, 1, 1);

			TextField correo = new TextField();
			correo.setText(corr);
			gridN.add(new Label("Correo: "), 2, 0);
			gridN.add(correo, 2, 1);

			dialogNat.getDialogPane().setContent(gridN);

			dialogNat.showAndWait();
			
			SuperAndesLogin.admin.modificarClienteNatural(tipoDoc, tipoDocN, numDoc, numDocumento.getText(), nombre.getText(), correo.getText());
			
		}


	}

}
