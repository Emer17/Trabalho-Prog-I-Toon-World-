class Stringg extends Variavel{
	private String conteudo;
	
	public Stringg(){
		this.conteudo = " ";
	}	
	
	public void setConteudo(String x){
		this.conteudo = x;
	}
	
	public String getConteudo(){
		return this.conteudo;
	}
	
	public void ImpConteudo(){
		System.out.println(this.conteudo);
	}
}
