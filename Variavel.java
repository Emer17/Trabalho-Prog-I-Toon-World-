class Variavel{ 
	
	public Inteiro vInteiro[];
	public Doublee vDouble[];
	public Stringg vString[];

	public Variavel(){
		int tamanho = 10;
		this.vInteiro = new Inteiro[tamanho];
		this.vDouble = new Doublee[tamanho];
		this.vString = new Stringg[tamanho];
		for (int a = 0; a < tamanho; a++){
			this.vString[a] = new Stringg();
			this.vDouble[a] = new Doublee();
			this.vInteiro[a] = new Inteiro();
		}
	}
	
	public void CriaVariavel(String l){
		if(l.contains("String")){
			CriaVariavel_String(l);
		} else if(l.contains("Int")){
			CriaVariavel_Inteiro(l);
		} else if(l.contains("Double")){
			CriaVariavel_Double(l);
		} else if(l.contains("<)")){
			Atribuicao(l);
		}
	}
	
	public void Atribuicao(String l){
		String Nome = "";
		int w = 0;
		while(l.charAt(w) != '<'){
			if(l.charAt(w) != ' ') Nome += l.charAt(w);
			else if (l.charAt(w) == ' ' && (l.charAt(w+1) == ' ' || l.charAt(w+1) == '<')) ;
			else Nome += " ";
			w++;
		}
		if (Pesquisar_Double(Nome) != 0) System.out.println(Pesquisar_Double(Nome));
		if (Pesquisar_Inteiro(Nome) != 0) System.out.println(Pesquisar_Inteiro(Nome));
	}
	
	public double Pesquisar_Double(String Nome){
		double a = 0;
		for(int w =0; w < this.vDouble.length; w++){
			if(this.vDouble[w].nome.equals(Nome)){
				a = this.vDouble[w].valor;
				return a;
			}
		}
		return 0;
	}
	
	public int Pesquisar_Inteiro(String Nome){
		int a = 0;
		for(int w =0; w < this.vInteiro.length; w++){
			if(this.vInteiro[w].nome.equals(Nome)){
				a = this.vInteiro[w].valor;
				return a;
			}
		}
		return 0;
	}
	
	public void CriaVariavel_Double(String l){
		int x = 0;
		x = PosicionaX(x,l);
		for(int w =0; w < this.vDouble.length; w++){
			if(this.vDouble[w].Vazio){
				vDouble[w].SetVariavel_Double(l,x);
				break;
			}
		}
	}
	
	public void CriaVariavel_Inteiro(String l){
		int x = 0;
		x = PosicionaX(x,l);
		for(int w =0; w < this.vInteiro.length; w++){
			if(this.vInteiro[w].Vazio){
				vInteiro[w].SetVariavel_Inteiro(l,x);
				break;
			}
		}
	}

	public void CriaVariavel_String(String l){
		int x = 0;
		x = PosicionaX(x,l);
		for(int w =0; w < this.vString.length; w++){
			if(this.vString[w].Vazio){
				while(l.charAt(x) != '<'){
					if(l.charAt(x) != ' ') vString[w].ConcatenarNome(l.charAt(x));
					else vString[w].ConcatenarNome(' ');
					x++;
				}
				while(l.charAt(x) != '"'){
					x++;
				}
				x = PosicionaX(x,l);
				while(l.charAt(x) != '"'){
					if(l.charAt(x) != ' ') vString[w].ConcatenarConteudo(l.charAt(x));
					else vString[w].ConcatenarConteudo(' ');
					x++;
				}
				vString[w].Vazio = false;
				break;
			}
		}
	}
	
	public int PosicionaX(int x, String l){
		while(l.charAt(x) != ' ') x++; // Quando sai X ta valendo ESPAÇO;
		while(l.charAt(x) == ' ') x++; // Agora X vale o primeiro caracter
		return x;
	}
}
