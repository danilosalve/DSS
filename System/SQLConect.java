package System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe de conexão com banco de dados
 * @author Danilo Salve
 * @since 23/02/2019
 *
 */

public class SQLConect {
	//Dados para a conexão com o banco
    private static final String USUARIO = "dssAdmin";
    private static final String SENHA = "1234";
    private static final String DATABASE = "DSS";
    private static final String DRIVER_CONEXAO = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String STR_CONEXAO = "jdbc:sqlserver://localhost:1433;databaseName=DSS";
    
 
    public static Connection getConexao() throws SQLException, ClassNotFoundException {
 
        Connection conn = null;
        try {
            Class.forName(DRIVER_CONEXAO);
            conn = DriverManager.getConnection(STR_CONEXAO,USUARIO, SENHA); 
            return conn; 
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(
                    "Driver SQL Server não foi encontrado " + e.getMessage());
 
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar "
                    + "com a base de dados" + e.getMessage());
        }
    }
 
    public static void fechaConexao(Connection conn) {
 
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexão encerrada com o banco de dados");
            }
 
        } catch (Exception e) {
            System.out.println("Não foi possível encerrar a conexão com o banco de dados " + e.getMessage());
        }
    }
 
    public static void fechaConexao(Connection conn, PreparedStatement stmt) {
 
        try {
            if (conn != null) {
                fechaConexao(conn);
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement encerrado com sucesso");
            }
 
 
        } catch (Exception e) {
            System.out.println("Não foi possível encerrar o statement " + e.getMessage());
        }
    }
 
    public static void fechaConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {
 
        try {
            if (conn != null || stmt != null) {
                fechaConexao(conn, stmt);
            }
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet encerrado com sucesso");
            }
 
 
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o ResultSet " + e.getMessage());
        }
    }
}
