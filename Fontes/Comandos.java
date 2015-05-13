/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
import java.util.Scanner;
class Comandos{
	
	private Interpretador Inter;
	
	public Comandos(Interpretador I){
		Inter = I;
	}
	
	public void Imprimir(String l){
		int x = 0;
		while(l.charAt(x) != '{') x++;
		x++;
		if(l.startsWith("println")) System.out.println(ConcatenarFrases(l,x,""));
		else System.out.print(ConcatenarFrases(l,x,""));
	}
	
	public String ConcatenarFrases(String l,int x, String ImprimirLinha){
		String Conteudo = "";
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
		ImprimirLinha += Inter.LocalizarVariavel(Conteudo);
		if(l.charAt(x) == '+') ImprimirLinha = ConcatenarFrases(l,x+1,ImprimirLinha);
		return ImprimirLinha;
	}
	
	public void Scanf(String l){//ler(#NomeVariavel)
		int x = 0;
		String Nome = "";
		Scanner scanner = new Scanner(System.in);
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
		Inter.Pesquisar(Nome,valor);
	}
}