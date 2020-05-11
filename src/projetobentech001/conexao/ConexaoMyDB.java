/*
 * Classe para conectar com o banco de dados
 * 
 * 
 */
package projetobentech001.conexao;



import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author thome
 */
public class ConexaoMyDB {
    
    //atributos para concexao
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "LTSlucas14@";
    
    
    public static Connection getConexao(){
        
        try
        {
            Class.forName(DRIVER);
            
            return (Connection) DriverManager.getConnection(URL, USER, PASS);
        }
        catch(ClassNotFoundException | SQLException ex)
        {
           throw new RuntimeException("Algo aconteceu de errado com a conexão com o banco, veja: " + ex);
                    
        }
    }
    
    public static void fecharConexao(Connection conn)
    {
        if(conn != null)//se estiver conectado
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
                throw new RuntimeException("Algo aconteceu de errado com o fechamento da conexao com o banco,, veja: " + ex);
            }
        }
    }
    
    public static void fecharConexao(Connection conn, PreparedStatement stmt)
    {
        if(stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch(SQLException ex)
            {
                throw new RuntimeException("Algo aconteceu de errado com o fechamento da conexao com o banco,, veja: " + ex);
            }
        }
    
        fecharConexao(conn);
    }
    
    
    public static void fecharConexao(Connection conn, PreparedStatement stmt, ResultSet rs)
    {
        try
        {
            rs.close();
        }
        catch(SQLException ex)
        {
            throw new RuntimeException("Algo aconteceu de errado com o fechamento da conexao com o banco,, veja: " + ex);
        }
    fecharConexao(conn, stmt);
    }
    
}