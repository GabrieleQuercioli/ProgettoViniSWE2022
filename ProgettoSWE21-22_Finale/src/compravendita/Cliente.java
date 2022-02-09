package compravendita;

import java.io.IOException;
import java.util.Scanner;

public abstract class Cliente {
    private final String nome;
    private final String indirizzo;
    private final String CAP;


    Cliente(String nome, String indirizzo, String CAP) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.CAP = CAP;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getCAP() { return CAP; }
}
