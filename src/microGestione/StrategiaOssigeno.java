package microGestione;

import vino.Vino;

public class StrategiaOssigeno implements StrategiaVino {

    @Override
    public void gestisciVino(Vino v) {
        System.out.println("\nCorrezione Vino, valore sballato: " + v.getOssigeno());
        v.correggiVino();
    }
}
