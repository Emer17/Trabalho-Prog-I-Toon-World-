import java.util.Scanner;
class Comandos{
	public void ComandoDeTela(String l, Variavel V[]){
		int x = 0;
		if(l.contains("print")){
			String ImprimirLinha = "";
			while(l.charAt(x) != '{') x++;
			x++;
			ImprimirLinha += print(l,V,x,ImprimirLinha);
			System.out.println(ImprimirLinha);
		}
		if(l.contains("ler")){
			Scanf(l,V,x);
		}
	}
	
	public String print(String l, Variavel V[], int x, String ImprimirLinha){
		String Conteudo = "";
		Variavel Var = new Variavel();
		boolean aspas = false;
		if(l.charAt(x) == '"'){
			aspas = true;
			x++;
		}
		while(l.charAt(x) != '}'){
			if(l.charAt(x) == '+' || l.charAt(x) == '}') break;
			else if(l.charAt(x) == '"');
			else if(l.charAt(x) == ' '){
				if(aspas) Conteudo += " ";
			} else Conteudo += l.charAt(x);
			x++;
		}
		ImprimirLinha += Var.LocalizarVariavel(Conteudo,V);
		if(l.charAt(x) == '+') ImprimirLinha = print(l,V,x+1,ImprimirLinha);
		return ImprimirLinha;
	}
	
	public void Scanf(String l, Variavel V[], int x){//ler(#NomeVariavel)
		Variavel Var = new Variavel();
		while(l.charAt(x) != '#') x++;
		x++;
		String Nome = "";
		while(l.charAt(x) != ')'){
			if(l.charAt(x) != ' ') Nome += l.charAt(x);
			else if (l.charAt(x) == ' ' && (l.charAt(x+1) == ' ' || l.charAt(x+1) == ')'));
			else Nome += " ";
			x++;
		}
		System.out.println("Lendo para variavel ( " + Nome + " ) :");
		Scanner scanner = new Scanner(System.in);
		String valor = scanner.nextLine();
		Var.Pesquisar(Nome,valor,V);
	}
}