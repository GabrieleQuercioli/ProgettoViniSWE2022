package gestionevini;
import vino.Vino;

//visibilità package
class StrategiaOssigeno implements StrategiaVino {

    //costruttore di default che è sicuramente package perché la classe è package

    @Override
    public void gestisciVino(Vino v) {
        v.correggiVino();
    }
}
