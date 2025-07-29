package HashTableTSpack;
import java.util.ArrayList;
import java.util.Iterator;
import Symbolpack.Symbol;
import java.lang.Math;
public class HashTableTS {

    private ArrayList<Symbol>[] tabela;
    
    public HashTableTS() {
        this.tabela = new ArrayList[40];
    }
    
    private int hash(String name) {
        return (Math.abs(name.hashCode())) % this.tabela.length;
    }
    
    public Symbol get(String name) {
		int hash = hash(name);
		ArrayList<Symbol> symbols = this.tabela[hash];
		
		if (symbols == null) 
		    return null;
		
		for (Symbol symbol : symbols) {
		        if (symbol.name.equals(name))
		            return symbol;
		}
		
		return null;
    }  

    public void put(Symbol valor) {
		int hash = hash(valor.name);
		ArrayList<Symbol> symbols = this.tabela[hash];
		
		if (symbols == null) {
		    symbols = new ArrayList<Symbol>();
		    symbols.add(valor);
		    this.tabela[hash] = symbols;

		} else {
		    for (int i = 0; i < symbols.size(); i++) {
		        if (symbols.get(i).name.equals(valor.name)) {
		            symbols.set(i, valor);
		            return;
		        }
		    }
		
		    symbols.add(valor);
		}  
    }  

    public Symbol remove(String name) {
		int hash = hash(name);
		ArrayList<Symbol> symbols = this.tabela[hash];
		
		Iterator<Symbol> it = symbols.iterator();
		Symbol atual = null;
		
		while (it.hasNext()) {
		    atual = it.next();
		    if (atual.name.equals(name)) {
		        it.remove();
		        return atual;
		    }
		}
		
		return atual;
    }
     
}

