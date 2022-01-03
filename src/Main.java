import cantina.Cantina;
import gestionestanze.ControlloreStanza;
import gestionevini.MonitorVino;
import vino.*;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] argv) {

        Cantina c = Cantina.getCantina(4);

        ArrayList<Vino> primaFornitura = new ArrayList<Vino>();

        primaFornitura.add(new VinoBianco("Chamonix",
                new SchedaTecnica(false, "SudAfrica", 2018, "Chardonnay(100%)", "IWSA")));
        primaFornitura.add(new VinoRosato("Marques",
                new SchedaTecnica(false, "Rioja", 2020,
                        "Tempranillo(100%)", "AOC")));
        primaFornitura.add(new VinoBianco("Bastianich",
                new SchedaTecnica(false, "Colli del Friuli", 2020, "Sauvignon(100%)", "DOC")));
        primaFornitura.add(new VinoRosso("BolgheriSassicaia",
                new SchedaTecnica(false, "Castagneto Carducci", 2018,
                        "Cabernet Sauvignon(85%), Caberenet Franc(15%)", "DOC")));
        primaFornitura.add(new VinoRosso("Morellino",
                new SchedaTecnica(false, "Scanzano", 2019,
                        "Sangiovese(96%), Ciliegiolo(4%)", "DOCG")));
        primaFornitura.add(new VinoRosso("AmaroneMazzano",
                new SchedaTecnica(false, "Sant'Ambrogio di Valpolicella", 2012,
                        "Corvina(70%), Molinara(20%), Rondinella(10%)", "DOCG")));
        primaFornitura.add(new VinoRosso("BaroloMonprivato",
                new SchedaTecnica(false, "Castiglione Falletto", 2011,
                        "Nebbiolo(100%)", "DOCG")));
        primaFornitura.add(new VinoRosato("Vulcano",
                new SchedaTecnica(false, "Lanzarote", 2018, "Listan Nero(100%)", "AOC")));
        primaFornitura.add(new VinoBianco("Spera",
                new SchedaTecnica(false, "Gallura", 2019, "Vermentino(100%)", "DOCG")));
        primaFornitura.add(new VinoRosso("Brunello",
                new SchedaTecnica(false, "Montalcino", 2013,
                        "Sangiovese(100%)", "DOCG")));

        for (int i = 0; i < primaFornitura.size(); i++)
        {
            try {
                c.addVino(primaFornitura.get(i));
            } catch (NullPointerException | IllegalArgumentException np)
            {
                System.out.println(np.getMessage());
            }
        }

        c.printHashMap();                           //funziona

        System.out.println("\n[GESTIONE STANZE CANTINA]");

        for (int i = 0; i < c.getNumStanze(); i++) {                                //ciclo stanze
            c.getStanza(i).addObserver(new ControlloreStanza());
            c.getStanza(i).varia();
            c.getStanza(i).printStanza();
        }

        System.out.println("\n[GESTIONE VINI CANTINA]");
        for (int i = 0; i < c.getSizeHashMap(); i++){                               //ciclo vini
            Vino v = c.getVino(i);
            v.addObserver(new MonitorVino());                                     //non ho riferimenti agli observer
            v.varia();
        }

        c.printHashMap();

        //implementazione funzionalità di aggiungere un vino in una botte vuota
        ArrayList<Vino> secondaFornitura = new ArrayList<Vino>();

        secondaFornitura.add(new VinoBianco("Ceretto",
                new SchedaTecnica(false, "Asti", 2021, "Moscato(100%)", "DOCG")));
        secondaFornitura.add(new VinoRosato("ValdelleCorti",
                new SchedaTecnica(false, "Chianti", 2020, "Sangiovese(100%)", "DOC")));
        secondaFornitura.add(new VinoBianco("Alsace",
                new SchedaTecnica(false, "Barr", 2020, "Rieslig(100%)", "AOC")));
        secondaFornitura.add(new VinoRosso("Nobile",
                new SchedaTecnica(false, "Montepulciano", 2018,
                        "Prugnolo(90%), Merlot(10%)", "DOCG")));
        secondaFornitura.add(new VinoRosso("Tignanello",
                new SchedaTecnica(true, "San Casciano", 2018,
                        "Sangiovese(80%), Cabernet Franc (5%), Sauvignon(15%)", "IGT")));
        secondaFornitura.add(new VinoRosso("Solaia",
                new SchedaTecnica(false, "San Casciano", 2017,
                        "SanGiovese(20%), Cabernet Franc(5%), Sauvigno(75%)", "IGT")));
        secondaFornitura.add(new VinoBianco("Panizzi",
                new SchedaTecnica(true, "San Gimignano", 2020, "Vernaccia(100%)", "DOCG")));
        secondaFornitura.add(new VinoBianco("Temi",
                new SchedaTecnica(false, "Piemonte", 2016, "Cortese(100%)", "DOC")));
        secondaFornitura.add(new VinoBianco("Roero",
                new SchedaTecnica(false, "San Michele", 2019, "Arneis(100%)", "DOCG")));
        secondaFornitura.add(new VinoRosato("Voria",
                new SchedaTecnica(true, "Porta del Vento", 2018,
                        "Perricone(100%)", "DOCG")));


        for (int i = 0; i < secondaFornitura.size(); i++)
        {
            try {
                c.addVino(secondaFornitura.get(i));
            } catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        }

        c.printHashMap();

    }
}
