/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.main;

/**
 * FXML Controller class
 *
 * @author s.carrero
 */
public class SuperAndesLogin implements Initializable {

	public static SuperAndesAdministrador admin;

	@FXML
	private ProgressBar progressBar;
	@FXML
	private PasswordField contrasena;
	@FXML
	private Button loginbutton;
	@FXML
	private TextField usuario;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	public void confirmarLogIn(Event evento) throws IOException{

		//INVOCAR AL BACK PARA REVISAR LA BASE DE DATOS PARA VERIFICAR CREDENCIALES     

		if(comboBox.getValue().equals("Administrador")){

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/interfazsuperandes/InterfazAdministrador.fxml"));

			Parent window1 = loader.load();

			admin = loader.getController();			

			Stage mainStage = main.parentWindow;			

			mainStage.getScene().setRoot(window1);       

		}

		else if(comboBox.getValue().equals("Sucursal")){


		}

		else {



		}


	}    



	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		ObservableList<String> elements = FXCollections.observableArrayList("Administrador", "Sucursal");

		comboBox.setItems(elements);        


	}    

}
