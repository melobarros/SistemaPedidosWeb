package Bean;

import DAO.ClienteDAO;
import DAO.PedidoDAO;
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
public class PedidoBean {

    private Pedido p = new Pedido("", null, null);
    private List<ItemDoPedido> ipList = new ArrayList<>();
    PedidoDAO pDAO = new PedidoDAO();
    ClienteDAO cDAO = new ClienteDAO();
    String cpf = "";

    public void listar() throws ClassNotFoundException {
        Cliente c;
        ItemDoPedido ip;
        Pedido p;
        Produto prod;
        int id;
        int qtd;
        
        c = cDAO.consultaCliente(cpf);
        
        if(c != null){
            p = pDAO.consultaPedido(c.getId());
            
            if(p != null){
                ipList = pDAO.listaItens(p);
                cpf = "";
            }else{
                addMsg("Pedido", "Pedido nao existe!", FacesMessage.SEVERITY_INFO);
             }
            
        }else{
            addMsg("Cliente", "Cliente nao existe!", FacesMessage.SEVERITY_INFO);
        }
    }

    public Pedido getP() {
        return p;
    }

    public void setP(Pedido p) {
        this.p = p;
    }

    public List<ItemDoPedido> getIpList() {
        return ipList;
    }

    public void setIpList(List<ItemDoPedido> ipList) {
        this.ipList = ipList;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public void addMsg(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }



}
