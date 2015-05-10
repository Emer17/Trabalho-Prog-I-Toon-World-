class Laco{
	public int four(Variavel V[], String l, int TOPO, String linhas[]){
		int x = 0;
		int i = TOPO+1;
		String Parte1 = "", Parte2 = "", Parte3 = "";
		Variavel Var = new Variavel();
		Interpretador I = new Interpretador();
		Operacao OP = new Operacao();
		if (l.startsWith("four")){
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
			Parte2 += l.charAt(x);
			OP.Expressoes(Parte2, V);//Confere qual tipo de comparação eh. Termino da FASE 2 do for
			x++;
			while(l.charAt(x) == ' ') x++;
			while(l.charAt(x) != '?'){
				Parte3 += l.charAt(x);
				x++;
			}
			Parte3 += l.charAt(x);//Confere se eh MaisMais ou MenosMenos. Termino da FASE 3 do for
			if(!OP.TokenComparativo){
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
					OP.Expressoes(Parte2, V);
				}while(OP.TokenComparativo);
			}
			TOPO = i;
		}
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

	public int executaWhile(Variavel Var,String linha,String l[],Variavel V[], Operacao OP,Comandos Com,int p){
		int i = 0,x = 0;
		String teste ="";
		if(linha != null){
			if(linha.contains("while")){
				x = p+1; //agora x tem a linha de inicio
				while(linha.charAt(i) != '{'){
					i++;
				}
				i++;
				while(linha.charAt(i-1) != '}'){
					teste += linha.charAt(i);
					i++;
				}		
				p++;
				OP.Expressoes(teste,V);
				if(OP.TokenComparativo){
					do{
						p = x; //volta pro inicio
						while(!l[p].contains("]")){
							//V.CriaVariavel(l[p],V);
							Var.ModificacaoNaVariavel(l[p],V);						
							Com.ComandoDeTela(l[p],V,Var);
							p = executaWhile(Var,l[p],l,V,OP,Com,p);
							p = four(V,Var,OP,l[p],p,l,Com);
							p++;
						}
						OP.Expressoes(teste,V);
					}while(OP.TokenComparativo);
				}else{
					while(!l[p].contains("]")){
						p++;
					}
					return p;
				}				
			}
		}
		return p;
	}
			
}
