# SymbolTable - Estrutura

- SymbolTable.java - Código principal onde deve ser implementada a Tabela de símbolos
- Symbol.java e Symbolpack - Declaração da classe symbol que define os atributos de um símbolo
  * public String name - Identificador da função ou variável
  * public String type - Tipo de variável ou função
	* public int dimension - Tamanho[]
	* public int line_declared- Linha de declaração

- Node.java e Nodepack - Declaração de um nó da árvore AVL que define os atributos do nó
- HashTableTS.java e HashTableTSpack - Implementação de um hashtable para a classe symbol, permitindo o uso dessa estrutura para armazenar os símbolos da nossa tabela de símbolos com as operações a seguir:
  * public Symbol get(String name) - recupera um símbolo à partir do nome da variável que foi lida pelo compilador
  * public void put(Symbol valor) - Armazena um símbolo utilizando um objeto da classe symbol na tabela Hash
  * public Symbol remove(String name) - Remove um símbolo utilizando o nome da variável que foi lida pelo compilador
  
- AVLTreeTS.java e AVLTreepack - Implementação de uma árvore binária de busca AVL para a classe symbol, permitindo o uso dessa estruturs para armazenar os símbolos da nossa tabela de símbolos com as operações a seguir:
  * public Node insertNode(Node node, Symbol item) -  Armazena um símbolo utilizando um objeto da classe symbol na árvore e o nó raiz da árvore (adiciona um novo nó à árvore)
  * public Node deleteNode(Node root, Symbol item) - Remove um símbolo utilizando um objeto da classe symbol usando como critério o nome da variável relacionada à esse símbolo, recebendo também o nó da raiz da árvore
  * public Node searchNode(Node root, Symbol item) - Busca um símbolo utilizando um objeto da classe symbol e usando como critério o nome da variável relacionada à esse símbolo, recebendo também o nó da raiz da árvore
