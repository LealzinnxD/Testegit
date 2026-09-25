# Sistema de Venda de Doces e Salgados — Projeto Java (POO + Coleções)

Projeto de console que simula o gerenciamento de uma confeitaria/lanchonete,
cobrindo todos os tópicos pedidos de forma integrada.

## Como compilar e executar

```bash
cd src
javac *.java
java Main
```

## Estrutura

| Arquivo | Papel |
|---|---|
| `Promocional.java` | Interface |
| `Produto.java` | Classe abstrata (base da herança/encapsulamento) |
| `Doce.java`, `Salgado.java`, `Combo.java` | Subclasses (herança + polimorfismo) |
| `Pedido.java` | Classe simples usada nas filas |
| `Lanchonete.java` | Gerenciador — reúne LinkedList, TreeMap, Queue, ArrayDeque e Stack |
| `Main.java` | Demonstração passo a passo de cada conceito |

## Onde está cada conceito pedido

- **Classes** — todas as 7 classes/interfaces do projeto.
- **Herança** — `Doce`, `Salgado` e `Combo` estendem `Produto`.
- **Encapsulamento** — atributos `private` em `Produto` e `Pedido`, acessados
  só por getters/setters (com validação simples em alguns setters).
- **LinkedList + Polimorfismo** — `Lanchonete.produtosCadastrados` é uma
  `LinkedList<Produto>`. Ela guarda `Doce`, `Salgado` e `Combo` juntos, e cada
  chamada a `getDescricaoEspecial()`/`getCategoria()` executa a versão certa
  de cada subclasse.
- **LinkedList — for-each** — método `listarProdutosCadastrados()`.
- **LinkedList — remove()** — método `removerProduto(int codigo)`, usando
  `Iterator.remove()` para remoção segura durante a iteração.
- **TreeMap — Árvore** — `catalogo` é um `TreeMap<Integer, Produto>`, que
  mantém as chaves (códigos) sempre ordenadas automaticamente
  (`firstKey()`/`lastKey()` mostrados no exemplo).
- **Queue — FIFO** — `filaBalcao` (interface `Queue`, implementada por
  `LinkedList`): `offer()` insere no fim, `poll()` remove do início — o
  primeiro pedido do balcão é o primeiro a ser preparado.
- **ArrayDeque** — `filaComPrioridade` usa `ArrayDeque` como deque: pedidos
  de delivery/expresso entram pela frente (`addFirst`) e furam a fila comum.
- **Stack — LIFO** — `historicoVendas` é um `Stack<String>` usado como
  histórico de ações: `push()` para registrar, `pop()` para "desfazer" a
  última ação (ex.: cancelar a última venda lançada por engano).
- **Interface + Polimorfismo** — `Promocional` é implementada por `Produto`
  e herdada por todas as subclasses; `aplicarPromocao()`/`getPrecoComDesconto()`
  funcionam de forma uniforme para qualquer tipo de produto.

## Saída esperada (resumo)

O `Main` executa, em ordem:
1. Cadastra 5 produtos (2 doces, 2 salgados, 1 combo).
2. Aplica promoção em 2 produtos.
3. Lista todos via `for-each` na `LinkedList` (mostra o polimorfismo das descrições).
4. Lista o catálogo ordenado por código via `TreeMap`.
5. Remove um produto da `LinkedList`/`TreeMap` e lista de novo.
6. Demonstra a fila do balcão (FIFO) com `Queue`.
7. Demonstra a fila com prioridade (delivery) usando `ArrayDeque`.
8. Mostra e desfaz ações do histórico usando `Stack` (LIFO).

## Observação sobre o ambiente

Este ambiente de geração de código tem apenas o JRE instalado (sem `javac`),
então o código não pôde ser compilado automaticamente aqui — mas foi revisado
manualmente (chaves/parênteses balanceados, tipos e assinaturas conferidos) e
deve compilar sem erros com qualquer JDK 11+. Recomendo compilar e rodar você
mesmo para confirmar e se sentir seguro para apresentar.
