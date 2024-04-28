package biblioteca;

public class Autor {
	
	private int codigo;
	private String nome;
	private String nacionalidade;
	
	public Autor(int codigo, String nome, String nacionalidade) {
		this.codigo = codigo;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
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
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	@Override
	public String toString() {
		String x = "Nome: "+ this.nome +" Codigo: "+this.codigo+" Nacionalidade: "+this.nacionalidade;
		return x;
	}

}
