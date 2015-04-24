/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
class Interpretador {
    private String linhas[];

    public void interpreta(String l[]) {
        this.linhas = l;
		Operacao OP = new Operacao();
		Variavel V = new Variavel();
		Laco L = new Laco();
		Condicao C = new Condicao();
		for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
				//if(this.linhas[i].contains("four") || this.linhas[i].contains("if") || this.linhas[i].contains("while") || this.linhas[i].contains("else")){
				if(TesteIgnora(this.linhas,i)){
					int k = i;
					for(int a = i; a < this.linhas.length; a++) {
						if(this.linhas[a] != null){
							if(this.linhas[a].contains("]")) break;
							k++;
						}
					}
					i = k;
				} else {
					V.CriaVariavel(linhas[i], V);
					V.ModificacaoNaVariavel(linhas[i], V);
					C.print(linhas[i],V,OP);
				}
			}
		}
		C.executaIf(l,V,OP,0);
		U.four(V,OP,l,0);
		V.Imprimir_Vetores();
		L.executaWhile(l,V,OP,0);
	}
	
	public boolean TesteIgnora(String l[], int i){
		if (l[i].contains("four")) return true;
		else if (l[i].contains("if")) return true;
		else if (l[i].contains("while")) return true;
		else if (l[i].contains("else")) return true;
		else return false;
	}
	
	/*public int Ignora(String l[], int i){
		int k = i;
		int a = 0;
		for(a = i; a < l.length; a++) {
			if(l[a] != null){
				if(l[a].contains("]")) break;
				k++;
			}
		}
		i = k;
		return i;
	}*/
}
