package gestionevini;


import compravendita.Cantina;
import org.junit.Before;
import org.junit.Test;
import vino.Vino;
import vino.VinoRosso;

import javax.management.InvalidAttributeValueException;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class MonitorVinoTest {
    private MonitorVino mv;
    private Vino v;
    private Cantina c;


    @Before
    public void initialize() throws InvalidAttributeValueException, FileNotFoundException {
        mv = new MonitorVino();
        v = new VinoRosso("Morellino");
        c = Cantina.getCantina(4);//serve per Istanceof
        c.getStanza(0).addVino(v);
        v.addObserver(mv);
    }

    @Test ( expected = NullPointerException.class)
    public void nullObservableUpdate() {
        mv.update(null,new Float(2.2));
    }

    @Test ( expected = NullPointerException.class)
    public void nullParameterPushUpdate() {
        mv.update(v,null);
    }

    @Test ( expected = ClassCastException.class)
    public void wrongTypeParameterPushUpdate() {
        mv.update(v,new Integer(2));
    }


    @Test
    public void testSetStrategy() {
        do {
            v.varia();
        } while (v.getAnidrideSolforosa() <= 160 && v.getpH() >= 3.3 && v.getpH() <= 3.5 && v.getZuccheriRiduttori() <= 210
                && v.getGradoAlcolico() >= 10 && v.getGradoAlcolico() <= 16);
        if (v.getAnidrideSolforosa() > 160 || v.getpH() < 3.3 || v.getpH() > 3.5 || v.getZuccheriRiduttori() > 210
                || v.getGradoAlcolico() < 10 || v.getGradoAlcolico() > 16)
        {
        assertTrue(mv.getStrategy() instanceof StrategiaGenerale);
        } else {
            assertTrue(mv.getStrategy() instanceof StrategiaOssigeno);
        }
    }
}






