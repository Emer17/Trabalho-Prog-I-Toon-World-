class Inteiro {
	public int valor;
	public String nome;
	public boolean Vazio;
	
	public Inteiro(){
		this.nome = "";
		this.valor = 0;
		this.Vazio = true;
	}
	
	public void ConcatenarNome(char N){
		this.nome += "" + N;
	}
	
	public void TransformaValor(String Valor){
		this.valor =  Integer.parseInt(Valor);
	}
	
	public void SetVariavel_Inteiro(String l, int x){
		Operacao Exp = new Operacao();
		String Valor = "";
		while(l.charAt(x) != '<'){
			if(l.charAt(x) != ' ') ConcatenarNome(l.charAt(x));
			else if (l.charAt(x) == ' ' && (l.charAt(x+1) == ' ' || l.charAt(x+1) == '<')) ;
			else ConcatenarNome(' ');
			x++;
		}
		while(l.charAt(x) != ')'){
			x++;
		}
		x++;
		if(Exp.testeExpressao(l)){
			Exp.Expressoes(l);
			this.valor = (int)Exp.Result;
		} else {
			while(l.charAt(x) != '?'){
				if(l.charAt(x) != ' ') Valor += l.charAt(x);
				x++;
			}
			TransformaValor(Valor);
		}
		this.Vazio = false;
	}
	
}