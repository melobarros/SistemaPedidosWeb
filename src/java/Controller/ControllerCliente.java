package Controller;

import DAO.ClienteDAO;
import Model.Cliente;
import java.util.List;

public class ControllerCliente {
    ClienteDAO cDAO;
    
    public ControllerCliente(){
        cDAO = new ClienteDAO();
    }
    
    public void insereCliente(Cliente c) throws ClassNotFoundException{
        cDAO.inserirCliente(c);
    }
    
    public void atualizaCliente(Cliente c) throws ClassNotFoundException{
        cDAO.atualizarCliente(c);
    }
    
    public void excluiCliente(int id) throws ClassNotFoundException{
        cDAO.excluirCliente(id);
    }
    
    public List<Cliente> listaClientes() throws ClassNotFoundException{
        return cDAO.listarClientes();
    }
    
    public Cliente consultaCliente(String cpf) throws ClassNotFoundException{
        return cDAO.consultaCliente(cpf);
    }
}
