package microGestione;

import vino.Vino;

public class StrategiaOssigeno implements StrategiaVino {

    @Override
    public void gestisciVino(Vino v) {
        System.out.println("La quantità di ossigeno nel Vino " + v.getName() + " verrà corretta, poichè il suo valore è: " + v.getOssigeno() + "\n");
        v.correggiVino();
    }
}
