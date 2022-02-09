package compravendita;


public class Azienda extends Cliente {
    private final String partitaIva;

    public Azienda(String nome, String indirizzo, String CAP, String partitaIva) {
        super(nome,indirizzo,CAP);
        this.partitaIva = partitaIva;
    }

    public String getPartitaIva() {
        return partitaIva;
    }
}
