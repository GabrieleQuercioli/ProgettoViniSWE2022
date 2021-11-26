package microGestione;

import cantina.Cantina;
import vino.Vino;

public class StrategiaGenerale implements StrategiaVino {

    private Cantina cantina;

    public StrategiaGenerale () {           //come istanzio il rifermento all'oggetto cantina?
        cantina = cantina.getCantina();
    }

    @Override
    public void gestisciVino(Vino v) {
        //TODO da implementare (qui si butta il vino)
    }
}
