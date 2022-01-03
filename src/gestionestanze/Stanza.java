package gestionestanze;
import java.util.Observable;

public class Stanza extends Observable{

    private final int numStanza;
    private float temperatura;
    private float umidita;

    public Stanza(int numStanza) throws Error {
        if (numStanza < 0)
            throw new Error("Il numero della stanza non può essere negativo");
        this.numStanza = numStanza;
        temperatura = (float)(6 + Math.random() * 10);        //tra 6 e 16 gradi
        umidita = (float)(60 + Math.random() * 25);           //tra 60 e 85 %
    }

    //Qui l'Observer è implementato in modo PULL perchè da problemi se entrambi richiedono intervento
    public void varia() {
        temperatura = (float)(0 + Math.random() * 30);        //tra 0 e 30 gradi
        umidita = (float)(35 + Math.random() * 60);           //tra 35 e 95 %

        printStanza();
        setChanged();
        notifyObservers();
    }

    void correggiTemperatura() {        //package-private perchè deve essere chaimata solo nelle strategie
        System.out.println("\nLa temperatura della stanza " + numStanza + " verrà corretta, poichè il suo valore è: "
                + temperatura + " C°");
        temperatura = (float)(6 + Math.random() * 10);
    }

    void correggiUmidita() {
        System.out.println("\nL'umidità della stanza " + numStanza + " verrà corretta, poichè il suo valore è: "
                + umidita + " %");
        umidita = (float)(60 + Math.random() * 25);
    }

    public float getTemperatura() {
        return temperatura;
    }

    public float getUmidita() {
        return umidita;
    }

    public void printStanza() {
        System.out.println("\nId stanza: " + numStanza + "\n");
        System.out.println("Temperatura: " + temperatura + " C°");
        System.out.println("Umidità: " + umidita + " %");
    }
}
