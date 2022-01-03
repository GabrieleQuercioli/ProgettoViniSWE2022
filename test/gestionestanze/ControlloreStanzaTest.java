package gestionestanze;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ControlloreStanzaTest {

    private ControlloreStanza cs;
    private Stanza s;

    @Before
    public void setUp() {
        cs = new ControlloreStanza();
        s = new Stanza(0);
        s.varia();
    }

    @Test (expected = NullPointerException.class)
    public void nullObservableUpdate() {
        cs.update(null, null);
    }

    @Test
    public void testSetStrategy() {
        //Deve entrare nel primo IF se è solo la temperatura a sforare, altrimenti deve entrare nel secondo
        //se sballa l'umidità, strategy sempre = a StrategiaUmidita
        if ((s.getTemperatura() < 6 || s.getTemperatura() >= 16) && (s.getUmidita() >= 60 && s.getUmidita() < 85)) {
            cs.update(s,null);
            assertTrue(cs.getStrategy() instanceof StrategiaTemperatura);
        }
        else if (s.getUmidita() < 60 || s.getUmidita() >= 85) {
            cs.update(s,null);
            assertTrue(cs.getStrategy() instanceof StrategiaUmidita);
        }
        else {
            assertFalse(cs.getStrategy() instanceof StrategiaTemperatura);
            assertFalse(cs.getStrategy() instanceof StrategiaUmidita);
        }
    }
}