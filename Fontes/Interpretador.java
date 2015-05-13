/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 5.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.
 * 
 * Esta classe é responsavel pela criação de variaveis,correção das linhas e interpretação dos comandos da linguagem.*/
 
class Interpretador {

	public Variavel V[] = new Variavel[100]; 

	public void CriaVariavel(String l){
		Variavel K = new Variavel();
		for(int w =0; w < this.V.length; w++){
			if(this.V[w] == null){
				if(l.startsWith("Int ")){
					if(K.Pesquisar(K.Pegar_Nome(l,true),K.Pegar_Valor(l,V),V));
					else V[w] = new Inteiro(l,this.V);
					break;
				} else if(l.startsWith("Double ")){
					if(K.Pesquisar(K.Pegar_Nome(l,true),K.Pegar_Valor(l,V),V));
					else V[w] = new Doublee(l,this.V);
					break;
				} else if(l.startsWith("String ")){
					if (K.Pesquisar(K.Pegar_Nome(l,true),K.Pegar_ValorString(l),V));
					else V[w] = new Stringg(l);
					break;
				}
			}
		}
	}
	
	public void corrige(String l[]){ //corrige o problema de espaços e tabs desnecessarios
		String linhas_corrigidas[];
		String [] frase; //frase é um vetor por que se a linha tiver "(aspas) ela vai ser quebrada em 3 partes.
		linhas_corrigidas = l;
		String Nlinha = new String();
		LogErro log = new LogErro();
		int x = 0; //x vai percorrer o programa...
		for(int i = 0; i < linhas_corrigidas.length; i++){
			if(linhas_corrigidas[i] != null){
				if(linhas_corrigidas[i].contains("\"") && linhas_corrigidas[i].contains("<)")){ //excluir espaços duplicados FORA da string
					frase = linhas_corrigidas[i].split("\""); //quebra a string em uma nova parte toda vez que achar " (aspas)									
					frase[0] = frase[0].replaceAll("\\s+"," ");  //substitui multiplos espaços por apenas 1 espaço.(antes das aspas)
					frase[2] = frase[2].replaceAll("\\s+"," ");	 //substitui multiplos espaços por apenas 1 espaço.(depois das aspas)
					frase[0] = frase[0].concat(frase[1]).concat(frase[2]); //junta a string novamente, a string dentro das " ficou intacta.
				}
				linhas_corrigidas[i] = linhas_corrigidas[i].trim();	//exclui tabs antes e depois da linha.
				if(!(linhas_corrigidas[i].startsWith("print"))){  //excluir espaço duplicado da linha toda.(se ela nao for um print!)
					linhas_corrigidas[i] = linhas_corrigidas[i].replaceAll("\\s+"," ");	//mesma funçao ali de cima, agora com a linha toda.			
				}
				//System.out.println("Linha reescrita:" + linhas_corrigidas[i]); //quer ver a linha reescrita?
			}
		}
		if(log.VerificaErros(linhas_corrigidas)) interpreta(linhas_corrigidas);
	}
	
	public int VereficarLinha(String linhas[], Variavel V[], int posicao){
		
		if ( linhas[posicao].startsWith("if") ){ // Condicao
			Condicao C = new Condicao();
			posicao = C.executaIf(V,linhas[posicao],posicao,linhas);
		} else if ( linhas[posicao].startsWith("four") ){//For
			Laco L = new Laco();
			posicao = L.four(V,linhas[posicao],posicao,linhas);
		} else if ( linhas[posicao].startsWith("while") ){//While
			Laco L = new Laco();
			posicao = L.executaWhile(V,linhas[posicao],posicao,linhas);
		} else if ( linhas[posicao].startsWith("ler") ){
			Comandos C = new Comandos();
			C.Scanf(linhas[posicao],V);
		} else if ( linhas[posicao].startsWith("print") || linhas[posicao].startsWith("println") ){
			Comandos C = new Comandos();
			C.Imprimir(linhas[posicao],V);
		} else { //Criação, atribuição, mais_mais e menos_menos, em VARIAVEIS.
			Variavel Var = new Variavel();
			CriaVariavel(linhas[posicao]);
			Var.ModificacaoNaVariavel(linhas[posicao], V);
		}
		return posicao;
	}
	
     public void interpreta(String linhas[]) {
		for(int i = 0; i < linhas.length; i++) {
            if(linhas[i] != null) {			
				i = VereficarLinha(linhas,V,i);				
			}
		}
		System.out.println("=================================");
		System.out.println("        Vetor de Variaveis");
		System.out.println("=================================");
		for(int w = 0; w < V.length; w++){
			if (V[w] != null){
				System.out.println( w + " <- Posicao ");
				System.out.println("             ->> Nome : " + V[w].nome);
				System.out.println("             ->> Conteudo : " + V[w].valor);
			}
		}
	}
}
