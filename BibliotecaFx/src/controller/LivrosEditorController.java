package controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Livro;

public class LivrosEditorController {

    @FXML
    private TextField novaEdicao;

    @FXML
    private TextField novaEditora;

    @FXML
    private TextField novoAnoPublic;

    @FXML
    private TextField novoCodigo;

    @FXML
    private TextField novoGenero;

    @FXML
    private TextField novoTitulo;
    
    private int codigoLivro;
    
    public void setCampos(Livro livro) {
    	this.novaEdicao.setText(Integer.toString(livro.getEdicao()));
    	this.novoAnoPublic.setText(Integer.toString(livro.getAnoPublic()));
    	this.novoCodigo.setText(Integer.toString(livro.getCodigo()));
    	this.novoTitulo.setText(livro.getTitulo());
    	this.novaEditora.setText(Integer.toString(livro.getCodEditora()));
    	this.novoGenero.setText(livro.getGenero());
    	this.codigoLivro = livro.getCodigo();
    }
    
    public boolean updateLivro() {
    	boolean exito = MetodosSql.updateLivro(this.novoTitulo.getText(), this.novoGenero.getText(), Integer.valueOf(this.novoAnoPublic.getText()), Integer.valueOf(this.novaEdicao.getText()), Integer.valueOf(this.novoCodigo.getText()), Integer.valueOf(this.novaEditora.getText()), codigoLivro);
    	return exito;
    }
    
    public boolean addLIvro() {
    	boolean exito = false;
    	try {
    		exito = MetodosSql.addLivro(this.novoTitulo.getText(), this.novoGenero.getText(), Integer.valueOf(this.novoAnoPublic.getText()), Integer.valueOf(this.novaEdicao.getText()), Integer.valueOf(this.novoCodigo.getText()), Integer.valueOf(this.novaEditora.getText()));
    	}catch(Exception e) {
    		//System.out.println("O Erro é aqui!");
    	}
    		return exito;
    }

}
