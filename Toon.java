/* Nome : Toon.java (representa Main.java)
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
 
import java.util.Scanner;
import java.io.*;

class Toon {
    /*public static void main(String args[]) throws Exception {
		File f; 
		Scanner s;
		Interpretador b;
		b = new Interpretador();
		String linhas[] = new String[2000];
		f = new File(args[0]);//args[0] é o arquivo a ser interpretado
		s = new Scanner(f); //scanner funcionara no arquivo
		
        while(s.hasNext()) { //copia todas as linhas do arquivo para linhas[i]
            linhas[i] = s.nextLine();
            i++;
        }
        
        b.interpreta(linhas); //inicia interpretador
        //String a = "e u+";
        //Operacoes x;
        //x = new Operacoes();
        //x.resolve(a);
	}
}*/
    public static void main(String args[]) throws Exception {
        File f;
        Scanner s;
        Interpretador b;
        String linhas[] = new String[2000]; // arquivo pode ter, no máximo, 2000 linhas.

        // args[0] conterá o caminho para o arquivo que serah interpretado.
        f = new File(args[0]);
        // Mandamos o Scanner ler a partir do arquivo.
        s = new Scanner(f);
        // Instanciamos o interpretador.
        b = new Interpretador();

        // Lemos todas as linhas do arquivo para dentro do
        // vetor "linhas".
        int i = 0;
        while(s.hasNext()) {
            linhas[i] = s.nextLine();
            i++;
        }

        // Inicializamos o interpretador com o vetor de linhas. A partir
        // desse ponto, o objeto "b" irá interpretar o código lido do arquivo.
        b.interpreta(linhas);
    }
}
