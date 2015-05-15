/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
- * Versão: 5.0
- * Descrição: Classe Comandos da Toon World, linguagem baseada em java.
- * 
- * Esta classe é responsavel pelos comandos "print" e "scanf" da linguagem.*/
import java.util.Scanner;
class Comandos{
	
	private Interpretador Inter;
	
	public Comandos(Interpretador I){
		Inter = I;
	}
	
	public void ExecutaPRINT(String l){
		if(l.startsWith("PRINTLN")){ 
			l = l.replaceAll("PRINTLN", "");
			System.out.println(ConcatenarFrases(l));
		} else {
			l = l.replaceAll("PRINT", "");
			System.out.print(ConcatenarFrases(l));
		}
	}
	
	public String ConcatenarFrases(String l){
		String ImprimirLinha = "";
		l = l.replaceAll("[\\{\\}\\?]", "");// Retira esses caracteres.
		String[] Imprimir = l.split("\\+");//Quebras os indices nos mais encontradas.
		for(int w = 0; w < Imprimir.length; w++){// Anda pelo vetor de nomes.
			if(!Imprimir[w].contains("\"")){
				Imprimir[w] = Imprimir[w].replaceAll(" ", "");
				Imprimir[w] = Inter.LocalizarVariavel(Imprimir[w]);
			} else {
				Imprimir[w] = Imprimir[w].replaceAll("\"", "");
			}
			ImprimirLinha += Imprimir[w];
		}
		return ImprimirLinha;
	}
	
	public void ExecutaSCANF(String l){//ler(#NomeVariavel)
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
		String valor = scanner.nextLine();
		Inter.AlteraVariavel(Nome,valor);
	}
}