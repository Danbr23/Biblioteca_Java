package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import controller.MetodosSql;

public class Biblioteca {

	private ArrayList<Livro> colecao;
	private ArrayList<Funcionario> funcionarios;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Editora> editoras;
	private ArrayList<Autor> autores;
	private HashMap<String, String> emprestimos;

	public Biblioteca(ArrayList<Livro> colecao, ArrayList<Funcionario> funcionarios, ArrayList<Usuario> usuarios, 
			ArrayList<Editora> editoras, ArrayList<Autor> autores,HashMap<String, String> emprestimos) {
		this.colecao = colecao;
		this.funcionarios = funcionarios;
		this.usuarios = usuarios;
		this.editoras = editoras;
		this.autores = autores;
		this.emprestimos = emprestimos;
	}

	public ArrayList<Livro> getColecao() {
		return colecao;
	}

	public void setColecao(ArrayList<Livro> colecao) {
		this.colecao = colecao;
	}

	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public HashMap<String, String> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(HashMap<String, String> emprestimos) {
		this.emprestimos = emprestimos;
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

	public ArrayList<String> getTitulos() {
		/*
		 * ArrayList<String> titulos = new ArrayList<>(); colecao.forEach(livro ->
		 * titulos.add(livro.getTitulo())); return titulos;
		 */
		return new ArrayList<String>(colecao.stream().map(livro -> livro.getTitulo()).collect(Collectors.toList()));
	}

}
