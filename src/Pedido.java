/**
 * CLASSE simples + ENCAPSULAMENTO.
 * Representa um pedido que entra nas filas (Queue / ArrayDeque).
 */
public class Pedido {

    private static int contador = 1; // gera o número do pedido automaticamente

    private final int numero;
    private final Produto produto;
    private final int quantidade;
    private final boolean delivery; // true = pedido de delivery/expresso (tem prioridade)

    public Pedido(Produto produto, int quantidade, boolean delivery) {
        this.numero = contador++;
        this.produto = produto;
        this.quantidade = quantidade;
        this.delivery = delivery;
    }

    public int getNumero() {
        return numero;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public double getValorTotal() {
        return produto.getPrecoComDesconto() * quantidade;
    }

    @Override
    public String toString() {
        return String.format("Pedido #%d - %dx %s - R$ %.2f%s",
                numero, quantidade, produto.getNome(), getValorTotal(),
                delivery ? " [DELIVERY]" : "");
    }
}
