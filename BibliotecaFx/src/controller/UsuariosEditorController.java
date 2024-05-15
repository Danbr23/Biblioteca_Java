package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Livro;
import model.Usuario;

public class UsuariosEditorController {

    @FXML
    private TextField novoContato;

    @FXML
    private TextField novoCpf;

    @FXML
    private TextField novoEndereco;

    @FXML
    private TextField novoNome;
    
    private String cpfUsuario;
    
    public void setCampos(Usuario user) {
    	this.novoContato.setText(user.getTelefone());
    	this.novoCpf.setText(user.getCPF());
    	this.cpfUsuario = user.getCPF();
    	this.novoEndereco.setText(user.getEndereco());
    	this.novoNome.setText(user.getNome());
    }
    
    public boolean updateUsuario() {
    	boolean exito = MetodosSql.updateUsuario(this.novoNome.getText(), this.novoEndereco.getText(), this.novoContato.getText(), this.novoCpf.getText(), this.cpfUsuario);
    	return exito;
    }
    
    public boolean addUsuario() {
    	boolean exito = false;
    	try {
    		exito = MetodosSql.addUsuario(this.novoNome.getText(), this.novoEndereco.getText(), this.novoContato.getText(), this.novoCpf.getText());
    	}catch(Exception e) {
    		//System.out.println("O Erro é aqui!");
    	}
    		return exito;
    }

}
