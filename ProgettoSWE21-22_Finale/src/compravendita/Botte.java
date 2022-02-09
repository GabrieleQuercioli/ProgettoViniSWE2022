package compravendita;

class Botte {
    private final int idBotte;
    private float capacita;

    Botte(int idBotte) {
        this.idBotte = idBotte;
        this.capacita = 100; //100 litri
    }

    public int getIdBotte() {
        return idBotte;
    }

    public float getCapacita() {
        return capacita;
    }

    void setCapacita(float quantita) {//TODO controlla bene il setter
        this.capacita = quantita;
    }
}
