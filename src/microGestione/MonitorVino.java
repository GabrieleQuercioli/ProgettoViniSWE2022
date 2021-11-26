package microGestione;

import vino.Vino;
import java.util.Observable;
import java.util.Observer;

public class MonitorVino implements Observer {

    private StrategiaVino strategy;

    //default constructor

    public void setStrategy(Vino v){
        //TODO da implementare
        strategy.gestisciVino(v);
    }

    @Override
    public void update(Observable observable, Object o) {
        //TODO da implementare
        // mettici il vino nel setStrategy
    }
}
