/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
- * Versão: 5.0
- * Descrição: Classe Main da Toon World, linguagem baseada em java.
- * 
- * Esta classe implementa a variavel "Double" da linguagem.*/
class Doublee extends Variavel{	
	public Doublee(String Nome, String Valor){
		this.nome = Nome;
		this.valor = Double.valueOf(Valor).doubleValue();
	}
}
