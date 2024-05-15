package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

public class Controller1 implements Initializable {

	@FXML
	private TableView<Livro> tableViewTitulos;
	@FXML
	private TableColumn<Livro,String> tableColumnTitulos;
	@FXML
	private Label titulo, genero, ano, edicao, codigoLivro, codigoEditora;
	@FXML
	private Button botaoAdicionarLivro, botaoEditarLivro, botaoRemoverLivro;
	//private SimpleListProperty<Livro> SimpleListTitulos;
	private ObservableList<Livro> ObservableListTitulos;
	private Livro livroSelecionado;
	// private ObservableList<String> titulosCadastrados;	
	private Alert alerta;
	private Biblioteca biblioteca;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		alerta = new Alert(null);
		biblioteca = new Biblioteca(MetodosSql.retornarLivros(), MetodosSql.retornarFuncionarios(), MetodosSql.retornarUsuarios(),MetodosSql.retornarEditoras(), MetodosSql.retornarAutores(), null);
		// titulosCadastrados = FXCollections.observableArrayList(MetodosSql.retornarTitulos());
		//SimpleListTitulos = new SimpleListProperty<Livro>(FXCollections.observableArrayList(biblioteca.getColecao()));
		ObservableListTitulos = FXCollections.observableArrayList(biblioteca.getColecao());
		tableViewTitulos.setItems(ObservableListTitulos);
		tableColumnTitulos.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));	
		tableViewTitulos.getSelectionModel().selectedItemProperty().addListener(changeListenerLivros());	
	}

	@FXML
	void editarAdicionarLivro(ActionEvent event) {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/application/LivrosEditorView.fxml"));
			DialogPane livrosEditorPane = fxmlLoader.load();
			LivrosEditorController livrosEditorController = fxmlLoader.getController();

			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(livrosEditorPane);
			if (event.getSource().equals(botaoEditarLivro)) {
				dialog.setTitle("Editar");
				livroSelecionado = tableViewTitulos.getSelectionModel().getSelectedItem();
				livrosEditorController.setCampos(livroSelecionado);
			} else {
				dialog.setTitle("Adicionar");
			}

			Optional<ButtonType> clickedButton = dialog.showAndWait();

			if (clickedButton.get() == ButtonType.OK) {
				if (event.getSource().equals(botaoEditarLivro)) {
					if (livrosEditorController.updateLivro()) {
						tableViewTitulos.getSelectionModel().clearSelection();
						ObservableListTitulos.clear();
						refreshBiblioteca();						
						ObservableListTitulos.addAll(biblioteca.getColecao());
						limparSelecao();
					} else {
						erroEditarLivro();
					}
				} else {
					if (livrosEditorController.addLIvro()) {
						ObservableListTitulos.clear();
						refreshBiblioteca();
						ObservableListTitulos.addAll(biblioteca.getColecao());
						limparSelecao();
						// listaTitulos.add("XML");
						// System.out.println(titulos.getLast());
					}else {
						erroCadastrarLivro();
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	@FXML
	public void removerLivro() {

		Optional<ButtonType> clickedButton = confirmaDeletarLivro();
		if (clickedButton.get() == ButtonType.OK) {
			if (MetodosSql.removerLivro(livroSelecionado.getTitulo())) {
				ObservableListTitulos.clear();
				refreshBiblioteca();
				ObservableListTitulos.addAll(biblioteca.getColecao());
				limparSelecao();
			}else {
				erroDeletarLivro();
			}
		}
	}
	*/
	public void erroCadastrarLivro() {
		alerta.setAlertType(AlertType.ERROR);
		alerta.setWidth(400);
		alerta.setHeight(300);
		// show the dialog
		alerta.setTitle("Erro");
		alerta.setContentText("Por favor, verifique as possíveis causas do erro: \n"
				+ "Já existe um livro com o código fornecido; \n" + "Não existe uma editora com o código informado; \n"
				+ "Você digitou um valor inválido (caracter em um campo numérico)");
		alerta.setHeaderText("Não foi possível cadastrar o livro!");
		alerta.show();
	}

	public void erroEditarLivro() {
		alerta.setAlertType(AlertType.ERROR);
		// show the dialog
		alerta.setTitle("Erro");
		alerta.setHeight(250);
		alerta.setContentText("Por favor, verifique as possíveis causas do erro: \n" + "Código do livro duplicado; \n"
				+ "Não existe uma editora com o código informado; \n" + "O livro está emprestado.");
		alerta.setHeaderText("Não foi possível fazer a alteração!");
		alerta.show();
	}

	public void erroDeletarLivro() {
		alerta.setAlertType(AlertType.ERROR);
		alerta.setWidth(400);
		alerta.setHeight(200);
		// show the dialog
		alerta.setTitle("Erro");
		alerta.setContentText("O livro está emprestado, por isso não é possível remove-lo!");
		alerta.setHeaderText("Não foi possível remover o livro!");
		alerta.show();
	}

	public Optional<ButtonType> confirmaDeletarLivro() {
		alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("");
		alerta.setHeaderText("Excluir?");
		alerta.setContentText("Você realmente dejesa excluir " + livroSelecionado.getTitulo() + "?");
		alerta.setWidth(400);
		alerta.setHeight(200);
		return alerta.showAndWait();
	}
	
	public void limparSelecao() {
		this.titulo.setText("");
		this.genero.setText("");
		this.ano.setText("");
		this.codigoLivro.setText("");
		this.edicao.setText("");
		this.codigoEditora.setText("");
	}
	
	public ChangeListener<Livro> changeListenerLivros(){
		
		return new ChangeListener<Livro>() {
			@Override
			public void changed(ObservableValue<? extends Livro> arg0, Livro arg1, Livro arg2) {
					
				livroSelecionado = tableViewTitulos.getSelectionModel().getSelectedItem();
				if(livroSelecionado != null) {
				titulo.setText(livroSelecionado.getTitulo());
				genero.setText(livroSelecionado.getGenero());
				ano.setText(Integer.toString(livroSelecionado.getAnoPublic()));
				codigoLivro.setText(Integer.toString(livroSelecionado.getCodigo()));
				edicao.setText(Integer.toString(livroSelecionado.getEdicao()));
				codigoEditora.setText(Integer.toString(livroSelecionado.getCodEditora()));
				}
			}
		};
	}
	
	public void refreshBiblioteca() {
		this.biblioteca.setColecao(MetodosSql.retornarLivros());
		this.biblioteca.setAutores(MetodosSql.retornarAutores());
		this.biblioteca.setEditoras(MetodosSql.retornarEditoras());
		this.biblioteca.setUsuarios(MetodosSql.retornarUsuarios());
		this.biblioteca.setFuncionarios(MetodosSql.retornarFuncionarios());
	}
	
	

}
