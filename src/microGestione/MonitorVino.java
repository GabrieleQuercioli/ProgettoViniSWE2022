package microGestione;

import vino.Vino;               //Monitor dipende da Vino anche se è Push, va bene?
import java.util.Observable;
import java.util.Observer;

public class MonitorVino implements Observer {

    private StrategiaVino strategy;

    //default constructor

    private void setStrategy(Vino v, float param){              //private perchè lo voglio chiamare solo da update()
        if (param == v.getOssigeno())
            strategy = new StrategiaOssigeno();
        else
            strategy = new StrategiaGenerale();
        strategy.gestisciVino(v);
    }

    @Override
    public void update(Observable observable, Object changedParameter) {
        Vino v = (Vino)observable;
        float param = ((Float)changedParameter).floatValue();
        setStrategy(v, param);
    }
}
