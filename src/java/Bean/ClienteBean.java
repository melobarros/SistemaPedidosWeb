package Bean;

import DAO.ClienteDAO;
import DAO.PedidoDAO;
import Model.Cliente;
import Model.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ClienteBean {

    private Cliente c = new Cliente("", "", "");
    private List<Cliente> cList = new ArrayList<>();
    ClienteDAO cDAO = new ClienteDAO();
    PedidoDAO pDAO = new PedidoDAO();

    public void salvar() throws ClassNotFoundException {
        if (c.getId() == -1) {
            cDAO.inserirCliente(c);
        } else {
            cDAO.atualizarCliente(c);
        }

        c = new Cliente("", "", "");
    }

    public void editar(Cliente c_) throws ClassNotFoundException {
        c = c_;
    }

    public void excluir(Cliente c_) throws ClassNotFoundException {

        Pedido pedidoLido = null;
        pedidoLido = pDAO.consultaPedido(c_.getId());

        if (pedidoLido != null) {
            addMsg("Excluir", "Cliente nao pode ser excluido!", FacesMessage.SEVERITY_INFO);
        } else {
            cDAO.excluirCliente(c_.getId());
            addMsg("Excluir", "Cliente excluido", FacesMessage.SEVERITY_INFO);
        }

           

        c = new Cliente("", "", "");
    }

    public void addMsg(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public List<Cliente> getcList() throws ClassNotFoundException {
        cList = cDAO.listarClientes();
        return cList;
    }

    public void setcList(List<Cliente> cList) {
        this.cList = cList;
    }

}
