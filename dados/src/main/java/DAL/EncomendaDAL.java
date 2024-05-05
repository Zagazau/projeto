package DAL;

import entity.Encomenda;
import java.util.ArrayList;
import java.util.List;

public class EncomendaDAL {
    private List<Encomenda> encomendas;

    public EncomendaDAL() {
        this.encomendas = new ArrayList<>();
    }

    public void salvar(Encomenda encomenda) {
        encomendas.add(encomenda);
    }

    public List<Encomenda> listar() {
        return encomendas;
    }

    public void atualizar(Encomenda encomenda) {
        for (Encomenda e : encomendas) {
            if (e.getIdencomenda() == encomenda.getIdencomenda()) {
                e.setIdfornecedor(encomenda.getIdfornecedor());
                e.setQuantidade(encomenda.getQuantidade());
                e.setData(encomenda.getData());
                break;
            }
        }
    }

    public void excluir(int id) {
        encomendas.removeIf(encomenda -> encomenda.getIdencomenda() == id);
    }
}
