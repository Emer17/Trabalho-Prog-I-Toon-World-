/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
class Interpretador {
    private String linhas[];
	public Variavel V[] = new Variavel[10]; 
	
	public void CriaVariavel(String l){
		Variavel K = new Variavel();
		for(int w =0; w < this.V.length; w++){
			if(this.V[w] == null){
				if(l.contains("Int")){
					if(K.Pesquisar(K.Pegar_Nome(l,true),K.Pegar_Valor(l,V),V));
					else V[w] = new Inteiro(l,this.V);
					break;
				} else if(l.contains("Double")){
					if(K.Pesquisar(K.Pegar_Nome(l,true),K.Pegar_Valor(l,V),V));
					else V[w] = new Doublee(l,this.V);
					break;
				} else if(l.contains("String")){
					if (K.Pesquisar(K.Pegar_Nome(l,true),K.Pegar_ValorString(l),V));
					else V[w] = new Stringg(l);
					break;
				}
			}
		}
	}

	public void corrige(String l[]){//este bloco corrige o problema de "espaços duplicados"
		String linhas_corrigidas[];
		linhas_corrigidas = l;
		String Nlinha = new String();
		for(int i = 0; i < linhas_corrigidas.length;/*this.linhas.length;*/ i++){
			if(linhas_corrigidas[i] != null){
				linhas_corrigidas[i] = linhas_corrigidas[i].replaceAll("\\s+"," ");
				//System.out.println("Linha reescrita:" + linhas_corrigidas[i]);
			}
		}
		interpreta(linhas_corrigidas);
	}

    public void interpreta(String l[]) {
        this.linhas = l;
		Condicao Con = new Condicao();		
		Operacao OP = new Operacao();
		Variavel Var = new Variavel();
		Laco U = new Laco();
		Comandos C = new Comandos();
		for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
				i = U.four(V,OP,l[i],i,l,C);
				i = Con.executaIf(linhas[i],l,V,OP,C,i);
				i = U.executaWhile(linhas[i],l,V,OP,C,i);
				CriaVariavel(linhas[i]);
				Var.ModificacaoNaVariavel(linhas[i], V);
				C.ComandoDeTela(linhas[i],V);
				
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
