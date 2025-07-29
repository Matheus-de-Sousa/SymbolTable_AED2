package Symbolpack;
public class Symbol {
	public String name; // identificador da função ou variável
	public String type; // Tipo de variável ou função
	public int line_declared;
	
	public Symbol(String name, String type, int line) 
	{
		this.name = name;
		this.type = type;
		this.line_declared = line;
	}
}
