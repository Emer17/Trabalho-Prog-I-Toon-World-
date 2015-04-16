class Operacao{
	private double A;
	private double B;
	public double Result;
	
	public Operacao(){
		this.A = 0;
		this.B = 0;
		this.Result = 0;
	}
	
	public void Imp(){
		System.out.println("Oh resultado eh: " + this.Result);
	}
	
	public void setA(String C, boolean T_E){
		if (T_E) this.A = Double.valueOf(C).doubleValue()*(-1);
		else this.A = Double.valueOf(C).doubleValue();
	}
	
	public void setB(String C){
		this.B = Double.valueOf(C).doubleValue();
	}
	
	public void Soma(){
		this.Result = this.A + this.B;
	}
	
	public void Sub(){
		this.Result = this.A - this.B;
	}
	
	public void Mult(){
		this.Result = this.A * this.B;
	}
	
	public void Div(){
		this.Result = this.A / this.B;
	}
	
	public void Mod(){
		this.Result = this.A % this.B;
	}
	
	public boolean testeExpressao(String l){
		boolean T = false;
		for(int w = 0; w < l.length(); w++) {
			if(l.charAt(w) == '-'){ 
				T = true;
				break;
			} else if (l.charAt(w) == '+'){				
				T = true;
				break;
			} else if (l.charAt(w) == '/'){ 
				T = true;
				break;				
			} else if (l.charAt(w) == '*'){ 
				T = true;
				break;
			} else if (l.charAt(w) == '%'){
				T = true;
				break;
			} else {
				T = false;
			}
		}
		return T;
	}
	
	public boolean TokenEspecial(String l){
		boolean T = true;
		for(int w = 0; w < l.length(); w++) {
			if(l.charAt(w) == '@'){ 
				T = true;
			} else {
				T = false;
			}
		}
		return T;
	}
	
	public void Expressoes(String l){
		if(testeExpressao(l)){
			int x;
			boolean TokenEspecial = false;
			if(TokenEspecial(l)) TokenEspecial = true;
			for(x = 0; x < l.length(); x++ ){
				if(l.charAt(x) == '+' || l.charAt(x) == '-' || l.charAt(x) == '*' || l.charAt(x) == '/' || l.charAt(x) == '%'){
					break;
				}
			}
			char Token = l.charAt(x);
			int w = x - 1;
			while(l.charAt(w) == ' ') w--;// Percore ate achar a Variavel A. Exemplo: 8      + 9?
			String Concatenar = "";
			while(l.charAt(w) != ' '){
				if(l.charAt(w) == ')') break;
				Concatenar += l.charAt(w);
				w--;
			}
			Concatenar = Inverter(Concatenar);
			setA(Concatenar,TokenEspecial);
			Concatenar = "";
			w = x + 1;
			while(l.charAt(w) == ' ' ) w++;// Percore ate achar a Variavel B. Exemplo: 8 +      9?
			while(l.charAt(w) != '?'){
				if(l.charAt(w) == ' ') break;
				Concatenar += l.charAt(w);
				w++;
			}
			setB(Concatenar);
			if (Token == '-'){
				Sub();
			} else if (Token == '+') {
				Soma();
			} else if (Token == '/') {
				Div();
			} else if (Token == '*') {
				Mult();
			} else if (Token == '%') {
				Mod();
			}
			Imp();
		}
	}
	
	public String Inverter(String S){  
        String Reverse = "";
		for (int w = S.length() - 1; w > -1; w--) {    
            Reverse += String.valueOf(S.charAt(w));
		}
		return Reverse;
	}
}