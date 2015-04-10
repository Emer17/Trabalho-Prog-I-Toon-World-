class Inteiro extends Variavel{
	private int valor;
	
	public void setValor(int valor){
		this.valor = valor;
	}
	
	public int getValor(){
		return this.valor;
	}
	
	public Inteiro(){
		this.valor = 0;
	}

/*	public void adiciona(Inteiro [] vetor,String nome,int valor){
		System.out.println("why?3");
		int i = 0;
		//Inteiro x;
		//x = new Inteiro();
		System.out.println("why?3");
		this.setNome(nome);
		this.setValor(valor);
		while(i < 1000){
			if(vetor[i] == null){
				vetor[i] = this;
				break;			
			}
			i++;
		}
		System.out.println("why?4");
	}*/
	
	/*public int posicao(Inteiro [] vetor){
		System.out.println("why?4");
		int i = 0;
		while(i < 1000){
			if(vetor[i] == null){
				break;			
			}
			i++;
		}
		return i;
	}*/		
	
	/*public void adiciona(String nome, int valor){
		this.nome.setNome(nome);
		this.valor = setValor(valor);
	}*/
}
