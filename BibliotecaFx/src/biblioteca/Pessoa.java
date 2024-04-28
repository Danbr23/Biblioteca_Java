package biblioteca;

public class Pessoa {
	
	protected int CPF;
	protected String nome;
	public int getCPF() {
		return CPF;
	}
	
	public Pessoa(int cpf, String nome) {
		this.CPF = cpf;
		this.nome = nome;
	}
	
	public void setCPF(int cPF) {
		CPF = cPF;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
