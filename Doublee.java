class Doublee extends Variavel{	

	public Doublee(String l, Variavel V[]){
		this.nome = super.Pegar_Nome(l,true);
		this.valor = Double.valueOf(super.Pegar_Valor(l,V)).doubleValue();
	}
}
