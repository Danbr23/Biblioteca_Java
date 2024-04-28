package biblioteca;
import java.util.ArrayList;
import application.MetodosSql;

public class Biblioteca {
	
	private ArrayList<Usuario> usuarios;
	private ArrayList<Funcionario> funcionarios;
	private ArrayList<Livro> livros;
	private ArrayList<Editora> editoras;
	private ArrayList<Autor> autores;
	
	public Biblioteca() {
		
		usuarios = MetodosSql.retornarUsuarios();
		funcionarios = MetodosSql.retornarFuncionarios();
		livros = MetodosSql.retornarLivros();
		editoras = MetodosSql.retornarEditoras();
		autores = MetodosSql.retornarAutores();
	}
	
	public static void main(String[] args) {
		
		Biblioteca bibli = new Biblioteca();
		System.out.println(bibli.usuarios.get(2));
		System.out.println(bibli.funcionarios.get(2));
		System.out.println(bibli.livros.get(2));
		System.out.println(bibli.autores.get(2));
		System.out.println(bibli.editoras.get(2));
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public ArrayList<Livro> getLivros() {
		return livros;
	}

	public void setLivros(ArrayList<Livro> livros) {
		this.livros = livros;
	}

	public ArrayList<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(ArrayList<Editora> editoras) {
		this.editoras = editoras;
	}

	public ArrayList<Autor> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}
	
	

}
