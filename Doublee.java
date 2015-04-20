class Doublee {
	public double valor;
	public String nome;
	public boolean Vazio;
	
	public Doublee(){
		this.nome = "";
		this.valor = 0;
		this.Vazio = true;
	}
	
	public void TransformaValor(String Valor){
		this.valor =  Double.valueOf(Valor).doubleValue();
	}
	
	public void SetVariavel_Double(String Nome, String Valor){
		this.nome = Nome;
		TransformaValor(Valor);
		this.Vazio = false;
	}
}
