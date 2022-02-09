package vino;

import org.junit.Test;

import javax.management.InvalidAttributeValueException;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class SchedaTecnicaTest {
    @Test
    public void testBuilder() throws InvalidAttributeValueException, FileNotFoundException {
        Vino v = new VinoRosso("Morellino");
        assertEquals("Scansano", v.getSt().getProvenienzaMosto());
        assertEquals("2019", v.getSt().getAnnata());
        assertEquals("DOCG", v.getSt().getQualifica());
        assertEquals("No", v.getSt().getBio());
        assertEquals("Sangiovese 96%, Ciliegiolo 4%", v.getSt().getVitigni());
        assertEquals("28.20", v.getSt().getPrezzoL());
    }

    //Se fosse creato un Vino con un nome che non appartiene a nessun file di Fornitura
    @Test (expected = FileNotFoundException.class)
    public void buildVinoNonInFornitura() throws InvalidAttributeValueException, FileNotFoundException {
        Vino v = new VinoRosso("Non in fornitura");
    }
}