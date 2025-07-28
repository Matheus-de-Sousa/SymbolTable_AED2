import Symbolpack.Symbol;
import Nodepack.Node;
import HashTableTSpack.HashTableTS;
import AVLTreeTSpack.AVLTreeTS;

public class SymbolTable {
	public static void main(String[] args) {
        Symbol s1 = new Symbol("A","int",1,1);
        Symbol s2 = new Symbol("B","int2",1,1);
        Symbol s3 = new Symbol("C","int3",1,1);
        Symbol s4 = new Symbol("D","int4",1,1);
        Symbol s5 = new Symbol("E","int5",1,1);
        
        // Exemplo usando hashtable
        HashTableTS SymTable = new HashTableTS();
        SymTable.put(s1);
        SymTable.put(s2);
        SymTable.put(s3);
        SymTable.put(s4);
        SymTable.put(s5);
        
        Symbol Test = SymTable.get("B");
        System.out.println("TS Get: " + Test.name);
        Test = SymTable.remove("C");
        System.out.println("TS Remove: " + Test.name);
        Test = SymTable.get("C");
        if(Test != null)
        {
        	System.out.println("TS Get: " + Test.name);
        }
        else
        {
        	System.out.println("null"); 
        }
        
        // Exemplo Usando AVL
        AVLTreeTS tree = new AVLTreeTS();
        tree.root = tree.insertNode(tree.root, s1);
        tree.root = tree.insertNode(tree.root, s2);
        tree.root = tree.insertNode(tree.root, s3);
        tree.root = tree.insertNode(tree.root, s4);
        tree.root = tree.insertNode(tree.root, s5);
        
        Node AVLTest = tree.searchNode(tree.root,s3);
        if(AVLTest != null)
        {
        	System.out.println("Avl Search: " + AVLTest.item.name);
        }
        else
        {
        	System.out.println("null"); 
        }
        System.out.println("Before Deletion: ");
        tree.printTree(tree.root, "", true);
        
        tree.root = tree.deleteNode(tree.root, s3);
        System.out.println("After Deletion: ");
        tree.printTree(tree.root, "", true);
  	}
}

