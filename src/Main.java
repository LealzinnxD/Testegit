/**
 * Classe Main: ponto de entrada do programa.
 * Aqui executamos passo a passo cada conceito pedido no projeto.
 */
public class Main {

    public static void main(String[] args) {

        Lanchonete loja = new Lanchonete();

        System.out.println("============ CADASTRO (Classes + Herança + Encapsulamento) ============");
        Doce brigadeiro = new Doce(1, "Brigadeiro", 2.50, 50, 3);
        Salgado coxinha = new Salgado(2, "Coxinha", 6.00, 30, "Frito");
        Doce bolo = new Doce(3, "Bolo de Chocolate (fatia)", 7.50, 15, 2);
        Salgado empada = new Salgado(4, "Empada de Frango", 6.50, 20, "Assado");
        Combo combo1 = new Combo(5, "Combo Lanche da Tarde", 11.00, 10, brigadeiro, coxinha);

        loja.cadastrarProduto(brigadeiro);
        loja.cadastrarProduto(coxinha);
        loja.cadastrarProduto(bolo);
        loja.cadastrarProduto(empada);
        loja.cadastrarProduto(combo1);

        System.out.println("\n============ INTERFACE + POLIMORFISMO (Promocional) ============");
        brigadeiro.aplicarPromocao(20); // 20% de desconto
        bolo.aplicarPromocao(10);       // 10% de desconto

        System.out.println("\n============ LINKEDLIST + FOR-EACH + POLIMORFISMO ============");
        loja.listarProdutosCadastrados();

        System.out.println("\n============ TREEMAP - ÁRVORE ORDENADA POR CÓDIGO ============");
        loja.listarCatalogoOrdenadoPorCodigo();

        System.out.println("\n============ LINKEDLIST - REMOVE() ============");
        loja.removerProduto(4); // remove a Empada de Frango
        loja.listarProdutosCadastrados();
        loja.listarCatalogoOrdenadoPorCodigo();

        System.out.println("\n============ QUEUE - FIFO ============");
        loja.fazerPedidoBalcao(new Pedido(brigadeiro, 6, false));
        loja.fazerPedidoBalcao(new Pedido(coxinha, 3, false));
        loja.prepararProximoPedidoBalcao(); // brigadeiro é preparado primeiro (entrou primeiro)
        loja.prepararProximoPedidoBalcao(); // depois a coxinha

        System.out.println("\n============ ARRAYDEQUE - FILA COM PRIORIDADE ============");
        loja.fazerPedidoComPrioridade(new Pedido(bolo, 2, false));
        loja.fazerPedidoComPrioridade(new Pedido(combo1, 1, true)); // delivery, fura a fila
        loja.prepararProximoComPrioridade(); // combo (delivery) é preparado primeiro

        System.out.println("\n============ STACK - LIFO (desfazer ações) ============");
        loja.mostrarHistorico();
        loja.desfazerUltimaAcao();
        loja.mostrarHistorico();
    }
}
