package vino;

import javax.management.InvalidAttributeValueException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VinoRosato extends Vino {

    public enum TipoRosato {
        Vulcano,
        Marques,
        ValdelleCorti,
        Voria;
    }

    private final TipoRosato denominazione;

    public VinoRosato(String nome) throws InvalidAttributeValueException, FileNotFoundException {
        super(nome);
        denominazione = TipoRosato.valueOf(nome);
    }

    @Override
    public boolean varia() {//observer PUSH perché i parametri del vino sono esclusivi tra loro
        anidrideSolforosa = 150 + (float)(Math.random() * 42);//150 e 192 LIMITE:190
        pH = (float)(3.18 + Math.random() * 0.24);//tra 3.18 e 3.42 LIMITE: 3.2 <= x <= 3.4
        zuccheriRiduttori = 190 + (float)(Math.random() * 42);//190 e 232 LIMITE: 230
        gradoAlcolico = (float)(9.7 + Math.random() * 6.6);//9.7 e 16.3 LIMITE: 10 <= x <= 16
        ossigeno = (float)(0.3 + Math.random() * 0.5);//tra 0.3 e 0.8(uguale)

        printVino();

        setChanged();


        if (anidrideSolforosa > 190) {
            notifyObservers(new Float(anidrideSolforosa));
            return true;
        }
        if (pH < 3.2 || pH > 3.4) {
            notifyObservers(new Float(pH));
            return true;
        }
        if (zuccheriRiduttori > 230) {
            notifyObservers(new Float(zuccheriRiduttori));
            return true;
        }
        if (gradoAlcolico < 10 || gradoAlcolico > 16) {
            notifyObservers(new Float(gradoAlcolico));
            return true;
        }
        if (ossigeno >= 0.5) {//è l'ultima cosa da controllare perché è l'unico caso in cui il vino non si butta
            notifyObservers(new Float(ossigeno));
            return true;
        }
        System.out.println("\nIl vino "+denominazione+" non ha richiesto alcun intervento");
        return false;//serve per sapere se i parametri del vino del vino sono ancora accettabili
    }




}