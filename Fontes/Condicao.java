/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 5.0
 * Descrição: Classe Condição da Toon World, linguagem baseada em java.
 * 
 * Esta classe é responsavel pelo comando "if" da linguagem.*/
 
class Condicao{	
	public int executaIf(Variavel V[], String l, int i, String linhas[]){
		Interpretador I = new Interpretador();
		Operacao OP = new Operacao();
		String ExprecaoIF = "";
		ExprecaoIF = l.replaceAll(" ", ""); // Remove os espaços ;
		ExprecaoIF = ExprecaoIF.replaceAll("if\\{", ""); // So copia a expreção por Exemplo : Antes-> if{a<<b}[    Depois-> {a<<b}[;
		i++; // Avança para linha de baixo do IF;
		if(OP.ExpressoesComparacao(ExprecaoIF, V)){ // Testa a expreção caso for verdade executa o IF;
			while(!linhas[i].startsWith("]i")){ //Vai executar ate achar o fecha escopo do IF;
				i = I.VereficarLinha(linhas,V,i);
				i++;
			}
			if(linhas[i].startsWith("]i else")){ //Caso esse IF conter um ELSE;
				i++; // Avança para linha de baixo do ELSE; 
				while(!linhas[i].startsWith("]e")) i++; // Ignora todas as linhas do ELSE;
			}	
		} else { // Se a expreção testada no IF de false entra aqui; 
			while(!linhas[i].startsWith("]i")){ //Percorre até achar o fecha escopo do IF;
				i = IgnoraOutrosIfs(linhas,i); //Executa esse metodo quando há outro if dentro desse if falso, assim nesse metodo o programa vai ignoralo;
				i++;
			}
			if(linhas[i].startsWith("]i else")){ //Caso esse IF der falso e conter um ELSE;
				i++; //Avança para linha de baixo do ELSE;
				while(!linhas[i].contains("]e")){ //Vai executar ate achar o fecha escopo do ELSE;
					i = I.VereficarLinha(linhas,V,i);
					i++;
				}
			}
		}
		return i;
	}
	
	public int IgnoraOutrosIfs(String linhas[], int i){
		if(linhas[i].startsWith("if")){
			i++;
			while(!linhas[i].startsWith("]i")){
				i = IgnoraOutrosIfs(linhas,i);
				i++;
			}
		}
		return i;
	}
	
}
