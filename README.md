# Documentação do Sistema de Tabela de Símbolos

## Visão Geral
Este sistema implementa uma tabela de símbolos para compiladores, utilizando duas estruturas de dados distintas:
- **Tabela Hash (HashTableTS)**
- **Árvore AVL (AVLTreeTS)**

O objetivo principal é identificar variáveis declaradas e detectar erros de compilação em pseudocódigo, com medição de desempenho comparativa entre as estruturas.

## Estruturas de Dados Implementadas

### 1. Tabela Hash (HashTableTS)
**Características:**
- Implementação com encadeamento para tratamento de colisões
- Tamanho fixo de 40 posições
- Função hash baseada em `hashCode()` da String

**Operações Principais:**
- `put(Symbol valor)`: Insere um símbolo na tabela
- `get(String name)`: Busca um símbolo pelo nome
- `remove(String name)`: Remove um símbolo da tabela

**Complexidade:**
| Operação | Melhor Caso | Pior Caso |
|----------|-------------|-----------|
| Inserção | O(1)        | O(n)      |
| Busca    | O(1)        | O(n)      |
| Remoção  | O(1)        | O(n)      |

### 2. Árvore AVL (AVLTreeTS)
**Características:**
- Árvore de busca binária balanceada
- Auto-balanceamento através de rotações (LL, RR, LR, RL)
- Altura armazenada em cada nó

**Operações Principais:**
- `insertNode(Node node, Symbol item)`: Insere um novo nó mantendo o balanceamento
- `searchNode(Node root, String name)`: Busca recursiva por um símbolo
- `deleteNode(Node root, String name)`: Remove um nó mantendo o balanceamento

**Complexidade:**
| Operação | Melhor Caso | Pior Caso |
|----------|-------------|-----------|
| Inserção | O(log n)    | O(log n)  |
| Busca    | O(log n)    | O(log n)  |
| Remoção  | O(log n)    | O(log n)  |

## Fluxo Principal (SymbolTable)

### Processamento de Arquivo
1. **Leitura do arquivo** linha por linha
2. **Identificação de declarações** usando regex:
   ```regex
   \b(String|int|double|float|boolean|char|byte|short|long)\s+([a-zA-Z_][a-zA-Z0-9_]*)\s*(?:=\s*[^;]+)?\s*;
   ```
3. **Verificação de usos** de variáveis com outra regex:
   ```regex
   \b([a-zA-Z_][a-zA-Z0-9_]*)\b(?![\\w(])
   ```

### Detecção de Erros
- Variáveis não declaradas
- Uso incorreto de palavras reservadas
- Ignora literais (números, strings entre aspas)

### Métricas de Desempenho
- Tempos de inserção e busca
- Quantidade de operações
- Comparação entre estruturas

## Exemplo de Uso

```java
public static void main(String[] args) {
    try {
        String[] testes = {"controleEstoque.txt", "calcSimples.txt"};
        for(String teste : testes) {
            // Teste com HashTable
            SymbolTable hashSymbolTable = new SymbolTable(true);
            hashSymbolTable.processPseudocodeFile(teste);
            hashSymbolTable.printPerformanceReport("HashTable");
            
            // Teste com AVL
            SymbolTable avlSymbolTable = new SymbolTable(false);
            avlSymbolTable.processPseudocodeFile(teste);
            avlSymbolTable.printPerformanceReport("AVL Tree");
        }
    } catch (IOException e) {
        System.err.println("Erro ao processar arquivo: " + e.getMessage());
    }
}
```

## Saída Esperada

```
=== Executando com HashTable (controleEstoque.txt) ===
Variável declarada - Linha 2: quantidadeProdutoA do tipo int
Variável declarada - Linha 3: quantidadeProdutoB do tipo int
Variável declarada - Linha 4: quantidadeProdutoC do tipo int
Variável declarada - Linha 5: precoBaseA do tipo double
Variável declarada - Linha 6: precoBaseB do tipo double
Variável declarada - Linha 7: precoBaseC do tipo double
Variável declarada - Linha 8: descontoFidelidade do tipo float
Variável declarada - Linha 9: clientePremium do tipo boolean
Variável declarada - Linha 10: categoriaCliente do tipo char
Variável declarada - Linha 11: diasUltimaCompra do tipo int
Variável declarada - Linha 38: precoFinalA do tipo double
Variável declarada - Linha 39: precoFinalB do tipo double
Variável declarada - Linha 40: precoFinalC do tipo double
Variável declarada - Linha 43: estoqueTotal do tipo int
Variável declarada - Linha 44: necessitaReposicao do tipo boolean
Variável declarada - Linha 47: pedidoMinimo do tipo int
Variável declarada - Linha 48: custoReposicao do tipo double
ERRO - Linha 51: Variável 'taxaImportacao' não declarada
ERRO - Linha 52: Variável 'valorFrete' não declarada
ERRO - Linha 53: Variável 'custoTotal' não declarada
ERRO - Linha 53: Variável 'taxaImportacao' não declarada
ERRO - Linha 53: Variável 'valorFrete' não declarada
Variável declarada - Linha 57: valorEstoqueA do tipo double
Variável declarada - Linha 58: valorEstoqueB do tipo double
Variável declarada - Linha 59: valorEstoqueC do tipo double
Variável declarada - Linha 60: valorTotalEstoque do tipo double
ERRO - Linha 63: Variável 'margemLucro' não declarada
ERRO - Linha 64: Variável 'valorImposto' não declarada
ERRO - Linha 65: Variável 'liquido' não declarada
ERRO - Linha 65: Variável 'valorImposto' não declarada
Variável declarada - Linha 69: alertaCritico do tipo boolean
Variável declarada - Linha 70: prioridadeReposicao do tipo char
ERRO - Linha 79: Variável 'tempoReposicao' não declarada
ERRO - Linha 88: Variável 'estoqueAtualizado' não declarada

=== Relatório de Desempenho (HashTable) ===
Tempo total de inserção: 53 us
Tempo médio por inserção: 2333 ns
Tempo total de busca: 160 us
Tempo médio por busca: 1625 ns
Total de inserções: 23
Total de buscas: 99
Tempo total (HashTable): 213 us

=== Executando com AVL (controleEstoque.txt) ===
Variável declarada - Linha 2: quantidadeProdutoA do tipo int
Variável declarada - Linha 3: quantidadeProdutoB do tipo int
Variável declarada - Linha 4: quantidadeProdutoC do tipo int
Variável declarada - Linha 5: precoBaseA do tipo double
Variável declarada - Linha 6: precoBaseB do tipo double
Variável declarada - Linha 7: precoBaseC do tipo double
Variável declarada - Linha 8: descontoFidelidade do tipo float
Variável declarada - Linha 9: clientePremium do tipo boolean
Variável declarada - Linha 10: categoriaCliente do tipo char
Variável declarada - Linha 11: diasUltimaCompra do tipo int
Variável declarada - Linha 38: precoFinalA do tipo double
Variável declarada - Linha 39: precoFinalB do tipo double
Variável declarada - Linha 40: precoFinalC do tipo double
Variável declarada - Linha 43: estoqueTotal do tipo int
Variável declarada - Linha 44: necessitaReposicao do tipo boolean
Variável declarada - Linha 47: pedidoMinimo do tipo int
Variável declarada - Linha 48: custoReposicao do tipo double
ERRO - Linha 51: Variável 'taxaImportacao' não declarada
ERRO - Linha 52: Variável 'valorFrete' não declarada
ERRO - Linha 53: Variável 'custoTotal' não declarada
ERRO - Linha 53: Variável 'taxaImportacao' não declarada
ERRO - Linha 53: Variável 'valorFrete' não declarada
Variável declarada - Linha 57: valorEstoqueA do tipo double
Variável declarada - Linha 58: valorEstoqueB do tipo double
Variável declarada - Linha 59: valorEstoqueC do tipo double
Variável declarada - Linha 60: valorTotalEstoque do tipo double
ERRO - Linha 63: Variável 'margemLucro' não declarada
ERRO - Linha 64: Variável 'valorImposto' não declarada
ERRO - Linha 65: Variável 'liquido' não declarada
ERRO - Linha 65: Variável 'valorImposto' não declarada
Variável declarada - Linha 69: alertaCritico do tipo boolean
Variável declarada - Linha 70: prioridadeReposicao do tipo char
ERRO - Linha 79: Variável 'tempoReposicao' não declarada
ERRO - Linha 88: Variável 'estoqueAtualizado' não declarada

=== Relatório de Desempenho (AVL Tree) ===
Tempo total de inserção: 337 us
Tempo médio por inserção: 14672 ns
Tempo total de busca: 281 us
Tempo médio por busca: 2838 ns
Total de inserções: 23
Total de buscas: 99
Tempo total (AVL Tree): 618 us

=== Executando com HashTable (calcSimples.txt) ===
Variável declarada - Linha 1: a do tipo int
Variável declarada - Linha 2: b do tipo int
Variável declarada - Linha 3: resultado do tipo int
Variável declarada - Linha 4: operacao do tipo char
ERRO - Linha 17: Variável 'total' não declarada

=== Relatório de Desempenho (HashTable) ===
Tempo total de inserção: 9 us
Tempo médio por inserção: 2261 ns
Tempo total de busca: 19 us
Tempo médio por busca: 1137 ns
Total de inserções: 4
Total de buscas: 17
Tempo total (HashTable): 28 us

=== Executando com AVL (calcSimples.txt) ===
Variável declarada - Linha 1: a do tipo int
Variável declarada - Linha 2: b do tipo int
Variável declarada - Linha 3: resultado do tipo int
Variável declarada - Linha 4: operacao do tipo char
ERRO - Linha 17: Variável 'total' não declarada

=== Relatório de Desempenho (AVL Tree) ===
Tempo total de inserção: 6 us
Tempo médio por inserção: 1535 ns
Tempo total de busca: 10 us
Tempo médio por busca: 627 ns
Total de inserções: 4
Total de buscas: 17
Tempo total (AVL Tree): 16 us

```

## Comparação de Desempenho

| Métrica               | HashTable | AVL Tree | Observações                     |
|-----------------------|-----------|----------|---------------------------------|
| Inserção              | Mais rápido | Mais lento | AVL tem overhead de balanceamento |
| Busca                 | Similar   | Similar  | AVL pode ser melhor para muitos dados |
| Consistência          | Variável  | Estável  | AVL mantém desempenho no pior caso |
| Uso de Memória        | Maior     | Menor    | HashTable precisa de array extra |

## Casos de Uso Recomendados

**HashTable vantajoso quando:**
- O pseudocódigo tem muitas variáveis globais
- A ordem dos símbolos não é importante
- Prioriza velocidade de inserção inicial

**AVL vantajoso quando:**
- Necessita manter os símbolos ordenados
- O pseudocódigo tem escopos aninhados
- Quer garantia de desempenho no pior caso
