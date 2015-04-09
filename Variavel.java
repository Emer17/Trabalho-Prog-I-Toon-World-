class Variavel{
	private String nome;
	private int valor;
	
	public void criaVariavel(Variavel vetor[],String nome,int valor){
		int i = 0;
		while(i < 1000){
			//nome?if(vetor)
			if(vetor[i] == null){
				vetor[i].nome = nome;
				vetor[i].valor = valor;
				break;			
			}
			i++;
		}
	}

	public void VerificarConteudo(String linha){
		int i = 0;
		if(linha.contains("int")){
			//while(linha.charAt(i) != 't'){
			//	i++;
			//}
			//if(linha.charAt(i) == 't')
				System.out.println(" " + linha.length());
			}
		}
	
	public String setNome(String nome){
		return this.nome = nome;
	}
	
	public int setValor(int valor){
		return this.valor = valor;
	}	
		
		
		
	public String getNome(){
		return this.nome;
	}
	
	public double getValor(){
		return this.valor;
	}
}

/*class Interpretador{
	private Variavel [] vetor;
	
		
	public Interpretador() {
		vetor = new Variavel [10];
	}
	
	public Variavel getVariavel(String a){
		int i ;
		String x;
		x = new String();
		
		for(i = 0;i < 10;i++){
			//if((this.vetor[i].getNome()) == (a)){
			//if((this.vetor[i].getNome()).equals(a)){
			x = this.vetor[i].getNome();
			if(x.equals(a)){
				break;
			}
		}
		return this.vetor[i];
	}
	
	public void criaVariavel(Variavel v){
		int i = 0;
		while(i < 10){
			if(vetor[i] == null){
				vetor[i] = v;
				break;			
			}
			i++;
		}
	}
}
*/
