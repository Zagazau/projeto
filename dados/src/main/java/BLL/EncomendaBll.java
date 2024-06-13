package BLL;

import DAL.EncomendaDAL;
import entity.Encomenda;

import java.util.List;

public class EncomendaBll {
    private EncomendaDAL encomendaDAL;

    public EncomendaBll() {
        this.encomendaDAL = new EncomendaDAL();
    }

    public void salvarEncomenda(Encomenda encomenda) {
        encomendaDAL.salvar(encomenda);
    }

    public List<Encomenda> listarEncomendas() {
        return encomendaDAL.listar();
    }

    public void atualizarEncomenda(Encomenda encomenda) {
        encomendaDAL.atualizar(encomenda);
    }

    public void excluirEncomenda(int id) {
        encomendaDAL.excluir(id);
    }
    public Encomenda obterEncomendaPorId(int id) {
        return encomendaDAL.buscarPorId(id); // Implemente o método buscarPorId na classe EncomendaDAL
    }

}
