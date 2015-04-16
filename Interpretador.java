/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/

class Interpretador {
    private String linhas[];

    public void interpreta(String l[]) {
        this.linhas = l;
		Operacao OP = new Operacao();
		Variavel V = new Variavel();
		for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
				V.CriaVariavel(linhas[i]);
				OP.Expressoes(linhas[i]);
			}
		}
		System.out.println("=================================");
		System.out.println("        Vetor de String");
		System.out.println("=================================");
		for(int w = 0; w < V.vString.length; w++){
			if (V.vString[w].Vazio){
				
			} else {
				System.out.println( w + " <- Posicao ");
				System.out.println("             ->> Nome : " + V.vString[w].nome);
				System.out.println("             ->> Conteudo : " + V.vString[w].conteudo);
			}
		}
		System.out.println("=================================");
		System.out.println("        Vetor de Inteiros");
		System.out.println("=================================");
		for(int a = 0; a < V.vInteiro.length; a++){
			if (V.vInteiro[a].Vazio){
				
			} else {
				System.out.println( a + " <- Posicao ");
				System.out.println("             ->> Nome : " + V.vInteiro[a].nome);
				System.out.println("             ->> Valor : " + V.vInteiro[a].valor);
			}
		}
		System.out.println("=================================");
		System.out.println("        Vetor de Double");
		System.out.println("=================================");
		for(int y = 0; y < V.vDouble.length; y++){
			if (V.vDouble[y].Vazio){
				
			} else {
				System.out.println( y + " <- Posicao ");
				System.out.println("             ->> Nome : " + V.vDouble[y].nome);
				System.out.println("             ->> Valor : " + V.vDouble[y].valor);
			}
		}
        /*for(int i = 0; i < this.linhas.length; i++) {
            int x = 0;
            if(this.linhas[i] != null) {
				//System.out.println("entrou aqui1");
				//aqui a magica acontece! (ou não!)
				while(linhas[i].charAt(x) != '?' || linhas[i].charAt(x) != '['){
					VerificarConteudo(linhas[i]);
					break;
					//System.out.println("entrou aqui2");
					//if(linhas[i].charAt(x) == '<' && linhas[i].charAt(x+1) == '-'){
						//atribuição
						System.out.println("entrou aqui3");
						System.out.println(" " + linhas[i].length());
						break;
					}else if(linhas[i].charAt(x) == '<' && linhas[i].charAt(x+1) == '<'){
						//comparaçao menor
					}else if(linhas[i].charAt(x) == '>' && linhas[i].charAt(x+1) == '>'){
						//comparaçao maior
					}else if(linhas[i].charAt(x) == '<' && linhas[i].charAt(x+1) == '|'){
						//comparaçao menor igual
					}else if(linhas[i].charAt(x) == '>' && linhas[i].charAt(x+1) == '|'){
						//comparaçao maior igual
					}else if(linhas[i].charAt(x) == '|' && linhas[i].charAt(x+1) == '=' && linhas[i].charAt(x+2) == '|'){
						//igual
					}else if(linhas[i].charAt(x) == '=' && linhas[i].charAt(x+1) == '|' && linhas[i].charAt(x+2) == '='){
						//diferente
						System.out.println(" " + linhas[i].length());
						break;
					}else if(linhas[i].charAt(x) == '+' && linhas[i].charAt(x+1) == '='){
						//i++
					}else if(linhas[i].charAt(x) == '-' && linhas[i].charAt(x+1) == '='){
						//i--
					}else{
						//é uma linha de "escopo"
						x++;
					}
				}
			}
		}*/
	}
}

