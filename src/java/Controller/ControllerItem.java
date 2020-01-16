package Controller;

import DAO.ItemDoPedidoDAO;
import Model.ItemDoPedido;

public class ControllerItem {
    ItemDoPedidoDAO iDAO;
    
    public ControllerItem(){
        iDAO = new ItemDoPedidoDAO();
    }
    
    public void inserirItem(ItemDoPedido p){
        iDAO.inserirItemDoPedido(p);
    }
}
