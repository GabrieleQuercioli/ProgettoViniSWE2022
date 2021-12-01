package microGestione;

import cantina.Cantina;
import vino.Vino;

public class StrategiaGenerale implements StrategiaVino {

    private Cantina cantina;

    public StrategiaGenerale () {  //come istanzio il rifermento all'oggetto cantina?
        cantina = Cantina.getCantina(0);     //gli devo passare un int 'dummy' che non verrà utilizzato
    }

    @Override
    public void gestisciVino(Vino v) {
        cantina.removeVino(v);
    }
}
