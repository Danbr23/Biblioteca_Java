package biblioteca;

public class Funcionario extends Pessoa {
	
	String funcao;
	float salario;
	
	public Funcionario(int cpf, String nome, String funcao, float salario) {
		super(cpf,nome);
		this.funcao = funcao;
		this.salario = salario;
		// TODO Auto-generated constructor stub
	}
	
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		String x = "Nome: "+ this.nome +" CPF: "+this.CPF+" Funcao: "+this.funcao+" Salario: "+this.salario;
		return x;
	}
	
	

}
