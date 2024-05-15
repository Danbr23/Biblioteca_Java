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

public class Controller implements Initializable {

	@FXML
	private Button botaoAdicionarAutor, botaoAdicionarEditora, botaoAdicionarEmprestimo, botaoAdicionarFuncionario,
			botaoAdicionarLivro, botaoAdicionarUsuario, botaoEditarAutor, botaoEditarEditora, botaoEditarFuncionario,
			botaoEditarLivro, botaoEditarUsuario, botaoRemoverAutor, botaoRemoverEditora, botaoRemoverEmprestimo,
			botaoRemoverFuncionario, botaoRemoverLivro, botaoRemoverUsuario;

	@FXML
	private Label labelAnoLivro, labelCodigoAutor, labelCodigoEditoraLivro, labelCodigoLivro, labelContatoEditora,
			labelContatoUsuario, labelCpfFuncionario, labelCpfUsuario, labelEdicaoLivro, labelEnderecoUsuario,
			labelFuncaoFuncionario, labelGeneroLivro, labelNacionalidadeAutor, labelNomeAutor, labelNomeEditora,
			labelNomeFuncionario, labelNomeUsuario, labelSalarioFuncionario, labelTituloLivro, labelCodigoEditora;

	@FXML
	private TableColumn<Autor, String> tableColumnAutor;
	@FXML
	private TableColumn<Usuario, Integer> tableColumnEmprestimoCpf;
	@FXML
	private TableColumn<Livro, String> tableColumnEmprestimoLivro;
	@FXML
	private TableColumn<Livro, Integer> tableColumnEmprestimoLivroCodigo;
	@FXML
	private TableColumn<Usuario, String> tableColumnEmprestimoUsuario;
	@FXML
	private TableColumn<Funcionario, String> tableColumnFuncionario;
	@FXML
	private TableColumn<Livro, String> tableColumnLivro;
	@FXML
	private TableColumn<Editora, String> tableColumnEditora;
	@FXML
	private TableColumn<Usuario, String> tableColumnUsuario;

	@FXML
	private TableView<Autor> tableViewAutor;
	@FXML
	private TableView<Editora> tableViewEditora;
	@FXML
	private TableView<Funcionario> tableViewFuncionario;
	@FXML
	private TableView<Livro> tableViewLivro;
	@FXML
	private TableView<Usuario> tableViewUsuario;

	private ObservableList<Livro> ObservableListTitulos;
	private ObservableList<Usuario> ObservableListUsuarios;
	private ObservableList<Autor> ObservableListAutores;
	private ObservableList<Editora> ObservableListEditoras;
	private ObservableList<Funcionario> ObservableListFuncionarios;
	private Alert alerta;
	private Biblioteca biblioteca;
	private Livro livroSelecionado;
	private Usuario usuarioSelecionado;
	private Autor autorSelecionado;
	private Editora editoraSelecionada;
	private  Funcionario funcionarioSelecionado;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		alerta = new Alert(null);
		biblioteca = new Biblioteca(MetodosSql.retornarLivros(), MetodosSql.retornarFuncionarios(),
				MetodosSql.retornarUsuarios(), MetodosSql.retornarEditoras(), MetodosSql.retornarAutores(), null);
		// titulosCadastrados =
		// FXCollections.observableArrayList(MetodosSql.retornarTitulos());
		// SimpleListTitulos = new
		// SimpleListProperty<Livro>(FXCollections.observableArrayList(biblioteca.getColecao()));
		ObservableListTitulos = FXCollections.observableArrayList(biblioteca.getColecao());
		ObservableListUsuarios = FXCollections.observableArrayList(biblioteca.getUsuarios());
		ObservableListAutores = FXCollections.observableArrayList(biblioteca.getAutores());
		ObservableListEditoras = FXCollections.observableArrayList(biblioteca.getEditoras());
		ObservableListFuncionarios = FXCollections.observableArrayList(biblioteca.getFuncionarios());
		
		tableViewLivro.setItems(ObservableListTitulos);
		tableViewUsuario.setItems(ObservableListUsuarios);
		tableViewAutor.setItems(ObservableListAutores);
		tableViewEditora.setItems(ObservableListEditoras);
		tableViewFuncionario.setItems(ObservableListFuncionarios);

		tableColumnLivro.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
		tableColumnUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
		tableColumnAutor.setCellValueFactory(new PropertyValueFactory<Autor, String>("nome"));
		tableColumnEditora.setCellValueFactory(new PropertyValueFactory<Editora, String>("nome"));
		tableColumnFuncionario.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));

		tableViewLivro.getSelectionModel().selectedItemProperty().addListener(changeListenerLivros());
		tableViewUsuario.getSelectionModel().selectedItemProperty().addListener(changeListenerUsuario());
		tableViewAutor.getSelectionModel().selectedItemProperty().addListener(changeListenerAutores());
		tableViewEditora.getSelectionModel().selectedItemProperty().addListener(changeListenerEditoras());
		tableViewFuncionario.getSelectionModel().selectedItemProperty().addListener(changeListenerFuncionarios());
	}

	@FXML
	void editarAdicionarAutor(ActionEvent event) {

	}

	@FXML
	void editarAdicionarEditora(ActionEvent event) {

	}

	@FXML
	void editarAdicionarFuncionario(ActionEvent event) {

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
				livroSelecionado = tableViewLivro.getSelectionModel().getSelectedItem();
				livrosEditorController.setCampos(livroSelecionado);
			} else {
				dialog.setTitle("Adicionar");
			}

			Optional<ButtonType> clickedButton = dialog.showAndWait();

			if (clickedButton.get() == ButtonType.OK) {
				if (event.getSource().equals(botaoEditarLivro)) {
					if (livrosEditorController.updateLivro()) {
						tableViewLivro.getSelectionModel().clearSelection();
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
					} else {
						erroCadastrarLivro();
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void editarAdicionarUsuario(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/application/UsuariosEditorView.fxml"));
			DialogPane livrosEditorPane = fxmlLoader.load();
			UsuariosEditorController usuariosEditorController = fxmlLoader.getController();

			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(livrosEditorPane);
			if (event.getSource().equals(botaoEditarUsuario)) {
				dialog.setTitle("Editar");
				usuarioSelecionado = tableViewUsuario.getSelectionModel().getSelectedItem();
				usuariosEditorController.setCampos(usuarioSelecionado);
			} else {
				dialog.setTitle("Adicionar");
			}

			Optional<ButtonType> clickedButton = dialog.showAndWait();

			if (clickedButton.get() == ButtonType.OK) {
				if (event.getSource().equals(botaoEditarUsuario)) {
					if (usuariosEditorController.updateUsuario()) {
						tableViewUsuario.getSelectionModel().clearSelection();
						ObservableListUsuarios.clear();
						refreshBiblioteca();
						ObservableListUsuarios.addAll(biblioteca.getUsuarios());
						limparSelecao();
					} else {
						erroEditarLivro();
					}
				} else {
					if (usuariosEditorController.addUsuario()) {
						ObservableListUsuarios.clear();
						refreshBiblioteca();
						ObservableListUsuarios.addAll(biblioteca.getUsuarios());
						limparSelecao();
						// listaTitulos.add("XML");
						// System.out.println(titulos.getLast());
					} else {
						erroCadastrarLivro();
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void fazerEmprestimo(ActionEvent event) {

	}

	@FXML
	void removerAutor(ActionEvent event) {
		Optional<ButtonType> clickedButton = confirmaDeletarAutor();
		if (clickedButton.get() == ButtonType.OK) {
			if (MetodosSql.removerAutor(autorSelecionado.getCodigo())) {
				ObservableListAutores.clear();
				refreshBiblioteca();
				ObservableListAutores.addAll(biblioteca.getAutores());
				limparSelecao();
			} else {
				erroDeletarEditora();
			}
		}
	}

	@FXML
	void removerEditora(ActionEvent event) {
		Optional<ButtonType> clickedButton = confirmaDeletarEditora();
		if (clickedButton.get() == ButtonType.OK) {
			if (MetodosSql.removerEditora(editoraSelecionada.getCodigo())) {
				ObservableListEditoras.clear();
				refreshBiblioteca();
				ObservableListEditoras.addAll(biblioteca.getEditoras());
				limparSelecao();
			} else {
				erroDeletarEditora();
			}
		}
	}

	@FXML
	void removerEmprestimo(ActionEvent event) {

	}

	@FXML
	void removerFuncionario(ActionEvent event) {
		Optional<ButtonType> clickedButton = confirmaDeletarFuncionario();
		if (clickedButton.get() == ButtonType.OK) {
			if (MetodosSql.removerFuncionario(funcionarioSelecionado.getCPF())) {
				ObservableListFuncionarios.clear();
				refreshBiblioteca();
				ObservableListFuncionarios.addAll(biblioteca.getFuncionarios());
				limparSelecao();
			} else {
				erroDeletarFuncionario();
			}
		}
	}

	@FXML
	void removerLivro(ActionEvent event) {
		Optional<ButtonType> clickedButton = confirmaDeletarLivro();
		if (clickedButton.get() == ButtonType.OK) {
			if (MetodosSql.removerLivro(livroSelecionado.getCodigo())) {
				ObservableListTitulos.clear();
				refreshBiblioteca();
				ObservableListTitulos.addAll(biblioteca.getColecao());
				limparSelecao();
			} else {
				erroDeletarLivro();
			}
		}
	}

	@FXML
	void removerUsuario(ActionEvent event) {
		Optional<ButtonType> clickedButton = confirmaDeletarUsuario();
		if (clickedButton.get() == ButtonType.OK) {
			if (MetodosSql.removerUsuario(usuarioSelecionado.getCPF())) {
				ObservableListUsuarios.clear();
				refreshBiblioteca();
				ObservableListUsuarios.addAll(biblioteca.getUsuarios());
				limparSelecao();
			} else {
				erroDeletarLivro();
			}
		}
	}

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
	
	public void erroDeletarEditora() {
		alerta.setAlertType(AlertType.ERROR);
		alerta.setWidth(400);
		alerta.setHeight(200);
		// show the dialog
		alerta.setTitle("Erro");
		alerta.setContentText("A editora está emprestado, por isso não é possível remove-lo!");
		alerta.setHeaderText("Não foi possível remover a editora!");
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
	
	public void erroDeletarFuncionario() {
		alerta.setAlertType(AlertType.ERROR);
		alerta.setWidth(400);
		alerta.setHeight(200);
		// show the dialog
		alerta.setTitle("Erro");
		alerta.setContentText("O funcionario está emprestado, por isso não é possível remove-lo!");
		alerta.setHeaderText("Não foi possível remover o funcionario!");
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
	
	public Optional<ButtonType> confirmaDeletarUsuario(){
		alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("");
		alerta.setHeaderText("Excluir?");
		alerta.setContentText("Você realmente dejesa remover " + usuarioSelecionado.getNome() + "?");
		alerta.setWidth(400);
		alerta.setHeight(200);
		return alerta.showAndWait();
	}
	
	public Optional<ButtonType> confirmaDeletarEditora(){
		alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("");
		alerta.setHeaderText("Excluir?");
		alerta.setContentText("Você realmente dejesa remover " + editoraSelecionada.getNome() + "?");
		alerta.setWidth(400);
		alerta.setHeight(200);
		return alerta.showAndWait();
		}

	public Optional<ButtonType> confirmaDeletarAutor(){
		alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("");
		alerta.setHeaderText("Excluir?");
		alerta.setContentText("Você realmente dejesa remover " + autorSelecionado.getNome() + "?");
		alerta.setWidth(400);
		alerta.setHeight(200);
		return alerta.showAndWait();
	}
	
	public Optional<ButtonType> confirmaDeletarFuncionario(){
		alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("");
		alerta.setHeaderText("Excluir?");
		alerta.setContentText("Você realmente dejesa remover " + funcionarioSelecionado.getNome() + "?");
		alerta.setWidth(400);
		alerta.setHeight(200);
		return alerta.showAndWait();
	}

	public void limparSelecao() {
		this.labelTituloLivro.setText("");
		this.labelGeneroLivro.setText("");
		this.labelAnoLivro.setText("");
		this.labelCodigoLivro.setText("");
		this.labelEdicaoLivro.setText("");
		this.labelCodigoEditoraLivro.setText("");
		
		this.labelContatoUsuario.setText("");
		this.labelCpfUsuario.setText("");
		this.labelEnderecoUsuario.setText("");
		this.labelNomeUsuario.setText("");
		
		this.labelCodigoAutor.setText("");
		this.labelNacionalidadeAutor.setText("");
		this.labelNomeAutor.setText("");
		
		this.labelCodigoEditora.setText("");
		this.labelContatoEditora.setText("");
		this.labelNomeEditora.setText("");
		
		this.labelCpfFuncionario.setText("");
		this.labelNomeFuncionario.setText("");
		this.labelFuncaoFuncionario.setText("");
		this.labelSalarioFuncionario.setText("");
	}

	public ChangeListener<Livro> changeListenerLivros() {

		return new ChangeListener<Livro>() {
			@Override
			public void changed(ObservableValue<? extends Livro> arg0, Livro arg1, Livro arg2) {

				livroSelecionado = tableViewLivro.getSelectionModel().getSelectedItem();
				if (livroSelecionado != null) {
					labelTituloLivro.setText(livroSelecionado.getTitulo());
					labelGeneroLivro.setText(livroSelecionado.getGenero());
					labelAnoLivro.setText(Integer.toString(livroSelecionado.getAnoPublic()));
					labelCodigoLivro.setText(Integer.toString(livroSelecionado.getCodigo()));
					labelEdicaoLivro.setText(Integer.toString(livroSelecionado.getEdicao()));
					labelCodigoEditoraLivro.setText(Integer.toString(livroSelecionado.getCodEditora()));
				}
			}
		};
	}
	
	public ChangeListener<Usuario> changeListenerUsuario() {

		return new ChangeListener<Usuario>() {
			@Override
			public void changed(ObservableValue<? extends Usuario> arg0, Usuario arg1, Usuario arg2) {

				usuarioSelecionado = tableViewUsuario.getSelectionModel().getSelectedItem();
				if (usuarioSelecionado != null) {
					labelCpfUsuario.setText(usuarioSelecionado.getCPF());
					labelNomeUsuario.setText(usuarioSelecionado.getNome());
					labelEnderecoUsuario.setText(usuarioSelecionado.getEndereco());
					labelContatoUsuario.setText(usuarioSelecionado.getTelefone());
					
				}
			}
		};
	}
	
	public ChangeListener<Autor> changeListenerAutores() {

		return new ChangeListener<Autor>() {
			@Override
			public void changed(ObservableValue<? extends Autor> arg0, Autor arg1, Autor arg2) {

				autorSelecionado = tableViewAutor.getSelectionModel().getSelectedItem();
				if (autorSelecionado != null) {
					labelNacionalidadeAutor.setText(autorSelecionado.getNacionalidade());
					labelNomeAutor.setText(autorSelecionado.getNome());
					labelCodigoAutor.setText(Integer.toString(autorSelecionado.getCodigo()));
				}
			}
		};
	}
	
	public ChangeListener<Editora> changeListenerEditoras() {

		return new ChangeListener<Editora>() {
			@Override
			public void changed(ObservableValue<? extends Editora> arg0, Editora arg1, Editora arg2) {

				editoraSelecionada = tableViewEditora.getSelectionModel().getSelectedItem();
				if (editoraSelecionada != null) {
					labelContatoEditora.setText(editoraSelecionada.getContato());
					labelNomeEditora.setText(editoraSelecionada.getNome());
					labelCodigoEditora.setText(Integer.toString(editoraSelecionada.getCodigo()));
				}
			}
		};
	}
	
	public ChangeListener<Funcionario> changeListenerFuncionarios() {

		return new ChangeListener<Funcionario>() {
			@Override
			public void changed(ObservableValue<? extends Funcionario> arg0, Funcionario arg1, Funcionario arg2) {

				funcionarioSelecionado = tableViewFuncionario.getSelectionModel().getSelectedItem();
				if (funcionarioSelecionado != null) {
					labelCpfFuncionario.setText(funcionarioSelecionado.getCPF());
					labelNomeFuncionario.setText(funcionarioSelecionado.getNome());
					labelFuncaoFuncionario.setText(funcionarioSelecionado.getFuncao());
					labelSalarioFuncionario.setText(Double.toString(funcionarioSelecionado.getSalario()));
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
