package compravendita;


public class Privato extends Cliente {

    private final String cognome;

    public Privato(String nome, String cognome, String indirizzo, String CAP) {
        super(nome,indirizzo,CAP);
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }
}
