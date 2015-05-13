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
				 if(l[x].contains("while") || l[x].contains("four") || l[x].contains("if") || l[x].contains("else")){
					if(!l[x].contains("[")){
						System.out.println("ERRO: Falta '[' na linha " + (x+1));
						return false;
					}else{
						num++;
					}
					if(l[x].contains("]")){
						num--;
					}
					if(l[x].contains("four") && l[x].contains("..?") && !l[x].contains("...?") || l[x].contains("four") && l[x].contains(",,?") && !l[x].contains(",,,?")){
						System.out.println("ERRO: Comando de atribuiçao incorreto na linha " + (x+1));
						return false;
					}
					if(!l[x].contains("else") && !l[x].contains("<<") && !l[x].contains(">>") && !l[x].contains("<|") && !l[x].contains(">|") && !l[x].contains("|=|") && !l[x].contains("=|=")){
						System.out.println("ERRO: Falta simbolo de verificaçao na linha " + (x+1));
						return false;
					}
					if(!l[x].contains("else") && !l[x].contains("{") || !l[x].contains("else") && !l[x].contains("}")){
						System.out.println("ERRO: Falta simbolo de inicio/fim de expressão na linha " + (x+1));
						return false;
					}
				}else if(l[x].contains("print")){
					if(!l[x].contains("{") || !l[x].contains("}")){
						System.out.println("ERRO: Falta simbolo de inicio/fim de expressão na linha " + (x+1));
						return false;
					}
				}else if(l[x].contains("..?") && !l[x].contains("...?") || l[x].contains(",,?") && !l[x].contains(",,,?")){
						System.out.println("ERRO: Comando de atribuiçao incorreto na linha " + (x+1));
						return false;
				}else if(l[x].contains("]")){
					num--;
				}else{
					if(!l[x].contains("?")){
						System.out.println("ERRO: Falta '?' na linha " + (x+1));
						return false;
					}
				}
			}
			x++;
		}
		if(num==0) return true;
		else System.out.println("ERRO: Falta ']' em alguma linha! Favor corrigir. ");
		return false;
	 }
 }