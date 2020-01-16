package Bean;

import DAO.ClienteDAO;
import DAO.ItemDoPedidoDAO;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import Model.Cliente;
import Model.ItemDoPedido;
import Model.Pedido;
import Model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ItemBean {

    private ItemDoPedido ip = new ItemDoPedido(null, null, 0);
    private List<Produto> pList = new ArrayList<>();
    private String cpf = null;


    
    ItemDoPedidoDAO ipDAO = new ItemDoPedidoDAO();
    ClienteDAO cDAO = new ClienteDAO();
    ProdutoDAO pDAO = new ProdutoDAO();
    PedidoDAO peDAO = new PedidoDAO();

    Cliente c = new Cliente("", "", "");
    Pedido p;
    Produto prod = null;
    int id;
    int qtd = 1;

    public void incluir() throws ClassNotFoundException {

        c = cDAO.consultaCliente(cpf);
        if(c != null){
            p = peDAO.consultaPedido(c.getId());
            
            if(prod != null){
                if(p != null){
                    ip = new ItemDoPedido(prod, p, qtd);
                    ipDAO.inserirItemDoPedido(ip);
                    addMsg("Sucesso", "Item inserido com sucesso!", FacesMessage.SEVERITY_INFO);
                }else{
                    p = new Pedido("Data", c, null);
                    id = peDAO.inserirPedido(p);
                    p.setId(id);

                    ip = new ItemDoPedido(prod, p, qtd);
                    ipDAO.inserirItemDoPedido(ip);
                    addMsg("Sucesso", "Pedido criado e Item inserido com sucesso!", FacesMessage.SEVERITY_INFO);
                }
            }else{
                addMsg("Selecione", "Selecione um produto!", FacesMessage.SEVERITY_INFO);
            }
            
        }else{
            addMsg("Consulta", "Cliente nao nao existe!", FacesMessage.SEVERITY_INFO);
        }
        
        ip = new ItemDoPedido(null, null, 0);
        cpf = "";
        qtd = 1;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    public void selecionar(Produto p_) throws ClassNotFoundException {
        prod = p_;
    }

    public void addMsg(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }
    
        public ItemDoPedido getIp() {
        return ip;
    }

    public void setIp(ItemDoPedido ip) {
        this.ip = ip;
    }

    public List<Produto> getpList() throws ClassNotFoundException {
        pList = pDAO.listarProdutos();
        return pList;
    }

    public void setpList(List<Produto> pList) throws ClassNotFoundException {
        
        this.pList = pList;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
