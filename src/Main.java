import cantina.Cantina;
import macroGestione.ControlloreStanza;
import microGestione.MonitorVino;
import vino.Vino;
import vino.VinoBianco;
import vino.VinoRosso;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Observer;

public class Main {
    public static void main(String[] argv) {

        Cantina c = Cantina.getCantina(4);

        ArrayList<Vino> primaFornitura = new ArrayList<Vino>();

        primaFornitura.add(new VinoBianco("Sauvignon", false));
        primaFornitura.add(new VinoRosso("Chianti", false));
        primaFornitura.add(null);
        primaFornitura.add(new VinoBianco("Chardonnay", true));
        Vino vino = new VinoRosso("Bolgheri", false);
        primaFornitura.add(vino);
        primaFornitura.add(vino);
        primaFornitura.add(new VinoRosso("Morellino", true));
        primaFornitura.add(new VinoRosso("Amarone", true));
        primaFornitura.add(new VinoRosso("Sangiovese", false));
        primaFornitura.add(new VinoBianco("Verdicchio", false));
        primaFornitura.add(new VinoBianco("Vermentino", false));
        primaFornitura.add(new VinoRosso("Nero di Troia", false));


        for (int i = 0; i < primaFornitura.size(); i++)
        {
            try {
                c.addVino(primaFornitura.get(i));
            } catch (InvalidParameterException e)
            {
                System.out.println(e.getMessage());
            }
        }


        //Stanza[] stanze = c.getStanze();
       /* c.addVino(new VinoBianco("Sauvignon", false));
        c.addVino(new VinoRosso("Chianti", false));
        c.addVino(new VinoBianco("Chardonnay", true));
        c.addVino(new VinoRosso("Bolgheri", false));
        c.addVino(new VinoRosso("Morellino", true));
        c.addVino(new VinoRosso("Amarone", true));
        c.addVino(new VinoRosso("Sangiovese", false));
        c.addVino(new VinoBianco("Verdicchio", false));
        c.addVino(new VinoBianco("Vermentino", false));
        c.addVino(new VinoRosso("Nero di Troia", false));*/

        c.printHashMap();                           //funziona

        System.out.println("\n[GESTIONE STANZE CANTINA]");
        /*for (int i = 0; i < stanze.length; i++)
        {
            stanze[i].addObserver(new ControlloreStanza());
            stanze[i].printStanza();
            stanze[i].varia();
            stanze[i].printStanza();
        }*/

        for (int i = 0; i < c.getNumStanze(); i++) {                                //ciclo stanze
            c.getStanza(i).addObserver(new ControlloreStanza());
            c.getStanza(i).printStanza();
            c.getStanza(i).varia();
            c.getStanza(i).printStanza();
        }

        System.out.println("\n[GESTIONE VINI CANTINA]");
        for (int i = 0; i < c.getSizeHashMap(); i++){                               //ciclo vini
            Vino v = c.getVino(i);
            v.addObserver(new MonitorVino());                                     //non ho riferimenti agli observer
            v.printVino();
            v.varia();
            v.printVino();
        }

       /* Vino v0 = c.getVino(0);
        Observer obs0 = new MonitorVino();
        v0.addObserver(obs0);*/



        c.printHashMap();

        //implementazione funzionalità di aggiungere un vino in una botte vuota
        ArrayList<Vino> secondaFornitura = new ArrayList<Vino>();

        secondaFornitura.add(new VinoBianco("Moscato", false));
        secondaFornitura.add(new VinoRosso("Merlot", false));
        secondaFornitura.add(new VinoBianco("Riesling", true));
        secondaFornitura.add(new VinoRosso("Brunello", false));
        Vino vino1 = new VinoRosso("Nobile", true);
        secondaFornitura.add(vino1);
        secondaFornitura.add(vino1);
        secondaFornitura.add(new VinoRosso("Nebbiolo", true));
        secondaFornitura.add(new VinoBianco("Vernaccia", false));
        secondaFornitura.add(new VinoBianco("Cortese", false));
        secondaFornitura.add(new VinoBianco("Arneis", false));
        secondaFornitura.add(null);
        secondaFornitura.add(new VinoBianco("Muller Thurgau", false));

        for (int i = 0; i < secondaFornitura.size(); i++)
        {
            try {
                c.addVino(secondaFornitura.get(i));
            } catch (InvalidParameterException e)
            {
                System.out.println(e.getMessage());
            }
        }

        c.printHashMap();

    }
}
