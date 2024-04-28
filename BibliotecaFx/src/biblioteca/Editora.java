package biblioteca;

public class Editora {
	
	private int codigo;
	private String nome;
	private String contato;
	
	
	
	public Editora(int codigo, String nome, String contato) {
		this.codigo = codigo;
		this.nome = nome;
		this.contato = contato;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	
	@Override
	public String toString() {
		String x = "Nome: "+ this.nome +" Codigo: "+this.codigo+" Contato: "+this.contato;
		return x;
	}
	
	

}
