package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/func";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";


    public static void main(String[] args) {
    	/*
        insertData(1, "Teste");
        readData();
        */
    	System.out.println("Teste");
    }


    public static void insertData(int id, String name) {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "INSERT INTO funcionarios (idfuncionario, nome) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1,5);
                statement.setString(2, name);
               
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void readData() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM funcionarios";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {


                while (resultSet.next()) {
                    int id = resultSet.getInt("idfuncionario");
                    String name = resultSet.getString("nome");;


                    System.out.println("ID: " + id + ", Name: " + name + ", Age: ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

