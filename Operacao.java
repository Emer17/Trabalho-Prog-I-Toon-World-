class Operacao{
	public double A;
	public double B;
	public double Result;
	
	public Operacao(){
		this.A = 0;
		this.B = 0;
		this.Result = 0;
	}
	public void setA(double a){
		this.A = a;
	}
	public void setB(double b){
		this.B = b;
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




}