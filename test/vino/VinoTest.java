package vino;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class VinoTest {

    private Vino vr;
    private Vino vb;
    private Vino vrs;

    @Before
    public void setUp(){
        vr = new VinoRosso("Morellino", new SchedaTecnica(true, "prova", 1, "prova2", "prova3"));
        vb = new VinoBianco("Bastianich", new SchedaTecnica(true, "prova", 1, "prova2", "prova3"));
        vrs = new VinoRosato("Vulcano", new SchedaTecnica(true, "prova", 1, "prova2", "prova3"));
    }

    @Test
    public void testCorreggiVino() {
        vr.varia();
        vr.correggiVino();
        assertTrue(vr.getOssigeno() < 0.5);
    }

    //E' giusto anche se sballa solo l'ossigeno?
    //(In questo caso non dovrebbe entrare nell'IF perchè viene corretto prima)
    //NO perchè non sono collegati observer (quindi non fa la notufy ma varia solo i parametri)
    @Test
    public void testRossoVaria() {
        boolean hasSpoiled = vr.varia();
        if (vr.getAnidrideSolforosa() > 160 || vr.getpH() < 3.3 || vr.getpH() > 3.5 || vr.getZuccheriRiduttori() > 210 ||
                vr.getGradoAlcolico() < 10 || vr.getGradoAlcolico() > 16 || vr.getOssigeno() >= 0.5 )
        {
            assertTrue(hasSpoiled);
        }
        else{
            assertFalse(hasSpoiled);
        }
    }

    @Test
    public void testBiancoVaria() {
        boolean hasSpoiled = vb.varia();
        if (vb.getAnidrideSolforosa() > 210 || vb.getpH() < 3 || vb.getpH() > 3.3 || vb.getZuccheriRiduttori() > 260 ||
                vb.getGradoAlcolico() < 10 || vb.getGradoAlcolico() > 14 || vb.getOssigeno() >= 0.5 )
        {
            assertTrue(hasSpoiled);
        }
        else{
            assertFalse(hasSpoiled);
        }
    }

    @Test
    public void testRosatoVaria() {
        boolean hasSpoiled = vb.varia();
        if (vb.getAnidrideSolforosa() > 190 || vb.getpH() < 3.2 || vb.getpH() > 3.4 || vb.getZuccheriRiduttori() > 230 ||
                vb.getGradoAlcolico() < 10 || vb.getGradoAlcolico() > 16 || vb.getOssigeno() >= 0.5 )
        {
            assertTrue(hasSpoiled);
        }
        else{
            assertFalse(hasSpoiled);
        }
    }


}
