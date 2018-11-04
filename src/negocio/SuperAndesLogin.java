/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import interfazsuperandes.SuperAndesSucursalInterfaz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.main;
import persistencia.PersistenciaSuperAndes;

/**
 * FXML Controller class
 *
 * @author s.carrero
 */
public class SuperAndesLogin implements Initializable {

	public static SuperAndesAdministrador admin;

	public static SuperAndesSucursalInterfaz sucursalInt;

	private static Logger log = Logger.getLogger(SuperAndesLogin.class.getName());


	@FXML
	private PasswordField contrasena;
	@FXML
	private Button loginbutton;
	@FXML
	private TextField usuario;
	@FXML
	private ComboBox<String> comboBox;

	private PersistenciaSuperAndes pp;	



	@FXML
	public void confirmarLogIn(Event evento) throws IOException{
		
		String user = usuario.getText();

		String pass = contrasena.getText();	

		if(comboBox.getValue().equals("Administrador")){

			try {	
				
				pp.verificarDatosAdmin(user, pass);

				FXMLLoader loader = new FXMLLoader();

				loader.setLocation(getClass().getResource("/interfazsuperandes/InterfazAdministrador.fxml"));

				Parent window1 = loader.load();

				admin = loader.getController();			

				Stage mainStage = main.parentWindow;			

				mainStage.getScene().setRoot(window1); 
				
			} 
			
			catch (Exception e) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Usuario o contrasena incorrecto verifique los datos y el perfil");
				alert.showAndWait();								
				
			}
		}

		else if(comboBox.getValue().equals("Sucursal")){
			
			
			try {				
		
			long sucursal = pp.verificarDatosSucursal(user, pass);

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/interfazsuperandes/InterfazSucursal.fxml"));

			Parent window1 = loader.load();

			sucursalInt = loader.getController();
			
			sucursalInt.setSucursal(sucursal);
			
			sucursalInt.mostrarProductos();

			Stage mainStage = main.parentWindow;			

			mainStage.getScene().setRoot(window1);   
			
			}
			
			catch (Exception e) {
				
				e.printStackTrace();
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Usuario o contrasena incorrecto verifique los datos y el perfil");
				alert.showAndWait();	
				
			}


		}

		else {



		}


	}    



	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		JsonObject tableConfig = openConfig ("Tablas BD", "./resources/TablasBD_A.json");

		pp =  PersistenciaSuperAndes.getInstance (tableConfig);

		ObservableList<String> elements = FXCollections.observableArrayList("Administrador", "Sucursal");

		comboBox.setItems(elements);        


	}    




	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontro un archivo de configuracion valido: " + tipo);
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			//log.info ("No se encontro un archivo de configuracion valido");			
			JOptionPane.showMessageDialog(null, "No se encontro un archivo de configuracion de interfaz valido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

}
