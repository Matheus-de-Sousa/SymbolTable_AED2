package Nodepack;
import Symbolpack.Symbol;
public class Node { 
    public int height;
    public Node left, right;
    public Symbol item;

    public Node(Symbol s) {
        item = s;
        height = 1;
    }
}
