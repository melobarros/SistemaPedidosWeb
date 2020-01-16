package Bean;

import DAO.ProdutoDAO;
import Model.Cliente;
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
public class ProdutoBean {

    private Produto p = new Produto("");
    private List<Produto> pList = new ArrayList<>();
    ProdutoDAO pDAO = new ProdutoDAO();

    public void salvar() throws ClassNotFoundException {
        if (p.getId() == -1) {
            pDAO.inserirProduto(p);
        } else {
            pDAO.atualizarProduto(p);
        }

        p = new Produto("");
    }

    public void editar(Produto p_) throws ClassNotFoundException {
        p = p_;
    }

    public void excluir(Produto p_) throws ClassNotFoundException {
        pDAO.excluirProduto(p_.getId());
        addMsg("Excluir", "Produto excluido", FacesMessage.SEVERITY_INFO);

        p = new Produto("");
    }

    public void addMsg(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }

    public List<Produto> getpList() throws ClassNotFoundException {
        pList = pDAO.listarProdutos();
        return pList;
    }

    public void setpList(List<Produto> pList) {
        this.pList = pList;
    }


}
