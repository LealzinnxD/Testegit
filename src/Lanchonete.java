import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Classe principal de gerenciamento da confeitaria/lanchonete.
 * Reúne todas as estruturas de dados exigidas no projeto, cada uma
 * resolvendo um problema diferente do negócio.
 */
public class Lanchonete {

    // ------------------------------------------------------------------
    // LINKEDLIST + POLIMORFISMO
    // Guarda os produtos na ordem em que foram cadastrados.
    // Como é uma LinkedList<Produto>, ela aceita Doce, Salgado, Combo...
    // e ao chamar produto.getDescricaoEspecial() o Java executa a versão
    // certa de cada subclasse automaticamente (polimorfismo).
    // ------------------------------------------------------------------
    private LinkedList<Produto> produtosCadastrados = new LinkedList<>();

    // ------------------------------------------------------------------
    // TREEMAP - ÁRVORE
    // Por baixo dos panos o TreeMap é implementado como uma árvore
    // rubro-negra balanceada. As chaves (código do produto) ficam
    // SEMPRE ordenadas, sem precisar ordenar manualmente.
    // ------------------------------------------------------------------
    private TreeMap<Integer, Produto> catalogo = new TreeMap<>();

    // ------------------------------------------------------------------
    // QUEUE - FIFO (First In, First Out)
    // A interface Queue é implementada aqui por uma LinkedList.
    // offer() insere no fim, poll() remove do início -> primeiro
    // pedido feito no balcão é o primeiro a ser preparado.
    // ------------------------------------------------------------------
    private Queue<Pedido> filaBalcao = new LinkedList<>();

    // ------------------------------------------------------------------
    // ARRAYDEQUE (fila de duas pontas)
    // Usada como fila "com prioridade": pedidos de delivery/expresso
    // entram pela FRENTE (addFirst) e furam a fila; pedidos comuns
    // entram pelo FIM (addLast), como uma fila normal.
    // ------------------------------------------------------------------
    private Deque<Pedido> filaComPrioridade = new ArrayDeque<>();

    // ------------------------------------------------------------------
    // STACK - LIFO (Last In, First Out)
    // Histórico de vendas/ações do caixa. A última ação registrada é
    // sempre a primeira a ser "desfeita" (pop) -> ex.: cancelar a
    // última venda lançada por engano.
    // ------------------------------------------------------------------
    private Stack<String> historicoVendas = new Stack<>();

    // =================== CADASTRO ===================

    public void cadastrarProduto(Produto produto) {
        produtosCadastrados.add(produto);
        catalogo.put(produto.getCodigo(), produto);
        historicoVendas.push("CADASTRO: " + produto.getNome() + " (Cód " + produto.getCodigo() + ")");
        System.out.println("✔ Cadastrado: " + produto);
    }

    // =================== LINKEDLIST: remove() ===================

    public boolean removerProduto(int codigo) {
        // Usamos um Iterator para remover com segurança durante a navegação
        Iterator<Produto> it = produtosCadastrados.iterator();
        while (it.hasNext()) {
            Produto p = it.next();
            if (p.getCodigo() == codigo) {
                it.remove(); // remove o elemento atual da LinkedList
                catalogo.remove(codigo);
                historicoVendas.push("REMOÇÃO: " + p.getNome() + " (Cód " + codigo + ")");
                System.out.println("✘ Removido: " + p.getNome());
                return true;
            }
        }
        System.out.println("⚠ Produto com código " + codigo + " não encontrado.");
        return false;
    }

    // =================== LINKEDLIST: for-each + polimorfismo ===================

    public void listarProdutosCadastrados() {
        System.out.println("\n--- Produtos cadastrados (ordem de cadastro - LinkedList) ---");
        for (Produto p : produtosCadastrados) {
            // p.toString() chama internamente p.getDescricaoEspecial() e
            // p.getCategoria(), sobrescritos em cada subclasse -> polimorfismo
            System.out.println(p);
        }
    }

    // =================== TREEMAP: árvore ordenada ===================

    public void listarCatalogoOrdenadoPorCodigo() {
        System.out.println("\n--- Catálogo ordenado por código (TreeMap / árvore) ---");
        for (Map.Entry<Integer, Produto> entry : catalogo.entrySet()) {
            System.out.println("Cód " + entry.getKey() + " -> " + entry.getValue().getNome());
        }
        if (!catalogo.isEmpty()) {
            System.out.println("Menor código cadastrado: " + catalogo.firstKey());
            System.out.println("Maior código cadastrado: " + catalogo.lastKey());
        }
    }

    // =================== QUEUE: FIFO ===================

    public void fazerPedidoBalcao(Pedido pedido) {
        filaBalcao.offer(pedido); // insere no FIM da fila
        System.out.println("📋 Entrou na fila do balcão: " + pedido);
    }

    public void prepararProximoPedidoBalcao() {
        Pedido atual = filaBalcao.poll(); // remove do INÍCIO da fila
        if (atual != null) {
            atual.getProduto().venderUnidade();
            System.out.println("👉 Preparando agora (FIFO): " + atual);
        } else {
            System.out.println("Fila do balcão vazia.");
        }
    }

    // =================== ARRAYDEQUE: fila com prioridade ===================

    public void fazerPedidoComPrioridade(Pedido pedido) {
        if (pedido.isDelivery()) {
            filaComPrioridade.addFirst(pedido); // delivery "fura" a fila
        } else {
            filaComPrioridade.addLast(pedido); // pedido comum entra no fim
        }
        System.out.println("🚨 Entrou na fila com prioridade: " + pedido);
    }

    public void prepararProximoComPrioridade() {
        Pedido atual = filaComPrioridade.pollFirst();
        if (atual != null) {
            atual.getProduto().venderUnidade();
            System.out.println("👉 Preparando agora (prioridade): " + atual);
        } else {
            System.out.println("Fila com prioridade vazia.");
        }
    }

    // =================== STACK: LIFO ===================

    public void desfazerUltimaAcao() {
        if (!historicoVendas.isEmpty()) {
            String ultima = historicoVendas.pop(); // remove o ÚLTIMO item inserido
            System.out.println("↩ Desfazendo última ação: " + ultima);
        } else {
            System.out.println("Não há ações para desfazer.");
        }
    }

    public void mostrarHistorico() {
        System.out.println("\n--- Histórico de ações (mais recente primeiro - Stack/LIFO) ---");
        ListIterator<String> it = historicoVendas.listIterator(historicoVendas.size());
        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }
    }
}
