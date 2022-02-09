package gestionevini;

import compravendita.Cantina;
import vino.Vino;

class StrategiaGenerale implements StrategiaVino {

    @Override
    public void gestisciVino(Vino v) {
        try {
            Cantina cantina = Cantina.getCantina(0);
            cantina.getStanza(v).removeVino(v);

            System.out.println("\n"+"Il vino "+v.getNome()+" è stato buttato");

        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }


    }
}
