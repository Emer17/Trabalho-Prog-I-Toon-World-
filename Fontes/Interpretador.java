/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
- * Versão: 5.0
- * Descrição: Classe Main da Toon World, linguagem baseada em java.
- * 
- * Esta classe é responsavel pela criação de variaveis,correção das linhas e interpretação dos comandos da linguagem.*/
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
			if(this.V[w] == null){// Acha o primeiro indice que não estaja ocupado.
				if(l.startsWith("Int ")){// Se for a criação de um INT.
					if(AlteraVariavel(Pegar_Nome(l),Pegar_Valor(l)));
					// Pesquisa se a variavel ja existe, caso ja tenha uma com o mesmo nome ele subscreve ela.
					else V[w] = new Inteiro(Pegar_Nome(l),Pegar_Valor(l));// Se ela não existe ela entra nesse Else.
					break;
				} else if(l.startsWith("Double ")){// Se for a criação de um DOUBLE.
					if(AlteraVariavel(Pegar_Nome(l),Pegar_Valor(l)));
					else V[w] = new Doublee(Pegar_Nome(l),Pegar_Valor(l));
					break;
				} else if(l.startsWith("String ")){// Se for a criação de uma STRING.
					if (AlteraVariavel(Pegar_Nome(l),Pegar_ValorString(l)));
					else V[w] = new Stringg(Pegar_Nome(l),Pegar_ValorString(l));
					break;
				}
			}
		}
	}
	
	public void corrige(String l[]){ //corrige o problema de espaços e tabs desnecessarios
		this.linhas = l;
		for(int posicao = 0; posicao < linhas.length; posicao++) {
			if(linhas[posicao] != null) {
				linhas[posicao] = l[posicao].replaceAll("\t", "");
				if(linhas[posicao].startsWith("String ") || linhas[posicao].startsWith("PRINTLN") || linhas[posicao].startsWith("PRINT"));
				else linhas[posicao] = linhas[posicao].replaceAll("\\s+"," ");
			}
		}
		LogErro log = new LogErro();
		if(log.VerificaErros(linhas)) interpreta();
	}
	
	public int ControleDeLinha(int posicao){
		if ( linhas[posicao].startsWith("WENN") ){ //If. Vem da linguagem Alemão.
			posicao = Condicao.ExecutaIF(posicao);
		} else if ( linhas[posicao].startsWith("VOOR") ){//For. Vem da linguagem Holandesa.
			posicao = Laco.ExecutaFor(posicao);
		} else if ( linhas[posicao].startsWith("GIRAGIRA") ){//While. Vem da linguagem HUE.
			posicao = Laco.ExecutaWHILE(posicao);
		} else if ( linhas[posicao].startsWith("DIZPRAMIM") ){//Scanf. Vem da linguagem HUE.
			Comandos.ExecutaSCANF(linhas[posicao]);
		} else if ( linhas[posicao].startsWith("PRINT") || linhas[posicao].startsWith("PRINTLN") ){//Print.
			Comandos.ExecutaPRINT(linhas[posicao]);
		} else if (linhas[posicao].startsWith("DARKSIDE") ){//Imprimi a memoria.
			Darkside(linhas[posicao]);
		} else { //Criação, atribuição, mais_mais e menos_menos, em VARIAVEIS.
			CriaVariavel(linhas[posicao]);
			ModificacaoNaVariavel(linhas[posicao]);
		}
		return posicao;
	}
	
	public String Pegar_Valor(String l){
		if(!l.contains("<)")){// Caso seja so uma variavel sem valor. Exemplo: Double j?
			return "0";// Retorna o valor ZERO para a variavel que esta sendo criada.
		}
		l = l.replaceAll(" ", "");//Retira todos os espaços.
		String[] Valor = l.split("\\<\\)");// Separa o valor do nome. Exemplo: Int K <) 78 - A?, no Valor[1] vai conter 78 - A?		
		if(Operacao.TokensAritmeticos(l) != '0'){// Caso retornar diferente de ZERO, quer dizer que existe uma operação aritmerica.
			Valor[1] = String.valueOf(Operacao.ExpressoesAritmeticas(Valor[1]));// Faz a operação e retorna o valor.
		} else {
			Valor[1] = Valor[1].replaceAll("\\?", "");// Se existir um numero ou uma variavel.
			Valor[1] = LocalizarVariavel(Valor[1]);// Se tiver uma variavel vai retornar o valor dela.
		}
		return Valor[1];
	}
	
	public void Darkside(String l){
		if(l.contains(",")){// Quando quiser imprimir variaveis especificas.
			l = l.replaceAll("DARKSIDE", "");// Retira a palavra.
			l = l.replaceAll("[ \\{\\}\\?]", "");// Retira esses caracteres.
			String[] Nomes = l.split(",");//Quebras os indices nas virgulas encontradas.
			for(int w = 0; w < Nomes.length; w++){// Anda pelo vetor de nomes.
				for(int a = 0; a < V.length; a++){// Anda pelo vetor de variaveis criadas pelo usuario.
					if(V[a] != null){
						if(V[a].nome.equals(Nomes[w])){// Confere os nomes.
							System.out.println("[ " + a + " ] Nome Variavel : " + V[a].nome); 
							System.out.println("      Conteudo : " +  V[a].valor);
							System.out.println();
						}
					}
				}
			}
		} else {
			for(int w = 0; w < V.length; w++){// Anda pelo vetor de variaveis criadas pelo usuario.
				if (V[w] != null){
					System.out.println("[ " + w + " ] Nome Variavel : " + V[w].nome); 
					System.out.println("      Conteudo : " +  V[w].valor);
					System.out.println();
				}
			}
		}
	}
	
	public void MaisMais_E_MenosMenos(String l){
		int Somar = 1;// Caso seja Mais_Mais.
		if(l.contains("--")) Somar = (-1);// Caso seja Menos_Menos.
		String Nome = l.replaceAll("[ \\+\\-\\?]", "");// Tira esse caracteres. 
		for(int w = 0; w < V.length; w++){ // Anda pelo vetor de variaveis criadas pelo usuario.
			if(V[w] != null){
				if(V[w].nome.equals(Nome)){// Se o Nome passado pelo usuario, for igual ao que tem na variaveis criadas entra nesse if.
					if(V[w] instanceof Doublee){
						double valor = (double) V[w].valor;// Como é Object não posso somar diretamente, então transformo em Double.
						V[w].valor = valor + Somar;// Soma o valor que tinha mais com Somar, que varia se for Menos_Menos somar é -1.
					} else if(V[w] instanceof Inteiro){
						int valor = (int) V[w].valor;// Como é Object não posso somar diretamente, então transformo em Int.						
						V[w].valor = valor + Somar;
					}					
				}
			}
		}
	}
	
	public void ModificacaoNaVariavel(String l){
		if(l.contains("++") || l.contains("--")){
			MaisMais_E_MenosMenos(l);
		} else if(l.contains("<)")){
			Atribuicao(l);
		} else if(l.contains("<+)")){
			ConcatenarString(l);
		}
	}
	
	public void ConcatenarString(String l){
		AlteraVariavel(Pegar_Nome(l),Pegar_ValorString(l));
	}
	
	public void Atribuicao(String l){
		AlteraVariavel(Pegar_Nome(l),Pegar_Valor(l));
	}
	
	public boolean AlteraVariavel(String Nome, String Valor){// Esse metodo é do tipo boolean, pois ele é usado também na criação
		for(int w = 0; w < V.length; w++){ //                   de variaveis para ver se já existe uma variavel com aquele nome e se
			if(V[w] != null){//									caso existir uma variavel com aquele nome ela será subscrevida. Mas esse
				if(V[w].nome.equals(Nome)){//					é também utilizado para alteração no valor da variavel.
					if(V[w] instanceof Doublee){
						V[w].valor = Double.valueOf(Valor).doubleValue();
					} else if(V[w] instanceof Inteiro){
						V[w].valor = (int) Double.parseDouble(Valor);
					} else if(V[w] instanceof Stringg){
						V[w].valor += Valor;
					}
					return true;// Significa que a variavel ja existe e foi subscrevida.
				}
			}
		}
		return false;
	}
	
	public String LocalizarVariavel(String Nome){// Utilizado principalmente em expressões aritmerica com variaveis que precisa
		for(int w = 0; w < V.length; w++){ //       do valor da variavel.
			if(V[w] != null){
				if(V[w].nome.equals(Nome)){
					return String.valueOf(V[w].valor);// Retorna o valor da variavel.
				}
			}
		}
		return Nome;// Se não encontra retorna oque foi passado, porque pode ser um numero.
	}
	
	public String Pegar_ValorString(String l){
		int x = 0;
		String Valor = "";
		while(l.charAt(x) != '"') x++;
		x++;// Vale o proximo caracter da aspas.
		while(l.charAt(x) != '"'){// Copia até achar as aspas.
			Valor += l.charAt(x);
			x++;
		}
		return Valor;
	}
	
	public String Pegar_Nome(String l){
		int a = 0;
		if(l.startsWith("Int ") || l.startsWith("String ") || l.startsWith("Double ")) a = 1;
		// Caso for na declaração de variavel o nome vai se encontrar no indice 1 do vetor, pois no split é mandado quebrar
		// nos espaços.
		l = l.replaceAll("[\\<\\)\\?]", " ");// Retira esses caracteres.
		String[] vet = l.split(" ");
		return vet[a];
	}
	
     public void interpreta() {
		for(int posicao = 0; posicao < linhas.length; posicao++) {
            if(linhas[posicao] != null) {			
				posicao = ControleDeLinha(posicao);// Confere oque tem na linha e chama os metodos necessarios nessa linha.		
			}
		}
	}
}