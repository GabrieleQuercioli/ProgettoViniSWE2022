package cantina;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;
import vino.SchedaTecnica;
import vino.Vino;
import vino.VinoBianco;
import vino.VinoRosso;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)       //esegue i test in ordine lessico-grafico
public class CantinaTest {


    @Test(expected = Error.class)
    public void anEmptyGetCantina() {
        Cantina.getCantina(0);
    }

    //Cantina c = Cantina.getCantina(4);

    @Test(expected = IllegalArgumentException.class)
    public void nullAddVino() {
        Cantina c = Cantina.getCantina(4);
        c.addVino(null);
    }

    @Test(expected = NullPointerException.class)
    public void nullNameAddVino() {
        Cantina c = Cantina.getCantina(4);
        c.addVino(new VinoBianco(null, new SchedaTecnica(true, "prova", 1, "prova2", "prova3")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyNameAddVino() {
        Cantina c = Cantina.getCantina(4);
        c.addVino(new VinoRosso("", new SchedaTecnica(true, "prova", 1, "prova2", "prova3")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullSchedaTecnica() {
        Cantina c = Cantina.getCantina(4);
        c.addVino(new VinoBianco("BolgheriSassicaia", null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void sameAddVino() {
        Cantina c = Cantina.getCantina(4);
        Vino v = new VinoRosso("Morellino", new SchedaTecnica(true, "prova", 1, "prova2", "prova3"));
        Vino w = v;
        c.addVino(v);
        c.addVino(w);
    }

    @Test
    public void newBotteAddVino() {                         //Testa la creazione di una nuova Botte, nel caso
        Cantina c = Cantina.getCantina(4);                  //non ci siano botti vuote disponibili per mettere un vino
        for (int i = 0; i < 3; i++)
        {
            c.addVino(new VinoRosso("Morellino", new SchedaTecnica(true, "prova", 1, "prova2", "prova3")));
        }
        Vino v = new VinoBianco("Chamonix", new SchedaTecnica(true, "prova", 1, "prova2", "prova3"));
        c.addVino(v);
        Integer key = c.getKey(v);
        assertEquals( key.intValue(), c.getSizeHashMap()-1);   //dimostro che il vino è stato inserito in coda all'hashmap
        assertEquals(v, c.getVino(key));
    }

    @Test
    public void emptyBotteAddVino() {                               //Testa l'aggiunta di un nuovo vino in una botte che
        Cantina c = Cantina.getCantina(4);                          //era rimasta vuota
        c.addVino(new VinoRosso("Morellino", new SchedaTecnica(true, "prova", 1, "prova2", "prova3")));
        Vino oldVino = new VinoBianco("Chamonix", new SchedaTecnica(true, "prova", 1, "prova2", "prova3"));
        c.addVino(oldVino);
        c.addVino(new VinoRosso("Morellino", new SchedaTecnica(true, "prova", 1, "prova2", "prova3")));
        Integer key = c.getKey(oldVino);
        c.removeVino(oldVino);
        Vino newVino = new VinoRosso("Morellino", new SchedaTecnica(true, "prova", 1, "prova2", "prova3"));
        c.addVino(newVino);
        assertEquals(newVino, c.getVino(key));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullRemoveVino() {
        Cantina c = Cantina.getCantina(4);
        c.removeVino(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notFoundRemoveVino() {
        Cantina c = Cantina.getCantina(4);
        c.addVino(new VinoRosso("Morellino", new SchedaTecnica(true, "prova", 1, "prova2", "prova3")));
        c.addVino(new VinoBianco("Chamonix", new SchedaTecnica(true, "prova", 1, "prova2", "prova3")));
        Vino v = new VinoBianco("Bastianich", new SchedaTecnica(true, "prova", 1, "prova2", "prova3"));
        c.removeVino(v);
    }

    @Test
    public void testRemoveVino() {
        Cantina c = Cantina.getCantina(4);
        c.addVino(new VinoRosso("Morellino", new SchedaTecnica(true, "prova", 1, "prova2", "prova3")));
        Vino toRemove = new VinoBianco("Chamonix", new SchedaTecnica(true, "prova", 1, "prova2", "prova3"));
        c.addVino(toRemove);
        Integer key = c.getKey(toRemove);
        c.addVino(new VinoBianco("Bastianich", new SchedaTecnica(true, "prova", 1, "prova2", "prova3")));
        c.removeVino(toRemove);
        assertNull(c.getVino(key));
    }

}