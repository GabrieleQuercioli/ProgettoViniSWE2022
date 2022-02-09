package compravendita;

import input_output.EditorODS;
import vino.Vino;
import vino.VinoBianco;
import vino.VinoRosso;

import java.util.ArrayList;


public class Cantina {
    private final Stanza[] stanze;
    private static int numCatalogo = 0;

    private static Cantina cantina = null;

    private Cantina(int numStanze) throws Error {
        if (numStanze <= 0)
            throw new Error("La cantina deve avere almeno una stanza!");
        stanze = new Stanza[numStanze];
        for (int i=0; i<stanze.length; i++) {
            stanze[i] = new Stanza(i);
        }
    }

    public static Cantina getCantina(int numStanze) {
        if (cantina == null)
            cantina = new Cantina(numStanze);
        return cantina;
    }

    //dato che questo codice viene riutilizzato si fa un metodo
    private Vino getVino(String nome) throws IllegalArgumentException{
        for (Stanza stanza : stanze) {
            Vino v = stanza.getVino(nome);
            if (v != null)
                return v;
        }
        throw new IllegalArgumentException("Non esiste vino nella cantina con tale nome"); //se non viene trovato
    }

    private Botte getBotte(Vino v) throws IllegalArgumentException {
        for (Stanza stanza : stanze) {
            Botte b = stanza.getBotte(v);
            if (b != null)
                return b;
        }
        throw new IllegalArgumentException("Il vino non è presente nella cantina"); //se non viene trovato
    }

    // in base al vino cerca in tutte le stanze e le relative mappe, se trova corrispondenza restituisce la stanza
    public Stanza getStanza(Vino v) {
        return _getStanza(v);
    }

    public Stanza getStanza(int numStanza) {
        return stanze[numStanza];
    }

    // metodo di appoggio che viene usato in vendi()
    private Stanza _getStanza(Vino v) {
        for (Stanza stanza : stanze) {
            if (stanza.getBotte(v) != null)
                return stanza;
        }
        return null;
    }

    public int getNumeroStanze() {
        return stanze.length;
    }


    //Il metodo alla fine crea sempre un nuovo catalogo perchè nella scrittura del ODS non c'è funzione di chiusura del file
    public void mostraCatalogo() throws EmptyCatalogoException {
        ArrayList<String[]> infoVini = new ArrayList<String[]>();
        final String[] titoli = new String[] {"Tipo", "Nome", "Provenienza Mosto",
                "Annata", "Qualifica", "Biologico", "Vitigni", "Prezzo (€/L)"};
        for (Stanza stanzaIes : stanze) {
            String[] infoVino = new String[titoli.length];
            for (int j = 0; j < stanzaIes.getHashMapSize(); j++) {
                Vino vinoJes = stanzaIes.getVino(j);

                if (vinoJes != null) {
                    if (vinoJes instanceof VinoRosso)
                        infoVino[0] = "Rosso";
                    else if (vinoJes instanceof VinoBianco)
                        infoVino[0] = "Bianco";
                    else
                        infoVino[0] = "Rosato";

                    infoVino[1] = vinoJes.getNome();
                    infoVino[2] = vinoJes.getSt().getProvenienzaMosto();
                    infoVino[3] = vinoJes.getSt().getAnnata();
                    infoVino[4] = vinoJes.getSt().getQualifica();
                    infoVino[5] = vinoJes.getSt().getBio();
                    infoVino[6] = vinoJes.getSt().getVitigni();
                    infoVino[7] = vinoJes.getSt().getPrezzoL();

                    infoVini.add(infoVino);
                }
                infoVino = new String[titoli.length];
            }
        }
        if (infoVini.isEmpty())
            throw new EmptyCatalogoException("Il catalogo è vuoto!");

        EditorODS.scriviNuovoODS(infoVini,titoli,"files/Catalogo"+numCatalogo+".ods");
        System.out.println("\nÈ stato mostrato il catalogo n° "+numCatalogo);
        numCatalogo++;
    }


    //si applica lo sconto solo sopra 10 litri, la percentuale sconto è 0.5% al litro
    public float calcolaSconto(float prezzo, float quantita) {
        if (quantita > 10) {
           float scontoPerc = (quantita - 10)*0.5f;
            System.out.println("\nSconto del "+scontoPerc+" %, riservato alle aziende");
            float sconto = (prezzo/100)*scontoPerc;
           prezzo -= sconto;
           return prezzo;
        }
        return prezzo;
    }


    public float[] vendi(String nome, int quantita) throws IllegalArgumentException {
       try {
           Vino v = getVino(nome);
           Botte botteV = getBotte(v);
           Stanza stanzaV = _getStanza(v);

           float[] acquisto = new float[2];
           float prezzo = Float.parseFloat(v.getSt().getPrezzoL());

           float oldCapacita = botteV.getCapacita();
           if (quantita >= botteV.getCapacita()) {
               botteV.setCapacita(0);
               stanzaV.removeVino(v);
               prezzo *= oldCapacita;
               acquisto[1] = oldCapacita;
               if (quantita > oldCapacita) {//se > si vende quello che c'è
                   System.out.println("Poiché la quantità di "+nome+" richiesta non è disponibile, ti sono stati venduti "+oldCapacita+" L");
               }
               else {//se quantita e capacita sono uguali
                   System.out.println("\n Sono stati venduti "+ quantita + " L del vino " + nome);
               }
           }
           else {
               botteV.setCapacita(oldCapacita-quantita);
               prezzo *= quantita;
               acquisto[1] = quantita;
               System.out.println("\n Sono stati venduti "+ quantita + " L del vino " + nome);
           }
           System.out.println("\n La botte n° "+botteV.getIdBotte()+" della stanza "+stanzaV.getNumStanza()+" adesso contiene "+botteV.getCapacita()+" L di vino");
           acquisto[0] = prezzo;
           return acquisto;

       } catch (IllegalArgumentException e){
           throw new IllegalArgumentException("\nDeve essere inserito un vino presente nel catalogo!");
       }
    }
}
