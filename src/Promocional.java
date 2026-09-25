/**
 * INTERFACE + POLIMORFISMO
 *
 * Qualquer produto que implemente esta interface sabe entrar e saltar
 * de promoção e calcular seu próprio preço com desconto. Cada tipo de
 * produto (Doce, Salgado, Combo) usa a MESMA interface, mas o preço
 * final calculado pode variar de acordo com as regras de cada um.
 */
public interface Promocional {
    void aplicarPromocao(double percentualDesconto);
    double getPrecoComDesconto();
    boolean isEmPromocao();
}
