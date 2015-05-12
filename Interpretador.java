/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
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
	
	public void corrige(String l[]){
		String linhas_corrigidas[];
		String [] frase;
		linhas_corrigidas = l;
		String Nlinha = new String();
		int x = 0;
		for(int i = 0; i < linhas_corrigidas.length; i++){
			if(linhas_corrigidas[i] != null){
				if(linhas_corrigidas[i].contains("\"") && linhas_corrigidas[i].contains("<)")){ //excluir espaços duplicados
					frase = linhas_corrigidas[i].split("\"");									//FORA da string
					frase[0] = frase[0].replaceAll("\\s+"," ");
					frase[2] = frase[2].replaceAll("\\s+"," ");
					frase[0] = frase[0].concat(frase[1]).concat(frase[2]);
				}else{
					linhas_corrigidas[i] = linhas_corrigidas[i].replaceAll("\\s+"," ");			//excluir espaço duplicado da linha toda.	
				}
				linhas_corrigidas[i] = linhas_corrigidas[i].trim();								//exclui tabs antes e depois da linha.
				//System.out.println("Linha reescrita:" + linhas_corrigidas[i]);
			}
		}
		interpreta(linhas_corrigidas);
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