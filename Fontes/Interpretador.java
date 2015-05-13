/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
class Interpretador {

	public Variavel V[]; 
	public Condicao Condicao; 
	public Laco Laco;
	public Comandos Comandos;
	public String linhas[];
	public Operacao Operacao;

	public Interpretador() {
		V = new Variavel[100];
		Condicao = new Condicao(this);
		Laco = new Laco(this);
		Comandos = new Comandos(this);	
		Operacao = new Operacao(this);
	}
	
	public void CriaVariavel(String l){
		for(int w =0; w < V.length; w++){
			if(this.V[w] == null){
				if(l.startsWith("Int ")){
					if(Pesquisar(Pegar_Nome(l),Pegar_Valor(l)));
					else V[w] = new Inteiro(Pegar_Nome(l),Pegar_Valor(l));
					break;
				} else if(l.startsWith("Double ")){
					if(Pesquisar(Pegar_Nome(l),Pegar_Valor(l)));
					else V[w] = new Doublee(Pegar_Nome(l),Pegar_Valor(l));
					break;
				} else if(l.startsWith("String ")){
					if (Pesquisar(Pegar_Nome(l),Pegar_ValorString(l)));
					else V[w] = new Stringg(Pegar_Nome(l),Pegar_ValorString(l));
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
			}
		}
		if(log.VerificaErros(linhas_corrigidas)) interpreta(linhas_corrigidas);
	}
	
	public int ControleDeLinha(int posicao){
		
		if ( linhas[posicao].startsWith("if") ){ // Condicao
			posicao = Condicao.executaIf(posicao);
		} else if ( linhas[posicao].startsWith("four") ){//For
			posicao = Laco.four(posicao);
		} else if ( linhas[posicao].startsWith("while") ){//While
			posicao = Laco.executaWhile(posicao);
		} else if ( linhas[posicao].startsWith("ler") ){
			Comandos.Scanf(linhas[posicao]);
		} else if ( linhas[posicao].startsWith("print") || linhas[posicao].startsWith("println") ){
			Comandos.Imprimir(linhas[posicao]);
		} else if (linhas[posicao].startsWith("LadoEscuro") ){
			LadoEscuro(linhas[posicao]);
		} else { //Criação, atribuição, mais_mais e menos_menos, em VARIAVEIS.
			CriaVariavel(linhas[posicao]);
			ModificacaoNaVariavel(linhas[posicao]);
		}
		return posicao;
	}
	
	public String Pegar_Valor(String l){
		String Valor = "";
		l = l.replaceAll(" ", "");
		String[] vet = l.split("\\<\\)");
		if(!l.contains("<)")){
			Valor += '0';
		} else {		
			if(Operacao.TokensAritmeticos(l) != '0' || !Operacao.TokenComparativos(l).equals("")){
				Valor = String.valueOf(Operacao.ExpressoesAritmeticas(vet[1]));
			} else {
				vet[1] = vet[1].replaceAll("\\?", "");
				Valor = LocalizarVariavel(vet[1]);
			}
		}
		return Valor;
	}
	
	public void LadoEscuro(String l){
		if(l.contains(",")){
			l = l.replaceAll("LadoEscuro", "");
			l = l.replaceAll("[ \\{\\}\\?]", "");
			String[] Nomes = l.split(",");
			for(int w = 0; w < Nomes.length; w++){
				for(int a = 0; a < V.length; a++){
					if(V[a] != null){
						if(V[a].nome.equals(Nomes[w])){
							System.out.println("[ " + a + " ] Nome Variavel : " + V[a].nome); 
							System.out.println("      Conteudo : " +  V[a].valor);
							System.out.println();
						}
					}
				}
			}
		} else {
			for(int w = 0; w < V.length; w++){
				if (V[w] != null){
					System.out.println("[ " + w + " ] Nome Variavel : " + V[w].nome); 
					System.out.println("      Conteudo : " +  V[w].valor);
					System.out.println();
				}
			}
		}
	}
	
	public void MaisMais_E_MenosMenos(String l){
		String Nome = l.replaceAll("[ \\.\\,\\?]", "");
		for(int w = 0; w < V.length; w++){
			if(V[w] != null){
				if(V[w].nome.equals(Nome)){
					if(V[w] instanceof Doublee){
						double valor = (double) V[w].valor;
						if(l.contains("...")) V[w].valor = valor + 1;
						else V[w].valor = valor - 1;
					} else if(V[w] instanceof Inteiro){
						int valor = (int) V[w].valor;
						if(l.contains("...")) V[w].valor = valor + 1;
						else V[w].valor = valor - 1;
					}					
				}
			}
		}
	}
	
	public void ModificacaoNaVariavel(String l){
		if(l.contains("...") || l.contains(",,,")){
			MaisMais_E_MenosMenos(l);
		} else if(l.contains("<)")){
			Atribuicao(l);
		} else if(l.contains("<+)")){
			ConcatenarString(l);
		}
	}
	
	public void ConcatenarString(String l){
		Pesquisar(Pegar_Nome(l),Pegar_ValorString(l));
	}
	
	public void Atribuicao(String l){
		Pesquisar(Pegar_Nome(l),Pegar_Valor(l));
	}
	
	public boolean Pesquisar(String Nome, String Valor){
		for(int w = 0; w < V.length; w++){
			if(V[w] != null){
				if(V[w].nome.equals(Nome)){
					if(V[w] instanceof Doublee){
						V[w].valor = Double.valueOf(Valor).doubleValue();
					} else if(V[w] instanceof Inteiro){
						V[w].valor = (int) Double.parseDouble(Valor);
					} else if(V[w] instanceof Stringg){
						V[w].valor += Valor;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public String LocalizarVariavel(String Nome){
		for(int w = 0; w < V.length; w++){
			if(V[w] != null){
				if(V[w].nome.equals(Nome)){
					return String.valueOf(V[w].valor);
				}
			}
		}
		return Nome;
	}
	
	public String Pegar_ValorString(String l){
		int x = 0;
		String Valor = "";
		while(l.charAt(x) != '"') x++;
		x++;
		while(l.charAt(x) != '"'){
			Valor += l.charAt(x);
			x++;
		}
		return Valor;
	}
	
	public String Pegar_Nome(String l){
		int a = 0;
		if(l.startsWith("Int ") || l.startsWith("String ") || l.startsWith("Double ")) a = 1;
		l = l.replaceAll("[\\<\\)\\?]", " ");
		String[] vet = l.split(" ");
		return vet[a];
	}
	
	
	
	
	
	
	
	
     public void interpreta(String l[]) {
		 this.linhas = l;
		for(int posicao = 0; posicao < linhas.length; posicao++) {
            if(linhas[posicao] != null) {			
				posicao = ControleDeLinha(posicao);				
			}
		}
	}
}