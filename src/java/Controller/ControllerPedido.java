package Controller;

import DAO.PedidoDAO;
import Model.ItemDoPedido;
import Model.Pedido;
import java.util.List;

public class ControllerPedido {
    PedidoDAO pDAO;
    
    public ControllerPedido(){
        pDAO = new PedidoDAO();
    }
    
    public Pedido consultaPedido(int id){
        return pDAO.consultaPedido(id);
    }
    
    public int inserirPedido(Pedido P){
        return pDAO.inserirPedido(P);
    }
    
    public List<ItemDoPedido> listaItens(Pedido p){
        return pDAO.listaItens(p);
    }
}
