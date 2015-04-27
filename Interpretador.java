/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
class Interpretador {
    private String linhas[];

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
		Variavel V = new Variavel();
		Laco U = new Laco();
		Comandos C = new Comandos();
		for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
				i = U.four(V,OP,l[i],i,l,C);
				i = Con.executaIf(linhas[i],l,V,OP,C,i);
				i = U.executaWhile(linhas[i],l,V,OP,C,i);
				V.CriaVariavel(linhas[i], V);
				V.ModificacaoNaVariavel(linhas[i], V);
				C.ComandoDeTela(linhas[i],V);
				
			}
		}
		//V.Imprimir_Vetores();
	}
}
