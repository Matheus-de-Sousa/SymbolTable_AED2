import Symbolpack.Symbol;
import Nodepack.Node;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import HashTableTSpack.HashTableTS;
import AVLTreeTSpack.AVLTreeTS;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SymbolTable {
    private HashTableTS hashTable;
    private AVLTreeTS avlTree;
    private boolean useHashTable;
    
    // Métricas de desempenho
    private long insertionTime;
    private long searchTime;
    private int totalInsertions;
    private int totalSearches;
    
    public SymbolTable(boolean useHashTable) {
        this.useHashTable = useHashTable;
        if (useHashTable) {
            this.hashTable = new HashTableTS();
        } else {
            this.avlTree = new AVLTreeTS();
        }
        this.insertionTime = 0;
        this.searchTime = 0;
        this.totalInsertions = 0;
        this.totalSearches = 0;
    }

    public void processPseudocodeFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int lineNumber = 0;
        
        Pattern declarationPattern = Pattern.compile(
            "\\b(String|int|double|float|boolean|char|byte|short|long)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*(?:=\\s*[^;]+)?\\s*;"
        );
        
        Pattern usagePattern = Pattern.compile(
            "\\b([a-zA-Z_][a-zA-Z0-9_]*)\\b(?![\\w(])"
        );
        
        Set<String> reservedWords = new HashSet<>(Arrays.asList(
            "int", "double", "float", "boolean", "char", "byte", "short", "long",
            "if", "else", "while", "for", "return", "break", "continue", "class",
            "void", "public", "private", "static", "true", "false", "null", "String"
        ));
        
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            line = line.trim();
            
            if (line.isEmpty() || line.startsWith("//")) {
                continue;
            }
            
            String lineWithoutLiterals = line
                .replaceAll("'.'", "' '")
                .replaceAll("\".*?\"", "\" \"");
            
            // Processar declarações
            Matcher declMatcher = declarationPattern.matcher(line);
            while (declMatcher.find()) {
                String type = declMatcher.group(1);
                String variableName = declMatcher.group(2);
                
                Symbol symbol = new Symbol(variableName, type, lineNumber);
                
                long startTime = System.nanoTime();
                if (useHashTable) {
                    hashTable.put(symbol);
                } else {
                    avlTree.root = avlTree.insertNode(avlTree.root, symbol);
                }
                insertionTime += System.nanoTime() - startTime;
                totalInsertions++;
                
                System.out.println("Variável declarada - Linha " + lineNumber + 
                                 ": " + variableName + " do tipo " + type);
            }
            
            // Verificar usos de variáveis
            Matcher usageMatcher = usagePattern.matcher(lineWithoutLiterals);
            while (usageMatcher.find()) {
                String variableName = usageMatcher.group(1);
                
                if (reservedWords.contains(variableName) || variableName.matches("^\\d+\\.?\\d*$")) {
                    continue;
                }
                
                long startTime = System.nanoTime();
                Symbol symbol = findSymbol(variableName);
                searchTime += System.nanoTime() - startTime;
                totalSearches++;
                
                if (symbol == null) {
                    System.err.println("ERRO - Linha " + lineNumber + 
                                     ": Variável '" + variableName + "' não declarada");
                }
            }
        }
        reader.close();
    }

    public Symbol findSymbol(String name) {
        if (useHashTable) {
            return hashTable.get(name);
        } else {
            Node node = avlTree.searchNode(avlTree.root, name);
            return node != null ? node.item : null;
        }
    }

    // Método para gerar relatório de desempenho
    public void printPerformanceReport(String structureName) {
        System.out.println("\n=== Relatório de Desempenho (" + structureName + ") ===");
        System.out.println("Tempo total de inserção: " + (insertionTime / 1_000) + " us");
        System.out.println("Tempo médio por inserção: " + 
                         (totalInsertions > 0 ? (insertionTime / totalInsertions) : 0) + " ns");
        System.out.println("Tempo total de busca: " + (searchTime / 1_000) + " us");
        System.out.println("Tempo médio por busca: " + 
                         (totalSearches > 0 ? (searchTime / totalSearches) : 0) + " ns");
        System.out.println("Total de inserções: " + totalInsertions);
        System.out.println("Total de buscas: " + totalSearches);
        System.out.println("Tempo total (" + structureName + "): " + ((searchTime / 1_000) + (insertionTime/1_000)) + " us");
    }

    public static void main(String[] args) {
        try {
        	String[] testes = {"controleEstoque.txt", "calcSimples.txt"};
        	for(String teste : testes)
        	{
		        // Teste com HashTable
		        System.out.println("\n=== Executando com HashTable ("+teste+") ===");
		        SymbolTable hashSymbolTable = new SymbolTable(true);
		        hashSymbolTable.processPseudocodeFile(teste);
		        hashSymbolTable.printPerformanceReport("HashTable");
		        
		        // Teste com AVL
		        System.out.println("\n=== Executando com AVL (" +teste + ") ===");
		        SymbolTable avlSymbolTable = new SymbolTable(false);
		        avlSymbolTable.processPseudocodeFile(teste);
		        avlSymbolTable.printPerformanceReport("AVL Tree");
            }
            
        } catch (IOException e) {
            System.err.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }
}
