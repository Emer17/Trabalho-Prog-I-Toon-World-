/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 5.0
 * Descrição: Classe Stringg da Toon World, linguagem baseada em java.
 * 
 * Esta classe implementa a variavel "String" da linguagem.*/
 
class Stringg extends Variavel{
	public Stringg(String l){
		this.nome = super.Pegar_Nome(l,true);
		this.valor = super.Pegar_ValorString(l);
	}
}
