class Comandos{
	public void print(String linha, Variavel V, Operacao OP){
		int i,p = 0; //p guarda ultima letra do comando... explicaçao adiante.
		String printa = "";
		double imprime;
		if(linha.contains("print") || linha.contains("printlb")){
			for(i=0;i<linha.length();i++){
				if(linha.charAt(i) ==' ' && linha.charAt(i-1) == 't' || linha.charAt(i) =='{' && linha.charAt(i-1) == 't' || linha.charAt(i) ==' ' && linha.charAt(i-1) == 'b' || linha.charAt(i) =='{' && linha.charAt(i-1) == 'b'){
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
}