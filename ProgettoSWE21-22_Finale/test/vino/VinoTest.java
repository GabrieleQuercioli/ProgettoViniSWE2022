package vino;

import org.junit.Before;
import org.junit.Test;

import javax.management.InvalidAttributeValueException;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class VinoTest {
    Vino vr;
    Vino vb;
    Vino vrs;

    @Before
    public void inizialize() throws Exception{
        vr = new VinoRosso("Morellino");
        vb = new VinoBianco("Bastianich");
        vrs = new VinoRosato("Vulcano");
    }

    //verifico solo che dopo l'operazione correggiVino() la quantità di ossigeno sia minore di 0.5
    @Test
    public void testCorreggiVino() {
        do {
            vr.varia();
        } while (vr.getOssigeno() < 0.5);
        vr.correggiVino();
        assertTrue(vr.getOssigeno() < 0.5);
    }


    //Testa il lancio dell'eccezione se il nome del vino di un determinato tipo non trova riscontro tra quelli
    //definiti nella variabile di tipo ENUM di quello stesso tipo
    @Test ( expected = IllegalArgumentException.class)
    public void notValidVinoName() throws Exception {
        vr = new VinoRosso("Bastianich");
    }

    //Lancia NullPointer probabilmente perchè quando va a controllare la stringa passata sugli ENUM usa il metodo equals()
    //fare null.equals() lancia NullPointer
    @Test ( expected = NullPointerException.class)
    public void nullVinoName() throws Exception {
        vr = new VinoRosso(null);
    }

    @Test (expected = FileNotFoundException.class)
    public void vinoInesistente() throws FileNotFoundException, InvalidAttributeValueException {
        vr = new VinoRosso("non in fornitura");
    }

    //Testa se viene chiamata la notifica (dato che nel metodo subito dopo notify ritorna true) solo nel caso in cui
    //un parametro esca dai valori di soglia, altrimenti che il metodo ritorni false
    //NOTA: non è istanziato nessun observer, quindi quando varia() fa la notify() non verrà
    //chiamato l'update su nessun observer
    @Test
    public void testRossoVaria() {
        boolean hasSpoiled = vr.varia();
        if (vr.getAnidrideSolforosa() > 160 || vr.getpH() < 3.3 || vr.getpH() > 3.5 ||
                vr.getZuccheriRiduttori() > 210 || vr.getGradoAlcolico() < 10 || vr.getGradoAlcolico() > 16 ||
                vr.getOssigeno() >= 0.5)
            assertTrue(hasSpoiled);
        else
            assertFalse(hasSpoiled);//se non esce dai limiti nulla
    }

    @Test
    public void testBiancoVaria() {
        boolean hasSpoiled = vb.varia();
        if (vb.getAnidrideSolforosa() > 210 || vb.getpH() < 3 || vb.getpH() > 3.3 || vb.getZuccheriRiduttori() > 260 || vb.getGradoAlcolico() < 10 || vb.getGradoAlcolico() > 14 || vb.getOssigeno() >= 0.5)
            assertTrue(hasSpoiled);
        else
            assertFalse(hasSpoiled);
    }

    @Test
    public void testRosatoVaria() {
        boolean hasSpoiled = vrs.varia();
        if (vrs.getAnidrideSolforosa() > 190 || vrs.getpH() < 3.2 || vrs.getpH() > 3.4 || vrs.getZuccheriRiduttori() > 230 || vrs.getGradoAlcolico() < 10 || vrs.getGradoAlcolico() > 16 || vrs.getOssigeno() >= 0.5)
            assertTrue(hasSpoiled);
        else
            assertFalse(hasSpoiled);
    }
}




