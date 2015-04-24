class Condicao{	
	public void executaIf(String l[],Variavel V,Operacao OP,int p){
		int num = 0;
		String teste = "";
		int i = 0;
		while(p < l.length){
			if(l[p] != null){
				if(l[p].contains("if")){
					while(l[p].charAt(i) != '{'){
						i++;
					}
					i++;
					while(l[p].charAt(i-1) != '}'){
						teste += l[p].charAt(i);
						i++;
					}
					p++;
					OP.Expressoes(teste,V);//executa as linhas, independente de estarem dentro do "if" ou do "else"
					if(OP.TokenComparativo){
						while(!l[p].contains("else")){
							V.CriaVariavel(l[p],V);						
							if(l[p].contains("print") || l[p].contains("printlb")){
								print(l[p],V,OP);
							}									
							if(l[p].contains("if")){
								executaIf(l,V,OP,p);
							}	
							p++;
						}
						break;
					} else {
						while(!l[p].contains("else")){
							p++;
						}
						p++;
						while(!l[p].contains("]")){
							V.CriaVariavel(l[p],V);							
							if(l[p].contains("print") || l[p].contains("printlb")) print(l[p],V,OP);
							if(l[p].contains("if")) executaIf(l,V,OP,p);						
							p++;
						}
						break;
					}
				}
			}
			p++;
		}
		return;
	}
}
