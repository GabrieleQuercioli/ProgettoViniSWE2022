package macroGestione;
import java.util.Observable;

public class Stanza extends Observable{

    private int numStanza;
    private double temperatura;
    private double umidita;
    private boolean riscaldamento;
    private boolean umidificatore;

    public Stanza(int numStanza) {  //mettere parametri accettabili che non inneschino osservazione
        this.numStanza = numStanza;
        temperatura = Math.random();
        umidita = Math.random();
        riscaldamento = false;
        umidificatore = false;
    }

    public void obsMethod() {}



}
