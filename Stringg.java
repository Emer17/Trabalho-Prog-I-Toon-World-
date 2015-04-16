class Stringg {
	public String conteudo;
	public String nome;
	public boolean Vazio;
	
	public Stringg(){
		this.nome = "";
		this.conteudo = "";
		this.Vazio = true;
	}
	
	public void ConcatenarNome(char N){
		this.nome += "" + N;
	}
	
	public void ConcatenarConteudo(char C){
		this.conteudo += "" + C;
	}
	
}