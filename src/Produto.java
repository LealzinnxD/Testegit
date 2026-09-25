/**
 * CLASSE (abstrata) + HERANÇA + ENCAPSULAMENTO
 *
 * Produto é a superclasse de Doce, Salgado e Combo (HERANÇA).
 * Os atributos são todos privados e só podem ser acessados/alterados
 * através de getters/setters (ENCAPSULAMENTO).
 *
 * getDescricaoEspecial() é abstrato: cada subclasse é OBRIGADA a
 * implementá-lo do seu próprio jeito -> base do POLIMORFISMO usado
 * no restante do projeto.
 *
 * Produto também implementa Promocional (INTERFACE + POLIMORFISMO).
 */
public abstract class Produto implements Promocional {

    // ---- Atributos privados (encapsulamento) ----
    private final int codigo;
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private boolean emPromocao;
    private double percentualDesconto;

    public Produto(int codigo, String nome, double preco, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.emPromocao = false;
        this.percentualDesconto = 0;
    }

    // Método abstrato -> cada subclasse descreve a si mesma do seu jeito (POLIMORFISMO)
    public abstract String getDescricaoEspecial();

    // Outro método sobrescrevível (mais polimorfismo)
    public String getCategoria() {
        return "Produto genérico";
    }

    // ---- Implementação da interface Promocional ----
    @Override
    public void aplicarPromocao(double percentualDesconto) {
        this.emPromocao = true;
        this.percentualDesconto = percentualDesconto;
        System.out.printf("🏷 %s entrou em promoção: %.0f%% de desconto!%n", nome, percentualDesconto);
    }

    @Override
    public double getPrecoComDesconto() {
        if (emPromocao) {
            return preco - (preco * percentualDesconto / 100.0);
        }
        return preco;
    }

    @Override
    public boolean isEmPromocao() {
        return emPromocao;
    }

    // ---- Getters e Setters (encapsulamento) ----
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco > 0) {
            this.preco = preco;
        }
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if (quantidadeEstoque >= 0) {
            this.quantidadeEstoque = quantidadeEstoque;
        }
    }

    public void venderUnidade() {
        if (quantidadeEstoque > 0) {
            quantidadeEstoque--;
        }
    }

    @Override
    public String toString() {
        return String.format("[Cód:%d] %-12s | %-20s | %-28s | R$ %6.2f%s | Estoque: %d",
                codigo, nome, getCategoria(), getDescricaoEspecial(), getPrecoComDesconto(),
                emPromocao ? " (promo)" : "", quantidadeEstoque);
    }
}
