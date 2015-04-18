class Doublee {
	public double valor;
	public String nome;
	public boolean Vazio;
	
	public Doublee(){
		this.nome = "";
		this.valor = 0;
		this.Vazio = true;
	}
	
	public void ConcatenarNome(char N){
		this.nome += "" + N;
	}
	
	public void TransformaValor(String Valor){
		this.valor =  Double.valueOf(Valor).doubleValue();
	}
	
	public void SetVariavel_Double(String l, int x){
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
			this.valor = Exp.Result;
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