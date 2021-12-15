package microGestione;

import vino.Vino;

class StrategiaOssigeno implements StrategiaVino {      //package

    //default package costructor

    @Override
    public void gestisciVino(Vino v) {
        v.correggiVino();
    }
}
