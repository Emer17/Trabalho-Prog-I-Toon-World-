/* Nome : Toon.java (representa Main.java)
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 5.0
 * Descrição: Classe Toon da Toon World, linguagem baseada em java.
 * 
 * Esta classe funciona como a "Main". Ela é responsavel pelo inicio da execução do programa.*/
 
import java.util.Scanner;
import java.io.*;

class Toon {
	
    public static void main(String args[]) throws Exception {
        File f;
        Scanner s;
        Interpretador b;
        String linhas[] = new String[2000];
        f = new File(args[0]);
        s = new Scanner(f);
        b = new Interpretador();
        int i = 0;
        while(s.hasNext()) {
            linhas[i] = s.nextLine();
            i++;
        }
		b.corrige(linhas);
    }
}
