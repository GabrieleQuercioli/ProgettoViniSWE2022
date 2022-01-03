package gestionestanze;

class StrategiaTemperatura implements StrategiaStanza {

    //default costructor

    @Override
    public void gestisciStanza(Stanza s) {
        s.correggiTemperatura();
    }
}
