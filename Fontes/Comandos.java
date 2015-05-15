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
			l = l.replaceAll("PRINTLN", "");// Retira a palavra PRINTLN.
			System.out.println(ConcatenarFrases(l));
		} else {
			l = l.replaceAll("PRINT", "");// Retira a palavra PRINT.
			System.out.print(ConcatenarFrases(l));
		}
	}
	
	public String ConcatenarFrases(String l){
		String ImprimirLinha = "";
		l = l.replaceAll("[\\{\\}\\?]", "");// Retira esses caracteres.
		String[] Imprimir = l.split("\\+");//Quebras os indices nos mais encontradas.
		for(int w = 0; w < Imprimir.length; w++){// Anda pelo vetor de nomes.
			if(!Imprimir[w].contains("\"")){// Entra se for uma variavel.
				Imprimir[w] = Imprimir[w].replaceAll(" ", "");// Retira os espaços.
				Imprimir[w] = Inter.LocalizarVariavel(Imprimir[w]);// Pesquisa ela e retorna seu valor.
			} else {
				Imprimir[w] = Imprimir[w].replaceAll("\"", "");// Se for uma frase so retira as aspas.
			}
			ImprimirLinha += Imprimir[w];
		}
		return ImprimirLinha;
	}
	
	public void ExecutaSCANF(String l){//DIZPRAMIM(#NomeVariavel)?
		int x = 0;
		String Nome = "";
		Scanner scanner = new Scanner(System.in);
		while(l.charAt(x) != '#') x++;// Vai ate o #.
		x++;// X esta valendo o proximo caracter.
		if(l.charAt(x) == ' ') x++;// Enquanto ser diferente de espaço
		while(l.charAt(x) != '}'){
			if(l.charAt(x) == ' ') break;
			Nome += l.charAt(x);
			x++;
		}
		String valor = scanner.nextLine();
		Inter.AlteraVariavel(Nome,valor);
	}
}