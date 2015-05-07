class Condicao{	
	public int executaIf(Variavel Var,String linha,String l[],Variavel V[],Operacao OP,Comandos Com,int p){
		//System.out.println("p = " + p);
		String teste = "";
		int i = 0;
			if(linha != null){
				if(linha.contains("if")){
					while(linha.charAt(i) != '{'){
						i++;
					}
					i++;
					while(linha.charAt(i-1) != '}'){
						teste += linha.charAt(i);
						i++;
					}
					p++; // uma linha abaixo daquela que contains("if")
					OP.Expressoes(teste,V);
					if(OP.TokenComparativo){
						while(!l[p].contains("else") && !l[p].contains("]")){
							//V.CriaVariavel(l[p],V);
							Var.ModificacaoNaVariavel(l[p],V);
							if(l[p].contains("print") || l[p].contains("printlb")) Com.ComandoDeTela(l[p],V,Var);									
							if(l[p].contains("if")) p = executaIf(Var,l[p],l,V,OP,Com,p);	
							p++;
						}
						return p;
					}else{
						while(!l[p].contains("else") && !l[p].contains("]")){
							p++;
						}
						if(l[p].contains("else")) p++;
						while(!l[p].contains("]")){
							//V.CriaVariavel(l[p],V);
							Var.ModificacaoNaVariavel(l[p],V);						
							Com.ComandoDeTela(l[p],V,Var);
							p = executaIf(Var,l[p],l,V,OP,Com,p);						
							p++;
						}
						return p;
					}
				}
			}
			return p;
		}
}
