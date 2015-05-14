/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
- * Versão: 5.0
- * Descrição: Classe Condição da Toon World, linguagem baseada em java.
- * 
- * Esta classe é responsavel pelo comando "if" da linguagem.*/
class Condicao{	
	private Interpretador Inter;
	
	public Condicao(Interpretador I){
		Inter = I;
	}

	public int ExecutaIF(int posicao){
		String ExprecaoIF = "";
		ExprecaoIF = Inter.linhas[posicao].replaceAll(" ", ""); // Remove os espaços ;
		ExprecaoIF = ExprecaoIF.replaceAll("WENN\\{", ""); // So copia a expreção por Exemplo : Antes-> if{a<<b}[    Depois-> {a<<b}[;
		posicao++; // Avança para linha de baixo do IF;
		if(Inter.Operacao.ExpressoesComparacao(ExprecaoIF)){ // Testa a expreção caso for verdade executa o IF;
			while(!Inter.linhas[posicao].startsWith("]W")){ //Vai executar ate achar o fecha escopo do IF;
				posicao = Inter.ControleDeLinha(posicao);
				posicao++;
			}
			if(Inter.linhas[posicao].startsWith("]W SONST")){ //Caso esse IF conter um ELSE;
				posicao++; // Avança para linha de baixo do ELSE; 
				while(!Inter.linhas[posicao].startsWith("]S")) posicao++; // Ignora todas as linhas do ELSE;
			}	
		} else { // Se a expreção testada no IF de false entra aqui; 
			while(!Inter.linhas[posicao].startsWith("]W")){ //Percorre até achar o fecha escopo do IF;
				posicao = IgnoraOutrosIfs(posicao); //Executa esse metodo quando há outro if dentro desse if falso, assim nesse metodo o programa vai ignoralo;
				posicao++;
			}
			if(Inter.linhas[posicao].startsWith("]W SONST")){ //Caso esse IF der falso e conter um ELSE;
				posicao++; //Avança para linha de baixo do ELSE;
				while(!Inter.linhas[posicao].contains("]S")){ //Vai executar ate achar o fecha escopo do ELSE;
					posicao = Inter.ControleDeLinha(posicao);
					posicao++;
				}
			}
		}
		return posicao;
	}
	
	public int IgnoraOutrosIfs(int posicao){
		if(Inter.linhas[posicao].startsWith("WENN")){
			posicao++;
			while(!Inter.linhas[posicao].startsWith("]W")){
				posicao = IgnoraOutrosIfs(posicao);
				posicao++;
			}
		}
		return posicao;
	}
	
}
