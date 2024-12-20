package com.example.calculodatadevolucao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Emprestimo {
	Date dataEmprestimo = new Date();
	Date dataPrevista = new Date();
	Date dataAux = new Date();
	String nome;
	public List<Item> item = new ArrayList<Item>();
    int emprestimo = 0;
	
	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	
    public boolean emprestar(List<Livro> livros) {

		if(livros == null) {
			throw new IllegalArgumentException("A lista de livros não pode ser nula");
		}

		if(livros.size() <= 0) {
			throw new IllegalArgumentException("A lista de livros não pode ser vazia");
		}

		if(livros.size() > 5) {
			throw new IllegalArgumentException("O limite máximo de livros que pode ser emprestado é 5");
		}
		

    	for(int i=0; i<livros.size();i++) {
    		item.add(new Item(livros.get(i)));
    		this.emprestimo += 1; 
    	}

    	CalculaDataDevolucao();
    	System.out.println("Numero de Livros Emprestados: " + this.emprestimo);
    	System.out.println("Data de Emprestimo: " + this.dataEmprestimo);
    	System.out.println("Data de Devolucao: " + this.dataPrevista);
    	return true;
	}
    
	private Date CalculaDataDevolucao() {   
		Date date = new Date();
		if(item.size() == 0) {
			throw new IllegalArgumentException("Nenhum livro emprestado");
		}
		if(item.size() > 5) {
			throw new IllegalArgumentException("O limite maximo de livros que pode ser emprestado e 5");
		}
		for(int j = 0; j < item.size(); j++) {   
			dataAux = item.get(j).calculaDataDevolucao(date);
		    if(dataPrevista.compareTo(dataAux) < 0)
			  dataPrevista = dataAux;
		}
		if(item.size() > 2) {
			int tam = item.size() - 2;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dataPrevista);
			calendar.add(Calendar.DATE, (tam * 2));
	        dataPrevista = calendar.getTime();
		}
		for(int j = 0 ; j < item.size(); j++)
			item.get(j).setDataDevolucao(dataPrevista);
		
		return dataPrevista;
	}
}