/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/

class Interpretador{
    private String linhas[];
	public Inteiro vetor1[] = new Inteiro[1000];
	public Doublee vetor2[] = new Doublee[1000];
	public Stringg vetor3[] = new Stringg[1000];

	
	
	public void corrige(String l[]){
		this.linhas = l; //este bloco corrige o problema de "espaços duplicados"
		linhas = l;
		String Nlinha = new String();
		for(int i = 0; i < this.linhas.length; i++){
			if(this.linhas[i] != null){
				linhas[i] = this.linhas[i].replaceAll("\\s+"," ");
				//System.out.println("Linha reescrita:" + linhas[i]);
				criaVariavel(linhas[i]);
				criaVariavel(linhas[i+1]);
			}
		}
	}	
	
	public void criaVariavel(String linha){//falta "localizar" um local para chamar a funçao de criação de variavel
		int i,j,k,x = 0;   // i percorre a linha, j percorre o vetor nome, 
		char [] nome;    //k percorre a partir do tipo primitivo e x percorre guardando o valor
		double m;
		nome = new char [20];
		char [] copia = linha.toCharArray();
		String nome2,valor2;
		char [] valor;
		valor = new char [10];
		if(linha.contains("main")){
			return;
		}else if(linha.contains("int") || linha.contains("double") || linha.contains("string")){
				for(i=0;i<linha.length();i++){
					if(linha.charAt(i) ==' ' && linha.charAt(i-1) == 't' || linha.charAt(i) ==' ' && linha.charAt(i-1) == 'e' || linha.charAt(i) ==' ' && linha.charAt(i-1) == 'g'){
						for(k=i,j=0;j<(linha.length()-i);k++){
							if(copia[k]==' ' || copia[k]== '?'){
								continue;
							}else if(copia[k]=='<'){
								k = k+2;
								if(copia[k]==' '){
									k++;
								}
								while(copia[k] != ' '){
									valor[x] = copia[k];
									k++;
									x++;
									valor2 = String.copyValueOf(valor);
									m = 1 * Double.valueOf(valor2).doubleValue();
									//System.out.println(m);
								}
							}else{
								nome[j] = copia[k];
								j++;
								nome2 = String.copyValueOf(nome);
								//System.out.println(nome2);
							}
						}
					}
				}
			}
		}

		
	public double teste_k(double k){
		if(k == 1){
			k = 10;
		} else {
			k = k*10;
		}
		return k;
	}
	
    public void interpreta(String l[]) {
        this.linhas = l;
		
		for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
				for(int x = 0; x < linhas[i].length(); x++ ){
					if(linhas[i].charAt(x) == '-' || linhas[i].charAt(x) == '+' || linhas[i].charAt(x) == '/' || linhas[i].charAt(x) == '*' || linhas[i].charAt(x) == '%'){
						Operacao OP = new Operacao();
						Boolean Percorre_W = true;
						int w = x-1;
						double k = 1;
						while(linhas[i].charAt(w) != ' '){
							OP.setA(linhas[i].charAt(w),k);
							k = teste_k(k);
							w--;
						}
						w = x+1;
						k = 1;
						while(linhas[i].charAt(w) != '?'){
							w++;
						}
						w--;
						while(Percorre_W){
							if(linhas[i].charAt(w) == '-' || linhas[i].charAt(w) == '+' || linhas[i].charAt(w) == '/' || linhas[i].charAt(w) == '*' || linhas[i].charAt(w) == '%'){
								Percorre_W = false;
							} else {
								OP.setB(linhas[i].charAt(w),k);
								k = teste_k(k);
								w--;
							}
						}
						if (linhas[i].charAt(x) == '-'){
							OP.Sub();
						} else if (linhas[i].charAt(x) == '+') {
							OP.Soma();
						} else if (linhas[i].charAt(x) == '/') {
							OP.Div();
						} else if (linhas[i].charAt(x) == '*') {
							OP.Mult();
						} else if (linhas[i].charAt(x) == '%') {
							OP.Mod();
						}
						System.out.println("Oh resultado da op eh: " + OP.Result);
					}	
				}
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

