/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
import java.util.Scanner;
class Comandos{
	public void Imprimir(String l, Variavel V[]){
		int x = 0;
		while(l.charAt(x) != '{') x++;
		x++;
		if(l.startsWith("println")) System.out.println(ConcatenarFrases(l,V,x,""));
		else System.out.print(ConcatenarFrases(l,V,x,""));
	}
	
	public String ConcatenarFrases(String l, Variavel V[], int x, String ImprimirLinha){
		String Conteudo = "";
		Variavel Var = new Variavel();
		boolean aspas = false;
		if(l.charAt(x) == '"'){
			aspas = true;
			x++;
		}
		while(l.charAt(x) != '}'){
			if(l.charAt(x) == '+') break;
			else if(l.charAt(x) == '"');
			else if(l.charAt(x) == ' '){
				if(aspas) Conteudo += " ";
			} else Conteudo += l.charAt(x);
			x++;
		}
		ImprimirLinha += Var.LocalizarVariavel(Conteudo,V);
		if(l.charAt(x) == '+') ImprimirLinha = ConcatenarFrases(l,V,x+1,ImprimirLinha);
		return ImprimirLinha;
	}
	
	public void Scanf(String l, Variavel V[]){//ler(#NomeVariavel)
		int x = 0;
		String Nome = "";
		Scanner scanner = new Scanner(System.in);
		Variavel Var = new Variavel();
		while(l.charAt(x) != '#') x++;
		x++;
		if(l.charAt(x) == ' ') x++;
		while(l.charAt(x) != '}'){
			if(l.charAt(x) == ' ') break;
			Nome += l.charAt(x);
			x++;
		}
		System.out.println("Lendo para variavel ( " + Nome + " ) :");
		String valor = scanner.nextLine();
		Var.Pesquisar(Nome,valor,V);
	}
}