class Laco{
	public void four(Variavel V, Operacao OP, String l[], int TOPO){
		int x = 0;
		String Parte1 = "";
		String Parte2 = "";
		String Parte3 = "";
		boolean PrimeiraEntrada = true;
		for(int i = TOPO; i < l.length; i++) {
			if(l[i] != null) {
				if (l[i].contains("four")){
					x = V.PosicionaX(x,l[i]);
					while(l[i].charAt(x-1) != '?'){
						Parte1 += l[i].charAt(x);
						x++;
					}
					V.Atribuicao(Parte1, V);//Atribui o valor para a variavel colocada ali
					//Termino da FASE 1 do for
					while(l[i].charAt(x) == ' ') x++;
					while(l[i].charAt(x) != '?'){
						Parte2 += l[i].charAt(x);
						x++;
					}
					Parte2 += l[i].charAt(x);
					OP.Expressoes(Parte2, V);//Confere qual tipo de comparação eh
					//Termino da FASE 2 do for
					x++;
					while(l[i].charAt(x) == ' ') x++;
					while(l[i].charAt(x) != '?'){
						Parte3 += l[i].charAt(x);
						x++;
					}
					Parte3 += l[i].charAt(x);//Confere se eh MaisMais ou MenosMenos
					//Termino da FASE 3 do for
					do{
						if(PrimeiraEntrada){
							if(!OP.TokenComparativo) break;
							PrimeiraEntrada = false;
						}
						for(int k = i+1; k < l.length; k++){
							if(l[k] != null) {
								V.CriaVariavel(l[k], V);
								V.ModificacaoNaVariavel(l[k],V);
								four(V,OP,l,k+1);
							}
							if(l[k].contains("]")) break;
						}
						V.ModificacaoNaVariavel(Parte3, V);
						OP.Expressoes(Parte2, V);
					}while(OP.TokenComparativo);
						
				}
			}
		}
	}
}