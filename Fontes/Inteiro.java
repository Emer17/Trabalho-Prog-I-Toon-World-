/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 5.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.
 * 
 * Esta classe implementa a variavel "Int" da linguagem.*/
 
class Inteiro extends Variavel{
	public Inteiro(String l, Variavel V[]){
		this.nome = super.Pegar_Nome(l,true);
		this.valor = (int) Double.parseDouble(super.Pegar_Valor(l,V));
	}
}
