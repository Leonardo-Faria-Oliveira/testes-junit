package com.example.calculodatadevolucao;

public class Titulo {
int prazo;
public Titulo(int codigo)
{
	//D� o prazo de devolu��o como sendo o c�digo do livro mais 1
 	this.prazo = codigo+1;
}
public int getPrazo() {
	return prazo;
}

public void setPrazo(int prazo) {
	this.prazo = prazo;
}

}
