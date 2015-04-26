class Laco{
	public int four(Variavel V, Operacao OP, String l, int TOPO, String linhas[], Comandos C){
		int x = 0;
		int i = TOPO+1;
		String Parte1 = "";
		String Parte2 = "";
		String Parte3 = "";
		boolean PrimeiraEntrada = true;
		if (l.contains("four")){
			x = V.PosicionaX(x,l);
			while(l.charAt(x-1) != '?'){
				Parte1 += l.charAt(x);
				x++;
			}
			V.Atribuicao(Parte1, V);//Atribui o valor para a variavel colocada ali
			//Termino da FASE 1 do for
			while(l.charAt(x) == ' ') x++;
			while(l.charAt(x) != '?'){
				Parte2 += l.charAt(x);
				x++;
			}
			Parte2 += l.charAt(x);
			OP.Expressoes(Parte2, V);//Confere qual tipo de comparação eh
			//Termino da FASE 2 do for
			x++;
			while(l.charAt(x) == ' ') x++;
			while(l.charAt(x) != '?'){
				Parte3 += l.charAt(x);
				x++;
			}
			Parte3 += l.charAt(x);//Confere se eh MaisMais ou MenosMenos
			//Termino da FASE 3 do for
			do{
				if(PrimeiraEntrada){
					if(!OP.TokenComparativo) break;
					PrimeiraEntrada = false;
				}
				for(i = TOPO+1; i < linhas.length; i++){
					if(linhas[i] != null) {
						i = four(V,OP,linhas[i],i,linhas,C);
						V.CriaVariavel(linhas[i], V);
						V.ModificacaoNaVariavel(linhas[i],V);
						C.ComandoDeTela(linhas[i],V);
					}
					if(linhas[i].contains("]")) break;
				}
				V.ModificacaoNaVariavel(Parte3, V);
				OP.Expressoes(Parte2, V);
			}while(OP.TokenComparativo);
			TOPO = i;
		}
		return TOPO;
	}
	
	public void executaWhile(String l[],Variavel V, Operacao OP,int p){
		int i = 0,x = 0;
		String teste ="";
		while(l[p] != null){
			if(l[p].contains("while")){//linha tem while
				x = p; //agora x tem a linha de inicio
				while(l[p].charAt(i) != '{'){
					i++;
				}
				i++;
				while(l[p].charAt(i-1) != '}'){
					teste += l[p].charAt(i);
					i++;
				}					
				p++;
				OP.Expressoes(teste,V);
				if(OP.TokenComparativo){
					do{	
						while(!l[p].contains("]")){
							V.CriaVariavel(l[p],V);
							V.ModificacaoNaVariavel(l[p],V);						
							if(l[p].contains("print") || l[p].contains("printlb"))
								print(l[p],V,OP);
							p++;
						}
						p = x; //volta pro inicio
						OP.Expressoes(teste,V);
					}while(OP.TokenComparativo);
				}else{
					while(!l[p].contains("]")){
						p++;
					}
					p++;
					break;
				}				
			}
			p++;
		}
		return;
	}	
	
	

}
