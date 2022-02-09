package gestionestanze;

import compravendita.Stanza;

import java.util.Observable;
import java.util.Observer;

public class ControlloreStanza implements Observer {
    private StrategiaStanza strategy;

    //costruttore di default

    private void setStrategy(Stanza s) {
        boolean stratApply = false;
        if (s.getTemperatura() < 6 || s.getTemperatura() >= 16) {
            strategy = new StrategiaTemperatura();
            strategy.gestisciStanza(s);
            stratApply = true;
        }
        if (s.getUmidita() < 60 || s.getUmidita() >= 85) {
            strategy = new StrategiaUmidita();
            strategy.gestisciStanza(s);
            stratApply = true;
        }
        if (!stratApply)
            System.out.println("\nLa stanza non ha richiesto alcun intervento");
    }

    //PULL
    @Override
    public void update(Observable observable, Object o) {
        Stanza s = (Stanza) observable;
        setStrategy(s);
    }

    public StrategiaStanza getStrategy() {
        return strategy;
    }
}
