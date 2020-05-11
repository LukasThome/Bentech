/*
 * será responsavel pela execução dos comandos SQL
 * 
 * 
 */
package projetobentech001.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import projetobentech001.conexao.ConexaoMyDB;
import projetobentech001.views.frmTelaPrincipal;
import projetobentech001.views.frmLogin;

/**
 *
 * @author thome
 */
public class LoginConexao {
    
    public void inserirUsuario()
    {
        
        Connection conn = null;
        String sql = "INSERT INTO tb_login (usuario,senha) VALUES (?, ?) ";
        conn = ConexaoMyDB.getConexao();
        
        PreparedStatement stmt = null;
        
        try
        {
           
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Login.usuario);
            stmt.setString(2, Login.senha);
            
            stmt.executeUpdate();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo no banco de dados. Erro: " + ex);
            
        }
        finally
        {            
            ConexaoMyDB.fecharConexao(conn, stmt);
        }
    }
    
    
    public void verificarUsuario()
    {
        Connection conn = null;
        String sql = "SELECT * FROM tb_login WHERE usuario = '" + Login.usuario + "'";
        conn = ConexaoMyDB.getConexao();
        
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        
        try
        {
            
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            rs.next();
            
            String usuario1 = rs.getString("usuario");
            String senha1 = rs.getString("senha");
            
            //testar se o usuario  é o mesmo digitado
            
            if((usuario1.equals(Login.usuario)) && (senha1.equals(Login.senha)))
            {
                
                frmTelaPrincipal tp = new frmTelaPrincipal();
                tp.setVisible(true);
                
                
            }
            
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o login. Erro: " + ex);
            
        }
        finally
        {            
            ConexaoMyDB.fecharConexao(conn, stmt);
        }
    }
    
}
