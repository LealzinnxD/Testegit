/**
 * HERANÇA: terceiro tipo de Produto, para deixar o polimorfismo da
 * LinkedList ainda mais evidente (3 implementações diferentes de
 * getDescricaoEspecial() / getCategoria()).
 *
 * Um Combo agrupa um Doce e um Salgado já cadastrados.
 */
public class Combo extends Produto {

    private Doce doce;
    private Salgado salgado;

    public Combo(int codigo, String nome, double preco, int quantidadeEstoque, Doce doce, Salgado salgado) {
        super(codigo, nome, preco, quantidadeEstoque);
        this.doce = doce;
        this.salgado = salgado;
    }

    @Override
    public String getDescricaoEspecial() {
        return "Combo: " + salgado.getNome() + " + " + doce.getNome();
    }

    @Override
    public String getCategoria() {
        return "Combo (Doce + Salgado)";
    }
}
