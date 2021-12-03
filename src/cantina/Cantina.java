package cantina;

import macroGestione.Stanza;
import vino.Vino;
import java.util.LinkedHashMap;

public class Cantina {

    private int numBotti = 0;
    private Stanza[] stanze;
    private LinkedHashMap<Integer , Vino> botti;
    private static Cantina cantina = null;        //Singleton: qui ci viene messo il vero oggetto cantina

    private Cantina(int numStanze) {             // can be a singleton?
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

    public void removeVino(Vino v){                                    //FIXME non funziona
        for (Integer key : botti.keySet()){
            if ( v == botti.get(key) ){
                botti.remove(key,botti.get(key));                      //basta remove(i)? da controllare il funzionamento
                numBotti--;
            }
        }
    }

    //Singleton getInstance: deve essere chiamato questo quando voglio costruire la cantina (nel main) e poi in seguito voglio
    //accedere alla stessa istanza
    public static Cantina getCantina(int numStanze){
        if(cantina == null)
            cantina = new Cantina(numStanze);
        return cantina;
    }

    public Vino getVino(int idBotte) {
        return botti.get(new Integer(idBotte));
    }

    public int getSizeHashMap() {
        return botti.size();
    }

    public void printHashMap() {
        for (Integer key : botti.keySet()){
            System.out.println("botte numero: " + key + " , " + botti.get(key).getName());
        }
    }

}
