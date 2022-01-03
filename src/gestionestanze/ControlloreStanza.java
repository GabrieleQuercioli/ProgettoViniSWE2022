package gestionestanze;

import java.util.Observer;
import java.util.Observable;

public class ControlloreStanza implements Observer {

    private StrategiaStanza strategy;

    //default constructor

    //Implementato in modo PULL (i controlli li fa qui)
    private void setStrategy(Stanza s){
        boolean stratApply = false;
        if (s.getTemperatura() < 6 || s.getTemperatura() >= 16){
            stratApply = true;
            strategy = new StrategiaTemperatura();
            strategy.gestisciStanza(s);
        }
        if (s.getUmidita() < 60 || s.getUmidita() >= 85){
            stratApply = true;
            strategy = new StrategiaUmidita();
            strategy.gestisciStanza(s);
        }
        if (!stratApply)
            System.out.println("\nLa stanza non ha richiesto alcun intervento");
    }

    @Override
    public void update(Observable observable, Object changedParameter) {
        Stanza s = (Stanza) observable;
        setStrategy(s);
    }

    public StrategiaStanza getStrategy() {
        return strategy;
    }
}
