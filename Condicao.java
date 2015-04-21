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
								printa = V.PESQ(printa);
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
		
	public boolean verificaToken(String l){
		if(l.contains("==") || l.contains("!=") || l.contains(">>") || l.contains("<<") || l.contains(">|") || l.contains("<|"))
			return true;
		else
			return false;
	}
	
	public void pegaToken(String l){
		int x;
		for(x = 0; x < l.length(); x++){
			if(l.charAt(x) == '{'){
				x++;
				while(l.charAt(x) != '=' && l.charAt(x) != '!' && l.charAt(x) != '>' && l.charAt(x) != '<'){
					this.antes += l.charAt(x);
					x++;
				}
				x = x+2;
				while(l.charAt(x) != '}'){
					this.depois += l.charAt(x);
					x++;
				}
			}
		}
	}				
				
	public boolean testaexp(String l,Variavel V,Operacao OP){
		String antes2,depois2;
		double antes3,depois3;
		if(l.contains("==")){
			if(this.antes == this.depois)
				return true;
			else
				return false;
		}else if(l.contains("!=")){
			if(this.antes != this.depois)
				return true;
			else
				return false;
		}else{
			antes2 = V.PESQ(this.antes);
			depois2 = V.PESQ(this.depois);
			antes3 =  Double.valueOf(antes2).doubleValue();
			depois3 =  Double.valueOf(depois2).doubleValue();

			if(l.contains(">>")){
				if(antes3 > depois3)
					return true;
			}else if(l.contains("<<")){
				if(antes3 < depois3)
					return true;
			}else if(l.contains(">|")){
				if(antes3 >= depois3)
					return true;							
			}else if(l.contains("<|")){
				if(antes3 <= depois3)
					return true;
			}
		}
		return false;
	}
	
	public double pegaExp(String l,Variavel V,Operacao OP){  //serve para o caso de dentro do if ter apenas o nome de uma variavel
		int x;
		String exp = "";
		String expressao = "";
		double result = 0.0;
		for(x = 0; x < l.length(); x++){
			if(l.charAt(x) == '{'){
				x++;
				while(l.charAt(x) != '}'){
					exp += l.charAt(x);
					x++;
				}
				expressao = V.PESQ(exp);
				System.out.println("expressao   " + expressao); //deveria retornar valor, mesmo problema la de cima :S
				result =  Double.valueOf(expressao).doubleValue(); //valor convertido pra double...
			}
		}
		return result;
	}
	
	public void executaIf(String l[],Variavel V,Operacao OP){
		int num = 0,p;
		double exp1;
		for(p = 0; p < l.length; p++){
			if(l[p] != null){
				do{
					if(l[p].contains("if")){
						num++;
						if(verificaToken(l[p])){
							pegaToken(l[p]);
							if(testaexp(l[p],V,OP)){
								p++;
							}else{
								while((!l[p].contains("else")) && (!l[p].contains("]"))){
									p++;
								}
								if((l[p].contains("]")) && (!l[p].contains("else"))){
									num--;
								}else if(l[p].contains("else")){
									p++;
								}
							}
						}else{
							exp1 = pegaExp(l[p],V,OP);
							if(exp1 >=1){
								p++;
							}else{
								while((!l[p].contains("else")) || (!l[p].contains("]"))){
									p++;
								}
								if((l[p].contains("]")) && (!l[p].contains("else"))){
									num--;
								}else if(l[p].contains("else")){
									p++;
								}
							}
						}
						}else if((!l[p].contains("else"))){ //executa as linhas, independente de estarem dentro do "if" ou do "else"
							V.CriaVariavel(l[p],V);
							OP.Expressoes(l[p],V);							
							if(l[p].contains("print") || l[p].contains("printlb")){
							print(l[p],V,OP);							
							}
							p++;
						}else{
							num--;
						}
					}while(num != 0);
				}
			}
		}
		
	
}
