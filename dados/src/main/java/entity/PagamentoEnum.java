package entity;

public enum PagamentoEnum {
    CARTAO_CREDITO(1, "Cartão de Crédito"),
    TRANSFERENCIA_BANCARIA(2, "Transferência Bancária"),
    PAYPAL(3, "PayPal");

    private final int id;
    private final String nome;

    PagamentoEnum(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static PagamentoEnum fromNome(String nome) {
        for (PagamentoEnum tipo : PagamentoEnum.values()) {
            if (tipo.getNome().equalsIgnoreCase(nome)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de pagamento não encontrado: " + nome);
    }
}
