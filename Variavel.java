class Variavel{ 
	
	public String nome;
	public Object valor;
		
	public void MaisMais_E_MenosMenos(String l, Variavel V[]){
		for(int w = 0; w < V.length; w++){
			if(V[w] != null){
				if(V[w].nome.equals(Pegar_Nome(l,false))){
					if(V[w] instanceof Doublee){
						double valor = (double) V[w].valor;
						if(l.contains("...")) V[w].valor = valor + 1;
						else V[w].valor = valor - 1;
					} else if(V[w] instanceof Inteiro){
						int valor = (int) V[w].valor;
						if(l.contains("...")) V[w].valor = valor + 1;
						else V[w].valor = valor - 1;
					}					
				}
			}
		}
	}
	
	public void ModificacaoNaVariavel(String l, Variavel V[]){
		if(l.contains("...") || l.contains(",,,")){
			MaisMais_E_MenosMenos(l, V);
		} else if(l.contains("<)")){
			Atribuicao(l, V);
		} else if(l.contains("<+)")){
			ConcatenarString(l, V);
		}
	}
	
	public void ConcatenarString(String l, Variavel V[]){
		Pesquisar(Pegar_Nome(l,false),Pegar_ValorString(l),V);
	}
	
	public void Atribuicao(String l, Variavel V[]){
		Pesquisar(Pegar_Nome(l,false),Pegar_Valor(l,V),V);
	}
	
	public boolean Pesquisar(String Nome, String Valor, Variavel V[]){
		for(int w = 0; w < V.length; w++){
			if(V[w] != null){
				if(V[w].nome.equals(Nome)){
					if(V[w] instanceof Doublee){
						V[w].valor = Double.valueOf(Valor).doubleValue();
					} else if(V[w] instanceof Inteiro){
						V[w].valor = Integer.parseInt(Valor);
					} else if(V[w] instanceof Stringg){
						V[w].valor += Valor;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public String LocalizarVariavel(String Nome, Variavel V[]){
		for(int w = 0; w < V.length; w++){
			if(V[w] != null){
				if(V[w].nome.equals(Nome)){
					return String.valueOf(V[w].valor);
				}
			}
		}
		return Nome;
	}
	
	public int PosicionaX(int x, String l){
		while(l.charAt(x) != ' '){
			x++; 
			if(l.charAt(x - 1) == '}' || l.charAt(x - 1) == ')') break;
			if(l.charAt(x - 1) == '{' || l.charAt(x - 1) == '(') break;// Usa no for exemplo four{K <) 45 caso o K esteja do lado do ( 
		}// Sai com X valendo ESPAÇO;
		while(l.charAt(x) == ' ') x++; // Agora X vale o primeiro caracter, caso nao tenha espaço ele nem entra no laço
		return x;
	}
	
	public String Pegar_Nome(String l, boolean Ignora){
		int x = 0;
		String Nome = "";
		if(Ignora) x = PosicionaX(x,l);
		while(l.charAt(x) != '<'){
			if(l.charAt(x) == '?' || l.charAt(x) == '.' || l.charAt(x) == ',') break;
			if(l.charAt(x) != ' ' && l.charAt(x) != 9) Nome += l.charAt(x);
			x++;
		}
		return Nome;
	}
	
	public String Pegar_ValorString(String l){
		int x = 0;
		String Valor = "";
		while(l.charAt(x) != '"') x++;
		x++;
		while(l.charAt(x) != '"'){
			Valor += l.charAt(x);
			x++;
		}
		return Valor;
	}
	
	public String Pegar_Valor(String l, Variavel V[]){
		int x = 0;
		String Valor = "";
		String LinhaExp = "";
		Operacao Exp = new Operacao();
		if(!l.contains("<)")){
			Valor += '0';
		} else {
			while(l.charAt(x)!= '<') x++;
			x = PosicionaX(x,l);
			if(Exp.TokensAritmeticos(l) != '0' || !Exp.TokenComparativos(l).equals("")){
				while(l.charAt(x-1) != '?'){ 
					LinhaExp += l.charAt(x);
					x++;
				}
				Exp.Expressoes(LinhaExp,V);
				Valor = String.valueOf(Exp.Result);
			} else {
				while(l.charAt(x) != '?'){
					if(l.charAt(x) == ' ') break; 
					Valor += l.charAt(x);
					x++;
				}
				Valor = LocalizarVariavel(Valor,V);
			}
		}
		return Valor;
	}
}