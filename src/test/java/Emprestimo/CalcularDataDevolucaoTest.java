package Emprestimo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.example.calculodatadevolucao.Emprestimo;
import com.example.calculodatadevolucao.Item;
import com.example.calculodatadevolucao.Livro;

import junit.framework.TestCase;

public class CalcularDataDevolucaoTest extends TestCase {

    @Test(expected = Error.class)
    public void testCalculaDataDevolucaoSemItens() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.CalculaDataDevolucao(); // Deve lançar exceção porque não há itens
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculaDataDevolucaoComItensExcedidos() {
        try {
            Emprestimo emprestimo = new Emprestimo();
            for (int j = 1; j <= 6; j++) {
                emprestimo.i.add(new Item(new Livro(j))); // Adiciona 6 itens para ultrapassar o limite
            }
            emprestimo.CalculaDataDevolucao(); // Deve lançar exceção pelo limite excedido
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Numero máximo de itens excedido", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCalculaDataDevolucaoComUmItem() {
        Emprestimo emprestimo = new Emprestimo();
        Item item = new Item(new Livro(1)); // Prazo de devolução: 1 + 1 = 2 dias
        emprestimo.i.add(item);

        Date dataDevolucao = emprestimo.CalculaDataDevolucao();
        
        // Data esperada: Data de hoje + 2 dias
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 2);
        Date expectedDate = calendar.getTime();

        Assert.assertEquals(expectedDate, dataDevolucao);
    }

    @Test
    public void testCalculaDataDevolucaoComDoisItens() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.i.add(new Item(new Livro(1))); // Prazo de devolução: 2 dias
        emprestimo.i.add(new Item(new Livro(2))); // Prazo de devolução: 3 dias

        Date dataDevolucao = emprestimo.CalculaDataDevolucao();

        // Data esperada: hoje + 3 dias (data mais distante sem incremento)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 3);
        Date expectedDate = calendar.getTime();

        Assert.assertEquals(expectedDate, dataDevolucao);
    }

    @Test
    public void testCalculaDataDevolucaoComMaisDeDoisItens() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.i.add(new Item(new Livro(1))); // Prazo: 2 dias
        emprestimo.i.add(new Item(new Livro(2))); // Prazo: 3 dias
        emprestimo.i.add(new Item(new Livro(3))); // Prazo: 4 dias

        Date dataDevolucao = emprestimo.CalculaDataDevolucao();

        // Data esperada: data mais distante (hoje + 4 dias) + incremento (1 * 2 dias) = hoje + 6 dias
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 6);
        Date expectedDate = calendar.getTime();

        Assert.assertEquals(expectedDate, dataDevolucao);
    }

    @Test
    public void testCalculaDataDevolucaoComQuatroItens() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.i.add(new Item(new Livro(1))); // Prazo: 2 dias
        emprestimo.i.add(new Item(new Livro(2))); // Prazo: 3 dias
        emprestimo.i.add(new Item(new Livro(3))); // Prazo: 4 dias
        emprestimo.i.add(new Item(new Livro(4))); // Prazo: 5 dias

        Date dataDevolucao = emprestimo.CalculaDataDevolucao();

        // Data esperada: hoje + 5 dias (maior prazo) + 4 dias de incremento = hoje + 9 dias
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 9);
        Date expectedDate = calendar.getTime();

        Assert.assertEquals(expectedDate.toString(), dataDevolucao.toString());
    }
}
