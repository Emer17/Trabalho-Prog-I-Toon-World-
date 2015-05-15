/* Nome : Interpretador.java
 * Autores: Emerson Martins  <emer-martins@hotmail.com>
 * 			Leonardo Vargas  <leu1607@hotmail.com>
 * Versão: 5.0
 * Descrição: Classe LogErro da Toon World, linguagem baseada em java.
 * 
 * Esta classe é responsavel por verificar ALGUNS erros na sintaxe do programa escrito.*/
 
class LogErro{
	 
	 public boolean VerificaErros(String l[]){
		 int x = 0,num = 0;
		 while(x < l.length){
			   if(l[x] != null && !l[x].isEmpty()){
				 if(l[x].contains("GIRAGIRA") || l[x].contains("VOOR") || l[x].contains("WENN") || l[x].contains("SONST")){ //esta condiçao verifica se há falta de "fecha escopo"
					if(!l[x].contains("[")){
						System.out.println("ERRO: Falta '[' na linha " + (x+1));
						return false;
					}else{
						num++;
					}
					if(l[x].contains("]")){
						num--;
					}
					if(l[x].contains("VOOR") && l[x].contains("+?") && !l[x].contains("++?") || l[x].contains("VOOR") && l[x].contains("-?") && !l[x].contains("--?")){//verifica se a atribuição da variavel no VOOR esta sendo feita corretamente
						System.out.println("ERRO: Comando de atribuiçao incorreto na linha " + (x+1));
						return false;
					}
					if(!l[x].contains("SONST") && !l[x].contains("<<") && !l[x].contains(">>") && !l[x].contains("<|") && !l[x].contains(">|") && !l[x].contains("==") && !l[x].contains("|=")){//verifica se os Tokens de comparaçao existem na linha
						System.out.println("ERRO: Falta simbolo de verificaçao na linha " + (x+1));
						return false;
					}
					if(!l[x].contains("SONST") && !l[x].contains("{") || !l[x].contains("SONST") && !l[x].contains("}")){ //verifica se o "inicio/fim" de condição existe na linha
						System.out.println("ERRO: Falta simbolo de inicio/fim de expressão na linha " + (x+1));
						return false;
					}
				}else if(l[x].contains("PRINT") || l[x].contains("DIZPRAMIM")){ //verifica se o "inicio/fim" de condição existe na linha
					if(!l[x].contains("{") || !l[x].contains("}")){
						System.out.println("ERRO: Falta simbolo de inicio/fim de expressão na linha " + (x+1));
						return false;
					}
					if(!l[x].contains("?")){//cai aqui se for uma linha "normal"
						System.out.println("ERRO: Falta '?' na linha " + (x+1));
						return false;
					}					
				}else if(l[x].contains("+?") && !l[x].contains("++?") || l[x].contains("-?") && !l[x].contains("--?")){ //verifica se a atribuição da variavel esta sendo feita corretamente
						System.out.println("ERRO: Comando de atribuiçao incorreto na linha " + (x+1));
						return false;
				}else if(l[x].contains("]")){ //contagem de ']'
					num--;
				}else{
					if(!l[x].contains("?")){//cai aqui se for uma linha "normal"
						System.out.println("ERRO: Falta '?' na linha " + (x+1));
						return false;
					}
				}
			}
			x++;
		}
		if(num==0) return true; //verifica se todos os escopos abertos foram devidamente fechados.
		else System.out.println("ERRO: Falta ']' em alguma linha! Favor corrigir. ");
		return false;
	 }
 }
