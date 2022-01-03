package gestionestanze;

class StrategiaUmidita implements StrategiaStanza {

    //default costructor

    @Override
    public void gestisciStanza(Stanza s) {
        s.correggiUmidita();
    }
}
