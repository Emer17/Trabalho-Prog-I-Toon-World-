class Operacao{
	private double A;
	private double B;
	public double Result;
	private boolean TokenNegativo = false;
	public boolean TokenComparativo = false;
	
	public Operacao(){
		this.A = 0;
		this.B = 0;
		this.Result = 0;
	}
	
	public void setA(String C){
		if (this.TokenNegativo) this.A = Double.valueOf(C).doubleValue()*(-1);
		else this.A = Double.valueOf(C).doubleValue();
	}
	
	public void setB(String C){
		this.B = Double.valueOf(C).doubleValue();
	}
	
	public void TokenEspecial(String l){
		for(int w = 0; w < l.length(); w++) {
			if(l.charAt(w) == '!'){ 
				this.TokenNegativo = true;
				break;
			} else {
				this.TokenNegativo = false;
			}
		}
	}
	
	public void OperacaoAritmetica(char Token){
		if(Token == '+'){
			this.Result = this.A + this.B;
		} else if(Token == '-'){
			this.Result = this.A - this.B;
		} else if(Token == '*'){
			this.Result = this.A * this.B;
		} else if(Token == '/'){
			this.Result = this.A / this.B;
		} else if(Token == '%'){
			this.Result = this.A % this.B;
		}
	}
	
	public char TokensAritmeticos(String l){
		for(int x = 0; x < l.length(); x++ ){
			if(l.charAt(x) == '+') return l.charAt(x);
			else if(l.charAt(x) == '-') return l.charAt(x);
			else if(l.charAt(x) == '*') return l.charAt(x);
			else if(l.charAt(x) == '/') return l.charAt(x);
			else if(l.charAt(x) == '%') return l.charAt(x);
		}
		return '0';
	}
	
	public String TokenComparativos(String l){
		String J = "";
		for(int x = 0; x < l.length(); x++ ){
			if(l.charAt(x) == '>' && l.charAt(x+1) == '>'){ //MAIOR
				J += l.charAt(x);
				J += l.charAt(x+1);
				return J;
			} else if(l.charAt(x) == '<' && l.charAt(x+1) == '<'){ //MENOR
				J += l.charAt(x);
				J += l.charAt(x+1);
				return J;
			} else if(l.charAt(x) == '>' && l.charAt(x+1) == '|'){ // MAIOR-IGUAL 
				J += l.charAt(x);
				J += l.charAt(x+1);
				return J;
			} else if(l.charAt(x) == '<' && l.charAt(x+1) == '|'){ // MENOR-IGUAL
				J += l.charAt(x);
				J += l.charAt(x+1);
				return J;
			} else if(l.charAt(x) == '|' && l.charAt(x+1) == '=' && l.charAt(x+2) == '|'){ // IGUAL
				J += l.charAt(x);
				J += l.charAt(x+1);
				J += l.charAt(x+2);
				return J;
			} else if(l.charAt(x) == '=' && l.charAt(x+1) == '|' && l.charAt(x+2) == '='){ // DIFERENTE
				J += l.charAt(x);
				J += l.charAt(x+1);
				J += l.charAt(x+2);
				return J;
			}
		}
		return J;
	}
	
	public void VereficaComparacao(String l){
		this.TokenComparativo = false;
		for(int x = 0; x < l.length(); x++ ){
			if(l.charAt(x) == '>' && l.charAt(x+1) == '>'){ //MAIOR
				if(this.A > this.B) this.TokenComparativo = true;
				break;
			} else if(l.charAt(x) == '<' && l.charAt(x+1) == '<'){ //MENOR
				if(this.A < this.B) this.TokenComparativo = true;
				break;
			} else if(l.charAt(x) == '>' && l.charAt(x+1) == '|'){ // MAIOR-IGUAL 
				if(this.A >= this.B) this.TokenComparativo = true;
				break;	
			} else if(l.charAt(x) == '<' && l.charAt(x+1) == '|'){ // MENOR-IGUAL
				if(this.A <= this.B) this.TokenComparativo = true;
				break;
			} else if(l.charAt(x) == '|' && l.charAt(x+1) == '=' && l.charAt(x+2) == '|'){ // IGUAL
				if(this.A == this.B) this.TokenComparativo = true;
				break;
			} else if(l.charAt(x) == '=' && l.charAt(x+1) == '|' && l.charAt(x+2) == '='){ // DIFERENTE
				if(this.A != this.B) this.TokenComparativo = true;
				break;
			}
		}
	}
	
	public void Expressoes(String l, Variavel V){
		this.TokenNegativo = false;
		TokenEspecial(l);
		String Concatenar = "";
		int x = 0;
		if(TokensAritmeticos(l) != '0'){
			while(l.charAt(x) != ' '){
				if(l.charAt(x) == TokensAritmeticos(l)) break;
				Concatenar += l.charAt(x);
				x++;
			}
			while(l.charAt(x-1) != TokensAritmeticos(l)) x++;
			Concatenar = V.PESQ(Concatenar,0);//Confere se eh uma variavel
			setA(Concatenar);
			Concatenar = "";
			while(l.charAt(x) == ' ' ) x++;// Percore ate achar a Variavel B. Exemplo: 8 +      9?
			while(l.charAt(x) != '?'){
				if(l.charAt(x) == ' ') break;
				Concatenar += l.charAt(x);
				x++;
			}
			Concatenar = V.PESQ(Concatenar,0);
			setB(Concatenar);
			OperacaoAritmetica(TokensAritmeticos(l));
		} else if(!TokenComparativos(l).equals("")){
			while(l.charAt(x) != ' '){
				if(l.charAt(x) == '|' || l.charAt(x) == '<' || l.charAt(x) == '>' || l.charAt(x) == '=') break;
				Concatenar += l.charAt(x);
				x++;
			}
			Concatenar = V.PESQ(Concatenar,0);//Confere se eh uma variavel
			setA(Concatenar);
			Concatenar = "";
			while(l.charAt(x) == '|' || l.charAt(x) == '<' || l.charAt(x) == '>' || l.charAt(x) == '=' || l.charAt(x) == ' ') x++;
			// Percore ate achar a Variavel B. Exemplo: 8 +      9?
			while(l.charAt(x) != '}'){
				if(l.charAt(x) == ' ') break;
				Concatenar += l.charAt(x);
				x++;
			}
			Concatenar = V.PESQ(Concatenar,0);
			setB(Concatenar);
			VereficaComparacao(l);
		}
	}
}
