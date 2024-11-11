package com.example.calculodatadevolucao;
import java.util.List;

public class Aluno {
String RA;

public Aluno(String RA) {
	super();
	//precisa ir no banco e criar o aluno
	this.RA = RA;
	
}

public String getRA() {
	return RA;
}

public void setRA(String RA) {
	this.RA = RA;
}

public boolean verficaAluno()
{   //Se o RA � null � retorna erro - m�todo aleat�rio
	if(this.RA.equals("10"))
	 return false;
else
	return true;
}

public boolean verificaDebito()
{       //instancia um objeto d�bito
	   Debito debito = new Debito( Integer.parseInt(this.RA)); 
	   return debito.verificaDebito();
}

//Metodo que delega a funcionalidade de emprestar para a classe emprestimo
public boolean emprestar(List<Livro> livros)
{   /* Aqui voc� deve intanciar um objeto emprestimo */
	 Emprestimo e = new Emprestimo();
	 return e.emprestar(livros);
	
	
}
}
