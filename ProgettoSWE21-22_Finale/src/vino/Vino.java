package vino;

import javax.management.InvalidAttributeValueException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

public abstract class Vino extends Observable {
    private final SchedaTecnica st;
    final String nome;
    float anidrideSolforosa;
    float pH;
    float zuccheriRiduttori;
    float gradoAlcolico;
    float ossigeno;

    Vino(String nome) throws NullPointerException, IllegalArgumentException, FileNotFoundException {
        if (nome == null)
            throw new NullPointerException("\nIl vino non può essere nullo!!");
        if (nome.isEmpty())
            throw new IllegalArgumentException("\nIl vino non può avere nome vuoto!!");
        this.nome = nome;
        this.st = new SchedaTecnica.SchedaTecnicaBuilder(nome).build();
        this.anidrideSolforosa = 110 + (float)(Math.random() * 50);
        this.pH = 3.3f;
        this.zuccheriRiduttori = 160 + (float)(Math.random() * 50);
        this.gradoAlcolico = 10 + (float)(Math.random() * 4);
        this.ossigeno = (float)(Math.random() * 0.5);
    }

    public void correggiVino() {
        System.out.println("\n"+"Trattamento "+nome+" con azoto. "+"Valore ossigeno da correggere: "+ossigeno);
        ossigeno = (float)(Math.random() * 0.5);//rimette i valori sotto 0.5
        printVino();
    }

    public abstract boolean varia();  //FIXME: chiamarlo avviaTrattamento (o aggiungiTrattamento)?

    public void printVino() {
        System.out.println("\nSCHEDA TECNICA:");
        System.out.println("Nome: "+nome);
        st.printSchedaTecnica();
        System.out.println("\nPARAMETRI:");
        System.out.println("Anidride Solforosa: "+anidrideSolforosa+" mg/L");
        System.out.println("PH: "+pH);
        System.out.println("Zuccheri Riduttori: "+zuccheriRiduttori+" mg/L");
        System.out.println("Grado Alcolico: "+gradoAlcolico+" %");
        System.out.println("Ossigeno: "+ossigeno+" mg/L");
    }


    public float getAnidrideSolforosa() { return anidrideSolforosa; }

    public float getpH() { return pH; }

    public float getZuccheriRiduttori() { return zuccheriRiduttori; }

    public float getGradoAlcolico() { return gradoAlcolico; }

    public String getNome() { return nome; }

    public float getOssigeno() { return ossigeno; }

    public SchedaTecnica getSt() { return st; }         //immutabile, non serve copia difensiva

}

