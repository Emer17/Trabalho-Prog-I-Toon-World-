/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
class Condicao{	
	private Interpretador Inter;
	
	public Condicao(Interpretador I){
		Inter = I;
	}

	public int executaIf(int posicao){
		String ExprecaoIF = "";
		ExprecaoIF = Inter.linhas[posicao].replaceAll(" ", ""); // Remove os espaços ;
		ExprecaoIF = ExprecaoIF.replaceAll("if\\{", ""); // So copia a expreção por Exemplo : Antes-> if{a<<b}[    Depois-> {a<<b}[;
		posicao++; // Avança para linha de baixo do IF;
		if(Inter.Operacao.ExpressoesComparacao(ExprecaoIF)){ // Testa a expreção caso for verdade executa o IF;
			while(!Inter.linhas[posicao].startsWith("]i")){ //Vai executar ate achar o fecha escopo do IF;
				posicao = Inter.ControleDeLinha(posicao);
				posicao++;
			}
			if(Inter.linhas[posicao].startsWith("]i else")){ //Caso esse IF conter um ELSE;
				posicao++; // Avança para linha de baixo do ELSE; 
				while(!Inter.linhas[posicao].startsWith("]e")) posicao++; // Ignora todas as linhas do ELSE;
			}	
		} else { // Se a expreção testada no IF de false entra aqui; 
			while(!Inter.linhas[posicao].startsWith("]i")){ //Percorre até achar o fecha escopo do IF;
				posicao = IgnoraOutrosIfs(posicao); //Executa esse metodo quando há outro if dentro desse if falso, assim nesse metodo o programa vai ignoralo;
				posicao++;
			}
			if(Inter.linhas[posicao].startsWith("]i else")){ //Caso esse IF der falso e conter um ELSE;
				posicao++; //Avança para linha de baixo do ELSE;
				while(!Inter.linhas[posicao].contains("]e")){ //Vai executar ate achar o fecha escopo do ELSE;
					posicao = Inter.ControleDeLinha(posicao);
					posicao++;
				}
			}
		}
		return posicao;
	}
	
	public int IgnoraOutrosIfs(int posicao){
		if(Inter.linhas[posicao].startsWith("if")){
			posicao++;
			while(!Inter.linhas[posicao].startsWith("]i")){
				posicao = IgnoraOutrosIfs(posicao);
				posicao++;
			}
		}
		return posicao;
	}
	
}
