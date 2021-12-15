package cantina;

import macroGestione.Stanza;
import vino.Vino;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;

public class Cantina {

    private int numBotti = 0;
    private Stanza[] stanze;
    private LinkedHashMap<Integer , Vino> botti;
    private static Cantina cantina = null;                  //Singleton: qui ci viene messo il vero oggetto cantina

    private Cantina(int numStanze) throws Error {              // Is a singleton
        if (numStanze == 0)
            throw new Error("\nLa cantina deve avere almeno una stanza");
        stanze = new Stanza[numStanze];
        for (int i=0; i<stanze.length; i++){
            stanze[i] = new Stanza(i);
        }
        botti = new LinkedHashMap<>();
    }

    //Singleton getInstance: deve essere chiamato questo quando voglio costruire la cantina (nel main)
    // e poi in seguito voglio accedere alla stessa istanza
    public static Cantina getCantina(int numStanze){
        if(cantina == null)
            cantina = new Cantina(numStanze);
        return cantina;
    }

    public void addVino(Vino v) throws InvalidParameterException {
        if (v == null || v.getName() == null || v.getName().isEmpty())
            throw new InvalidParameterException("\nNon è possibile inserire un vino nullo o senza nome");
        for (Integer key : botti.keySet()) {
            if (botti.get(key) == v)
                throw new InvalidParameterException("\nLo stesso vino " + v.getName() + " è già stato inserito");
        }

        boolean found = false;                              //serve per il primo inserimento
        for (Integer key : botti.keySet())
        {
            if (botti.get(key) == null && !found){
                botti.replace(key, v);
                System.out.println("\nNella botte vuota numero " + key.intValue() + " è stato inserito il vino "
                        + v.getName());
                found = true;
            }
        }
        if (!found){
            botti.put(new Integer(numBotti), v);
            System.out.println("\nIl Vino " + v.getName() + " è stato messo nella botte numero: " + numBotti);
            numBotti++;
        }
    }

    public void removeVino(Vino v) throws InvalidParameterException {
        if (v == null)
            throw new InvalidParameterException("\nIl vino da rimuovere non può essere nullo");
        boolean found = false;
        for (Integer key : botti.keySet()){
            if ( v == botti.get(key) ){
                botti.replace(key, null);                                                //mette a null il vino nella botte
                found = true;
                System.out.println("\nIl Vino " + v.getName() + " è stato buttato");
            }
        }
        if (!found)
            throw new InvalidParameterException("\nIl vino " + v.getName() + " non è presente nella cantina");
    }

    public Vino getVino(int idBotte) {
        return botti.get(new Integer(idBotte));
    }

    public int getSizeHashMap() {
        return botti.size();
    }

    public void printHashMap() {
        if (botti.isEmpty()){
            System.out.println("\nLa cantina non contiene vini");
        }
        else {
            System.out.println("\nElenco botti: ");
            for (Integer key : botti.keySet()) {
                if (botti.get(key) != null)
                    System.out.println("botte numero: " + key + " , " + botti.get(key).getName());
                else
                    System.out.println("botte numero: " + key + " vuota");
            }
        }
    }

    /*public Stanza[] getStanze() {
        return stanze;
    }*/

    public Stanza getStanza(int numStanza) {
        return stanze[numStanza];
    }

    public int getNumStanze() {
        return stanze.length;
    }
}
