package application;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import biblioteca.Biblioteca;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Controller implements Initializable  {
	
	@FXML
	private ListView<String> listaComidas;
	
	@FXML
	private Label titulo;
	
	Biblioteca bibli = new Biblioteca();
	
	String comidaAtual = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		listaComidas.getItems().addAll(MetodosSql.retornarTitulos());
		//System.out.println(bibli.getUsuarios().get(2));
		
		listaComidas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				comidaAtual = listaComidas.getSelectionModel().getSelectedItem();
				
				titulo.setText(comidaAtual);
			}
			
		});
		
	}

}
