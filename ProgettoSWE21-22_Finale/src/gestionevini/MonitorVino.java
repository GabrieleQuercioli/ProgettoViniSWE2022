package gestionevini;
import java.util.Observable;
import java.util.Observer;
import vino.Vino;

public class MonitorVino implements Observer {
    private StrategiaVino strategy;

    //costruttore di default

    private void setStrategy(Vino v, float param) {
        if (param == v.getOssigeno())
            strategy = new StrategiaOssigeno();
        else
            strategy = new StrategiaGenerale();
        strategy.gestisciVino(v);
    }

    //PUSH
    @Override
    public void update(Observable observable, Object o) {
        Vino v = (Vino) observable;
        float param = ((Float) o).floatValue();
        setStrategy(v,param);
    }

    public StrategiaVino getStrategy() {
        return strategy;
    }
}
