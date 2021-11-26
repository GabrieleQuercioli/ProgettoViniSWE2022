package cantina;

import macroGestione.Stanza;
import vino.Vino;
import java.util.LinkedHashMap;

public class Cantina {

    private int numBotti = 0;
    private Stanza[] stanze;
    private LinkedHashMap<Integer , Vino> botti;

    public Cantina(int numStanze) {             // can be a singleton?
        stanze = new Stanza[numStanze];
        for (int i=0; i<stanze.length; i++){
            stanze[i] = new Stanza(i);
        }
        botti = new LinkedHashMap<>();
    }

    public void addVino(int idBotte, Vino v) {
        botti.put(new Integer(idBotte), v);
        numBotti++;
    }

    public void removeVino(){
        //TODO da implementare
    }

    public Cantina getCantina(){  //serve?
        return this;
    }

}
