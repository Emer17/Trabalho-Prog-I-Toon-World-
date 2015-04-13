class Operacao{
	public double A;
	public double B;
	public double Result;
	
	public Operacao(){
		this.A = 0;
		this.B = 0;
		this.Result = 0;
	}
	public void setA(char a, double k){
		String a2 = ""+ a;
		k = k * Double.valueOf(a2).doubleValue();
		this.A =  this.A + k;
	}
	public void setB(char b, double k){
		String b2 = ""+ b;
		k = k * Double.valueOf(b2).doubleValue();
		this.B =  this.B + k;
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
}