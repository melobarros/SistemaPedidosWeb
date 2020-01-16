package DAO;

import Connection.ConnectionFactory;
import Model.ItemDoPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDoPedidoDAO {
    private final String stmtInserir = "INSERT INTO item_do_pedido(id_pedido, id_produto, quantidade) VALUES(?,?,?)";
    private final String stmtConsultar = "SELECT * FROM cliente WHERE id = ?";
    private final String stmtListar = "SELECT * FROM cliente";
    private final String stmtExcluir = "DELETE FROM cliente WHERE id = ?";
    private final String stmtAtualizar = "UPDATE cliente SET nome = ?, sobrenome = ?, cpf = ? WHERE id = ?";
    
    public void inserirItemDoPedido(ItemDoPedido item) throws ClassNotFoundException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserir,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, item.getPedido().getId());
            stmt.setInt(2, item.getProduto().getId());
            stmt.setInt(3, item.getQuantidade());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um item ao pedido no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    }
    
    

}
