/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/

class Interpretador{
    private String linhas[];
	private Variavel [] vetor;
	
		
	public Interpretador() {
		vetor = new Variavel [1000];
	}

    public void interpreta(String l[]) {
        this.linhas = l;

        for(int i = 0; i < this.linhas.length; i++) {
            int x = 0;
            if(this.linhas[i] != null) {
				//System.out.println("entrou aqui1");
				//aqui a magica acontece! (ou não!)
				while(linhas[i].charAt(x) != '?' || linhas[i].charAt(x) != '['){
					String j;
					j = new String();
					j = linhas[i];
					Variavel a;
					a = new Variavel();
					a.VerificarConteudo(linhas[i]);
					//System.out.println("entrou aqui2");
					/*if(linhas[i].charAt(x) == '<' && linhas[i].charAt(x+1) == '-'){
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
					}*/
				}
			}
		}
	}
}
