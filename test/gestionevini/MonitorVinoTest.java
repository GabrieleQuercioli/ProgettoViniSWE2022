package gestionevini;
import cantina.Cantina;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import vino.SchedaTecnica;
import vino.Vino;
import vino.VinoRosso;

public class MonitorVinoTest {

    private MonitorVino mv;
    private Vino v;
    private Cantina c;

    @Before
    public void setUp(){
        c = Cantina.getCantina(4);
        mv = new MonitorVino();
        v = new VinoRosso("Morellino",
                new SchedaTecnica(true, "prova", 1, "prova2", "prova3"));
        c.addVino(v);
        do{ //in caso i valori siano tutti giusti dopo varia (il setStrategy non verrebbe mai chiamato in tal caso)
            v.varia();
        } while (v.getAnidrideSolforosa() <= 160 && v.getpH() >= 3.3 && v.getpH() <= 3.5 && v.getZuccheriRiduttori() <= 210 &&
                v.getGradoAlcolico() >= 10 && v.getGradoAlcolico() <= 16 && v.getOssigeno() < 0.5 );
    }

    @Test (expected = NullPointerException.class)
    public void nullObservableUpdate() {
        mv.update(null, new Float(2.2));
    }

    @Test (expected = NullPointerException.class)
    public void nullParameterPushUpdate() {
        mv.update(v, null);
    }

    @Test (expected = ClassCastException.class)
    public void wrongTypeParameterUpdate() {
        mv.update(v, new Integer(2));
    }

    @Test
    public void testSetStrategy() {
        //entra se è sbagliato solo l'ossigeno
        if (v.getAnidrideSolforosa() <= 160 && v.getpH() >= 3.3 && v.getpH() <= 3.5 && v.getZuccheriRiduttori() <= 210 &&
                v.getGradoAlcolico() >= 10 && v.getGradoAlcolico() <= 16 && v.getOssigeno() >= 0.5)
        {
            mv.update(v, new Float(v.getOssigeno()));
            assertTrue(mv.getStrategy() instanceof StrategiaOssigeno);
        }
        else
        {
            mv.update(v, new Float(v.getAnidrideSolforosa()));
            assertTrue(mv.getStrategy() instanceof StrategiaGenerale);
        }
    }
}