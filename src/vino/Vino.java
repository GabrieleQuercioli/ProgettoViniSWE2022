package vino;
import java.util.Observable;

public abstract class Vino extends Observable {
    protected String name;
    protected boolean bio;
    protected double anidrideSolforosa; // in mg/L
    protected double pH;
    protected double zuccheriRiduttori; // in mg/L
    protected double gradoAlcolico;  //in %
    protected double ossigeno; //in mg/L

    public Vino(String name, boolean bio)       //mettere parametri accettabili che non inneschino osservazione
    {                                           //TODO mettere solo super() nel costruttore delle derivate
        this.name = name;
        this.bio = bio;
        anidrideSolforosa = 110 + Math.random()*50;  //tra 110 e 160 escluso
        pH = 3.3;
        zuccheriRiduttori = 160 + Math.random()*50;  // tra 160 e 210
        gradoAlcolico = 10 + Math.random()*4;        // tra 10 e 14
        ossigeno = Math.random() * 0.5;              // tra 0 e 0.5
    }

    //non è astratto perchè è uguale per tutti e due i tipi di vino
    //final perchè non deve avere override
    public final void correggiVino(){
        ossigeno = Math.random() * 0.5;
    }

    public abstract boolean varia();    //FIXME è davvero astratto? (le derivate lo implementano diversamente?)
    //questo metodo dovrà occuparsi di inviare alla notify il parametro ossigeno se solo quello è fuori soglia,
    //e se fossero fuori soglia ossigeno e un altro parametro invierà quest'ultimo

    public double getOssigeno() {
        return ossigeno;
    }
}
