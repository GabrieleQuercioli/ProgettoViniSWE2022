package gestionestanze;

import compravendita.Cantina;
import compravendita.Stanza;
import org.junit.Before;
import org.junit.Test;
import vino.VinoBianco;
import vino.VinoRosso;


import static org.junit.Assert.*;

public class StrategiaTemperaturaTest {

    private StrategiaTemperatura st = new StrategiaTemperatura();
    private Cantina c = Cantina.getCantina(4);
    private Stanza s;


    @Before
    public void setUp() throws Exception {
        s = c.getStanza(0);
        s.addVino(new VinoBianco("Chamonix"));
        s.addVino(new VinoRosso("Morellino"));
        do {
            s.varia();
        } while (s.getTemperatura() >= -1 && s.getTemperatura() <= 22);
    }

    //Testa il caso catastrofico di una stanza (quando raggiunge temperature troppo estreme) e controlla che siano
    //state buttate tutte le botti che essa conteneva e che la sua temperatura sia stata riportata ad una valore normale
    @Test
    public void testGestisciStanza() {
        assertEquals(2, s.getHashMapSize());
        st.gestisciStanza(s);
        assertEquals(0, s.getHashMapSize());
        assertTrue(s.getTemperatura() >= 6);
        assertTrue(s.getTemperatura() < 16);
    }
}