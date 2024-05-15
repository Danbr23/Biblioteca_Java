package controller;

import model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MetodosSql {

	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/biblioteca";
	private static final String DATABASE_USER = "root";
	private static final String DATABASE_PASSWORD = "";

	public static void insertData(int id, String name) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

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
	/*
	public static ArrayList<String> retornarTitulos() {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "SELECT titulo FROM livros";
			try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

				ArrayList<String> titulos = new ArrayList<String>();
				while (resultSet.next()) {
					String titulo = resultSet.getString("titulo");
					;
					titulos.add(titulo);
				}
				return titulos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/

	public static ArrayList<Livro> retornarLivros() {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "SELECT * FROM livros";
			try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

				ArrayList<Livro> livros = new ArrayList<Livro>();
				while (resultSet.next()) {
					String titulo = resultSet.getString("titulo");
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
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "SELECT * FROM editora";
			try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

				ArrayList<Editora> editoras = new ArrayList<Editora>();
				while (resultSet.next()) {
					String nome = resultSet.getString("nome");
					;
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
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "SELECT * FROM autores";
			try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

				ArrayList<Autor> autores = new ArrayList<Autor>();
				while (resultSet.next()) {
					String nome = resultSet.getString("nome");
					;
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
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "SELECT * FROM usuarios";
			try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

				ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
				while (resultSet.next()) {
					String cpf = resultSet.getString("cpf");
					String telefone = resultSet.getString("contato");
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
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "SELECT * FROM funcionarios";
			try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

				ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
				while (resultSet.next()) {
					String cpf = resultSet.getString("cpf");
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
	
	public static boolean updateLivro(String titulo, String genero, int anoPublic, int edicao, int novoCodigo, int editora, int codigoLivro) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "UPDATE livros SET titulo = (?), genero = (?), ano = (?), edicao = (?), codigo = (?), codigoEditora = (?) WHERE codigo = (?)";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {

				statement.setString(1, titulo);
				statement.setString(2, genero);
				statement.setInt(3, anoPublic);
				statement.setInt(4, edicao);
				statement.setInt(5,novoCodigo);
				statement.setInt(6,editora);
				statement.setInt(7, codigoLivro);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O livro foi editado");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	};
	
	public static boolean updateUsuario(String nome, String endereco, String novoCpf, String contato, String cpf) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "UPDATE usuarios SET nome = (?), endereco = (?), contato = (?), cpf = (?) WHERE cpf = (?)";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {

				statement.setString(1, nome);
				statement.setString(2, endereco);
				statement.setString(3, contato);
				statement.setString(4, novoCpf);
				statement.setString(5,cpf);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O usuario foi editado");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	};
	
	public static boolean updateAutor(String nome, String nacionalidade, int novoCodigo, int codigo) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "UPDATE Autores SET nome = (?), nacionalidade = (?), codigo = (?), WHERE codigo = (?)";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {

				statement.setString(1, nome);
				statement.setString(2, nacionalidade);
				statement.setInt(3, novoCodigo);
				statement.setInt(4, codigo);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O autor foi editado");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	};
	
	public static boolean updateEditora(String nome, String contato, int novoCodigo, int codigo) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "UPDATE Editora SET nome = (?), contato = (?), codigo = (?), WHERE codigo = (?)";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {

				statement.setString(1, nome);
				statement.setString(2, contato);
				statement.setInt(3, novoCodigo);
				statement.setInt(4, codigo);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("A editora foi editada");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	};
	
	public static boolean updateFuncionario(String nome, String funcao, Double salario, String novoCpf, String cpf) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

			String sql = "UPDATE funcionarios SET nome = (?), funcao = (?), salario = (?), cpf = (?) WHERE cpf = (?)";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {

				statement.setString(1, nome);
				statement.setString(2, funcao);
				statement.setDouble(3, salario);
				statement.setString(4, novoCpf);
				statement.setString(5,cpf);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O funcionario foi editado");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	};
	
	public static boolean addLivro(String titulo, String genero, int anoPublic, int edicao, int codigoLivro, int editora) {
		//String sql = "UPDATE livros SET titulo = (?), genero = (?), ano = (?), edicao = (?), codigo = (?), codigoEditora = (?) WHERE codigo = (?)";
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {	
			String sql = "INSERT INTO Livros VALUES (?,?,?,?,?,?)"; 
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
	
				statement.setInt(1, codigoLivro);
				statement.setString(2, titulo);
				statement.setInt(3, edicao);
				statement.setString(4, genero);
				statement.setInt(5, anoPublic);
				statement.setInt(6,editora);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O livro foi adicionado!");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	public static boolean addUsuario(String nome, String endereco, String contato, String cpf) {
		//String sql = "UPDATE livros SET titulo = (?), genero = (?), ano = (?), edicao = (?), codigo = (?), codigoEditora = (?) WHERE codigo = (?)";
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {	
			String sql = "INSERT INTO usuarios VALUES (?,?,?,?)"; 
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
	
				statement.setString(1,nome);
				statement.setString(2, endereco);
				statement.setString(3, contato);
				statement.setString(4, cpf);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O usuario foi adicionado!");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	public static boolean addAutor(String nome, String nacionalidade, int codigo) {
		//String sql = "UPDATE livros SET titulo = (?), genero = (?), ano = (?), edicao = (?), codigo = (?), codigoEditora = (?) WHERE codigo = (?)";
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {	
			String sql = "INSERT INTO Autores VALUES (?,?,?)"; 
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
	
				statement.setString(1,nome);
				statement.setString(2, nacionalidade);
				statement.setInt(3, codigo);
				
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O autor foi adicionado!");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	public static boolean addEditora(String nome, String contato, int codigo) {
		//String sql = "UPDATE livros SET titulo = (?), genero = (?), ano = (?), edicao = (?), codigo = (?), codigoEditora = (?) WHERE codigo = (?)";
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {	
			String sql = "INSERT INTO Editora VALUES (?,?,?)"; 
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
	
				statement.setInt(1,codigo);
				statement.setString(2, nome);
				statement.setString(3, contato);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("A editora foi adicionado!");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	public static boolean addFuncionaroi(String cpf, String nome, String funcao, Double salario) {
		//String sql = "UPDATE livros SET titulo = (?), genero = (?), ano = (?), edicao = (?), codigo = (?), codigoEditora = (?) WHERE codigo = (?)";
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {	
			String sql = "INSERT INTO funcionarios VALUES (?,?,?,?)"; 
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
	
				statement.setString(1,cpf);
				statement.setString(2, nome);
				statement.setString(3, funcao);
				statement.setDouble(4, salario);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O funcinario foi adicionado!");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	public static boolean removerLivro(int codigoLivro) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {	
			String sql = "DELETE FROM livros WHERE codigo = (?)"; 
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
	
				statement.setInt(1, codigoLivro);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O livro foi removido!");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public static boolean removerUsuario(String cpf) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {	
			String sql = "DELETE FROM usuarios WHERE cpf = (?)"; 
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
	
				statement.setString(1, cpf);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O usuario foi removido!");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public static boolean removerFuncionario(String cpf) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {	
			String sql = "DELETE FROM funcionarios WHERE cpf = (?)"; 
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
	
				statement.setString(1, cpf);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O funcionario foi removido!");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public static boolean removerEditora(int codigo) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {	
			String sql = "DELETE FROM Editora WHERE codigo = (?)"; 
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
	
				statement.setInt(1, codigo);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("A editora foi removida!");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public static boolean removerAutor(int codigo) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {	
			String sql = "DELETE FROM Autores WHERE codigo = (?)"; 
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
	
				statement.setInt(1, codigo);
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("O autor foi removido!");
					return true;
				}
				return false;
			}catch (Exception e) {
				return false;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
}
