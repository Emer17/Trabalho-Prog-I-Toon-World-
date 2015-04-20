import java.util.Scanner;
class Variavel{ 
	
	private Inteiro vInteiro[];
	private Doublee vDouble[];
	private Stringg vString[];

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
	
	public void CriaVariavel(String l, Variavel V){
		if(l.contains("String")){
			CriaVariavel_String(l);
		} else if(l.contains("Int")){
			CriaVariavel_Inteiro(l, V);
		} else if(l.contains("Double")){
			CriaVariavel_Double(l, V);
		} else if(l.contains("ler")){
			ler_valor(l, V);
		} else if(l.contains("<)")){
			Atribuicao(l, V);
		}
	}
	
	public void ler_valor(String l, Variavel V){
		int w=0;
		String Nome = "";
		while(l.charAt(w) != '#') w++;
		w++;
		while(l.charAt(w) != ')'){
			if(l.charAt(w) != ' ') Nome += l.charAt(w);
			else if (l.charAt(w) == ' ' && (l.charAt(w+1) == ' ' || l.charAt(w+1) == ')'));
			else Nome += " ";
			w++;
		}
		Scanner scanner = new Scanner(System.in);
		double valor = scanner.nextDouble();
		Pesquisar(Nome,String.valueOf(valor));
	}
	
	public void Atribuicao(String l, Variavel V){
		String Nome = "";
		int x = 0;
		while(l.charAt(x) != '<'){
			if(l.charAt(x) != ' ') Nome += l.charAt(x);
			else if (l.charAt(x) == ' ' && (l.charAt(x+1) == ' ' || l.charAt(x+1) == '<'));
			else Nome += " ";
			x++;
		}
		Pesquisar(Nome,Pegar_Valor(l,V));
	}
		
	public String PESQ(String Nome){
		int w;
		for(w = 0; w < this.vDouble.length; w++){
			if(this.vDouble[w].nome.equals(Nome)){
				return String.valueOf(vDouble[w].valor);
			}
		}
		for(w = 0; w < this.vInteiro.length; w++){
			if(this.vInteiro[w].nome.equals(Nome)){
				return String.valueOf(vInteiro[w].valor);
			}
		}
		return Nome;
	}
	
	public void Pesquisar(String Nome, String Valor){
		int w;
		for(w = 0; w < this.vDouble.length; w++){
			if(this.vDouble[w].nome.equals(Nome)){
				vDouble[w].TransformaValor(Valor);
			}
		}
		for(w = 0; w < this.vInteiro.length; w++){
			if(this.vInteiro[w].nome.equals(Nome)){
				vInteiro[w].TransformaValor(Valor);
			}
		}
	}
	
	public void CriaVariavel_Double(String l, Variavel V){
		for(int w =0; w < this.vDouble.length; w++){
			if(this.vDouble[w].Vazio){	
				vDouble[w].SetVariavel_Double(Pegar_Nome(l),Pegar_Valor(l,V));
				break;
			}
		}
	}
	
	public void CriaVariavel_Inteiro(String l, Variavel V){
		for(int w =0; w < this.vInteiro.length; w++){
			if(this.vInteiro[w].Vazio){
				vInteiro[w].SetVariavel_Inteiro(Pegar_Nome(l),Pegar_Valor(l,V));
				break;
			}
		}
	}

	public void CriaVariavel_String(String l){
		int x = 0;
		for(int w =0; w < this.vString.length; w++){
			if(this.vString[w].Vazio){
				vString[w].ConcatenarNome(Pegar_Nome(l));
				while(l.charAt(x) != '"'){
					x++;
				}
				x++;
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
	
	public void Imprimir_Vetores(){
		System.out.println("=================================");
		System.out.println("        Vetor de String");
		System.out.println("=================================");
		for(int w = 0; w < vString.length; w++){
			if (!vString[w].Vazio){
				System.out.println( w + " <- Posicao ");
				System.out.println("             ->> Nome : " + vString[w].nome);
				System.out.println("             ->> Conteudo : " + vString[w].conteudo);
			}
		}
		System.out.println("=================================");
		System.out.println("        Vetor de Inteiros");
		System.out.println("=================================");
		for(int a = 0; a < vInteiro.length; a++){
			if (!vInteiro[a].Vazio){
				System.out.println( a + " <- Posicao ");
				System.out.println("             ->> Nome : " + vInteiro[a].nome);
				System.out.println("             ->> Valor : " + vInteiro[a].valor);
			}
		}
		System.out.println("=================================");
		System.out.println("        Vetor de Double");
		System.out.println("=================================");
		for(int y = 0; y < vDouble.length; y++){
			if (!vDouble[y].Vazio){
				System.out.println( y + " <- Posicao ");
				System.out.println("             ->> Nome : " + vDouble[y].nome);
				System.out.println("             ->> Valor : " + vDouble[y].valor);
			}
		}
	}
	
	public int PosicionaX(int x, String l){
		while(l.charAt(x) != ' '){
			x++; 
			if(l.charAt(x - 1) == ')') break;
		}// Sai com X valendo ESPAÇO;
		while(l.charAt(x) == ' ') x++; // Agora X vale o primeiro caracter
		return x;
	}
	
	public String Pegar_Nome(String l){
		int x = 0;
		String Nome = "";
		x = PosicionaX(x,l);
		while(l.charAt(x) != '<'){
			if(l.charAt(x) != ' ') Nome += l.charAt(x);
			else if (l.charAt(x) == ' ' && (l.charAt(x+1) == ' ' || l.charAt(x+1) == '<')) ;
			else Nome += " ";
			x++;
		}
		return Nome;
	}
	
	public String Pegar_Valor(String l, Variavel V){
		int x = 0;
		String Valor = "";
		Operacao Exp = new Operacao();
		while(l.charAt(x)!= '<') x++;
		x = PosicionaX(x,l);
		if(Exp.testeExpressao(l)){
			Exp.Expressoes(l,V);
			Valor = String.valueOf(Exp.Result);
		} else {
			while(l.charAt(x) != '?'){
				if(l.charAt(x) == ' ') break; 
				Valor += l.charAt(x);
				x++;
			}
		}
		return Valor;
	}
	
}