/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazsuperandes.PanelesSucursal;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import negocio.SuperAndesLogin;

/**
 * FXML Controller class
 *
 * @author s.carrero
 */
public class PanelPromocionController implements Initializable {

	public final static String PAGUENLLEVEM = "Pague n unidades lleve m unidades";

	public final static String PAGUEXLLEVEY= "Pague x cantidad lleve y cantidad";

	public final static String PAQUETEPRODUCTOS = "Paquete de productos";

	public final static String PAGUE1LLEVE2CONDESCUENTO = "Lleve la segunda unidad con x descuento";


	@FXML
	private ListView<String> listViewPromocion;

	@FXML
	private Button butEliminar;

	@FXML
	private Button butCrear;

	@FXML
	private Button butverPromosPopulares;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}    



	public ListView<String> getListViewPromocion() {
		return listViewPromocion;
	}


	public void setListViewPromocion(ListView<String> listViewPromocion) {
		this.listViewPromocion = listViewPromocion;
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



	public Button getButverPromosPopulares() {
		return butverPromosPopulares;
	}



	public void setButverPromosPopulares(Button butverPromosPopulares) {
		this.butverPromosPopulares = butverPromosPopulares;
	}



	@FXML
	void crearPromocion(ActionEvent event) {

		Dialog dialog = new Dialog();
		dialog.setTitle("Crear promocion");
		dialog.setHeaderText("Crear promocion");

		ButtonType buttonNext = new ButtonType("Siguiente", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonNext, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

		ComboBox<String> comboPromos = new ComboBox<>();

		ObservableList<String> elements = FXCollections.observableArrayList(PAGUENLLEVEM, PAGUEXLLEVEY, PAQUETEPRODUCTOS, PAGUE1LLEVE2CONDESCUENTO);

		comboPromos.setItems(elements);

		grid.add(new Label("Tipo de promocion: "), 0, 0);

		grid.add(comboPromos, 1, 0);		

		dialog.getDialogPane().setContent(grid);

		dialog.showAndWait();

		String tipoPromo = comboPromos.getValue();

		TextField fechaVencimiento = new TextField();
		Label labFecha = new Label("Fecha de vencimiento: ");


		switch (tipoPromo) {

		case PAGUENLLEVEM:

			Dialog dialog1 = new Dialog();
			dialog1.setTitle("Crear promocion");
			dialog1.setHeaderText("Crear promocion");

			ButtonType buttonCreate1 = new ButtonType("Crear", ButtonData.OK_DONE);
			dialog1.getDialogPane().getButtonTypes().addAll(buttonCreate1, ButtonType.CANCEL);

			GridPane grid1 = new GridPane();
			grid1.setHgap(10);
			grid1.setVgap(10);

			TextField numUnidadesPaga = new TextField();			
			grid1.add(new Label("Numero de unidades que paga: "), 0, 0);
			grid1.add(numUnidadesPaga, 1, 0);

			TextField numUnidadesLleva = new TextField();
			grid1.add(new Label ("Numero de unidades que lleva: "), 0, 1);
			grid1.add(numUnidadesLleva, 1, 1);

			grid1.add(labFecha, 0, 2);
			grid1.add(fechaVencimiento, 1, 2);

			dialog1.getDialogPane().setContent(grid1);
			dialog1.showAndWait();




			break;

		case PAGUEXLLEVEY:


			Dialog dialog2 = new Dialog();
			dialog2.setTitle("Crear promocion");
			dialog2.setHeaderText("Crear promocion");

			ButtonType buttonCreate2 = new ButtonType("Crear", ButtonData.OK_DONE);
			dialog2.getDialogPane().getButtonTypes().addAll(buttonCreate2, ButtonType.CANCEL);

			GridPane grid2 = new GridPane();
			grid2.setHgap(10);
			grid2.setVgap(10);

			TextField numCantidadPaga = new TextField();			
			grid2.add(new Label("Cantidad que paga: "), 0, 0);
			grid2.add(numCantidadPaga, 1, 0);

			TextField numCantidadLleva = new TextField();
			grid2.add(new Label ("Cantidad que lleva: "), 0, 1);
			grid2.add(numCantidadLleva, 1, 1);

			grid2.add(labFecha, 0, 2);
			grid2.add(fechaVencimiento, 1, 2);		

			dialog2.getDialogPane().setContent(grid2);

			dialog2.showAndWait();

			break;

		case PAQUETEPRODUCTOS:

			Dialog dialog3 = new Dialog();
			dialog3.setTitle("Crear promocion");
			dialog3.setHeaderText("Crear promocion");

			ButtonType buttonCreate3 = new ButtonType("Crear", ButtonData.OK_DONE);
			dialog3.getDialogPane().getButtonTypes().addAll(buttonCreate3, ButtonType.CANCEL);

			GridPane grid3 = new GridPane();
			grid3.setHgap(10);
			grid3.setVgap(10);

			List<String> lista = SuperAndesLogin.admin.darListaCategorias();

			if(lista.isEmpty()) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("No existen categorias");
				alert.setHeaderText(null);
				alert.setContentText("No existen categorias, cree una categoria antes de continuar");
				alert.showAndWait();			}

			else {

				TextField codigotf = new TextField();

				grid3.add(new Label("Codigo de barras: "), 0, 0);

				grid3.add(codigotf, 1, 0);


				TextField nombretf = new TextField();

				grid3.add(new Label("Nombre: "), 0, 1);

				grid3.add(nombretf, 1, 1);


				TextField presentaciontf = new TextField();

				grid3.add(new Label("Presentacion: "), 0, 2);

				grid3.add(presentaciontf, 1, 2);


				TextField marcatf = new TextField();

				grid3.add(new Label("Marca: "), 0, 3);

				grid3.add(marcatf, 1, 3);


				TextField cantidadtf = new TextField();

				grid3.add(new Label("Cantidad: "), 0, 4);

				grid3.add(cantidadtf, 1, 4);


				TextField unidadmedidatf = new TextField();

				grid3.add(new Label("Unidad de medidad: "), 0, 5);

				grid3.add(unidadmedidatf, 1, 5);


				TextField especificacionEmpacadotf = new TextField();

				grid3.add(new Label("Especificaciones empacado: "), 0, 6);

				grid3.add(especificacionEmpacadotf, 1, 6);


				ComboBox<String> combo = new ComboBox<>();

				ObservableList<String> listO = FXCollections.observableList(lista);

				combo.setItems(listO);

				grid3.add(new Label("Categoria: "), 0, 7);

				grid3.add(combo, 1, 7);			

				grid3.add(labFecha, 0, 8);
				grid3.add(fechaVencimiento, 1, 8);					


				dialog3.getDialogPane().setContent(grid3);

				dialog3.showAndWait();		

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

			break;

		case PAGUE1LLEVE2CONDESCUENTO:
			
			
			Dialog dialog4 = new Dialog();
			dialog4.setTitle("Crear promocion");
			dialog4.setHeaderText("Crear promocion");

			ButtonType buttonCreate4 = new ButtonType("Crear", ButtonData.OK_DONE);
			dialog4.getDialogPane().getButtonTypes().addAll(buttonCreate4, ButtonType.CANCEL);

			GridPane grid4 = new GridPane();
			grid4.setHgap(10);
			grid4.setVgap(10);

			TextField porcentaje = new TextField();			
			grid4.add(new Label("Porcentaje descuento: "), 0, 0);
			grid4.add(porcentaje, 1, 0);
			
			grid4.add(labFecha, 0, 1);
			grid4.add(fechaVencimiento, 1, 1);		

			dialog4.getDialogPane().setContent(grid4);

			dialog4.showAndWait();

			break;

		}

	}


	@FXML
	void eliminarPromocion(ActionEvent event) {

	}

	@FXML
	void verPromosPopulares(ActionEvent event) {

	}




}
