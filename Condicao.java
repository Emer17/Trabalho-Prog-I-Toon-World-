class Condicao{	
	public int executaIf(Variavel V[], String linha, int p, String l[]){
		Interpretador I = new Interpretador();
		Operacao OP = new Operacao();
		String teste = "";
		int i = 0;
			if(linha != null){
				if(linha.startsWith("if")){
					while(linha.charAt(i) != '{') i++;
					i++;
					while(linha.charAt(i) == ' ') i++;
					while(linha.charAt(i-1) != '}'){
						teste += linha.charAt(i);
						i++;
					}
					p++; // uma linha abaixo daquela que contains("if")
					OP.Expressoes(teste,V);
					if(OP.TokenComparativo){
						while(!l[p].startsWith("]i")){
							p = I.VereficarLinha(l,V,p);
							p++;
						}
						if(l[p].contains("else")){
							p++;
							while(!l[p].startsWith("]e")) p++;
						}	
					} else {
						while(!l[p].startsWith("]i")) p++;
						if(l[p].contains("else")){
							p++;
							while(!l[p].contains("]e")){
								p = I.VereficarLinha(l,V,p);
								p++;
							}
						}
					}
				}
			}
			return p;
		}
}
