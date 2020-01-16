package Controller;

import DAO.ProdutoDAO;
import Model.Produto;
import java.util.List;

public class ControllerProduto {
    ProdutoDAO pDAO;
    
    public ControllerProduto(){
        pDAO = new ProdutoDAO();
    }
    
    public Produto consultaProduto(int id){
        return pDAO.consultarProduto(id);
    }
    
    public List<Produto> listarProdutos(){
        return pDAO.listarProdutos();
    }
    
    public void inserirProduto(Produto p){
        pDAO.inserirProduto(p);
    }
    
    public void atualizarProduto(Produto p){
        pDAO.atualizarProduto(p);
    }
    
    public void excluirProduto(int id){
        pDAO.excluirProduto(id);
    }
}
