package gestionestanze;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StanzaTest {

    private Stanza s;

    @Before
    public void setUp() {
        s = new Stanza(0);
    }

    @Test (expected = Error.class)
    public void negativeNumStanza() {
        new Stanza(-1);
    }

    //Non va testato perchè la funzione fa notify a prescindere
    /*@Test
    public void varia() {
    }*/

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
}