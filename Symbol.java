package Symbolpack;
public class Symbol {
	public String name; // identificador da função ou variável
	public String type; // Tipo de variável ou função
	public int dimension; // caso seja array (tamanho)
	public int line_declared;
	
	public Symbol(String name, String type, int dim, int line) 
	{
		this.name = name;
		this.type = type;
		this.dimension = dim;
		this.line_declared = line;
	}
}
