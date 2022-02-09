package vino;

import javax.management.InvalidAttributeValueException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class VinoBianco extends Vino {

    public enum TipoBianco {
        Chamonix,
        Bastianich,
        Spera,
        Ceretto,
        Alsace,
        Panizzi,
        Temi,
        Roero,
    }

    private final TipoBianco denominazione;


    public VinoBianco(String nome) throws InvalidAttributeValueException, FileNotFoundException {
        super(nome);
        denominazione = VinoBianco.TipoBianco.valueOf(nome);
    }

    @Override
    public boolean varia() {//observer PUSH perché i parametri del vino sono esclusivi tra loro
        anidrideSolforosa = 170 + (float)(Math.random() * 42);//170 e 212 LIMITE:210
        pH = (float)(2.98 + Math.random() * 0.34);//tra 2.98 e 3.32 LIMITE: 3 <= x <= 3.3
        zuccheriRiduttori = 220 + (float)(Math.random() * 42);//220 e 262 LIMITE:260
        gradoAlcolico =  (float)(9.7 + Math.random() * 4.6);//9.7 e 14.3 LIMITE: 10 <= x <= 14
        ossigeno = (float)(0.3 + Math.random() * 0.5);//tra 0.3 e 0.8 LIMITE: max 0.5

        printVino();

        setChanged();

        if (anidrideSolforosa > 210) {
            notifyObservers(new Float(anidrideSolforosa));
            return true;
        }
        if (pH < 3 || pH > 3.3) {
            notifyObservers(new Float(pH));
            return true;
        }
        if (zuccheriRiduttori > 260) {
            notifyObservers(new Float(zuccheriRiduttori));
            return true;
        }
        if (gradoAlcolico < 10 || gradoAlcolico > 14) {
            notifyObservers(new Float(gradoAlcolico));
            return true;
        }
        if (ossigeno >= 0.5) {
            notifyObservers(new Float(ossigeno));
            return true;
        }
        System.out.println("\nIl vino "+nome+" non ha richiesto alcun intervento");
        return false;//serve per sapere se i parametri del vino sono ancora accettabili
    }


}
