import cantina.Cantina;
import microGestione.MonitorVino;
import vino.Vino;
import vino.VinoBianco;
import vino.VinoRosso;
import java.util.Observer;

public class Main {
    public static void main(String[] argv) {

        Cantina c = Cantina.getCantina(4);

        c.addVino(0, new VinoBianco("Sauvignon", false));
        //c.addVino(2, new VinoBianco("Chardonnay", true));
        c.addVino(1, new VinoRosso("Chianti", false));
        /*c.addVino(3, new VinoRosso("Bolgheri", false));
        c.addVino(4, new VinoRosso("Morellino", true));
        c.addVino(5, new VinoRosso("Amarone", true));*/

        c.printHashMap();                           //funziona

        Vino v0 = c.getVino(0);
        Observer obs0 = new MonitorVino();
        v0.addObserver(obs0);

        Vino v1 = c.getVino(1);
        Observer obs1 = new MonitorVino();
        v1.addObserver(obs1);

        v1.printVino();


        boolean variazione0 = v0.varia();
        boolean variazione1 = v1.varia();

        v1.printVino();

        c.printHashMap();

    }
}
