import static org.junit.Assert.*;
import org.junit.Test;
import com.example.calculodatadevolucao.Emprestimo;
import com.example.calculodatadevolucao.Item;
import com.example.calculodatadevolucao.Livro;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestEmprestimo {

    // Método auxiliar para criar a lista de livros
    private List<Livro> criarLivros(int quantidade) {
        List<Livro> livros = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            livros.add(new Livro(i));
        }
        return livros;
    }

    // Método auxiliar para verificar a data de devolução
    private void verificarDataDevolucao(Item item, int diasEsperados) {
        Date now = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, diasEsperados);

        assertEquals("A data de devolução não corresponde à data esperada", 
                     item.getDataDevolucao().getDay(), calendar.getTime().getDay());
    }

    @Test
    public void testEmpresta1LivroNaoAplicaDataExtra() {
        List<Livro> livros = criarLivros(1);
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.emprestar(livros);
        int diasEsperados = 1;
        Item ultimoItem = emprestimo.item.get(0);
        verificarDataDevolucao(ultimoItem, diasEsperados);
    }

    @Test
    public void testEmpresta2LivrosNaoAplicaDataExtra() {
        List<Livro> livros = criarLivros(2);
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.emprestar(livros);
        
        Item ultimoItem = emprestimo.item.get(1);
        verificarDataDevolucao(ultimoItem, 2);
    }

    @Test
    public void testEmpresta3LivrosAplicaDataExtra() {
        List<Livro> livros = criarLivros(3);
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.emprestar(livros);
        
        Item ultimoItem = emprestimo.item.get(2);
        verificarDataDevolucao(ultimoItem, 5);
    }
    
    @Test
    public void testEmpresta4LivrosAplicaDataExtra() {
        List<Livro> livros = criarLivros(4);
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.emprestar(livros);
        
        Item ultimoItem = emprestimo.item.get(3);
        verificarDataDevolucao(ultimoItem, 8);
    }
    
    @Test
    public void testEmpresta5LivrosAplicaDataExtra() {
        List<Livro> livros = criarLivros(5);
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.emprestar(livros);
        
        Item ultimoItem = emprestimo.item.get(4);
        verificarDataDevolucao(ultimoItem, 11);
    }
    
    @Test
    public void testNaoEmprestaMaisDe5Livros() {
        List<Livro> livros = criarLivros(6);
        Emprestimo emprestimo = new Emprestimo();
        
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            emprestimo.emprestar(livros);
        });

        assertEquals("O limite máximo de livros que pode ser emprestado é 5", thrown.getMessage());
    }

    // Testando emprestar 0 livros
    @Test
    public void testNaoEmprestaZeroLivros() {
        List<Livro> livros = criarLivros(0); // Lista vazia
        Emprestimo emprestimo = new Emprestimo();
        
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            emprestimo.emprestar(livros);
        });

        assertEquals("A lista de livros não pode ser vazia", thrown.getMessage());
    }


    @Test
    public void testNaoEmprestaListaNula() {
        List<Livro> livros = null; 
        Emprestimo emprestimo = new Emprestimo();
        
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            emprestimo.emprestar(livros);
        });

        assertEquals("A lista de livros não pode ser nula", thrown.getMessage());
    }
    
    @Test
    public void testEmprestaLivrosComCenariosDiversos() {
        List<Livro> livros = criarLivros(2);
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.emprestar(livros);

        assertEquals("Deveriam ser 2 livros emprestados", 2, emprestimo.item.size());
        
        Item item = emprestimo.item.get(1);
        verificarDataDevolucao(item, 2);
    }
}
