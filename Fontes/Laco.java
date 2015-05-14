/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
- * Versão: 5.0
- * Descrição: Classe Laço da Toon World, linguagem baseada em java.
- * 
- * Esta classe é responsavel pelos comandos "for" e "while" da linguagem.*/
class Laco{
	
	private Interpretador Inter;
	
	public Laco(Interpretador I){
		Inter = I;
	}
	
	public int ExecutaFor(int TOPO){
		int posicao = TOPO+1;//Posição vai valer a linha abaixo do FOR;
		String ExpFor = "";
		ExpFor = Inter.linhas[TOPO].replaceAll("VOOR", "");//Retira a palavra FOOPING da linha;
		ExpFor = ExpFor.replaceAll("[\\{\\} ]", ""); // Remove os espaços ;
		String[] PartesFor = ExpFor.split("\\?");//Separa a linha em 3 partes apartir do ?. Exemplo: FOOPING{ A<)0 ? A<<2 ? A...? }[ = PartesFor[0] = A<)0, PartesFor[1] = A<<2, PartesFor[2] = A...
		Inter.Atribuicao(PartesFor[0]);//Atribui o valor para a variavel que esta na PartesFor[0];
		if(!Inter.Operacao.ExpressoesComparacao(PartesFor[1])){//Caso a expressão der falsa entra nesse if;
			while(!Inter.linhas[posicao].startsWith("]V")){//Ignora todas linhas até a chave que fecha o FOOPING;
				posicao = IgnoraOutrosFors(posicao);//Ignora os outros LOOPINGS que estiver dentro do FOOPING falso;
				posicao++;
			}
		} else {
			do{
				posicao = TOPO+1;//Recebe a linha a baixo do FOOPING;
				while(!Inter.linhas[posicao].startsWith("]V")){//Caso encontrar a chave do FOOPING termina a execução;				
					posicao = Inter.ControleDeLinha(posicao);//Confere oque tem na linha;
					posicao++;
				}
				Inter.ModificacaoNaVariavel(PartesFor[2]);//Depois de executar todas as linhas faz o MaisMais ou MenosMenos da variavel que se encontra na PartesFor[2];
			}while(Inter.Operacao.ExpressoesComparacao(PartesFor[1]));//Confere se a expressão encontrada na PartesFor[1] continua sendo verdade;
		}
		TOPO = posicao;//Retorna a chave do LOOPING;
		return TOPO;
	}
	
	public int IgnoraOutrosFors(int posicao){
		if(Inter.linhas[posicao].startsWith("VOOR")){
			posicao++;
			while(!Inter.linhas[posicao].startsWith("]V")){
				posicao = IgnoraOutrosFors(posicao);
				posicao++;
			}
		}
		return posicao;
	}

	public int ExecutaWHILE(int TOPO){
		int posicao = TOPO+1;
		String ExprecaoWhile = "";
		ExprecaoWhile = Inter.linhas[TOPO].replaceAll(" ", ""); // Remove os espaços ;
		ExprecaoWhile = ExprecaoWhile.replaceAll("GIRAGIRA\\{", ""); // So copia a expreção por Exemplo : Antes-> while{a<<b}[    Depois-> {a<<b}[;
		posicao++; // Avança para linha de baixo do WHILE;
		if(!Inter.Operacao.ExpressoesComparacao(ExprecaoWhile)){
			while(!Inter.linhas[posicao].startsWith("]G")){
				posicao = IgnoraOutroswhile(posicao);
				posicao++;
			}
		}else{
			do{
				posicao = TOPO+1;
				while(!Inter.linhas[posicao].startsWith("]G")){
					posicao = Inter.ControleDeLinha(posicao);
					posicao++;
				}
			}while(Inter.Operacao.ExpressoesComparacao(ExprecaoWhile));
		}
		TOPO = posicao;
		return TOPO;
	}
	
	public int IgnoraOutroswhile(int posicao){
		if(Inter.linhas[posicao].startsWith("GIRAGIRA")){
			posicao++;
			while(!Inter.linhas[posicao].startsWith("]G")){
				posicao = IgnoraOutroswhile(posicao);
				posicao++;
			}
		}
		return posicao;
	}
}