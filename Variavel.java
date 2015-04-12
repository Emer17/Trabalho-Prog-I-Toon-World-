class Variavel{
	private String nome;

	
	public void setNome(String nome){
		this.nome = nome;
	}

	public String getNome(){
		return this.nome;
	}

	public void Imp(){
		System.out.println(this.nome);
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
