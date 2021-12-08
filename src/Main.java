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
        c.addVino(1, new VinoRosso("Chianti", false));
        c.addVino(2, new VinoBianco("Chardonnay", true));
        c.addVino(3, new VinoRosso("Bolgheri", false));
        c.addVino(4, new VinoRosso("Morellino", true));
        c.addVino(5, new VinoRosso("Amarone", true));
        c.addVino(6, new VinoRosso("Sangiovese", false));
        c.addVino(7, new VinoBianco("Verdicchio", false));
        c.addVino(8, new VinoBianco("Vermentino", false));
        c.addVino(9, new VinoRosso("Nero di Troia", false));

        c.printHashMap();                           //funziona

        for (int i = 0; i<c.getSizeHashMap(); i++){
            Vino v = c.getVino(i);
            v.addObserver(new MonitorVino());                                     //non ho riferimenti agli observer
            v.printVino();
            v.varia();
            v.printVino();
        }

       /* Vino v0 = c.getVino(0);
        Observer obs0 = new MonitorVino();
        v0.addObserver(obs0);

        Vino v1 = c.getVino(1);
        Observer obs1 = new MonitorVino();
        v1.addObserver(obs1);*/

        //v1.printVino();

        /*boolean variazione0 = v0.varia();
        boolean variazione1 = v1.varia();
        v1.printVino();*/

        c.printHashMap();

        //TODO implementare funzionalità di aggiungere un vino in una botte vuota
        c.addVino(1, new VinoBianco("prova", true));
        c.printHashMap();
    }
}
