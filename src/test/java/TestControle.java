import static org.junit.Assert.*;
import org.junit.Test;
import com.example.calculodatadevolucao.Aluno;
import com.example.calculodatadevolucao.Controle;

public class TestControle {

	@Test
    public void testNaoEmprestaLivrosParaAlunoNaoVerificado() {
        String ra = "10";
        Aluno aluno = new Aluno(ra);
        
        Boolean possuiDebito = aluno.verficaAluno();
        assertFalse("Aluno não deve estar verificado", possuiDebito);

        int[] prazos = {1, 2, 3};
        Controle c = new Controle();
        boolean retorno = c.emprestar(ra, prazos, prazos.length);

        assertFalse("Não deveria ser possível emprestar livros para aluno não verificado", retorno);
    }

    @Test
    public void testNaoEmprestaLivrosParaAlunoComDebito() {
        String ra = "5";
        Aluno aluno = new Aluno(ra);
        Boolean possuiDebito = aluno.verficaAluno();
        assertTrue("Aluno deveria ter débito", possuiDebito);

        int[] prazos = {1, 2, 3};
        Controle c = new Controle();
        boolean retorno = c.emprestar(ra, prazos, prazos.length);
		System.out.println("retorno: " + ra);
        assertFalse("Não deveria ser possível emprestar livros para aluno com débito", retorno);
    }

    @Test
    public void testPodeEmprestarLibrosSemDebitoEVerificado() {
        String ra = "4";
        int[] prazos = {1, 3, 5, 6};
        Controle c = new Controle();
        boolean retorno = c.emprestar(ra, prazos, prazos.length);

        assertTrue("Deveria ser possível emprestar livros para aluno sem débito e verificado", retorno);
    }

    @Test
    public void testNaoEmprestaMaisDeCincoLivros() {
        String ra = "999";
        int[] prazos = {1, 2, 3, 4, 5, 6};
        Controle c = new Controle();
        
        boolean retorno = c.emprestar(ra, prazos, prazos.length);
        
        assertFalse("Não deveria ser possível emprestar mais de 5 livros", retorno);
    }

    @Test
    public void testEmprestaLivrosParaDiversosAlunos() {
        String ra1 = "4";
        int[] prazos1 = {1, 3, 5};
        Controle c = new Controle();
        boolean retorno1 = c.emprestar(ra1, prazos1, prazos1.length);
        assertTrue("Deveria ser possível emprestar livros para aluno verificado e sem débito", retorno1);

        String ra2 = "456";
        int[] prazos2 = {2, 4};
        boolean retorno2 = c.emprestar(ra2, prazos2, prazos2.length);
        assertFalse("Não deveria ser possível emprestar livros para aluno com débito", retorno2);

        String ra3 = "789";
        int[] prazos3 = {1};
        boolean retorno3 = c.emprestar(ra3, prazos3, prazos3.length);
        assertFalse("Não deveria ser possível emprestar livros para aluno não verificado", retorno3);
    }

}