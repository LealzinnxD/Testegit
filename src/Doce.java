/**
 * HERANÇA: Doce estende Produto e adiciona seu próprio atributo
 * (validadeDias), além de implementar getDescricaoEspecial() e
 * getCategoria() do seu próprio jeito (POLIMORFISMO de sobrescrita).
 */
public class Doce extends Produto {

    private int validadeDias; // atributo específico desta subclasse

    public Doce(int codigo, String nome, double preco, int quantidadeEstoque, int validadeDias) {
        super(codigo, nome, preco, quantidadeEstoque);
        this.validadeDias = validadeDias;
    }

    @Override
    public String getDescricaoEspecial() {
        return "Validade de " + validadeDias + " dia(s)";
    }

    @Override
    public String getCategoria() {
        return "Doce";
    }

    public int getValidadeDias() {
        return validadeDias;
    }
}
