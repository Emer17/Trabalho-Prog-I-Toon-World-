class Inteiro {
	public int valor;
	public String nome;
	public boolean Vazio;
	
	public Inteiro(){
		this.nome = "";
		this.valor = 0;
		this.Vazio = true;
	}
	
	public void TransformaValor(String Valor){
		this.valor = (int) Double.parseDouble(Valor);
	}
	
	public void SetVariavel_Inteiro(String Nome, String Valor){
		this.nome = Nome;
		TransformaValor(Valor);
		this.Vazio = false;
	}
}
