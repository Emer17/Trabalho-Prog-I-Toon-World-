/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
class Doublee extends Variavel{	
	public Doublee(String l, Variavel V[]){
		this.nome = super.Pegar_Nome(l,true);
		this.valor = Double.valueOf(super.Pegar_Valor(l,V)).doubleValue();
	}
}
