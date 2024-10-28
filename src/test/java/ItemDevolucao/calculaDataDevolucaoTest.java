package ItemDevolucao;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.example.calculodatadevolucao.Item;
import com.example.calculodatadevolucao.Livro;

public class calculaDataDevolucaoTest extends TestCase {

    @Test
    public void testShouldBeCalculaDataDevolucao(){
        int code = 1;
        Item item = new Item(new Livro(code));
        item.calculaDataDevolucao(new Date());
        Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.add(Calendar.DATE, code+1);
        Date expectedDate = calendar.getTime();
        assertEquals(expectedDate.toString(), item.getDataDevolucao().toString());
    }

    @Test
    public void testShouldNotBeSetWhenIsNull(){
        int code = 1;
        Item item = new Item(new Livro(code));
        item.calculaDataDevolucao(null);
        assertNull(item.getDataDevolucao().toString());
    }

    
}
