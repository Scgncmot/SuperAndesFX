/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazsuperandes.PanelesSucursal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.StageStyle;
import negocio.SuperAndesLogin;

/**
 * FXML Controller class
 *
 * @author s.carrero
 */
public class PanelCategoriaController implements Initializable {

	@FXML
	private ListView<String> listViewCategoria;

	@FXML
	private Button butEliminarCategoria;

	@FXML
	private Button butCrearCategoria;

	@FXML
	private Button butModificarCategoria;

	@FXML
	private Button butVerProductos;	

	public ListView<String> getListViewCategoria() {	return listViewCategoria;}

	public void setListViewCategoria(ListView<String> listViewCategoria) {this.listViewCategoria = listViewCategoria;}

	public Button getButEliminarCategoria() {return butEliminarCategoria;}

	public void setButEliminarCategoria(Button butEliminarCategoria) {this.butEliminarCategoria = butEliminarCategoria;}

	public Button getButCrearCategoria() {return butCrearCategoria;}

	public void setButCrearCategoria(Button butCrearCategoria) {this.butCrearCategoria = butCrearCategoria;}

	public Button getButModificarCategoria() {return butModificarCategoria;}

	public void setButModificarCategoria(Button butModificarCategoria) {this.butModificarCategoria = butModificarCategoria;}

	public Button getButVerProductos() {return butVerProductos;}

	public void setButVerProductos(Button butVerProductos) {this.butVerProductos = butVerProductos;}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	@FXML
	void crearCategoria(ActionEvent event) 
	{
		Dialog<?> dialog = new Dialog<>();
		dialog.setTitle("Crear categoria");
		dialog.setHeaderText("Crear categoria");
		dialog.initStyle(StageStyle.UTILITY);

		ButtonType crear = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(crear , ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField nombre = new TextField();
		nombre.setPromptText("Lacteos, Harinas, Pastas, etc");

		grid.add(new Label("Ingrese el nombre de la categoria"), 0, 0);
		grid.add(nombre, 1, 0);

		dialog.getDialogPane().setContent(grid);
		dialog.showAndWait().ifPresent(response -> 
		{
		     if (response == crear) 
		    	 SuperAndesLogin.admin.crearCategoria(nombre.getText());			     
		 });
	}

	@FXML
	void eliminarCategoria(ActionEvent event) 
	{
		String nombre = listViewCategoria.getSelectionModel().getSelectedItem(); 
		//[1] = Id , [3] = Nombre
		String[] arreglo = nombre.split(": ");		
		/*for(String a : arreglo)
			System.out.println("Esto es: " + a);*/
		
		SuperAndesLogin.admin.eliminarCategoria(arreglo[3].trim());
	}

	@FXML
	void modificarCategoria(ActionEvent event) 
	{
		String categoria = listViewCategoria.getSelectionModel().getSelectedItem();
		String[] arreglo = categoria.split(": ");	
		String idCategoria = arreglo[1].trim();
		String nombreActual = arreglo[3].trim();

		Dialog<?> dialog = new Dialog<>();
		dialog.setTitle("Modificar proveedor");
		dialog.setHeaderText("Modificar proveedor");
		dialog.initStyle(StageStyle.UTILITY);

		ButtonType modificar = new ButtonType("Modificar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(modificar, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField nombreNuevo = new TextField();
		nombreNuevo.setText((String) nombreActual); 


		grid.add(new Label("Ingrese el nuevo nombre:"), 0, 0);
		grid.add(nombreNuevo, 1, 0);


		dialog.getDialogPane().setContent(grid);
		dialog.showAndWait().ifPresent(response -> {
		     if (response == modificar) 
		     {
		    	 String newName = nombreNuevo.getText();
		 		 SuperAndesLogin.admin.modificarCategoria(Long.parseLong(idCategoria), newName, nombreActual);
		     }
		});
	}

	@FXML
	void verProductosCategoria(ActionEvent event) 
	{
		String categoria = listViewCategoria.getSelectionModel().getSelectedItem();
		String[] arreglo = categoria.split(": ");	
		String idCategoria = arreglo[1].trim();
		String nombreActual = arreglo[3].trim();
		
		Dialog<?> dialogPane = new Dialog();
		ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
		dialogPane.getDialogPane().getButtonTypes().addAll(ok);		
		dialogPane.setTitle("Productos pertenecientes a la categoria");	
		dialogPane.initStyle(StageStyle.UTILITY);
		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);	

		List<Object[]> valor = SuperAndesLogin.admin.darProductosCategoria(Long.parseLong(idCategoria.trim()));
		List<String> mostrarDatos = new ArrayList<String>(); /*Informacion que se muestra*/
			
		/*Agrega a la lista los datos del producto*/
		for (Object[] objects : valor)	
			mostrarDatos.add("Nombre:   " +  objects[1] + "   |   Codigo de Barras:   " + objects[0]);			
		
		ObservableList<String> datos = FXCollections.observableList(mostrarDatos);		
		ListView<String> vista = new ListView<String>();		
		vista.setItems(datos);/*Lista de Bodegas*/	
		grid.add(vista, 0, 0);
		
		dialogPane.getDialogPane().setContent(grid);	
		dialogPane.showAndWait();		
	}
}
