package microGestione;

import vino.Vino;               //Monitor dipende da Vino anche se è Push, va bene?
import java.util.Observable;
import java.util.Observer;

public class MonitorVino implements Observer {

    private StrategiaVino strategy;

    //default constructor

    //per decidere la strategia bisognerà passargli il parametro cambiato, a questo punto non è meglio fare tutto in update?
    public void setStrategy(Vino v, float param){
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
    //Se devo dare un riferimento a Vino nella chiamata a SetStrategy, dovrò avere un riferimento al Vino nella classe,
    //tale riferimento viene dato dall'observable?
}
