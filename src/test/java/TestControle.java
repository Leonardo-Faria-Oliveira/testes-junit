import static org.junit.Assert.*;

import org.junit.Test;

import com.example.calculodatadevolucao.Aluno;
import com.example.calculodatadevolucao.Controle;
import com.example.calculodatadevolucao.Livro;
import junit.framework.TestCase;



public class TestControle {

	@Test
	public void testNaoEmprestaLivrosParaAlunoNaoVerificado() {
		String ra = "10";
		Aluno aluno = new Aluno(ra);
		assertFalse(aluno.verficaAluno());

		int[] prazos = {1,2,3};
		Controle c = new Controle();
		boolean retorno = c.emprestar(ra, prazos, prazos.length);
		assertFalse(retorno);
	}

	@Test
	public void testNaoEmprestaLivrosParaAlunoComDebito() {
		String ra = "4";
		Aluno aluno = new Aluno(ra);
		assertFalse(aluno.verificaDebito());

		int[] prazos = {1,2,3};
		Controle c = new Controle();
		boolean retorno = c.emprestar(ra, prazos, prazos.length);
		assertFalse(retorno);
	}


	@Test
	public void testEmprestaLivros() {
		String ra = "123";
		int[] prazos = {1,3,5,6};
		Controle c = new Controle();
		boolean retorno = c.emprestar(ra, prazos, prazos.length);
		assertTrue(retorno);
	}
}