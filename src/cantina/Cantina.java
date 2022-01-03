package cantina;

import gestionestanze.Stanza;
import vino.Vino;

import java.lang.IllegalArgumentException;
import java.util.LinkedHashMap;

public class Cantina {

    private int numBotti = 0;
    private Stanza[] stanze;
    private LinkedHashMap<Integer , Vino> botti;
    private static Cantina cantina = null;

    private Cantina(int numStanze) throws Error {
        if (numStanze == 0)
            throw new Error("\nLa cantina deve avere almeno una stanza");
        stanze = new Stanza[numStanze];
        for (int i=0; i<stanze.length; i++){
            stanze[i] = new Stanza(i);
        }
        botti = new LinkedHashMap<>();
    }


    public static Cantina getCantina(int numStanze){
        if(cantina == null)
            cantina = new Cantina(numStanze);
        return cantina;
    }

    public void addVino(Vino v) throws IllegalArgumentException, NullPointerException {
        if (v == null || v.getName().isEmpty() || v.getSchedaTecnica() == null)
            throw new IllegalArgumentException("\nNon è possibile inserire un vino nullo, senza nome o scheda tecnica");
        if (v.getName() == null)
            throw new NullPointerException("\nNon è possibile inserire un vino con nome nullo");
        for (Integer key : botti.keySet()) {
            if (botti.get(key) == v)
                throw new IllegalArgumentException("\nLo stesso vino " + v.getName() + " è già stato inserito");
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

    public void removeVino(Vino v) throws IllegalArgumentException {
        if (v == null)
            throw new IllegalArgumentException("\nIl vino da rimuovere non può essere nullo");
        boolean found = false;
        for (Integer key : botti.keySet()){
            if ( v == botti.get(key) ){
                botti.replace(key, null);
                found = true;
                System.out.println("\nIl Vino " + v.getName() + " è stato buttato");
            }
        }
        if (!found)
            throw new IllegalArgumentException("\nIl vino " + v.getName() + " non è presente nella cantina");
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

    public Integer getKey(Vino v) {                 //serve per i test, dato che un singleton è complicato da testare
        for (Integer key : botti.keySet())
        {
            if (botti.get(key) == v)
                return key;
        }
        return null;
    }
}
