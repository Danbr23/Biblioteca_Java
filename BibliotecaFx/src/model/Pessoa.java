package model;

public class Pessoa {
	
	protected String CPF;
	protected String nome;
	
	public String getCPF() {
		return CPF;
	}
	
	public Pessoa(String cpf, String nome) {
		this.CPF = cpf;
		this.nome = nome;
	}
	
	public void setCPF(String cpf) {
		CPF = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
