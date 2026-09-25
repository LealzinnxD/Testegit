/**
 * HERANÇA: Salgado também estende Produto, mas implementa
 * getDescricaoEspecial() e getCategoria() de forma diferente do
 * Doce -> POLIMORFISMO.
 */
public class Salgado extends Produto {

    private String tipoPreparo; // "Frito" ou "Assado"

    public Salgado(int codigo, String nome, double preco, int quantidadeEstoque, String tipoPreparo) {
        super(codigo, nome, preco, quantidadeEstoque);
        this.tipoPreparo = tipoPreparo;
    }

    @Override
    public String getDescricaoEspecial() {
        return "Preparo: " + tipoPreparo;
    }

    @Override
    public String getCategoria() {
        return "Salgado";
    }

    public String getTipoPreparo() {
        return tipoPreparo;
    }
}
