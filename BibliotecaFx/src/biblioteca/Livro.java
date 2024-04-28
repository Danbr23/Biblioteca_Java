package biblioteca;

public class Livro extends Item implements Emprestavel {
	
	private int edicao;
	private String genero;
	private int anoPublic;
	private int codEditora;
	
	public Livro(int cod, String titulo, boolean emprestado, int ed, String genero, int anoPublic, int codEditora){
		// TODO Auto-generated constructor stub
		this.codigo = cod;
		this.titulo = titulo;
		this.emprestado = emprestado;
		this.edicao = ed;
		this.genero = genero;
		this.anoPublic = anoPublic;
		this.codEditora = codEditora;
		
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getAnoPublic() {
		return anoPublic;
	}

	public void setAnoPublic(int anoPublic) {
		this.anoPublic = anoPublic;
	}

	public int getCodEditora() {
		return codEditora;
	}

	public void setCodEditora(int codEditora) {
		this.codEditora = codEditora;
	}

	@Override
	public void emprestar() {
		// TODO Auto-generated method stub
		this.emprestado = true;
		
	}

	@Override
	public void devolver() {
		// TODO Auto-generated method stub
		this.emprestado = false;
	}
	
	@Override
	public String toString() {
		String x = "Titulo: "+ this.titulo +" Genero: "+this.genero+ " Codigo: "+this.codigo+" Ano de publi: "+this.anoPublic+" Edicao: "+this.edicao+" Editora: "+this.codEditora;
		return x;
	}
	
	
	

}
