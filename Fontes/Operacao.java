/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 1.0
 * Descrição: Classe Main da Toon World, linguagem baseada em java.*/
class Operacao{
	
	private Interpretador Inter;
	
	public Operacao(Interpretador I){
		Inter = I;
	}
	
	public double OperacaoAritmetica(char Token, double A, double B){
		if(Token == '+') return A + B;
		else if(Token == '-') return A - B;
		else if(Token == '*') return A * B;
		else if(Token == '/') return A / B;
		else if(Token == '%') return A % B;
		return 0;
	}
	
	public char TokensAritmeticos(String l){
		for(int x = 0; x < l.length(); x++ ){
			if(l.charAt(x) == '+') return l.charAt(x);
			else if(l.charAt(x) == '-') return l.charAt(x);
			else if(l.charAt(x) == '*') return l.charAt(x);
			else if(l.charAt(x) == '/') return l.charAt(x);
			else if(l.charAt(x) == '%') return l.charAt(x);
		}
		return '0';
	}
	
	public String TokenComparativos(String l){
		String Token = "";
		for(int x = 0; x < l.length(); x++ ){
			if(l.charAt(x) == '>' && l.charAt(x+1) == '>'){ //MAIOR
				Token += l.charAt(x);
				Token += l.charAt(x+1);
				return Token;
			} else if(l.charAt(x) == '<' && l.charAt(x+1) == '<'){ //MENOR
				Token += l.charAt(x);
				Token += l.charAt(x+1);
				return Token;
			} else if(l.charAt(x) == '>' && l.charAt(x+1) == '|'){ // MAIOR-IGUAL 
				Token += l.charAt(x);
				Token += l.charAt(x+1);
				return Token;
			} else if(l.charAt(x) == '<' && l.charAt(x+1) == '|'){ // MENOR-IGUAL
				Token += l.charAt(x);
				Token += l.charAt(x+1);
				return Token;
			} else if(l.charAt(x) == '|' && l.charAt(x+1) == '=' && l.charAt(x+2) == '|'){ // IGUAL
				Token += l.charAt(x);
				Token += l.charAt(x+1);
				Token += l.charAt(x+2);
				return Token;
			} else if(l.charAt(x) == '=' && l.charAt(x+1) == '|' && l.charAt(x+2) == '='){ // DIFERENTE
				Token += l.charAt(x);
				Token += l.charAt(x+1);
				Token += l.charAt(x+2);
				return Token;
			}
		}
		return Token;
	}
	
	public boolean VereficaComparacao(String l, double A, double B){
		for(int x = 0; x < l.length(); x++ ){
			if(l.charAt(x) == '>' && l.charAt(x+1) == '>'){ //MAIOR
				if(A > B) return true;
			} else if(l.charAt(x) == '<' && l.charAt(x+1) == '<'){ //MENOR
				if(A < B) return true;
			} else if(l.charAt(x) == '>' && l.charAt(x+1) == '|'){ // MAIOR-IGUAL 
				if(A >= B) return true;
			} else if(l.charAt(x) == '<' && l.charAt(x+1) == '|'){ // MENOR-IGUAL
				if(A <= B) return true;
			} else if(l.charAt(x) == '|' && l.charAt(x+1) == '=' && l.charAt(x+2) == '|'){ // IGUAL
				if(A == B) return true;
			} else if(l.charAt(x) == '=' && l.charAt(x+1) == '|' && l.charAt(x+2) == '='){ // DIFERENTE
				if(A != B) return true;
			}
		}
		return false;
	}
	
	public double TransformaEmDouble(String Valor){
		return Double.valueOf(Inter.LocalizarVariavel(Valor)).doubleValue();
	}
	
	public String ExpComComparacao(String l){
		if(TokensAritmeticos(l) != '0') return String.valueOf(ExpressoesAritmeticas(l));
		return l;
	}
	
	public boolean ExpressoesComparacao(String l){
		String Teste = "";
		int x = 0, ponto = 2;
		Teste = l.replaceAll("[  \\?]", ""); // Remove os espaços ;
		Teste = Teste.replaceAll("[\\<\\>\\=\\|]", ".");
		Teste = Teste.replaceAll("\\}\\[", "");
		if(Teste.contains("...")) ponto++;
		String[] vet = Teste.split("\\.");
		return VereficaComparacao(l,TransformaEmDouble(ExpComComparacao(vet[0])),TransformaEmDouble(ExpComComparacao(vet[ponto])));
	}
	
	public double ExpressoesAritmeticas(String l){
		l = l.replaceAll(" ", "");
		String[] vet = l.split("[^a-z,^0-9,^A-Z]");
		return OperacaoAritmetica(TokensAritmeticos(l),TransformaEmDouble(vet[0]),TransformaEmDouble(vet[1]));
	}
}