/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
class Interpretador {
    private String linhas[];

    public void interpreta(String l[]) {
        this.linhas = l;
		Condicao Con = new Condicao();		
		Operacao OP = new Operacao();
		Variavel V = new Variavel();
		Laco U = new Laco();
		Comandos C = new Comandos();
		for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
				i = U.four(V,OP,l[i],i,l,C);
				V.CriaVariavel(linhas[i], V);
				V.ModificacaoNaVariavel(linhas[i], V);
				C.ComandoDeTela(linhas[i],V);
			}
		}
		Con.executaIf(l,V,OP,Com,0);
		V.Imprimir_Vetores();
		L.executaWhile(l,V,OP,0);
	}
}
