package com.example.calculodatadevolucao;

public class Main {

	public static void main(String[] args) {
		Emprestimo e = new Emprestimo();

		Livro l1 = new Livro(1);
		
		Livro l2 = new Livro(2);
		
		 Livro l3 = new Livro(3); 
		
		Item i1 = new Item(l1); 
		Item i2 = new Item(l2);
		Item i3 = new Item(l3);
		e.i.add(i1);
     	e.i.add(i2);
	    e.i.add(i3);
      
		try {
			System.out.print("Data de Empr�stimo: " + e.getDataEmprestimo());
			System.out.print("\nData de Devolu��o: " + e.CalculaDataDevolucao());		
		} catch (Error erro) {
			System.out.println(erro.getMessage());
		}


	}

}