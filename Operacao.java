class Operacao{
	private double A;
	private double B;
	private double Result;
	
	public Operacao(){
		this.A = 0;
		this.B = 0;
		this.Result = 0;
	}
	
	public void Imp(char Token){
		System.out.println("Oh resultado de " + Math.round(this.A) + " " + Token + " " + Math.round(this.B) +" eh: " + this.Result);
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
	
	public boolean testeExpressao(char Token){
		boolean T;
		if(Token == '-'){ 
			T = true;
		} else if (Token == '+'){ 
			T = true;
		} else if (Token == '/'){ 
			T = true;	
		} else if (Token == '*'){ 
			T = true;
		} else if (Token == '%'){
			T = true;
		} else {
			T = false;
		}
		return T;
	}
	
	public boolean TokenEspecial(char T_Especial){
		boolean T;
		if(T_Especial == '@'){ 
			T = true;
		} else {
			T = false;
		}
		return T;
	}
	
	public void Exp(String l, char Token, boolean T_E){
		int x;
		for(x = 0; x < l.length(); x++ ){
			if(testeExpressao(l.charAt(x))){
				break;
			}
		}
		int w = x-1;
		String Concatenar = "";
		while(l.charAt(w) != ' '){
			Concatenar += l.charAt(w);
			w--;
		}
		Concatenar = Inverter(Concatenar);
		setA(Concatenar, T_E);
		Concatenar = "";
		w = x+1;
		while(l.charAt(w) != '?'){
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
	}
	
	public String Inverter(String S){  
        String Reverse = "";
		for (int w = S.length() - 1; w > -1; w--) {    
            Reverse += String.valueOf(S.charAt(w));
		}
		return Reverse;
	}
}