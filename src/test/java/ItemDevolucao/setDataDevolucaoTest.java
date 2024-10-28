package ItemDevolucao;

import java.util.Date;

import org.junit.Test;

import com.example.calculodatadevolucao.Item;
import com.example.calculodatadevolucao.Livro;

import junit.framework.TestCase;

public class setDataDevolucaoTest extends TestCase {
    
    @Test
    public void testShouldBeSetDataDevolucao(){
        Date currentDate = new Date();
        Item item = new Item(new Livro(1));
        item.setDataDevolucao(currentDate);
        assertEquals(currentDate, item.getDataDevolucao());
    }

    @Test
    public void testShouldNotDataDevolucaoBeSetWhenIsNull(){
        Item item = new Item(new Livro(1));
        item.setDataDevolucao(null);
        assertNull(item.getDataDevolucao());
    }

}
