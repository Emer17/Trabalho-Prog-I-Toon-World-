

compilar:
	javac Toon.java Interpretador.java Operacao.java Inteiro.java Stringg.java Doublee.java Condicao.java Comandos.java
	java Toon ./teste.toon

all: compilar
