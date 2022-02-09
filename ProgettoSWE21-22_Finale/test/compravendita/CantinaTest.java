package compravendita;

import input_output.EditorODS;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import vino.Vino;
import vino.VinoBianco;
import vino.VinoRosso;
import static org.junit.Assert.*;


import javax.management.InvalidAttributeValueException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CantinaTest {

    private Cantina c;


    @Test( expected = Error.class )
    public void anEmptyGetCantina() {//è fondamentale rimuovere tutti i vini dalla cantina alla fine di ogni metodo
        c = Cantina.getCantina(0);
    }

    @Test
    public void testGetStanza() throws InvalidAttributeValueException, FileNotFoundException {
        c = Cantina.getCantina(4);
        Vino v = new VinoRosso("Morellino");
        c.getStanza(0).addVino(v);
        Stanza s = c.getStanza(v);
        assertEquals(0, s.getNumStanza());
        assertNotEquals(1, s.getNumStanza());
        c.getStanza(0).removeVino(v);
    }

    @Test
    public void invalidGetStanza() throws FileNotFoundException, InvalidAttributeValueException {
        c = Cantina.getCantina(4);
        Vino v = new VinoRosso("Morellino");
        assertNull(c.getStanza(v));
    }

    @Test (expected = IllegalArgumentException.class)
    public void vendiVinoInesistente() {
        c = Cantina.getCantina(4);
        c.vendi("NonInCantina", 50);
    }

    @Test
    public void vendiTest() throws InvalidAttributeValueException, FileNotFoundException {
        c = Cantina.getCantina(4);
        Vino v = new VinoRosso("Morellino");
        c.getStanza(0).addVino(v);
        float[] acquisto1 = c.vendi("Morellino", 60);
        float prezzoAspettato = Float.parseFloat(v.getSt().getPrezzoL());
        assertEquals(prezzoAspettato*60, acquisto1[0], 0);
        assertEquals(60, acquisto1[1], 0);

        Botte botte1 = c.getStanza(v).getBotte(v);
        assertEquals(40, botte1.getCapacita(), 0);
        float[] acquisto2 = c.vendi("Morellino", 50);
        assertEquals(40, acquisto2[1], 0);
        assertNull(c.getStanza(v));
    }

    @Test
    public void testMostraCatalogo() throws InvalidAttributeValueException, EmptyCatalogoException, FileNotFoundException {
        Cantina c = Cantina.getCantina(4);
        Vino v = new VinoRosso("Morellino");
        Vino w = new VinoBianco("Chamonix");
        c.getStanza(0).addVino(v);
        c.getStanza(0).addVino(w);

        c.mostraCatalogo();
        ArrayList<String[]> infoVini1 = EditorODS.leggiODS(new File("files/Catalogo0.ods"));
        assertEquals(infoVini1.get(0)[1], v.getNome());

        c.getStanza(0).removeVino(v);

        c.mostraCatalogo();
        ArrayList<String[]> infoVini2 = EditorODS.leggiODS(new File("files/Catalogo1.ods"));
        assertNull(c.getStanza(0).getVino(0));
        assertNotEquals(infoVini2.get(0)[1], v.getNome());

        c.getStanza(0).removeVino(w);
    }

    @Test ( expected = EmptyCatalogoException.class)
    public void emptyMostraCatalogo() throws InvalidAttributeValueException, EmptyCatalogoException {
        Cantina c = Cantina.getCantina(4);
        c.mostraCatalogo();
    }

    //Testa il corretto prezzo finale nel caso di sconto (quantità richiesta >=10) o no
    @Test
    public void testCalcolaSconto() {
        Cantina c = Cantina.getCantina(4);
        float prezzoFinale1 = c.calcolaSconto(100, 20);
        float prezzoFinale2 = c.calcolaSconto(100, 5);
        assertEquals(95f, prezzoFinale1, 0);
        assertEquals(100f, prezzoFinale2, 0);
    }

}



