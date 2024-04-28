package biblioteca;

public class Usuario extends Pessoa {
	
	private String endereco;
	private String telefone;
	
	
	public Usuario(int cpf, String nome, String endereco, String telefone) {
		super(cpf,nome);
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		String x = "Nome: "+ this.nome +" CPF: "+this.CPF+" Endereco: "+this.endereco+" telefone: "+this.telefone;
		return x;
	}
	
	

}
