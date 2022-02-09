package businesslogic;

import compravendita.Cantina;
import gestionestanze.ControlloreStanza;
import gestionevini.MonitorVino;
import input_output.EditorODS;
import vino.*;

import javax.management.InvalidAttributeValueException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class OperatorePageController {
    //NOTA: la cantina viene instanziata qua
    private final Cantina c;

    public OperatorePageController(int numStanze) {
        c = Cantina.getCantina(numStanze);
    }

    public void accettaRifornimento(String path) {
        ArrayList<Vino> fornitura = new ArrayList<Vino>();
        File file = new File(path);
        ArrayList<String[]> listaInfoViniP = EditorODS.leggiODS(file);

        for (int i = 0; i < listaInfoViniP.size(); i++) {
            String tipo = listaInfoViniP.get(i)[0];
            String nome = listaInfoViniP.get(i)[1];

            if (tipo.equals("Rosso")) {
                try {
                    fornitura.add(new VinoRosso(nome));
                } catch (NullPointerException | IllegalArgumentException | InvalidAttributeValueException | FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (tipo.equals("Bianco")) {
                try {
                    fornitura.add(new VinoBianco(nome));
                } catch (NullPointerException | IllegalArgumentException | InvalidAttributeValueException | FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (tipo.equals("Rosato")) {
                try {
                    fornitura.add(new VinoRosato(nome));
                } catch (NullPointerException | IllegalArgumentException | InvalidAttributeValueException | FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        //aritmetica modulare per assegnare in modo equo i vini alle stanze
        for (int i = 0; i < fornitura.size(); i++) {
            try {
                int j = i % c.getNumeroStanze();
                c.getStanza(j).addVino(fornitura.get(i));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void printCantina() {
        for (int i = 0; i < c.getNumeroStanze(); i++)
            c.getStanza(i).printHashMap();
    }

    public void gestisciCantina() {
        int k = 0;
        while (k < c.getNumeroStanze()) {
            System.out.println("\nGESTIONE STANZA NUMERO " + k);
            c.getStanza(k).addObserver(new ControlloreStanza());
            c.getStanza(k).varia();
            if (c.getStanza(k).getHashMapSize() == 0)
                System.out.println("\nLa stanza numero " + k + " è vuota!!");
            else {
                System.out.println("\nGESTIONE VINI");
                for (int j = 0; j < c.getStanza(k).getHashMapSize(); j++) {
                    Vino v = c.getStanza(k).getVino(j);
                    v.addObserver(new MonitorVino());
                    v.varia();
                }
            }
            k++;
        }
    }
}
