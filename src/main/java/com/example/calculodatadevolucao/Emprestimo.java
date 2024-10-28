package com.example.calculodatadevolucao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Emprestimo {

	Date dataEmprestimo = new Date();
	/*metodo para gegar a variavel dataEmprestimo*/
	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	/*metodo para setar a variavel dataEmprestimo*/
	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	/*utilize essas v�riaveis para calcular a data final de devolu��o*/
        Date dataPrevista = new Date();
	Date data_aux = new Date();
	String nome;

        /*Cada Emprestimo � composto de v�rios itens*/
	public List<Item> i = new ArrayList<Item>();
	
	        //Metodo respons�vel por calcular a data de devolu��o
	public Date CalculaDataDevolucao()
	{   
        Date date = new Date();
		
		if(i.size() == 0){
			throw new IllegalArgumentException("Nenhum item adicionado ao emprestimo");
		}

		if(i.size() > 5){
			throw new IllegalArgumentException("Numero máximo de itens excedido");
		}

		for(int j=0;j<i.size();j++)
		{   data_aux = i.get(j).calculaDataDevolucao(date);
		    if( dataPrevista.compareTo(data_aux) < 0)
			  dataPrevista = data_aux;
		}
		if(i.size()>2)
		{
			int tam = i.size()-2;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dataPrevista);
			calendar.add(Calendar.DATE, (tam*2));
	        dataPrevista = calendar.getTime();
		}
		for(int j=0;j<i.size();j++)
			i.get(j).setDataDevolucao(dataPrevista);
		
		return dataPrevista; 
	}
	
}