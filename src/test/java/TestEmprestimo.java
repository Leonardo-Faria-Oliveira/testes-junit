import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.calculodatadevolucao.Emprestimo;
import com.example.calculodatadevolucao.Item;
import com.example.calculodatadevolucao.Livro;

import org.junit.Test;
import junit.framework.TestCase;

public class TestEmprestimo extends TestCase{

    @Test
	public void testEmpresta1LivroNaoAplicaDataExtra() {
		Livro l = new Livro(1);
		List<Livro> livros = new ArrayList<Livro>();  
		livros.add(l);
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.emprestar(livros);
		
		Item ultimoItem = emprestimo.item.get(0);

		Date now = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 2);  
		assertEquals(ultimoItem.getDataDevolucao().getDay(), calendar.getTime().getDay());
	}

	@Test
	public void testEmpresta2LivrosNaoAplicaDataExtra() {
		List<Livro> livros = new ArrayList<Livro>();  
        for(int i = 0; i < 2; i++) {
        	Livro l = new Livro(i);
        	livros.add(l);
        }
        
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.emprestar(livros);
		Item ultimoItem = emprestimo.item.get(1);

		Date now = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 2);  // 2 days
		assertEquals(ultimoItem.getDataDevolucao().getDay(), calendar.getTime().getDay());
	}

	@Test
	public void testEmpresta3LivrosAplicaDataExtra() {
		List<Livro> livros = new ArrayList<Livro>();  
        for(int i = 0; i < 3; i++) {
        	Livro l = new Livro(i);
        	livros.add(l);
        }
        
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.emprestar(livros);
		Item ultimoItem = emprestimo.item.get(2);

		Date now = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 5); 
		assertEquals(ultimoItem.getDataDevolucao().getDay(), calendar.getTime().getDay());
	}
	
	@Test
	public void testEmpresta4LivrosAplicaDataExtra() {
		List<Livro> livros = new ArrayList<Livro>();  
        for(int i = 0; i < 4; i++) {
        	Livro l = new Livro(i);
        	livros.add(l);
        }
        
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.emprestar(livros);
		Item ultimoItem = emprestimo.item.get(3);

		Date now = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 8);
		assertEquals(ultimoItem.getDataDevolucao().getDay(), calendar.getTime().getDay());
	}
	
	@Test
	public void testEmpresta5LivrosAplicaDataExtra() {
		List<Livro> livros = new ArrayList<Livro>();  
        for(int i = 0; i < 5; i++) {
        	Livro l = new Livro(i);
        	livros.add(l);
        }
        
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.emprestar(livros);
		Item ultimoItem = emprestimo.item.get(4);

		Date now = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 11);
		assertEquals(ultimoItem.getDataDevolucao().getDay(), calendar.getTime().getDay());
	}
	
	@Test
	public void testNaoEmprestaMaisDe5Livros() {
		List<Livro> livros = new ArrayList<Livro>();  
        for(int i=0; i < 6;i++) {
        	Livro l = new Livro(i);
        	livros.add(l);
        }
		Emprestimo emprestimo = new Emprestimo();
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			emprestimo.emprestar(livros);
		});
		assertEquals("O limite maximo de livros que pode ser emprestado e 5", thrown.getMessage());
	}

}