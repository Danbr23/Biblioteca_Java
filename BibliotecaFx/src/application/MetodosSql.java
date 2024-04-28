package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import biblioteca.*;

public class MetodosSql {
	
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";
    
    public static void main(String []args) {
    	ArrayList<String> titulos = retornarTitulos();
    	for(int i =0; i< titulos.size(); i++) {
    		System.out.println(titulos.get(i));
    	}
    }
    
    public static void insertData(int id, String name) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "INSERT INTO Livros (titulo,codigo) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                
                statement.setString(1, name);
                statement.setInt(2, id);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("O livro foi adicionado");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    


    public static ArrayList<String> retornarTitulos() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT titulo FROM livros";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

            	ArrayList<String> titulos = new ArrayList<String>();
                while (resultSet.next()) {
                    String titulo = resultSet.getString("titulo");;
                    titulos.add(titulo);
                }
                return titulos;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<Livro> retornarLivros() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM livros";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

            	ArrayList<Livro> livros = new ArrayList<Livro>();
                while (resultSet.next()) {
                    String titulo = resultSet.getString("titulo");;
                    int codigo = resultSet.getInt("codigo");
                    int edicao = resultSet.getInt("edicao");
                    String genero = resultSet.getString("genero");
                    int ano = resultSet.getInt("ano");
                    int codigoEditora = resultSet.getInt("codigoEditora");
                    Livro livro = new Livro(codigo, titulo, false, edicao, genero, ano, codigoEditora);
                    
                   livros.add(livro);
                }
                return livros;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<Editora> retornarEditoras() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM editora";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

            	ArrayList<Editora> editoras = new ArrayList<Editora>();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");;
                    int codigo = resultSet.getInt("codigo");
                    String contato = resultSet.getString("contato");
                    
                   Editora edit = new Editora(codigo, nome, contato);
                   editoras.add(edit);
                }
                return editoras;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<Autor> retornarAutores() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM autores";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

            	ArrayList<Autor> autores = new ArrayList<Autor>();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");;
                    int codigo = resultSet.getInt("codigo");
                    String nacionalidade = resultSet.getString("nacionalidade");
                    
                    Autor autor = new Autor(codigo, nome, nacionalidade);
                    autores.add(autor);
                }
                return autores;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<Usuario> retornarUsuarios() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM usuarios";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

            	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
                while (resultSet.next()) {
                    int cpf = resultSet.getInt("cpf");
                    String telefone = resultSet.getString("telefone");
                    String nome = resultSet.getString("nome");
                    String endereco = resultSet.getString("endereco");
                    
                    Usuario user = new Usuario(cpf, nome, endereco, telefone);
                    
                   usuarios.add(user);
                }
                return usuarios;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<Funcionario> retornarFuncionarios() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM funcionarios";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

            	ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
                while (resultSet.next()) {       
                    int cpf = resultSet.getInt("cpf");
                    String nome = resultSet.getString("nome");
                    String funcao = resultSet.getString("funcao");
                    float salario = resultSet.getFloat("salario");
                   
                    Funcionario func = new Funcionario(cpf, nome, funcao, salario);
                    
                   funcionarios.add(func);
                }
                return funcionarios;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}



