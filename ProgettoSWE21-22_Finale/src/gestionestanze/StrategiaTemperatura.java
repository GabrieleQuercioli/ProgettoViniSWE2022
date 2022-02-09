package gestionestanze;

import compravendita.Stanza;

class StrategiaTemperatura implements StrategiaStanza {//package

    // costruttore default

    @Override
    public void gestisciStanza(Stanza s) {
        if (s.getTemperatura() < -1 || s.getTemperatura() > 22 ) {
            s.resetStanza();
            System.out.println("\nA causa dell'estrema temperatura di valore "+s.getTemperatura()+" tutte le botti sono state buttate");
        }
        s.correggiTemperatura();
    }
}
