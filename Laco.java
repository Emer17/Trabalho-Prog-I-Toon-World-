/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
class Laco{
	public int four(Variavel V[], String l, int TOPO, String linhas[]){
		int x = 0;
		int i = TOPO+1;
		String Parte1 = "", Parte2 = "", Parte3 = "";
		Variavel Var = new Variavel();
		Interpretador I = new Interpretador();
		Operacao OP = new Operacao();
		x = l.indexOf(123);
		x++;
		while(l.charAt(x-1) != '?'){
			Parte1 += l.charAt(x);
			x++;
		}
		Var.Atribuicao(Parte1, V);//Atribui o valor para a variavel colocada ali. Termino da FASE 1 do for
		while(l.charAt(x) == ' ') x++;
		while(l.charAt(x) != '?'){
			Parte2 += l.charAt(x);
			x++;
		}
		Parte2 += l.charAt(x);//Confere qual tipo de comparação eh. Termino da FASE 2 do for
		x++;
		while(l.charAt(x) == ' ') x++;
		while(l.charAt(x) != '?'){
			Parte3 += l.charAt(x);
			x++;
		}
		Parte3 += l.charAt(x);//Confere se eh MaisMais ou MenosMenos. Termino da FASE 3 do for
		if(!OP.ExpressoesComparacao(Parte2, V)){
			while(!linhas[i].startsWith("]f")){
				i = IgnoraOutrosFors(linhas,i);
				i++;
			}
		} else {
			do{
				i = TOPO+1;
				while(!linhas[i].startsWith("]f")){
					i = I.VereficarLinha(linhas,V,i);
					i++;
				}
				Var.ModificacaoNaVariavel(Parte3, V);
			}while(OP.ExpressoesComparacao(Parte2, V));
		}
		TOPO = i;
		return TOPO;
	}
	
	public int IgnoraOutrosFors(String linhas[], int i){
		if(linhas[i].startsWith("four")){
			i++;
			while(!linhas[i].startsWith("]f")){
				i = IgnoraOutrosFors(linhas,i);
				i++;
			}
		}
		return i;
	}
	public int IgnoraOutroswhile(String linhas[], int i){
		if(linhas[i].startsWith("while")){
			i++;
			while(!linhas[i].startsWith("]w")){
				i = IgnoraOutroswhile(linhas,i);
				i++;
			}
		}
		return i;
	}

	public int executaWhile(Variavel V[], String linha, int TOPO, String linhas[]){
		int i = TOPO+1;
		Interpretador I = new Interpretador();
		Operacao OP = new Operacao();
		String ExprecaoWhile = "";
		ExprecaoWhile = linha.replaceAll(" ", ""); // Remove os espaços ;
		ExprecaoWhile = ExprecaoWhile.replaceAll("while\\{", ""); // So copia a expreção por Exemplo : Antes-> while{a<<b}[    Depois-> {a<<b}[;
		i++; // Avança para linha de baixo do WHILE;
		if(!OP.ExpressoesComparacao(ExprecaoWhile, V)){
			while(!linhas[i].startsWith("]w")){
				i = IgnoraOutroswhile(linhas,i);
				i++;
			}
		}else{
			do{
				i = TOPO+1;
				while(!linhas[i].startsWith("]w")){
					i = I.VereficarLinha(linhas,V,i);
					i++;
				}
			}while(OP.ExpressoesComparacao(ExprecaoWhile, V));
		}
		TOPO = i;
		return TOPO;
	}
}