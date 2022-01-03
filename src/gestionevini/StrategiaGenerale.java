package gestionevini;

import cantina.Cantina;
import vino.Vino;

import java.security.InvalidParameterException;

class StrategiaGenerale implements StrategiaVino {      //package class

    private Cantina cantina;

    StrategiaGenerale () {                  //package perchè deve essere creato solo in MonitorVino
        cantina = Cantina.getCantina(0);     //gli devo passare un int 'dummy' che non verrà utilizzato
    }

    @Override
    public void gestisciVino(Vino v) {
        try {
            cantina.removeVino(v);
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }

    }
}
