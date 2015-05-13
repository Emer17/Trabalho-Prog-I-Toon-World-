/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
class Laco{
	
	private Interpretador Inter;
	
	public Laco(Interpretador I){
		Inter = I;
	}
	
	public int four(int TOPO){
		int posicao = TOPO+1;
		String ExpFor = "";
		ExpFor = Inter.linhas[TOPO].replaceAll("four", "");
		ExpFor = ExpFor.replaceAll("[\\{\\} ]", ""); // Remove os espaços ;
		String[] PartesFor = ExpFor.split("\\?");
		Inter.Atribuicao(PartesFor[0]);//Atribui o valor para a variavel colocada ali. Termino da FASE 1 do for
		if(!Inter.Operacao.ExpressoesComparacao(PartesFor[1])){
			while(!Inter.linhas[posicao].startsWith("]f")){
				posicao = IgnoraOutrosFors(posicao);
				posicao++;
			}
		} else {
			do{
				posicao = TOPO+1;
				while(!Inter.linhas[posicao].startsWith("]f")){
					posicao = Inter.ControleDeLinha(posicao);
					posicao++;
				}
				Inter.ModificacaoNaVariavel(PartesFor[2]);
			}while(Inter.Operacao.ExpressoesComparacao(PartesFor[1]));
		}
		TOPO = posicao;
		return TOPO;
	}
	
	public int IgnoraOutrosFors(int posicao){
		if(Inter.linhas[posicao].startsWith("four")){
			posicao++;
			while(!Inter.linhas[posicao].startsWith("]f")){
				posicao = IgnoraOutrosFors(posicao);
				posicao++;
			}
		}
		return posicao;
	}

	public int executaWhile(int TOPO){
		int posicao = TOPO+1;
		String ExprecaoWhile = "";
		ExprecaoWhile = Inter.linhas[TOPO].replaceAll(" ", ""); // Remove os espaços ;
		ExprecaoWhile = ExprecaoWhile.replaceAll("while\\{", ""); // So copia a expreção por Exemplo : Antes-> while{a<<b}[    Depois-> {a<<b}[;
		posicao++; // Avança para linha de baixo do WHILE;
		if(!Inter.Operacao.ExpressoesComparacao(ExprecaoWhile)){
			while(!Inter.linhas[posicao].startsWith("]w")){
				posicao = IgnoraOutroswhile(posicao);
				posicao++;
			}
		}else{
			do{
				posicao = TOPO+1;
				while(!Inter.linhas[posicao].startsWith("]w")){
					posicao = Inter.ControleDeLinha(posicao);
					posicao++;
				}
			}while(Inter.Operacao.ExpressoesComparacao(ExprecaoWhile));
		}
		TOPO = posicao;
		return TOPO;
	}
	
	public int IgnoraOutroswhile(int posicao){
		if(Inter.linhas[posicao].startsWith("while")){
			posicao++;
			while(!Inter.linhas[posicao].startsWith("]w")){
				posicao = IgnoraOutroswhile(posicao);
				posicao++;
			}
		}
		return posicao;
	}
}