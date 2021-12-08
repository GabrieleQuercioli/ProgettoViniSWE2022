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
        Integer key = new Integer(idBotte);
        if(!botti.containsKey(key))
        {
            botti.put(key, v);
            System.out.println("\nIl Vino " + v.getName() + " è stato messo nella botte numero: " + idBotte);
            numBotti++;
        }
        else {
            if (botti.get(key) == null)
                botti.replace(key, v);
        }
    }

    public void removeVino(Vino v){
        for (Integer key : botti.keySet()){
            if ( v == botti.get(key) ){
                //numBotti--;
                botti.replace(key, null);                                                   //mette a null il vino nella botte
                System.out.println("\nIl Vino " + v.getName() + " è stato buttato");
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
        System.out.println("\nElenco botti: ");
        for (Integer key : botti.keySet()){
            if (botti.get(key) != null)
                System.out.println("botte numero: " + key + " , " + botti.get(key).getName());
            else
                System.out.println("botte numero: " + key + " vuota");
        }
    }

}
