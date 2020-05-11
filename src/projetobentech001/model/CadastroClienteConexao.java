/*
 * Responsavel por guardar no banco de dados a informação
 * 
 * 
 */
package projetobentech001.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import projetobentech001.conexao.ConexaoMyDB;

/**
 *
 * @author thome
 */
public class CadastroClienteConexao {
    
    /**
     *
     */
    public void InserirCliente()
    {
        
        Connection conn = null;
        
        String sql = "INSERT INTO tb_cadastro (nome, sobrenome, idade, data, sexo, cpf, codigo_cliente) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        conn = ConexaoMyDB.getConexao();//conectar ao banco de dados
        
        PreparedStatement stmt = null;
        
        try
        {
           
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, CadastroCliente.nome_cliente);
            stmt.setString(2,CadastroCliente.sobrenome_cliente);
            stmt.setInt(3,CadastroCliente.idade_cliente);
            stmt.setDate(4, (Date) CadastroCliente.data_cliente);
            stmt.setString(5,CadastroCliente.sexo_cliente);
            stmt.setString(6,CadastroCliente.cpf_cliente);
            stmt.setString(7,CadastroCliente.codigo_cliente);
            
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
    
}
