class Condicao{
	private String antes,depois;
	
	public Condicao(){
		this.antes = "";
		this.depois = "";
	}
	
	
	public void print(String linha,Variavel V, Operacao OP){
		int i,p = 0; //p guarda ultima letra do comando... explicaçao adiante.
		String printa = "";
		double imprime;
		if(linha.contains("print") || linha.contains("printlb")){
			for(i=0;i<linha.length();i++){
				if(linha.charAt(i) ==' ' && linha.charAt(i-1) == 't' || linha.charAt(i) =='{' && linha.charAt(i-1) == 't'
					|| linha.charAt(i) ==' ' && linha.charAt(i-1) == 'b' || linha.charAt(i) =='{' && linha.charAt(i-1) == 'b'){
						p = i-1;
						if(linha.charAt(i) == ' '){
							i = i+2;	
						}else{
							i++;
						}
						if(linha.charAt(i) == '"'){ //caso de ser uma frase qualquer
							i++;
							while(linha.charAt(i) != '"'){
								printa += linha.charAt(i);
								i++;
							}
							System.out.println(printa);
							break;
						}else{ //caso de nao ter "" e ser uma variavel
							while(linha.charAt(i) != '}'){
								printa += linha.charAt(i);
								i++;
								printa = V.PESQ(printa,0);
								imprime = Double.valueOf(printa).doubleValue();
								System.out.println(imprime);
								break;
							}
						}
					}
				}
				if(linha.charAt(p)=='b') //p é usado aqui para saber se uma linha deve ser quebrada ou nao.
					System.out.println();
				return;
			}
		}
		


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
									if(l[p].contains("print") || l[p].contains("printlb"))
										print(l[p],V,OP);							
									if(l[p].contains("if"))
										executaIf(l,V,OP,p);
									p++;
								}
								break;
							}else{
								while(!l[p].contains("else")){
									p++;
								}
								p++;
								while(!l[p].contains("]")){
									V.CriaVariavel(l[p],V);							
									if(l[p].contains("print") || l[p].contains("printlb"))
										print(l[p],V,OP);
									if(l[p].contains("if"))
										executaIf(l,V,OP,p);						
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
