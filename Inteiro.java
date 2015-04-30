class Inteiro extends Variavel{
	
	public Inteiro(String l, Variavel V[]){
		this.nome = super.Pegar_Nome(l,true);
		this.valor = (int) Double.parseDouble(super.Pegar_Valor(l,V));
	}
}