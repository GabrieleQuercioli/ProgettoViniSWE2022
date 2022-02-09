package compravendita;


import org.junit.Before;
import org.junit.Test;
import vino.Vino;
import vino.VinoBianco;
import vino.VinoRosso;
import javax.management.InvalidAttributeValueException;


import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class StanzaTest {
    private Stanza s;
    private Cantina c = Cantina.getCantina(4);


    @Before
    public void initialize() {
        s = c.getStanza(0);
        s.resetStanza();
    }


    @Test
    public void testCorreggiTemperatura() {
        s.varia();
        s.correggiTemperatura();
        assertTrue(s.getTemperatura() >= 6 && s.getTemperatura() < 16);
    }

    @Test
    public void testCorreggiUmidita() {
        s.varia();
        s.correggiUmidita();
        assertTrue(s.getUmidita() >= 60 && s.getUmidita() < 85);
    }


    @Test ( expected = IllegalArgumentException.class )
    public void nullAddVino() {
        s.addVino(null);
    }


    //Verifico che un riferimento a un vino non possa essere aggiunto in una stanza della cantina se in
    //precedenza era già stato aggiunto un altro riferimento sempre allo stesso vino
    @Test ( expected = IllegalArgumentException.class )
    public void sameAddVino() throws InvalidAttributeValueException, FileNotFoundException {
        Vino v = new VinoRosso("BolgheriSassicaia");
        c.getStanza(0).addVino(v);//SOLO QUA serve il riferimento alla cantina
        Vino w = v;
        c.getStanza(0).addVino(w);
    }


    //Testa la creazione di una nuova botte in una stanza nel caso non ci siano botti vuote
    @Test
    public void newBotteAddVino() throws InvalidAttributeValueException, FileNotFoundException {
        for (int i=0; i< 3; i++)
            s.addVino(new VinoRosso("BolgheriSassicaia"));
        Vino v = new VinoBianco("Chamonix");
        s.addVino(v);
        assertEquals(4,s.getHashMapSize());
        assertEquals(v,s.getVino(3));
    }

    //Testa l'inserimento di un nuovo vino nella prima botte vuota disponibile in una stanza
    @Test
    public void emptyBotteAddVino() throws InvalidAttributeValueException, FileNotFoundException {
        s.addVino(new VinoRosso("BolgheriSassicaia"));
        Vino oldVino = new VinoBianco("Chamonix");
        s.addVino(oldVino);
        s.addVino(new VinoRosso("BolgheriSassicaia"));
        s.removeVino(oldVino);
        Vino newVino = new VinoRosso("Morellino");
        s.addVino(newVino);
        assertEquals(newVino,s.getVino(1));
    }


    @Test ( expected = IllegalArgumentException.class )
    public void nullRemoveVino() {
        s.removeVino(null);
    }

    //Controllo che la remove() di un riferimento a un vino che non ho mai aggiunto in nessuna stanza lanci eccezione
    @Test ( expected = IllegalArgumentException.class )
    public void notFoundRemoveVino() throws InvalidAttributeValueException, FileNotFoundException {
        s.addVino(new VinoRosso("BolgheriSassicaia"));
        s.addVino(new VinoBianco("Chamonix"));
        Vino v = new VinoBianco("Bastianich");
        s.removeVino(v);
    }

    //Aggiungo 3 vini in una stanza e poi rimuovo quello nella botte nr 1 e controllo che effettivamente
    //dopo la remove() se vado a recuperare il riferimento al vino della botte 1 mi restituisca null
    @Test
    public void testRemoveVino() throws InvalidAttributeValueException, FileNotFoundException {
        s.addVino(new VinoRosso("BolgheriSassicaia"));
        Vino v = new VinoBianco("Bastianich");
        s.addVino(v);
        s.addVino(new VinoBianco("Chamonix"));
        s.removeVino(v);
        assertNull(s.getVino(1));
    }
}
